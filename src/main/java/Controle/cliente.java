/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Admin
 */
public class cliente extends JFrame {

    Conexao con_cliente;
    JLabel rCodigo, rNome, rData, rUsuario, rSenha, rEmail, rCPF, rTitulo;
    JTextField tCodigo, tNome, tUsuario, tSenha, tEmail, tCPF, tPesquisar;
    JFormattedTextField tData;
    MaskFormatter mData;
    JButton primeiro, anterior, proximo, ultimo, registro, gravar, alterar, excluir, pesquisar, sair;

    JTable tblClientes;
    JScrollPane scp_tabela;

    public cliente() throws SQLException, ParseException {

        Container tela = getContentPane();

        ImageIcon icone = new ImageIcon("src/imagens/icone.png"); // Substitua pelo caminho correto do ícone
        setIconImage(icone.getImage());

        rCodigo = new JLabel("Codigo:");
        rNome = new JLabel("Nome:");
        rData = new JLabel("Data-Nascimento:");
        rUsuario = new JLabel("Usuario:");
        rSenha = new JLabel("Senha:");
        rEmail = new JLabel("Email:");
        rCPF = new JLabel("CPF:");

        rTitulo = new JLabel("Painel Cliente");
        rTitulo.setBounds(450, 3, 250, 50);
        rTitulo.setForeground(new Color(43, 45, 66));
        rTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));

        tCodigo = new JTextField();
        tNome = new JTextField();
        tData = new JFormattedTextField();
        tUsuario = new JTextField();
        tSenha = new JTextField();
        tEmail = new JTextField();
        tCPF = new JTextField();
        tPesquisar = new JTextField();

        tela.setBackground(new Color(237, 241, 243));

        try {

            MaskFormatter mData = new MaskFormatter("####/##/##");

            tData = new JFormattedTextField(mData);
            tData.setBounds(100, 140, 30, 20);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        con_cliente = new Conexao();
        con_cliente.conecta();

        setTitle("Cliente");
        setSize(1000, 650);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        tela.setLayout(null);

        primeiro = new JButton("Primeiro");
        anterior = new JButton("Anterior");
        proximo = new JButton("Próximo");
        ultimo = new JButton("Último");

        registro = new JButton("Nova Registro");
        gravar = new JButton("Gravar");
        alterar = new JButton("Alterar");
        excluir = new JButton("Excluir");
        pesquisar = new JButton("Pesquisar");
        sair = new JButton("Menu");

        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    MenuAdm user = new MenuAdm();
                    user.setVisible(true);
                    dispose();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                } catch (ParseException ex) {
                    Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        primeiro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con_cliente.resultset.first();
                    mostrar_Dados();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        anterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con_cliente.resultset.isFirst()) {
                        JOptionPane.showMessageDialog(null, "Ja esta no primeiro registro");
                    } else {
                        con_cliente.resultset.previous();
                        mostrar_Dados();
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        proximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con_cliente.resultset.isLast()) {
                        JOptionPane.showMessageDialog(null, "Ja esta no ultimo registro");
                    } else {
                        con_cliente.resultset.next();
                        mostrar_Dados();
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        ultimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con_cliente.resultset.last();
                    mostrar_Dados();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        registro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tCodigo.setText("");
                tNome.setText("");
                tData.setText("");
                tUsuario.setText("");
                tSenha.setText("");
                tEmail.setText("");
                tCPF.setText("");
                tPesquisar.setText("");
            }
        });

        gravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = tNome.getText();
                String data = tData.getText();
                String User = tUsuario.getText();
                String senha = tSenha.getText();
                String email = tEmail.getText();
                String cpf = tCPF.getText();
                try {
                    String insert_sql = "insert into cliente( Nome,DataNasc,Usuário,Senha,Email,CPF)values('" + nome + "','" + data + "','" + User + "','" + senha + "','" + email + "','" + cpf + "')";
                    con_cliente.statement.executeUpdate(insert_sql);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso");

                    con_cliente.executaSQL("select * from cliente order by Id_Cliente ");
                    preencherTabela();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel gravar registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        alterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = tNome.getText();
                String data = tData.getText();
                String User = tUsuario.getText();
                String senha = tSenha.getText();
                String email = tEmail.getText();
                String cpf = tCPF.getText();
                String sql = "";
                String msg = "";

                try {
                    if (tCodigo.getText().equals("")) {
                        sql = "insert into cliente( Nome, DataNasc, Usuário, Senha, Email,  CPF) values ( '" + nome + "','" + data + "','"
                                + User + "','" + senha + "','" + email + "','" + cpf + "')";
                        msg = "Gravação de um novo registro";
                    } else {
                        sql = "update cliente set Nome='" + nome + "', DataNasc='" + data + "', Usuário='" + User + "', Senha='" + senha + "',Email='" + email + "', CPF='" + cpf + "' where Id_Cliente  =" + tCodigo.getText();
                        msg = "Alteração de registro";
                    }
                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                    con_cliente.executaSQL("select * from cliente order by  Id_Cliente");
                    preencherTabela();
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Erro na gravação :\n" + errosql, "Mesagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        excluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql;
                try {
                    int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o registro: ", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, 3);
                    if (resposta == 0) {
                        sql = "delete from cliente where Id_Cliente   =" + tCodigo.getText();
                        int excluir = con_cliente.statement.executeUpdate(sql);
                        if (excluir == 1) {
                            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                            con_cliente.executaSQL("select * from  cliente order by  Id_Cliente ");
                            con_cliente.resultset.first();
                            preencherTabela();
                            posicionarRegistro();

                        } else {
                            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException excecao) {
                    JOptionPane.showMessageDialog(null, "Erro na exclusão: " + excecao, "Mendagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        pesquisar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String pesquisa = "select * from cliente where Nome like'" + tPesquisar.getText() + "%'";
                    con_cliente.executaSQL(pesquisa);
                    if (con_cliente.resultset.first()) {
                        preencherTabela();
                    } else {
                        JOptionPane.showMessageDialog(null, "\n Nao existe");
                    }
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Dados nao encontrados");
                }
            }
        });

        tblClientes = new javax.swing.JTable();
        scp_tabela = new javax.swing.JScrollPane();

        tblClientes.setBounds(50, 400, 900, 200);
        scp_tabela.setBounds(50, 400, 900, 200);

        tela.add(tblClientes);
        tela.add(scp_tabela);

        tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblClientes.setFont(new java.awt.Font("Arial", 1, 12));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},},
                new String[]{"Id_Cliente ", "Nome", "DataNasc", "Usuário", "Senha", "Email", "CPF"}) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scp_tabela.setViewportView(tblClientes);
        tblClientes.setAutoCreateRowSorter(true);

        //SetBounds e Teladd
        primeiro.setBounds(60, 320, 90, 30);
        tela.add(primeiro);
        anterior.setBounds(150, 320, 90, 30);
        tela.add(anterior);
        proximo.setBounds(240, 320, 90, 30);
        tela.add(proximo);
        ultimo.setBounds(330, 320, 90, 30);
        tela.add(ultimo);

        registro.setBounds(500, 320, 130, 30);
        tela.add(registro);
        gravar.setBounds(635, 320, 90, 30);
        tela.add(gravar);
        alterar.setBounds(730, 320, 90, 30);
        tela.add(alterar);
        excluir.setBounds(830, 320, 90, 30);
        tela.add(excluir);

        primeiro.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        primeiro.setForeground(new Color(237, 242, 244));

        anterior.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        anterior.setForeground(new Color(237, 242, 244));

        proximo.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        proximo.setForeground(new Color(237, 242, 244));

        ultimo.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        ultimo.setForeground(new Color(237, 242, 244));

        registro.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        registro.setForeground(new Color(237, 242, 244));

        gravar.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        gravar.setForeground(new Color(237, 242, 244));

        alterar.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        alterar.setForeground(new Color(237, 242, 244));

        excluir.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        excluir.setForeground(new Color(237, 242, 244));

        pesquisar.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        pesquisar.setForeground(new Color(237, 242, 244));

        sair.setBackground(new Color(0, 74, 173)); // Define a cor de fundo do botão como azul
        sair.setForeground(new Color(237, 242, 244));

        pesquisar.setBounds(60, 355, 150, 30);

        tPesquisar.setBounds(220, 355, 250, 30);
        tela.add(tPesquisar);
        tela.add(pesquisar);

        sair.setBounds(750, 355, 150, 30);
        tela.add(sair);

        rCodigo.setBounds(50, 40, 150, 50);
        rNome.setBounds(50, 80, 150, 50);
        rData.setBounds(50, 120, 150, 50);
        rUsuario.setBounds(50, 160, 150, 50);
        rSenha.setBounds(50, 200, 150, 50);
        rEmail.setBounds(50, 240, 150, 50);
        rCPF.setBounds(470, 40, 150, 50);

        rCodigo.setForeground(new Color(95, 127, 202));
        rNome.setForeground(new Color(95, 127, 202));
        rData.setForeground(new Color(95, 127, 202));
        rUsuario.setForeground(new Color(95, 127, 202));
        rSenha.setForeground(new Color(95, 127, 202));
        rEmail.setForeground(new Color(95, 127, 202));
        rCPF.setForeground(new Color(95, 127, 202));

        rCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
        rNome.setFont(new Font("Tahoma", Font.BOLD, 15));
        rData.setFont(new Font("Tahoma", Font.BOLD, 15));
        rUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
        rSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
        rEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        rCPF.setFont(new Font("Tahoma", Font.BOLD, 15));

        tela.add(rTitulo);
        tela.add(rCodigo);
        tela.add(rNome);
        tela.add(rData);
        tela.add(rUsuario);
        tela.add(rSenha);
        tela.add(rEmail);
        tela.add(rCPF);

        tCodigo.setBounds(130, 50, 80, 30);
        tNome.setBounds(130, 90, 220, 30);
        tData.setBounds(190, 130, 100, 30);
        tUsuario.setBounds(130, 170, 100, 30);
        tSenha.setBounds(130, 210, 80, 30);
        tEmail.setBounds(130, 250, 250, 60);
        tCPF.setBounds(550, 50, 250, 60);

        tela.add(tCodigo);
        tela.add(tNome);
        tela.add(tData);
        tela.add(tUsuario);
        tela.add(tSenha);
        tela.add(tEmail);
        tela.add(tCPF);

        ImagePanel backgroundPanel = new ImagePanel("src/imagens/backgroundcrud.png");
        tela.add(backgroundPanel);
        backgroundPanel.setBounds(0, -20, backgroundPanel.getPreferredSize().width, backgroundPanel.getPreferredSize().height);

        con_cliente.executaSQL("select * from cliente order by Id_Cliente");
        preencherTabela();
        posicionarRegistro();
    }

    //método posicionarRegistro
    public void posicionarRegistro() {
        try {
            con_cliente.resultset.first(); // posiciona no 1° registro da tabela
            mostrar_Dados(); // chama o método que irá buscar o dado da tabela
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível posicionar no primeiro registro: " + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void mostrar_Dados() {
        try {
            tCodigo.setText(con_cliente.resultset.getString("Id_Cliente"));
            tNome.setText(con_cliente.resultset.getString("Nome"));
            tData.setText(con_cliente.resultset.getString("DataNasc"));
            tUsuario.setText(con_cliente.resultset.getString("Usuário"));
            tSenha.setText(con_cliente.resultset.getString("Senha"));
            tEmail.setText(con_cliente.resultset.getString("Email"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dataFormatada = sdf.format(con_cliente.resultset.getDate("DataNasc"));
            tData.setText(dataFormatada);

            tCPF.setText(con_cliente.resultset.getString("CPF"));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não localizou dados: " + erro, "Mensagem do prograna", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void preencherTabela() throws SQLException {
        tblClientes.getColumnModel().getColumn(0).setPreferredWidth(4);
        tblClientes.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblClientes.getColumnModel().getColumn(2).setPreferredWidth(11);
        tblClientes.getColumnModel().getColumn(3).setPreferredWidth(14);
        tblClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        tblClientes.getColumnModel().getColumn(5).setPreferredWidth(14);
        tblClientes.getColumnModel().getColumn(6).setPreferredWidth(100);

        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);

        try {
            con_cliente.resultset.beforeFirst();
            while (con_cliente.resultset.next()) {
                modelo.addRow(new Object[]{
                    con_cliente.resultset.getString("Id_Cliente"),
                    con_cliente.resultset.getString("Nome"),
                    con_cliente.resultset.getString("DataNasc"),
                    con_cliente.resultset.getString("Usuário"),
                    con_cliente.resultset.getString("Senha"),
                    con_cliente.resultset.getString("Email"),
                    con_cliente.resultset.getString("CPF")
                });
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro ao listar dados da tabela!! \n " + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public class ImagePanel extends JPanel {

        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(backgroundImage.getWidth(this), backgroundImage.getHeight(this));
        }
    }
}

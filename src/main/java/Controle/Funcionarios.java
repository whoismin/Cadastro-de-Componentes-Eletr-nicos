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
public class Funcionarios extends JFrame {

    Conexao con_cliente;
    JLabel rCodigo, rNome, rUsuario, rID, rSenha, rEmail, rData, rCPF, rTitulo;
    JTextField tCodigo, tNome, tUsuario, tID, tSenha, tEmail, tCPF, tPesq;
    JFormattedTextField tData;
    MaskFormatter mData;
    JButton Prim, Ante, Prox, Ult, NoR, Grav, Alter, Exclu, Pesq, sair;
    JTable tblClientes;
    JScrollPane scp_tabela;
    Image backgroundImage;

    public Funcionarios() throws SQLException, ParseException {
        ImageIcon icone = new ImageIcon("src/imagens/icone.png"); // Substitua pelo caminho correto do ícone
        setIconImage(icone.getImage());
        Container tela = getContentPane();
        ImageIcon backgroundImageIcon = new ImageIcon("src/img/NEED.jpg");
        backgroundImage = backgroundImageIcon.getImage();
        tela.setPreferredSize(new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null)));
        tela.setLayout(null);

        tela.setBackground(new Color(237, 241, 243));

        rCodigo = new JLabel("Codigo");
        rNome = new JLabel("Nome");
        rUsuario = new JLabel("Usuario");
        rID = new JLabel("ID");
        rSenha = new JLabel("Senha");
        rEmail = new JLabel("Email");
        rData = new JLabel("Data");

        rCPF = new JLabel("CPF");

        Prim = new JButton("Primeiro");
        Ante = new JButton("Anterior");
        Prox = new JButton("Proximo");
        Ult = new JButton("Último");
        NoR = new JButton("Novo Registro");
        Grav = new JButton("Gravar");
        Alter = new JButton("Alterar");
        Exclu = new JButton("Excluir");
        Pesq = new JButton("Pesquisar");
        sair = new JButton("Menu");

        rTitulo = new JLabel("Painel Funcionários");
        rTitulo.setBounds(410, 3, 250, 50);
        rTitulo.setForeground(new Color(43, 45, 66));
        rTitulo.setFont(new Font("Tahoma", Font.BOLD, 20));

        tCodigo = new JTextField();
        tNome = new JTextField();
        tUsuario = new JTextField();
        tID = new JTextField();
        tSenha = new JTextField();
        tEmail = new JTextField();
        tCPF = new JTextField();
        tPesq = new JTextField();

        try {

            MaskFormatter mData = new MaskFormatter("####/##/##");

            tData = new JFormattedTextField(mData);
            tData.setBounds(100, 140, 50, 20);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

        con_cliente = new Conexao();
        con_cliente.conecta();

        setTitle("Funcionários");
        setResizable(false);
        tela.setLayout(null);

        tCodigo.setBounds(130, 50, 80, 30);
        tNome.setBounds(130, 90, 220, 30);
        tData.setBounds(130, 130, 100, 30);
        tUsuario.setBounds(130, 170, 100, 30);

        tSenha.setBounds(130, 210, 80, 30);
        tEmail.setBounds(130, 250, 250, 60);
        tCPF.setBounds(550, 50, 250, 60);
        tID.setBounds(550, 130, 80, 30);

        rCodigo.setBounds(50, 40, 150, 50);
        rNome.setBounds(50, 80, 150, 50);
        rData.setBounds(50, 120, 150, 50);
        rUsuario.setBounds(50, 160, 150, 50);
        rSenha.setBounds(50, 200, 150, 50);
        rEmail.setBounds(50, 240, 150, 50);
        rCPF.setBounds(470, 40, 150, 50);
        rID.setBounds(470, 120, 150, 50);

        Prim.setBounds(60, 320, 90, 30);
        Ante.setBounds(150, 320, 90, 30);
        Prox.setBounds(240, 320, 90, 30);
        Ult.setBounds(330, 320, 90, 30);

        NoR.setBounds(500, 320, 130, 30);
        Grav.setBounds(635, 320, 90, 30);
        Alter.setBounds(730, 320, 90, 30);
        Exclu.setBounds(830, 320, 90, 30);

        Pesq.setBounds(60, 355, 150, 30);
        sair.setBounds(750, 355, 150, 30);

        tPesq.setBounds(220, 355, 250, 30);

        rCodigo.setForeground(new Color(95, 127, 202));
        rNome.setForeground(new Color(95, 127, 202));
        rData.setForeground(new Color(95, 127, 202));
        rUsuario.setForeground(new Color(95, 127, 202));
        rSenha.setForeground(new Color(95, 127, 202));
        rEmail.setForeground(new Color(95, 127, 202));
        rCPF.setForeground(new Color(95, 127, 202));
        rID.setForeground(new Color(95, 127, 202));

        rID.setFont(new Font("Tahoma", Font.BOLD, 15));
        rCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
        rNome.setFont(new Font("Tahoma", Font.BOLD, 15));
        rData.setFont(new Font("Tahoma", Font.BOLD, 15));
        rUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
        rSenha.setFont(new Font("Tahoma", Font.BOLD, 15));
        rEmail.setFont(new Font("Tahoma", Font.BOLD, 15));
        rCPF.setFont(new Font("Tahoma", Font.BOLD, 15));

        tblClientes = new javax.swing.JTable();
        scp_tabela = new javax.swing.JScrollPane();

        tblClientes.setBounds(50, 400, 900, 200);
        scp_tabela.setBounds(50, 400, 900, 200);

        Prim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con_cliente.resultset.first();
                    mostrar_Dados();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        Ante.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    if (con_cliente.resultset.isFirst()) {
                        JOptionPane.showMessageDialog(null, "Você está no primeiro registro,mas você poderá ir para o ultimo ao clicar novamente ");
                        con_cliente.resultset.last();

                    } else {
                        con_cliente.resultset.previous();
                        mostrar_Dados();
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o registro anterior");
                }
            }
        });
        Prox.addActionListener(new ActionListener() {
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

        Ult.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (con_cliente.resultset.last()) {
                        mostrar_Dados();
                    } else {
                        JOptionPane.showMessageDialog(null, "Você está no último registro, mas você poderá ir para o primeiro ao clicar novamente.");
                        con_cliente.resultset.first();
                        mostrar_Dados();
                    }

                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel acessar o Último");
                }
            }
        });

        NoR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                tCodigo.setText("");
                tNome.setText("");
                tUsuario.setText("");
                tID.setText("");
                tSenha.setText("");
                tEmail.setText("");
                tData.setText("");
                tCPF.setText("");
                tCodigo.requestFocus();

            }
        }
        );

        Grav.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nome = Funcionarios.this.tNome.getText();
                String Usuario = Funcionarios.this.tUsuario.getText();
                String ID = Funcionarios.this.tID.getText();
                String Senha = Funcionarios.this.tSenha.getText();
                String email = Funcionarios.this.tEmail.getText();
                String data_nasc = Funcionarios.this.tData.getText();
                String CPF = Funcionarios.this.tCPF.getText();

                try {

                    String insert_sql = "insert into funcionário( Nome_Func, Usuário, Nivel_ID, Senha, Email, Data_Nasc, CPF) values ('" + nome + "','" + Usuario + "','" + ID + "','" + Senha + "','" + email + "','" + data_nasc + "','" + CPF + "')";
                    con_cliente.statement.executeUpdate(insert_sql);
                    JOptionPane.showMessageDialog(null, "Gravado com sucesso");

                    con_cliente.executaSQL("select * from funcionário order by ID_Func");
                    preencherTabela();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel gravar registro" + erro, "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        Alter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = Funcionarios.this.tNome.getText();
                String Usuario = Funcionarios.this.tUsuario.getText();
                String ID = Funcionarios.this.tID.getText();
                String Senha = Funcionarios.this.tSenha.getText();
                String email = Funcionarios.this.tEmail.getText();
                String data_nasc = Funcionarios.this.tData.getText();
                String CPF = Funcionarios.this.tCPF.getText();
                String sql;
                String msg = "";
                try {
                    if (tCodigo.getText().equals("")) {
                        sql = "insert into funcionário( Nome_Func, Usuário, Nivel_ID, Senha, Email, Data_Nasc, CPF) values ( '" + nome + "','" + Usuario + "','" + ID + "','" + Senha + "','" + email + "','" + data_nasc + "','" + CPF + "')";
                        msg = "Gravação de um novo registro";
                    } else {
                        sql = "update funcionário set Nome_Func='" + nome + "', Usuário='" + Usuario + "', Nivel_ID='" + ID + "', Senha='" + Senha + "',Email='" + email + "', Data_Nasc='" + data_nasc + "',CPF='" + CPF + "' where ID_Func =" + tCodigo.getText();
                        msg = "Alteração de registro";
                    }
                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                    con_cliente.executaSQL("select * from funcionário order by  ID_Func");
                    preencherTabela();
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Erro na gravação :\n" + errosql, "Mesagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        );

        Exclu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String sql;
                try {
                    int resposta = JOptionPane.showConfirmDialog(rootPane, "Deseja excluir o registro: ", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION, 3);
                    if (resposta == 0) {
                        sql = "delete from funcionário where ID_Func  =" + tCodigo.getText();
                        int excluir = con_cliente.statement.executeUpdate(sql);
                        if (excluir == 1) {
                            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!!", "Mensagem do programa", JOptionPane.INFORMATION_MESSAGE);
                            con_cliente.executaSQL("select * from  funcionário order by  ID_Func");
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

        sair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuAdm adm;
                try {
                    adm = new MenuAdm();
                    adm.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(Componente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Componente.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        Pesq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {

                    String pesquisa = "select * from funcionário where Nome_Func like'" + tPesq.getText() + "%'";//Valor aproximado
                    con_cliente.executaSQL(pesquisa);
                    if (con_cliente.resultset.first()) {
                        preencherTabela();
                    } else {
                        JOptionPane.showMessageDialog(null, "\n Os dados digitados não foram localizados!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\n Erro ao executar a consulta SQL: " + errosql.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Prim.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Prim.setForeground(new Color(237, 242, 244));

        Ante.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Ante.setForeground(new Color(237, 242, 244));

        Prox.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Prox.setForeground(new Color(237, 242, 244));

        Ult.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Ult.setForeground(new Color(237, 242, 244));

        NoR.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        NoR.setForeground(new Color(237, 242, 244));

        Grav.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Grav.setForeground(new Color(237, 242, 244));

        Alter.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Alter.setForeground(new Color(237, 242, 244));

        Exclu.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Exclu.setForeground(new Color(237, 242, 244));

        Pesq.setBackground(new Color(76, 103, 185)); // Define a cor de fundo do botão como azul
        Pesq.setForeground(new Color(237, 242, 244));

        sair.setBackground(new Color(0, 74, 173)); // Define a cor de fundo do botão como azul
        sair.setForeground(new Color(237, 242, 244));

        tela.add(Prim);
        tela.add(Ante);
        tela.add(Prox);
        tela.add(Ult);
        tela.add(NoR);
        tela.add(Grav);
        tela.add(Alter);
        tela.add(Exclu);
        tela.add(Pesq);
        tela.add(sair);

        tela.add(rTitulo);
        tela.add(rCodigo);
        tela.add(rNome);
        tela.add(rUsuario);
        tela.add(rID);
        tela.add(rSenha);
        tela.add(rEmail);
        tela.add(rData);
        tela.add(rCPF);

        tela.add(tCodigo);
        tela.add(tNome);
        tela.add(tUsuario);
        tela.add(tID);
        tela.add(tSenha);
        tela.add(tEmail);
        tela.add(tData);
        tela.add(tCPF);
        tela.add(tPesq);

        tela.add(tblClientes);
        tela.add(scp_tabela);

        ImagePanel backgroundPanel = new ImagePanel("src/imagens/backgroundcrud.png");
        tela.add(backgroundPanel);
        backgroundPanel.setBounds(0, -20, backgroundPanel.getPreferredSize().width, backgroundPanel.getPreferredSize().height);

        tblClientes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblClientes.setFont(new java.awt.Font("Arial", 1, 12));

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null},},
                new String[]{"ID_Func", "Nome", "Usuário", "Nivel_ID", "Senha", "Email", "Data_Nasc", "CPF"}) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        scp_tabela.setViewportView(tblClientes);
        tblClientes.setAutoCreateRowSorter(true);

        setSize(1000, 650);
        setVisible(true);
        setLocationRelativeTo(null);

        con_cliente.executaSQL("select * from funcionário order by ID_Func");
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
            tCodigo.setText(con_cliente.resultset.getString("ID_Func"));
            tNome.setText(con_cliente.resultset.getString("Nome_Func"));
            tUsuario.setText(con_cliente.resultset.getString("Usuário"));
            tID.setText(con_cliente.resultset.getString("Nivel_ID"));
            tSenha.setText(con_cliente.resultset.getString("Senha"));
            tEmail.setText(con_cliente.resultset.getString("Email"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            String dataFormatada = sdf.format(con_cliente.resultset.getDate("Data_Nasc"));
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
        tblClientes.getColumnModel().getColumn(7).setPreferredWidth(100);

        DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
        modelo.setNumRows(0);

        try {
            con_cliente.resultset.beforeFirst();
            while (con_cliente.resultset.next()) {
                modelo.addRow(new Object[]{
                    con_cliente.resultset.getString("ID_Func"),
                    con_cliente.resultset.getString("Nome_Func"),
                    con_cliente.resultset.getString("Usuário"),
                    con_cliente.resultset.getString("Nivel_ID"),
                    con_cliente.resultset.getString("Senha"),
                    con_cliente.resultset.getString("Email"),
                    con_cliente.resultset.getString("Data_Nasc"),
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

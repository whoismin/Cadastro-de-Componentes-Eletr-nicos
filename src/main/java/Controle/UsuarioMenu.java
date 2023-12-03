/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexão.Conexao;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author dti
 */
public class UsuarioMenu extends JFrame {

    Conexao con_cliente;
    int var1 = 1, var2 = 2, var3 = 3, var4 = 4;
    JLabel titulo, product1name, product2name, product3name, productname4,
            product1image, productimage2, productimage3, productimage4;

    JTextField[] quantidadeFields;

    JButton product1button, product2button, product3button, product4button;

    public UsuarioMenu(int Id_user, String nomeuser) throws SQLException {
        con_cliente = new Conexao();

        con_cliente.conecta();

        Container tela = getContentPane();
        tela.setBackground(new Color(237, 241, 243));
        ImageIcon icone = new ImageIcon("src/imagens/icone.png"); // Substitua pelo caminho correto do ícone
        setIconImage(icone.getImage());

        int IDuser = Id_user;
        String clientename = nomeuser;

        ImageIcon customicon = new ImageIcon("src/imagens/confirmado.png");

        //Logo
        ImageIcon pi1 = createResizedImageIcon("src/imagens/computador.jpg", 140, 120);
        product1image = new JLabel(pi1);
        product1image.setBounds(80, 130, 140, 120);
        tela.add(product1image);

        ImageIcon pi2 = createResizedImageIcon("src/imagens/teclado.jpg", 150, 150);
        productimage2 = new JLabel(pi2);
        productimage2.setBounds(260, 110, 150, 150);
        tela.add(productimage2);

        ImageIcon pi3 = createResizedImageIcon("src/imagens/mouse.jpg", 80, 130);
        productimage3 = new JLabel(pi3);
        productimage3.setBounds(450, 110, 80, 130);
        tela.add(productimage3);

        ImageIcon pi4 = createResizedImageIcon("src/imagens/pc.jpg", 70, 130);
        productimage4 = new JLabel(pi4);
        productimage4.setBounds(620, 110, 70, 130);
        tela.add(productimage4);

        product1button = new JButton("Comprar");
        product2button = new JButton("Comprar");
        product3button = new JButton("Comprar");
        product4button = new JButton("Comprar");

        product1button.setBackground(new Color(0, 74, 173));  // Define a cor de fundo do botão como azul
        product1button.setForeground(new Color(237, 242, 244));

        product2button.setBackground(new Color(0, 74, 173)); // Define a cor de fundo do botão como azul
        product2button.setForeground(new Color(237, 242, 244));

        product3button.setBackground(new Color(0, 74, 173)); // Define a cor de fundo do botão como azul
        product3button.setForeground(new Color(237, 242, 244));

        product4button.setBackground(new Color(0, 74, 173)); // Define a cor de fundo do botão como azul
        product4button.setForeground(new Color(237, 242, 244));

        product1button.setBounds(80, 330, 150, 30);
        product2button.setBounds(260, 330, 150, 30);
        product3button.setBounds(420, 330, 150, 30);
        product4button.setBounds(580, 330, 150, 30);

        //Titulo
        titulo = new JLabel("Produtos");
        titulo.setBounds(330, 5, 200, 100);
        titulo.setForeground(new Color(43, 45, 66));
        titulo.setFont(new Font("Tahoma", Font.BOLD, 30));

        product1name = new JLabel("Computador MSI");
        product1name.setBounds(120, 55, 300, 100);
        product1name.setForeground(new Color(43, 45, 66));
        product1name.setFont(new Font("Tahoma", Font.BOLD, 15));

        product2name = new JLabel("Teclado MSI");
        product2name.setBounds(290, 55, 300, 100);
        product2name.setForeground(new Color(43, 45, 66));
        product2name.setFont(new Font("Tahoma", Font.BOLD, 15));

        product3name = new JLabel("Mouse MSI");
        product3name.setBounds(450, 45, 300, 100);
        product3name.setForeground(new Color(43, 45, 66));
        product3name.setFont(new Font("Tahoma", Font.BOLD, 15));

        productname4 = new JLabel("PC MSI");
        productname4.setBounds(610, 45, 200, 100);
        productname4.setForeground(new Color(43, 45, 66));
        productname4.setFont(new Font("Tahoma", Font.BOLD, 15));

        //Quantidade e Preço dos Produtos.
        int[] varArray = {var1, var2, var3, var4};

        for (int i = 0; i < varArray.length; i++) {
            String pesquisaest = "select * from componente where Id_Comp = '" + varArray[i] + "'";
            con_cliente.executaSQL(pesquisaest);

            if (con_cliente.resultset != null && con_cliente.resultset.next()) {
                String produtoest = con_cliente.resultset.getString("Estoque");
                String produtopreco = con_cliente.resultset.getString("Preço");
                JLabel estoque = new JLabel("Estoque " + produtoest);
                JLabel valor = new JLabel("Preço: R$" + produtopreco);

                estoque.setForeground(new Color(63, 75, 95));
                estoque.setFont(new Font("Tahoma", Font.BOLD, 12));

                valor.setForeground(new Color(43, 45, 66));
                valor.setFont(new Font("Tahoma", Font.BOLD, 12));

                // Use um switch case para ajustar os bounds com base no valor de i
                switch (i) {
                    case 0 -> {
                        estoque.setBounds(90, 190, 200, 100);
                        valor.setBounds(90, 220, 200, 100);
                    }
                    case 1 -> {
                        estoque.setBounds(260, 190, 200, 100);
                        valor.setBounds(260, 220, 200, 100);
                    }
                    case 2 -> {
                        estoque.setBounds(430, 200, 200, 100);
                        valor.setBounds(430, 230, 200, 100);
                    }
                    case 3 -> {
                        estoque.setBounds(580, 200, 200, 100);
                        valor.setBounds(580, 230, 200, 100);
                    }
                    default -> {
                    }
                }
                // Lida com outros casos, se necessário

                tela.add(estoque);
                tela.add(valor);
            }
        }

        quantidadeFields = new JTextField[4];
        for (int i = 0; i < quantidadeFields.length; i++) {
            quantidadeFields[i] = new JTextField(2);
            quantidadeFields[i].setForeground(new Color(43, 45, 66));
            quantidadeFields[i].setText("1");
            JLabel textqtd = new JLabel("QTD:");

            // Use um switch case para ajustar os bounds com base no valor de i
            switch (i) {
                case 0 -> {
                    textqtd.setBounds(93, 290, 50, 20);
                    quantidadeFields[i].setBounds(120, 290, 50, 23);
                }
                case 1 -> {
                    textqtd.setBounds(263, 290, 50, 20);
                    quantidadeFields[i].setBounds(290, 290, 50, 23);
                }
                case 2 -> {
                    textqtd.setBounds(433, 300, 50, 20);
                    quantidadeFields[i].setBounds(460, 300, 50, 23);
                }
                case 3 -> {
                    textqtd.setBounds(583, 300, 50, 20);
                    quantidadeFields[i].setBounds(610, 300, 50, 23);
                }
                default -> {
                }
            }
            // Lidar com outros casos, se necessário

            add(textqtd);
            add(quantidadeFields[i]);
        }

        product1button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = 1;
                    String estoquselecionado = quantidadeFields[0].getText();;
                    int qtd = Integer.parseInt(estoquselecionado);
                    LocalDate datevar = LocalDate.now();
                    String pesquisa2 = "select * from componente where Id_Comp = '" + id + "'";
                    con_cliente.executaSQL(pesquisa2);

                    if (con_cliente.resultset != null && con_cliente.resultset.next()) {
                        String quantidadebd = con_cliente.resultset.getString("Estoque");

                        if (Integer.parseInt(quantidadebd) >= qtd) {
                            try {
                                String insert_sql = "INSERT INTO registro_compra(Id_Componente, Id_Cliente, Data_compra, Quantidade) VALUES ('" + id + "','" + IDuser + "','" + datevar + "','" + qtd + "')";
                                con_cliente.statement.executeUpdate(insert_sql);
                                JOptionPane.showMessageDialog(null, "Compra Realizada com Sucesso", "Confirmação",
                                        JOptionPane.INFORMATION_MESSAGE, customicon);

                                int newquantidade = Integer.parseInt(quantidadebd) - qtd;
                                String atualiarsql = "UPDATE componente SET Estoque = " + newquantidade + " WHERE Id_Comp = " + id;
                                con_cliente.statement.executeUpdate(atualiarsql);

                                NTFiscal notinha = new NTFiscal(nomeuser, id, qtd);
                                notinha.setVisible(true);
                                dispose();
                                tela.revalidate();
                                tela.repaint();
                            } catch (SQLException ex) {
                                Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível comprar, pois a quantidade está indisponível.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado na base de dados.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        product2button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = 2;
                    String estoquselecionado = quantidadeFields[1].getText();;
                    int qtd = Integer.parseInt(estoquselecionado);
                    LocalDate datevar = LocalDate.now();
                    String pesquisa2 = "select * from componente where Id_Comp = '" + id + "'";
                    con_cliente.executaSQL(pesquisa2);

                    if (con_cliente.resultset != null && con_cliente.resultset.next()) {
                        String quantidadebd = con_cliente.resultset.getString("Estoque");

                        if (Integer.parseInt(quantidadebd) >= qtd) {
                            try {
                                String insert_sql = "INSERT INTO registro_compra(Id_Componente, Id_Cliente, Data_compra, Quantidade) VALUES ('" + id + "','" + IDuser + "','" + datevar + "','" + qtd + "')";
                                con_cliente.statement.executeUpdate(insert_sql);
                                JOptionPane.showMessageDialog(null, "Compra Realizada com Sucesso", "Confirmação",
                                        JOptionPane.INFORMATION_MESSAGE, customicon);

                                int newquantidade = Integer.parseInt(quantidadebd) - qtd;
                                String atualiarsql = "UPDATE componente SET Estoque = " + newquantidade + " WHERE Id_Comp = " + id;
                                con_cliente.statement.executeUpdate(atualiarsql);

                                NTFiscal notinha = new NTFiscal(nomeuser, id, qtd);
                                notinha.setVisible(true);
                                dispose();
                                tela.revalidate();
                                tela.repaint();
                            } catch (SQLException ex) {
                                Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível comprar, pois a quantidade está indisponível.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado na base de dados.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        product3button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = 3;
                    String estoquselecionado = quantidadeFields[2].getText();;
                    int qtd = Integer.parseInt(estoquselecionado);
                    LocalDate datevar = LocalDate.now();
                    String pesquisa2 = "select * from componente where Id_Comp = '" + id + "'";
                    con_cliente.executaSQL(pesquisa2);

                    if (con_cliente.resultset != null && con_cliente.resultset.next()) {
                        String quantidadebd = con_cliente.resultset.getString("Estoque");

                        if (Integer.parseInt(quantidadebd) >= qtd) {
                            try {
                                String insert_sql = "INSERT INTO registro_compra(Id_componente, Id_Cliente, Data_compra, Quantidade) VALUES ('" + id + "','" + IDuser + "','" + datevar + "','" + qtd + "')";
                                con_cliente.statement.executeUpdate(insert_sql);
                                JOptionPane.showMessageDialog(null, "Compra Realizada com Sucesso", "Confirmação",
                                        JOptionPane.INFORMATION_MESSAGE, customicon);

                                int newquantidade = Integer.parseInt(quantidadebd) - qtd;
                                String atualiarsql = "UPDATE componente SET Estoque = " + newquantidade + " WHERE Id_Comp = " + id;
                                con_cliente.statement.executeUpdate(atualiarsql);

                                NTFiscal notinha = new NTFiscal(nomeuser, id, qtd);
                                notinha.setVisible(true);
                                dispose();
                                tela.revalidate();
                                tela.repaint();
                            } catch (SQLException ex) {
                                Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível comprar, pois a quantidade está indisponível.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado na base de dados.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        product4button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = 4;
                    String estoquselecionado = quantidadeFields[3].getText();;
                    int qtd = Integer.parseInt(estoquselecionado);
                    LocalDate datevar = LocalDate.now();
                    String pesquisa2 = "select * from componente where Id_Comp = '" + id + "'";
                    con_cliente.executaSQL(pesquisa2);

                    if (con_cliente.resultset != null && con_cliente.resultset.next()) {
                        String quantidadebd = con_cliente.resultset.getString("Estoque");

                        if (Integer.parseInt(quantidadebd) >= qtd) {
                            try {
                                String insert_sql = "INSERT INTO registro_compra(Id_Componente, Id_Cliente, Data_compra, Quantidade) VALUES ('" + id + "','" + IDuser + "','" + datevar + "','" + qtd + "')";
                                con_cliente.statement.executeUpdate(insert_sql);
                                JOptionPane.showMessageDialog(null, "Compra Realizada com Sucesso", "Confirmação",
                                        JOptionPane.INFORMATION_MESSAGE, customicon);

                                int newquantidade = Integer.parseInt(quantidadebd) - qtd;
                                String atualiarsql = "UPDATE componente SET Estoque = " + newquantidade + " WHERE Id_Comp = " + id;
                                con_cliente.statement.executeUpdate(atualiarsql);

                                NTFiscal notinha = new NTFiscal(nomeuser, id, qtd);
                                notinha.setVisible(true);
                                dispose();
                                tela.revalidate();
                                tela.repaint();
                            } catch (SQLException ex) {
                                Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possível comprar, pois a quantidade está indisponível.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado na base de dados.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tela.add(product1name);
        tela.add(product2name);
        tela.add(product3name);
        tela.add(productname4);

        tela.add(product1button);
        tela.add(product2button);
        tela.add(product3button);
        tela.add(product4button);

        setTitle("Menu-Usuario");
        setResizable(false);
        tela.setLayout(null);

        JMenuBar barra = new JMenuBar();
        setJMenuBar(barra);

        JMenu Menusair = new JMenu("Sair");
        JMenuItem MenusairItem = new JMenuItem("Sair");

        JMenu MenuSobre = new JMenu("Sobre");
        JMenuItem MenuSobreItem = new JMenuItem("Sobre");

        JMenu Menuvoltar = new JMenu("Voltar");
        JMenuItem MenuvoltarItem = new JMenuItem("Voltar");

        Menusair.add(MenusairItem);
        MenuSobre.add(MenuSobreItem);
        Menuvoltar.add(MenuvoltarItem);

        barra.add(MenuSobre);
        barra.add(Menuvoltar);
        barra.add(Menusair);

        MenuvoltarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login adm;
                adm = new Login();
                adm.setVisible(true);
                dispose();

            }
        });
        MenusairItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int opcao;
                Object[] botoes = {"Sim", "Não"};
                opcao = JOptionPane.showOptionDialog(null, "Deseja mesmo fechar a janela?", "Fechar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, botoes, botoes[0]);
                if (opcao == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
        );

        MenuSobreItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Sobrenos fun = new Sobrenos(2);
                fun.setVisible(true);

            }
        });

        tela.add(titulo);

        ImagePanel backgroundPanel = new ImagePanel("src/imagens/backgroundlogin.png");
        tela.add(backgroundPanel);
        backgroundPanel.setBounds(0, -20, backgroundPanel.getPreferredSize().width, backgroundPanel.getPreferredSize().height);

        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args, int Id_user, String nomeuser) throws SQLException {
        try {
            UsuarioMenu menu = new UsuarioMenu(Id_user, nomeuser);
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (ClassNotFoundException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (InstantiationException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();

        } catch (IllegalAccessException e) {

            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }

    }

    ;

    
    private ImageIcon createResizedImageIcon(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(resizedImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
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

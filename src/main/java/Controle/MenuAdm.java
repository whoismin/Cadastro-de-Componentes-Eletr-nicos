/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controle;

import Conexão.Conexao;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Admin
 */
public class MenuAdm extends JFrame {

    Conexao con_cliente;
    JLabel ilogo, titulo, iuser, iadmin, icomponente;
    JButton funcionariolink, componentelink, clientelink;

    public MenuAdm() throws SQLException, ParseException {
        Container tela = getContentPane();
        tela.setBackground(new Color(237, 241, 243));
        ImageIcon icone = new ImageIcon("src/imagens/icone.png"); // Substitua pelo caminho correto do ícone
        setIconImage(icone.getImage());

        con_cliente = new Conexao();
        con_cliente.conecta();

        //Logo
        ImageIcon logo = createResizedImageIcon("src/imagens/admicone.png", 700, 500);
        ilogo = new JLabel(logo);
        ilogo.setBounds(200, -10, 700, 500);

        //Titulo
        titulo = new JLabel("Opções");
        titulo.setBounds(190, 5, 150, 100);
        titulo.setForeground(new Color(43, 45, 66));
        titulo.setFont(new Font("Tahoma", Font.BOLD, 30));

        //Icones
        ImageIcon user = new ImageIcon("src/imagens/usuario.png");
        ImageIcon admins = new ImageIcon("src/imagens/admin.png");
        ImageIcon componentes = new ImageIcon("src/imagens/componentes.png");

        iuser = new JLabel(user);
        icomponente = new JLabel(componentes);
        iadmin = new JLabel(admins);

        setTitle("Menu-ADM");
        setResizable(false);
        tela.setLayout(null);

        JMenuBar barra = new JMenuBar();
        setJMenuBar(barra);

        JMenu operacoesMenu = new JMenu("Cadastro");
        JMenuItem funcionarioItem = new JMenuItem("Funcionário");
        JMenuItem componenteItem = new JMenuItem("Remédio");
        JMenuItem clienteItem = new JMenuItem("Cliente");

        JMenu Menusair = new JMenu("Sair");
        JMenuItem MenusairItem = new JMenuItem("Sair");

        JMenu MenuSobre = new JMenu("Sobre");
        JMenuItem MenuSobreItem = new JMenuItem("Sobre");

        JMenu Menuvoltar = new JMenu("Voltar");
        JMenuItem MenuvoltarItem = new JMenuItem("Voltar");

        operacoesMenu.add(funcionarioItem);
        operacoesMenu.add(componenteItem);
        operacoesMenu.add(clienteItem);

        Menusair.add(MenusairItem);
        MenuSobre.add(MenuSobreItem);
        Menuvoltar.add(MenuvoltarItem);

        barra.add(operacoesMenu);
        barra.add(MenuSobre);
        barra.add(Menuvoltar);
        barra.add(Menusair);

        funcionarioItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Funcionarios fun = new Funcionarios();
                    fun.setVisible(true);
                    dispose();
                } catch (SQLException | ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        componenteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Componente fun = new Componente();
                    fun.setVisible(true);
                    dispose();
                } catch (SQLException | ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

        clienteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente cli = new cliente();
                    cli.setVisible(true);
                    dispose();
                } catch (SQLException | ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });

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

                Sobrenos fun = new Sobrenos(1);
                fun.setVisible(true);
                dispose();

            }
        });

        funcionariolink = new JButton("Funcionários");
        componentelink = new JButton("Componentes");
        clientelink = new JButton("Cliente");

        funcionariolink.setBackground(new Color(0, 74, 173)); // // Define a cor de fundo do botão como azul
        funcionariolink.setForeground(new Color(237, 242, 244));

        componentelink.setBackground(new Color(0, 74, 173)); // Define a cor de fundo do botão como azul
        componentelink.setForeground(new Color(237, 242, 244));

        clientelink.setBackground(new Color(0, 74, 173));  // Define a cor de fundo do botão como azul
        clientelink.setForeground(new Color(237, 242, 244));

        funcionariolink.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Funcionarios fun = new Funcionarios();
                    fun.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        componentelink.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Componente fun = new Componente();
                    fun.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        clientelink.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    cliente cli = new cliente();
                    cli.setVisible(true);
                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(MenuAdm.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        funcionariolink.setBounds(170, 95, 150, 30);
        iadmin.setBounds(70, 95, 150, 30);

        componentelink.setBounds(170, 195, 150, 30);
        icomponente.setBounds(70, 195, 150, 30);

        clientelink.setBounds(170, 295, 150, 30);
        iuser.setBounds(70, 295, 150, 30);

        tela.add(iadmin);
        tela.add(icomponente);
        tela.add(iuser);
        tela.add(titulo);
        tela.add(ilogo);
        tela.add(funcionariolink);
        tela.add(componentelink);
        tela.add(clientelink);

        ImagePanel backgroundPanel = new ImagePanel("src/imagens/backgroundlogin.png");
        tela.add(backgroundPanel);
        backgroundPanel.setBounds(0, -20, backgroundPanel.getPreferredSize().width, backgroundPanel.getPreferredSize().height);

        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        try {
            MenuAdm menu = new MenuAdm();
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
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

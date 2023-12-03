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
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

/**
 *
 * @author Admin
 */
public class Login extends JFrame {

    Conexao con_cliente;

    JLabel ilogo, iuser, isenha, rTitulo, Login, Senha1;
    JTextField Nome;
    JButton Logar;
    JPasswordField Senha;
    int tentativas = 3;

    public Login() {

        Container tela = getContentPane();

        con_cliente = new Conexao();
        con_cliente.conecta();

        setTitle("Login");
        setResizable(false);
        tela.setLayout(null);

        rTitulo = new JLabel("Login");
        Login = new JLabel("Login");
        Senha1 = new JLabel("Senha");
        Nome = new JTextField();
        Senha = new JPasswordField();
        Logar = new JButton("Logar");

        ImageIcon icone = new ImageIcon("src/imagens/icone.png"); // Substitua pelo caminho correto do ícone
        setIconImage(icone.getImage());

        ImageIcon user = new ImageIcon("src/imagens/usuario.png");
        ImageIcon senhas = new ImageIcon("src/imagens/senha.png");
        ImageIcon logo = createResizedImageIcon("src/imagens/logo.png", 500, 400);

        iuser = new JLabel(user);
        isenha = new JLabel(senhas);

        ilogo = new JLabel(logo);

        iuser.setBounds(3, 105, 220, 30);
        isenha.setBounds(3, 205, 220, 30);
        ilogo.setBounds(400, 60, 400, 300);

        rTitulo.setBounds(200, 5, 150, 100);
        Login.setBounds(130, 80, 150, 30);
        Senha1.setBounds(130, 180, 150, 30);
        Nome.setBounds(130, 105, 220, 30);
        Senha.setBounds(130, 205, 220, 30);
        Logar.setBounds(165, 305, 150, 30);

        rTitulo.setForeground(new Color(43, 45, 66));
        Nome.setForeground(new Color(43, 45, 66));
        Senha.setForeground(new Color(43, 45, 66));

        rTitulo.setFont(new Font("Tahoma", Font.BOLD, 30));

        Logar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String pesquisa = "select * from funcionário where Usuário like '" + Nome.getText() + "' && Senha = " + Senha.getText() + "";
                con_cliente.executaSQL(pesquisa);

                try {
                    if (con_cliente.resultset.first()) {
                        MenuAdm Adm = new MenuAdm();
                        Adm.setVisible(true);
                        dispose();
                    } else {
                        String pesquisa2 = "select * from cliente where Usuário like '" + Nome.getText() + "' && Senha = " + Senha.getText() + "";
                        con_cliente.executaSQL(pesquisa2);
                        if (con_cliente.resultset.first()) {
                            String iduser = con_cliente.resultset.getString("Id_Cliente");
                            String nomeuser = con_cliente.resultset.getString("Nome");
                            int Id_user = Integer.parseInt(iduser);
                            UsuarioMenu User = new UsuarioMenu(Id_user, nomeuser);
                            User.setVisible(true);
                            dispose();
                        } else {
                            tentativas--;
                            JOptionPane.showMessageDialog(null, "Usuário ou Senha incorreta \n" + tentativas + "  tentativas restantes.");
                            Nome.setText("");
                            Senha.setText("");
                        }
                        if (tentativas <= 0) {
                            JOptionPane.showMessageDialog(null, "Você já realizou todas tentativas, fechando o programa. ");
                            con_cliente.desconecta();
                            System.exit(0);
                        }
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(null, "Insira todos os campos");
                } catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);

                } catch (ParseException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
        Logar.setBackground(new Color(0, 74, 173)); // Define a cor de fundo do botão como azul
        Logar.setForeground(new Color(237, 242, 244));

        tela.add(ilogo);
        tela.add(iuser);
        tela.add(isenha);
        tela.add(rTitulo);
        tela.add(Nome);
        tela.add(Senha);
        tela.add(Logar);
        tela.add(Senha1);
        tela.add(Login);

        ImagePanel backgroundPanel = new ImagePanel("src/imagens/backgroundlogin.png");
        tela.add(backgroundPanel);
        backgroundPanel.setBounds(0, -20, backgroundPanel.getPreferredSize().width, backgroundPanel.getPreferredSize().height);

        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String args[]) {

        Login app = new Login();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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

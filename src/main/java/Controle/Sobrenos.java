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
 * @author alexe
 */
public class Sobrenos extends JFrame {

    Conexao con_cliente;
    JButton voltar;

    public Sobrenos(int chamada) {
        Container tela = getContentPane();

        ImageIcon icone = new ImageIcon("src/imagens/icone.png"); // Substitua pelo caminho correto do ícone
        setIconImage(icone.getImage());

        con_cliente = new Conexao();
        con_cliente.conecta();

        setTitle("Sobre Nós");
        setResizable(false);
        tela.setLayout(null);

        voltar = new JButton("Voltar");
        voltar.setBounds(300, 330, 200, 30);

        voltar.setBackground(new Color(239, 35, 60)); // Define a cor de fundo do botão como azul
        voltar.setForeground(new Color(237, 242, 244));

        voltar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                if (chamada == 1) {
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

                } else {
                    dispose();
                }
            }
        });

        tela.add(voltar);
        ImagePanel backgroundPanel = new ImagePanel("src/imagens/sobrenos.png");
        tela.add(backgroundPanel);
        backgroundPanel.setBounds(0, -20, backgroundPanel.getPreferredSize().width, backgroundPanel.getPreferredSize().height);

        setSize(800, 450);
        setVisible(true);
        setLocationRelativeTo(null);

    }

    public static void main(String[] args, int chamada) {
        Sobrenos menu = new Sobrenos(chamada);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
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

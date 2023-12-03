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
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author alexe
 */
public class NTFiscal extends JFrame {

    Conexao con_cliente;
    JLabel data, horario, nome, produto, quantidade, valor,CFOP;
    JButton product1button, product2button;

    public NTFiscal(String nomeuser, int produtoid, int quantidadecompra) throws SQLException {
        con_cliente = new Conexao();
        con_cliente.conecta();

        String pesquisa2 = "select * from componente where Id_Comp = '" + produtoid + "'";
        con_cliente.executaSQL(pesquisa2);

        if (con_cliente.resultset != null && con_cliente.resultset.next()) {
            String produtonome = con_cliente.resultset.getString("Nome_Comp");

            double produtopreco = con_cliente.resultset.getDouble("Preço");
            double valorfinal = produtopreco * quantidadecompra;
            String valorzinfinal = String.format("%.2f", valorfinal);

            LocalDate datazina = LocalDate.now();
            LocalTime timezinho = LocalTime.now();
            String horaFormatada = timezinho.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

             // Lista de CFOPs de exemplo
        String[] cfops = {
            "5101", "5405", "5902", "6101", "6949"
        };

        // Crie um objeto Random para gerar números aleatórios
        Random random = new Random();

        // Gere um CFOP aleatório da lista
        String cfopGerado = cfops[random.nextInt(cfops.length)];
            
            nome = new JLabel("Cliente: " + nomeuser);
            produto = new JLabel("Produto: " + produtonome);
            CFOP = new JLabel("CFOP: " +cfopGerado);
            quantidade = new JLabel("Quantidade: " + quantidadecompra);
            data = new JLabel("Data: " + datazina);
            horario = new JLabel("Hora: " + horaFormatada);
            valor = new JLabel("Valor Final: " + valorzinfinal);

            nome.setForeground(new Color(43, 45, 66));
            nome.setFont(new Font("Tahoma", Font.BOLD, 12));
            nome.setBounds(50, 220, 200, 100);

            CFOP.setForeground(new Color(43, 45, 66));
            CFOP.setFont(new Font("Tahoma", Font.BOLD, 12));
            CFOP.setBounds(50, 250, 200, 100);
            
            produto.setForeground(new Color(43, 45, 66));
            produto.setFont(new Font("Tahoma", Font.BOLD, 12));
            produto.setBounds(50, 280, 200, 100);

            quantidade.setForeground(new Color(43, 45, 66));
            quantidade.setFont(new Font("Tahoma", Font.BOLD, 12));
            quantidade.setBounds(50, 310, 200, 100);

            data.setForeground(new Color(43, 45, 66));
            data.setFont(new Font("Tahoma", Font.BOLD, 12));
            data.setBounds(50, 340, 200, 100);

            horario.setForeground(new Color(43, 45, 66));
            horario.setFont(new Font("Tahoma", Font.BOLD, 12));
            horario.setBounds(50, 370, 200, 100);

            valor.setForeground(new Color(43, 45, 66));
            valor.setFont(new Font("Tahoma", Font.BOLD, 12));
            valor.setBounds(50, 400, 200, 100);
            
            product1button = new JButton("Imprimir");
            product1button.setBounds(180, 350, 80, 30);
            product1button.setBackground(new Color(239, 35, 60)); // Define a cor de fundo do botão como azul
            product1button.setForeground(new Color(237, 242, 244));
            
            product2button = new JButton("Voltar");
            product2button.setBounds(180, 400, 80, 30);
            product2button.setBackground(new Color(239, 35, 60)); // Define a cor de fundo do botão como azul
            product2button.setForeground(new Color(237, 242, 244));
            
            
            product2button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UsuarioMenu log = null;
                try {
                    log = new UsuarioMenu(produtoid,nomeuser);
                } catch (SQLException ex) {
                    Logger.getLogger(NTFiscal.class.getName()).log(Level.SEVERE, null, ex);
                }
                log.setVisible(true);
                dispose();
            }
        });


            Container tela = getContentPane();

            ImageIcon icone = new ImageIcon("src/imagens/icone.png"); // Substitua pelo caminho correto do ícone
            setIconImage(icone.getImage());

            tela.add(nome);
            tela.add(produto);
            tela.add(quantidade);
            tela.add(data);
            tela.add(horario);
            tela.add(valor);
            tela.add(CFOP);
            tela.add(product1button);
            tela.add(product2button);

            setTitle("Nota Fiscal");
            setResizable(false);
            tela.setLayout(null);

            ImagePanel backgroundPanel = new ImagePanel("src/imagens/NTFiscal.png");
            tela.add(backgroundPanel);
            backgroundPanel.setBounds(0, -20, backgroundPanel.getPreferredSize().width, backgroundPanel.getPreferredSize().height);

            setSize(300, 620);
            setVisible(true);
            setLocationRelativeTo(null);
        } else {
            JOptionPane.showMessageDialog(null, "Produto não encontrado na base de dados.");
        }
    }

    public static void main(String[] args, String nomeuser, int produtoid, int quantidadecompra) throws SQLException {
        NTFiscal menu = new NTFiscal(nomeuser, produtoid, quantidadecompra);
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

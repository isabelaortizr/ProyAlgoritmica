package com.bo.upb.algoritmica1.ui;

import com.bo.upb.algoritmica1.model.Node;
import com.bo.upb.algoritmica1.model.TreeBinary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainForm extends JFrame {

    public static final int WIDTH_NODE = 50;

    private JTextField txtValues;
    private JButton btnInsertarHeap;
    private JPanel pnlArbol;
    private JButton btnEliminarHeap;
    private JPanel pnlMain;

    private TreeBinary tb = new TreeBinary();

    public MainForm() throws HeadlessException {
        this.setTitle("Árboles Max-Heap");
        this.setContentPane(pnlMain);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(700, 500);

        Font font1 = new Font("SansSerif", Font.PLAIN, 16); //Font.BOLD
        txtValues.setFont(font1);
        txtValues.setText("70");

        // Insert Heap en Strings
        tb.insertarHeap("10");
        tb.insertarHeap("20");
        tb.insertarHeap("30");
        tb.insertarHeap("40");
//        tb.insertarHeap("50");
//        tb.insertarHeap("60");
        tb.asignarPosNodos(this.getWidth(), tb.getRoot());


        // CODIGOS DE GIT  VISTOS EN CLASE
//        git status                                        (permite ver los cambios: nuevos, modificados o eliminados)
//        git add .                                         (permite adicionar los cambios a una zona intermedia)
//        git commit -m "cualquier comentario"              (permite confirmar los cambios de la zona intermedia)
//        git config --global user.name "Bob"               (la 1ra vez que se usa git, se necesita configurar esto)
//        git config --global user.email bob@example.com    (la 1ra vez que se usa git, se necesita configurar esto)
//        git checkout .                                    (descartar los cambios que no esten commiteados)

//        String valor = JOptionPane.showInputDialog("Ingrese un valor");
//        System.out.println("valor ingresado: " + valor);
//        JOptionPane.showMessageDialog(this,"valor ingresado: " + valor);

        btnEliminarHeap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tb.deleteHeap();
                JOptionPane.showMessageDialog(null, "Saludos elimino: " + txtValues.getText());
            }
        });
        btnInsertarHeap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tb.insertarHeap(txtValues.getText());
                JOptionPane.showMessageDialog(null, "Se agrego: " + txtValues.getText());
                tb.asignarPosNodos(getWidth(), tb.getRoot());
                repaint();
            }
        });
        txtValues.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {

                /*
                String value = txtValues.getText();
//                int l = value.length();
                if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                    txtValues.setEditable(true);
                    txtValues.setText("");
                } else {
                    txtValues.setEditable(false);
                    txtValues.setText("* Ingresar solo numeros");
                }

                 */

                char c = ke.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getToolkit().beep();
                    ke.consume();
                }
            }
        });
    }

    public void graficarNodos(Node node, Graphics2D g2d) {
        if (node == null)
            return;

        g2d.setColor(Color.yellow);
        g2d.fillOval(node.getPosX(), node.getPosY(), WIDTH_NODE, WIDTH_NODE); // rellenando circulo 1
        g2d.setPaint(Color.black); // color del borde
        g2d.drawOval(node.getPosX(), node.getPosY(), WIDTH_NODE, WIDTH_NODE); // dibujando circulo 1
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 14));
        g2d.drawString("" + node.getValue(), node.getPosX() + 18, node.getPosY() + 33);
        System.out.println("Circulo: " + node.getValue() + " | x:" + node.getPosX() + " | y:" + node.getPosY());

        graficarNodos(node.getLeft(), g2d);
        graficarNodos(node.getRight(), g2d);
    }

    public void graficarAristas(Node node, Graphics2D g2d) {
        double hipotenusa = Math.sqrt(Math.pow(150d - 0d, 2d) + Math.pow(150 - 0, 2));
        double angulo = Math.toDegrees(Math.asin((double) (150 - 0) / hipotenusa));

        int hipotenusaCirculo = 50 / 2;
        int xDif = (int) (Math.sin(angulo) * hipotenusaCirculo);
        System.out.printf("hipotenusa: %s, angulo: %s, xDif: %s", hipotenusa, angulo, xDif);

        // dibujando recta
        g2d.setColor(Color.black);

        int mitad = 50 / 2;
        if (node.getLeft() != null) {
            g2d.drawLine(node.getPosX() + mitad - xDif + 2, node.getPosY() + mitad + xDif - 2, node.getLeft().getPosX() + mitad + xDif - 2, node.getLeft().getPosY() + mitad - xDif + 2);
            graficarAristas(node.getLeft(),g2d);
        }
        if (node.getRight() != null) {
            g2d.drawLine(node.getPosX() + mitad + xDif - 2, node.getPosY() + mitad + xDif - 2, node.getRight().getPosX() + mitad - xDif + 2, node.getRight().getPosY() + mitad - xDif + 2);
            graficarAristas(node.getRight(), g2d);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //super.paint(pnlArbol.getGraphics());
        Graphics2D g2d = (Graphics2D) pnlArbol.getGraphics();
//        g2d.setStroke(new BasicStroke(2)); // ancho del borde

        graficarNodos(tb.getRoot(), g2d);
        graficarAristas(tb.getRoot(),g2d);
    }

    public static void main(String[] args) {
        MainForm form = new MainForm();
    }

}

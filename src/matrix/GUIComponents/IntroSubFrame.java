/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.GUIComponents;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Mohamed Elmahdy
 * @author Moustafa Mohamed
 */
public class IntroSubFrame extends JInternalFrame {

    JLabel sizeLabel = new JLabel("size(n)", SwingConstants.CENTER);
    JTextField sizeField = new JTextField();
    JLabel methodsLabel = new JLabel("method:", SwingConstants.CENTER);
    JButton start = new JButton("Set");
    ButtonGroup selections = new ButtonGroup();
    String[] inverseMethods = {"no steps","cross product","adjoint","Gauss-Jordan"};
    String[] determinantMethods = {"no steps", "pivot", "laplace", "erwin-bareiss"};
    JComboBox<String> methodsBox = new JComboBox<>(determinantMethods);
    JRadioButton inverse = new JRadioButton("INV");

    public JRadioButton getInverse() {
        return inverse;
    }

    public JRadioButton getDeterminant() {
        return determinant;
    }
    JRadioButton determinant = new JRadioButton("DET");

    public IntroSubFrame() throws HeadlessException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(1, 5));
        setVisible(true);
        methodsBox.setPreferredSize(new Dimension());
        methodsBox.setMaximumSize(methodsBox.getPreferredSize());
        setPreferredSize(new Dimension(600, 70));
        setSize(500, 50);
        selections.add(inverse);
        selections.add(determinant);
        setResizable(false);
        add(sizeLabel); //     O
        add(sizeField);//     /|\
        add(determinant);//   /\
        add(inverse);
        add(methodsLabel);
        add(methodsBox);
        add(start);
        determinant.setSelected(true);
        inverse.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() == KeyEvent.VK_LEFT) {
                    determinant.doClick();
                }
            }

            @Override
            public void keyPressed(KeyEvent ke) {
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });
        inverse.addActionListener((ActionEvent ae) -> {
            methodsBox.removeAllItems();
            for (String inverseMethod : inverseMethods) {
                methodsBox.addItem(inverseMethod);
            }
            setSize(500, 50);
            updateUI();
        });

        determinant.addActionListener((ActionEvent ae) -> {
            methodsBox.removeAllItems();
            for (String determinantMethod : determinantMethods) {
                methodsBox.addItem(determinantMethod);
            }

            updateUI();
        });
        determinant.addKeyListener(new PressButtonWithEnter(start, inverse));
        inverse.addKeyListener(new PressButtonWithEnter(start, determinant));

        sizeField.addKeyListener(new NumbersOnly(start));

    }
}

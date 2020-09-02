/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.GUIComponents;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;

/**
 *
 * @author Mohamed Elmahdy
 * @author Moustafa Mohamed
 */
public class OFrame extends JFrame {

    JPanel container = new JPanel();
    JScrollPane scrPane = new JScrollPane(container);

    public OFrame() {
        setPreferredSize(new Dimension(700, 455));
        setSize(700, 455);
        container.setLayout(new GridLayout(15, 1, 0, 10));
        container.setBackground(null);
        scrPane.setLayout(new ScrollPaneLayout());
        add(scrPane);

    }

    public JPanel getContainer() {
        return container;
    }

}

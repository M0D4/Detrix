/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.GUIComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author mahdy
 */
public class DetPanel extends JPanel {

    public LargeTextArea[][] nums;

    public DetPanel(int rows, int cols) {
        nums = new LargeTextArea[rows][cols];
        setPreferredSize(new Dimension(600, 300));
        setLayout(new GridLayout(rows, cols, 5, 5));
        setBorder(BorderFactory.createLineBorder(Color.black, 5));
        setBackground(Color.black);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                nums[i][j] = new LargeTextArea();
                nums[i][j].setSize(50, 50);
                nums[i][j].setAlignmentX(JTextArea.CENTER_ALIGNMENT);
                nums[i][j].setFont(nums[i][j].getFont().deriveFont(25.0f));
                nums[i][j].addKeyListener(new NumbersOnly(null));
                add(nums[i][j]);
            }
        }

    }

    public LargeTextArea[][] getNums() {
        return nums;
    }

}

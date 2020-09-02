/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.GUIComponents;

import java.awt.Font;
import javax.swing.JTextField;

/**
 *
 * @author Mohamed Elmahdy
 * @author Moustafa Mohamed
 */
public class LargeTextArea extends JTextField {

    public LargeTextArea() {
        setHorizontalAlignment(JTextField.CENTER);
        setBorder(null);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Serif", Font.BOLD, 20));
        

    }

    public LargeTextArea(String string) {
        super(string);
        setBackground(null);
        setBorder(null);
        setHorizontalAlignment(JTextField.CENTER);
        setFont(new Font("Serif", Font.BOLD, 20));
        setEditable(false);

    }

    

}

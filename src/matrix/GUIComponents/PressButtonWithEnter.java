/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.GUIComponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JRadioButton;

/**
 *
 * @author Mohamed Elmahdy
 * @author Moustafa Mohamed
 */
public class PressButtonWithEnter implements KeyListener {

    JButton toBePressed;
    JRadioButton radio;

    public PressButtonWithEnter(JButton toBePressed, JRadioButton radio) {
        this.toBePressed = toBePressed;
        this.radio = radio;
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
            toBePressed.doClick();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {     
        if (radio != null) {
            if (ke.getKeyCode()== 39 || ke.getKeyCode()== 37) {
                radio.doClick();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}

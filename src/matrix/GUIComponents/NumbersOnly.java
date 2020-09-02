/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.GUIComponents;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;

/**
 *
 * @author Mohamed Elmahdy
 * @author Moustafa Mohamed
 */
public class NumbersOnly implements KeyListener {

    JButton toBeClicked;

    public NumbersOnly(JButton toBeClicked) {
        this.toBeClicked = toBeClicked;
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        if (ke.getKeyChar() == KeyEvent.VK_ENTER) {
            if (toBeClicked != null) {
                toBeClicked.doClick();
            }
        } else if (!(Character.isDigit(ke.getKeyChar())
                || ke.getKeyChar() == KeyEvent.VK_BACK_SPACE
                || ke.getKeyChar() == KeyEvent.VK_DELETE
                || ke.getKeyChar() == '.'
                || ke.getKeyChar() == '/'
                || ke.getKeyChar() == '-')) {
            ke.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
}

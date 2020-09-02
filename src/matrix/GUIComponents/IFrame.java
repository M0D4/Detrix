/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.GUIComponents;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import matrix.methods.DeterminantMethods;
import matrix.methods.HelpingMethods;
import matrix.methods.InverseMethods;

/**
 *
 * @author mahdy
 */
public class IFrame extends JFrame {
    
    boolean firstTime = true;
    IntroSubFrame f = new IntroSubFrame();
    DetPanel input = new DetPanel(2, 2);
    boolean firstTimeInput = true;
    JButton calculate = new JButton("calculate");
    boolean firstTimeCalc = true;
    OFrame resultFrame = new OFrame();

    public IFrame() {
        setTitle("Detrix v1");
        setSize(640, 130);
        getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        resultFrame.setLocation(getX() + 480, getY());
        add(f);

        f.start.addActionListener((ActionEvent ae) -> {
            if (firstTime || HelpingMethods.isEmpty(input.nums)) {
                doThings();
                resultFrame.dispose();

            } else if (!HelpingMethods.isEmpty(input.nums)) {
                int answer = JOptionPane.showOptionDialog(null,
                        "are you sure you want to clear the input ?",
                        "comfirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null, null, null);
                if (answer == JOptionPane.YES_OPTION) {
                    doThings();
                    resultFrame.dispose();
                }
            }

        });

        calculate.setHorizontalAlignment(JButton.CENTER);
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                if (HelpingMethods.isFull(input.nums)) {
                    resultFrame.dispose();
                    int rows = Integer.parseInt(f.sizeField.getText());
                    if (f.inverse.isSelected()) {
                        switch (f.methodsBox.getSelectedItem().toString()) {
                            case "cross product":

                                if (rows != 3) {
                                    JOptionPane.showMessageDialog(null, "this method works for 3X3 matrix only!", "error !", JOptionPane.ERROR_MESSAGE);

                                    break;
                                } else {
                                    resultFrame = InverseMethods.getInverseByCrossProduct(input.getNums(), IFrame.this);
                                }
                                break;
                            case "no steps":
                                resultFrame = InverseMethods.getInverseByAugmentedMatrix(input.getNums(), IFrame.this, false);
                                break;
                            case "adjoint":
                                if (rows != 3 && rows != 2) {
                                    JOptionPane.showMessageDialog(null, "this method works for 3X3 and 2X2 matrices only!", "error !", JOptionPane.ERROR_MESSAGE);

                                    break;
                                } else {
                                    resultFrame = InverseMethods.getInverseByAdJoint(input.getNums(), IFrame.this);
                                    break;
                                }
                            case "Gauss-Jordan":
                                resultFrame = InverseMethods.getInverseByAugmentedMatrix(input.getNums(), IFrame.this, true);
                                break;
                        }

                    }else {
                    switch (f.methodsBox.getSelectedItem().toString()) {

                        case "pivot":
                            resultFrame = DeterminantMethods.getDetByPivot(input.getNums(), IFrame.this);
                            break;

                        case "laplace":

                            if (rows != 3) {
                                JOptionPane.showMessageDialog(null, "this method works for 3X3 matrix only! \n we will solve with pivot method", "will be solved with another method", JOptionPane.INFORMATION_MESSAGE);
                                resultFrame = DeterminantMethods.getDetByPivot(input.getNums(), resultFrame);
                                break;
                            } else {
                                resultFrame = DeterminantMethods.getDetByLaplace(input.getNums(), IFrame.this);
                                break;
                            }

                        case "no steps":
                            resultFrame = DeterminantMethods.getDetByNoSteps(input.getNums(), IFrame.this);
                            break;

                        case "erwin-bareiss":
                            resultFrame = DeterminantMethods.getDetByErwinBareiss(input.getNums(), IFrame.this);
                            break;

                    }
                }
                }

            }
        });

    }

    public void doThings() {
        firstTime = false;
        try {

            int rows = Integer.parseInt(f.sizeField.getText());
            if (rows >= 50 || rows == 0 || rows == 1) {

                throw new Exception();
            }
            if (firstTimeInput) {

                setSize(640, 455);
                input = new DetPanel(rows, rows);
                add(input);
                add(calculate);
                firstTimeInput = false;
            } else {
                remove(input);
                remove(calculate);
                setSize(640, 455);
                input = new DetPanel(rows, rows);
                add(input);
                add(calculate);
                input.updateUI();
                firstTimeCalc = false;
            }
            input.nums[0][0].requestFocus();
            input.nums[input.nums.length - 1][input.nums.length - 1].addKeyListener(new PressButtonWithEnter(calculate, null));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "invalid input !", "error !", JOptionPane.ERROR_MESSAGE);
        }
    }

}

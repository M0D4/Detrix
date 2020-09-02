/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.methods;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import matrix.GUIComponents.DetPanel;
import matrix.GUIComponents.LargeTextArea;
import matrix.GUIComponents.OFrame;

/**
 *
 * @author Mohamed Elmahdy
 * @author Moustafa Mohamed
 */
public class DeterminantMethods {

    public static OFrame getDetByNoSteps(LargeTextArea[][] inputs, JFrame neighbour) {
        OFrame outFrame = new OFrame();
        outFrame.setLocation(neighbour.getX() + 638, neighbour.getY());
        outFrame.setVisible(true);
        int pCounter = 0; //to count pivots
        double pMul = 1.0d; //to store the multiplication of the pivots
        int negCounter1 = 0; //to count number of negative signs
        int negCounter2 = 0; //the same 
        int size = inputs.length;
        double det[][] = new double[size][size];
        double temp[][] = new double[size - 1][size - 1]; //temporary array
        double pivot[] = new double[size - 2]; //to store the pivots in it
        for (int i = 0; i < det.length; i++) {
            for (int j = 0; j < det.length; j++) {
                det[i][j] = HelpingMethods.toDouble(inputs[i][j].getText());
            }
        }

        JPanel input = new JPanel(new GridLayout(1, 2));
        LargeTextArea inputTxt = new LargeTextArea("A = ");
        input.add(inputTxt);
        DetPanel inputDet = new DetPanel(size, size);
        inputTxt.setEditable(false);
        inputDet.setEnabled(false);
        for (int i = 0; i < det.length; i++) {
            for (int j = 0; j < det.length; j++) {
                inputDet.nums[i][j].setText(Double.toString(det[i][j]));
                inputDet.nums[i][j].setEditable(false);

            }

        }
        input.add(inputDet);
        outFrame.getContainer().add(input);

        while (size > 2) {

            //when size reaches 2, break the loop
            pivot[pCounter] = det[0][0]; //pivot= the first element in matrix
            int checkCounter = 1; //to check if all rows starts with 0
            while (pivot[pCounter] == 0) { //if the pivot equals 0
                double swap; //to use for swapping
                if (checkCounter < det.length && det[checkCounter][0] != 0) {
                    //if checkCounter(number of rows) didn't exceed the size
                    //and the next row doesn't start with 0
                    negCounter1++; //increase negative sign counter
                    negCounter2++; //the same
                    pivot[pCounter] = det[checkCounter][0];
                    //pivot =first element of the row after swapping
                    for (int i = 0; i < det.length; i++) {
                        //to swap the two rows
                        swap = det[0][i];
                        det[0][i] = det[checkCounter][i];
                        det[checkCounter][i] = swap;
                    }
                }

                if (checkCounter == det.length) { //if all rows start with 0, det(A)=0
                    String s = "det(A)=0";
                    LargeTextArea twoDimRes = new LargeTextArea(s);
                    outFrame.getContainer().add(twoDimRes);

                    return outFrame; //exit the whole program
                }
                checkCounter++; //increase the counter if the row starts with 0
                

            }
            for (int i = 0; i < temp.length; i++) { //to copy det[] in temp[]
                for (int j = 0; j < temp.length; j++) {
                    temp[i][j] = (pivot[pCounter] * det[i + 1][j + 1] - (det[0][j + 1] * det[i + 1][0])) / pivot[pCounter];
                }
            }
            
            //to print pivots which multiplied with matrix
            for (double d : pivot) {
                if (d != 0) { //if the pivot doesn't =0 "the initial value"
                    if (negCounter1 > 0) {
                        
                        // to print pivot with "-" according to the counter
                        negCounter1--; //decrease counter1
                    } 
                }
            }

            size--; //decrease matrix size
            det = new double[size][size]; //modify matrix size(-1)
            for (int i = 0; i < temp.length; i++) { //copy temp[] to det[]
                System.arraycopy(temp[i], 0, det[i], 0, temp.length);
            }

            temp = new double[size - 1][size - 1]; //modify temp size(-1)
            pCounter++; //increase pivots counter
        }

        //thus, the size = 2
        //print the final process part1 (pivots)
        String twoDetString = "";
        for (int i = 0; i < pivot.length; i++) {
            if (negCounter2 > 0) {
                //print all pivots according to the sign counter
                pMul *= -1 * pivot[i]; //calculate the product of the pivots
                //according to the sign counter
                negCounter2--; //decrease counter2
            } else {
                //print without "-"
                pMul *= pivot[i];
                //multiply without "-"
            }
        }
        //print the final process part2 (2*2 matrix value)
        double result = (det[0][0] * det[1][1] - (det[0][1] * det[1][0])); //2*2 matrix value
        result *= (pMul); //multiply 2*2 matrix value by the product of pivots

        
            twoDetString = "det(A) = " + result;
            OFrame noSteps = new OFrame();
            LargeTextArea finalres = new LargeTextArea(twoDetString);
            finalres.setAlignmentX((float) pMul);
            finalres.setFont(new Font("Serif", Font.BOLD, 20));
            finalres.setAlignmentX(LargeTextArea.CENTER_ALIGNMENT);
            finalres.setAlignmentY(LargeTextArea.CENTER_ALIGNMENT);
            noSteps.getContainer().add(finalres);
            noSteps.setVisible(true);
            noSteps.setLocation(outFrame.getLocation());
            outFrame.dispose();

            return noSteps;
        
        
    }

    public static OFrame getDetByPivot(LargeTextArea[][] inputs, JFrame neighbour) {
        OFrame outFrame = new OFrame();
        outFrame.setLocation(neighbour.getX() + 638, neighbour.getY());
        outFrame.setVisible(true);
        int pCounter = 0; //to count pivots
        double pMul = 1.0f; //to store the multiplication of the pivots
        int negCounter1 = 0; //to count number of negative signs
        int negCounter2 = 0; //the same 
        int size = inputs.length;
        double det[][] = new double[size][size];
        double temp[][] = new double[size - 1][size - 1]; //temporary array
        double pivot[] = new double[size - 2]; //to store the pivots in it
        for (int i = 0; i < det.length; i++) {
            for (int j = 0; j < det.length; j++) {
                det[i][j] = HelpingMethods.toDouble(inputs[i][j].getText());
            }
        }

        JPanel input = new JPanel(new GridLayout(1, 2));
        LargeTextArea inputTxt = new LargeTextArea("A = ");
        input.add(inputTxt);
        DetPanel inputDet = new DetPanel(size, size);
        inputTxt.setEditable(false);
        inputDet.setEnabled(false);
        for (int i = 0; i < det.length; i++) {
            for (int j = 0; j < det.length; j++) {
                inputDet.nums[i][j].setText(Double.toString(det[i][j]));
                inputDet.nums[i][j].setEditable(false);

            }

        }
        input.add(inputDet);
        outFrame.getContainer().add(input);

        while (size > 2) {
            if (allRowZero(det)) {
                LargeTextArea r0 = new LargeTextArea("det(A) = 0, row number #" + row0 + " is full of zeros.");
                outFrame.getContainer().add(r0);
                return outFrame;
            }
            if (allColmZero(det)) {
                LargeTextArea c0 = new LargeTextArea("det(A) = 0, column number #" + colm0 + " is full of zeros.");
                outFrame.getContainer().add(c0);
                return outFrame;
            }
            if (hasCommonRows(det)) {
                LargeTextArea com1 = new LargeTextArea("det(A) = 0, row number #" + commonRow1 + " & row number #" + commonRow2 + " are common");
                outFrame.getContainer().add(com1);
                return outFrame;
            }
            if (hasCommonColms(det)) {
                LargeTextArea com1 = new LargeTextArea("det(A) = 0, column number #" + commonColm1 + " & column number #" + commonColm2 + " are common");
                outFrame.getContainer().add(com1);
                return outFrame;
            }

            //when size reaches 2, break the loop
            pivot[pCounter] = det[0][0]; //pivot= the first element in matrix
            int checkCounter = 1; //to check if all rows starts with 0
            while (pivot[pCounter] == 0) { //if the pivot equals 0
                double swap; //to use for swapping
                if (checkCounter < det.length && det[checkCounter][0] != 0) {
                    //if checkCounter(number of rows) didn't exceed the size
                    //and the next row doesn't start with 0
                    negCounter1++; //increase negative sign counter
                    negCounter2++; //the same
                    pivot[pCounter] = det[checkCounter][0];
                    //pivot =first element of the row after swapping
                    for (int i = 0; i < det.length; i++) {
                        //to swap the two rows
                        swap = det[0][i];
                        det[0][i] = det[checkCounter][i];
                        det[checkCounter][i] = swap;
                    }
                }

                if (checkCounter == det.length) { //if all rows start with 0, det(A)=0
                    String s = "det(A)=0";
                    LargeTextArea twoDimRes = new LargeTextArea(s);
                    outFrame.getContainer().add(twoDimRes);

                    return outFrame; //exit the whole program
                }
                checkCounter++; //increase the counter if the row starts with 0
                if (pivot[pCounter] != 0) {

                    JPanel outs = new JPanel();
                    outs.setLayout(new GridLayout(1, 2));

                    //if we swapped two rows and didn't find 0
                    LargeTextArea t = new LargeTextArea("pivot = 0, Swap row 1 with row " + checkCounter);
                    t.setFont(new Font("Serif", Font.BOLD, 20));
                    outs.add(t);
                    //to know which row 

                    DetPanel dt = new DetPanel(det.length, det.length);
                    dt.setPreferredSize(new Dimension(100, 100));
                    for (int i = 0; i < det.length; i++) {
                        //to print the matrix after swapping
                        for (int j = 0; j < det.length; j++) {
                            dt.nums[i][j].setText(String.format("%.2f", det[i][j]));
                            dt.nums[i][j].setEditable(false);
                        }
                    }
                    dt.nums[0][0].setBackground(Color.red);
                    outs.add(dt);
                    outFrame.getContainer().add(outs);
                }

            }
            for (int i = 0; i < temp.length; i++) { //to copy det[] in temp[]
                for (int j = 0; j < temp.length; j++) {
                    temp[i][j] = (pivot[pCounter] * det[i + 1][j + 1] - (det[0][j + 1] * det[i + 1][0])) / pivot[pCounter];
                }
            }
            String s2 = "A= ";

            //to print pivots which multiplied with matrix
            for (double d : pivot) {
                if (d != 0) { //if the pivot doesn't =0 "the initial value"
                    if (negCounter1 > 0) {
                        s2 += "(" + -1 * d + ")";

                        // to print pivot with "-" according to the counter
                        negCounter1--; //decrease counter1
                    } else {
                        s2 += "(" + d + ")"; //print without "-"
                    }
                }
            }
            s2+=" * ";
            JPanel jp = new JPanel(new GridLayout(1, 2));
            LargeTextArea t = new LargeTextArea(s2);
            t.setFont(new Font("Serif", Font.BOLD, 20));
            t.setAlignmentX(LargeTextArea.CENTER_ALIGNMENT);
            t.setAlignmentY(LargeTextArea.CENTER_ALIGNMENT);
            DetPanel dt = new DetPanel(temp.length, temp.length);
            dt.setPreferredSize(new Dimension(100, 200));

            for (int i = 0; i < temp.length; i++) { //print the matrix itself
                for (int j = 0; j < temp.length; j++) {
                    dt.nums[i][j].setText(String.format("%.2f", temp[i][j]));
                    dt.nums[i][j].setEditable(false);
                }
                jp.add(t);
                jp.add(dt);
                dt.nums[0][0].setBackground(Color.RED);
                outFrame.getContainer().add(jp);
            }
            size--; //decrease matrix size
            det = new double[size][size]; //modify matrix size(-1)
            for (int i = 0; i < temp.length; i++) { //copy temp[] to det[]
                System.arraycopy(temp[i], 0, det[i], 0, temp.length);
            }

            temp = new double[size - 1][size - 1]; //modify temp size(-1)
            pCounter++; //increase pivots counter
        }

        //thus, the size = 2
        //print the final process part1 (pivots)
        String twoDetString = "det(A) = ";
        for (int i = 0; i < pivot.length; i++) {
            if (negCounter2 > 0) {
                twoDetString += "(" + String.format("%.2f", -1 * pivot[i]) + ")";
                //print all pivots according to the sign counter
                pMul *= -1 * pivot[i]; //calculate the product of the pivots
                //according to the sign counter
                negCounter2--; //decrease counter2
            } else {
                twoDetString += "(" + String.format("%.2f", pivot[i]) + ")";
                //print without "-"
                pMul *= pivot[i];
                //multiply without "-"
            }
        }
        //print the final process part2 (2*2 matrix value)
        double result = (det[0][0] * det[1][1] - (det[0][1] * det[1][0])); //2*2 matrix value
        twoDetString += "( (" + String.format("%.2f", det[0][0]) + "*(" + String.format("%.2f", det[1][1]) + "))" + "-( " + String.format("%.2f", det[0][1]) + "*(" + String.format("%.2f", det[1][0]) + ")) )";        //A=[a b; cd] , det=(a*d-b*c)
        result *= (pMul); //multiply 2*2 matrix value by the product of pivots
        twoDetString += "= " + String.format("%.2f", result) + "\n";

        
        LargeTextArea twoDimRes = new LargeTextArea(twoDetString);
        twoDimRes.setAlignmentX((float) pMul);
        twoDimRes.setFont(new Font("Serif", Font.BOLD, 20));
        twoDimRes.setAlignmentX(LargeTextArea.CENTER_ALIGNMENT);
        twoDimRes.setAlignmentY(LargeTextArea.CENTER_ALIGNMENT);
        outFrame.getContainer().add(twoDimRes);
        outFrame.setVisible(true);

        return outFrame;

    }

    public static OFrame getDetByLaplace(LargeTextArea[][] inputs, JFrame neighbour) {
        OFrame outFrame = new OFrame();
        outFrame.setLocation(neighbour.getX() + 638, neighbour.getY());
        outFrame.setVisible(true);
        JPanel container = outFrame.getContainer();

        JPanel input = new JPanel(new GridLayout(1, 2));
        LargeTextArea inputTxt = new LargeTextArea(" = A");

        DetPanel inputDet = new DetPanel(3, 3);

        inputDet.setEnabled(false);
        for (int i = 0; i < inputs.length; i++) {
            for (int j = 0; j < inputs.length; j++) {
                inputDet.nums[i][j].setText(inputs[i][j].getText());
                inputDet.nums[i][j].setEditable(false);

            }

        }
        inputTxt.setFont(new Font("Serif", Font.BOLD, 20));
        inputTxt.setEditable(false);
        input.add(inputDet);
        input.add(inputTxt);
        outFrame.getContainer().add(input);
        
        container.setLayout(new GridLayout(6, 1, 5, 5));
        double mat[][] = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mat[i][j] = HelpingMethods.toDouble(inputs[i][j].getText());
            }

        }
        if (allRowZero(mat)) {
                LargeTextArea r0 = new LargeTextArea("det(A) = 0, row number #" + row0 + " is full of zeros.");
                outFrame.getContainer().add(r0);
                return outFrame;
            }
            if (allColmZero(mat)) {
                LargeTextArea c0 = new LargeTextArea("det(A) = 0, column number #" + colm0 + " is full of zeros.");
                outFrame.getContainer().add(c0);
                return outFrame;
            }
            if (hasCommonRows(mat)) {
                LargeTextArea com1 = new LargeTextArea("det(A) = 0, row number #" + commonRow1 + " & row number #" + commonRow2 + " are common");
                outFrame.getContainer().add(com1);
                return outFrame;
            }
            if (hasCommonColms(mat)) {
                LargeTextArea com1 = new LargeTextArea("det(A) = 0, column number #" + commonColm1 + " & column number #" + commonColm2 + " are common");
                outFrame.getContainer().add(com1);
                return outFrame;
            }
        double result;
        //find the results through row 0 only

        double res1 = mat[0][0] * (mat[1][1] * mat[2][2] - (mat[1][2] * mat[2][1]));//element0 in row0
        JPanel firstRow = new JPanel();
        firstRow.setLayout(new GridLayout(1, 2));
        DetPanel dt = new DetPanel(3, 3);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                dt.nums[i][j].setEditable(false);
                dt.nums[i][j].setText(inputs[i][j].getText());

            }

        }

        JPanel first = new JPanel(new GridLayout(1, 2));
        dt.nums[0][0].setBackground(Color.red);
        dt.nums[1][1].setBackground(Color.green);
        dt.nums[2][2].setBackground(Color.green);
        dt.nums[1][2].setBackground(Color.green);
        dt.nums[2][1].setBackground(Color.green);
        first.add(dt);
        JLabel firstLabel = new JLabel(mat[0][0] + "*(" + mat[1][1] + "*" + mat[2][2] + "-(" + mat[1][2] + "*" + mat[2][1] + ")) = " + res1, SwingConstants.CENTER);
        firstLabel.setFont(new Font("Serif", Font.BOLD, 20));
        first.add(firstLabel);
        HelpingMethods.toBlack(dt.nums);
        container.add(first);
        //System.out.println();
        double res2 = -mat[0][1] * (mat[1][0] * mat[2][2] - (mat[1][2] * mat[2][0]));//element1 in row1

        JPanel second = new JPanel(new GridLayout(1, 2));

        DetPanel dt2 = new DetPanel(3, 3);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                dt2.nums[i][j].setEditable(false);
                dt2.nums[i][j].setText(inputs[i][j].getText());

            }

        }

        dt2.nums[0][1].setBackground(Color.red);
        dt2.nums[1][0].setBackground(Color.green);
        dt2.nums[2][2].setBackground(Color.green);
        dt2.nums[1][2].setBackground(Color.green);
        dt2.nums[2][0].setBackground(Color.green);
        second.add(dt2);
        JLabel secondLabel = new JLabel(-mat[0][1] + "*(" + mat[1][0] + "*" + mat[2][2] + "-(" + mat[1][2] + "*" + mat[2][0] + ")) = " + res2, SwingConstants.CENTER);
        secondLabel.setFont(new Font("Serif", Font.BOLD, 20));
        second.add(secondLabel);
        HelpingMethods.toBlack(dt2.nums);
        container.add(second);
        //System.out.println();

        double res3 = mat[0][2] * (mat[1][0] * mat[2][1] - (mat[1][1] * mat[2][0]));//element2 in row2
        JPanel third = new JPanel(new GridLayout(1, 2));
        DetPanel dt3 = new DetPanel(3, 3);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                dt3.nums[i][j].setEditable(false);
                dt3.nums[i][j].setText(inputs[i][j].getText());

            }

        }
        dt3.nums[0][2].setBackground(Color.red);
        dt3.nums[1][0].setBackground(Color.green);
        dt3.nums[2][1].setBackground(Color.green);
        dt3.nums[1][1].setBackground(Color.green);
        dt3.nums[2][0].setBackground(Color.green);
        third.add(dt3);
        JLabel thirdLabel = new JLabel(mat[0][2] + "*(" + mat[1][0] + "*" + mat[2][1] + "-(" + mat[1][1] + "*" + mat[2][0] + ")) = " + res3, SwingConstants.CENTER);
        thirdLabel.setFont(new Font("Serif", Font.BOLD, 20));
        third.add(thirdLabel);
        HelpingMethods.toBlack(dt3.nums);
        container.add(third);
        // System.out.println();
        result = res1 + res2 + res3; //sum of all results
        JLabel preFinalJLabel = new JLabel("det(A)= " + "(" + res1 + ") + (" + res2 + ") + (" + res3 + ") = " + result, SwingConstants.CENTER);
        preFinalJLabel.setFont(new Font("Serif", Font.BOLD, 20));
        container.add(preFinalJLabel);

        return outFrame;
    }

    public static OFrame getDetByErwinBareiss(LargeTextArea[][] inputs, JFrame neighbour) {
        OFrame outFrame = new OFrame();
        outFrame.setLocation(neighbour.getX() + 638, neighbour.getY());
        outFrame.setVisible(true);
        JPanel container = outFrame.getContainer();
        int size = inputs.length;
        Double mat[][] = new Double[size][size];//to store as numbers
        DetPanel det2 = new DetPanel(size, size);

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = (double) HelpingMethods.toDouble(inputs[i][j].getText()); //to take values
                det2.nums[i][j].setText(String.format("%.2f", mat[i][j]));
            }
        }
        
        JPanel p2 = new JPanel(new GridLayout(1, 2));
        p2.add(new LargeTextArea("A = "));
        p2.add(det2);
        outFrame.getContainer().add(p2);
        if (allRowZero(mat)) {
                LargeTextArea r0 = new LargeTextArea("det(A) = 0, row number #" + row0 + " is full of zeros.");
                outFrame.getContainer().add(r0);
                return outFrame;
            }
            if (allColmZero(mat)) {
                LargeTextArea c0 = new LargeTextArea("det(A) = 0, column number #" + colm0 + " is full of zeros.");
                outFrame.getContainer().add(c0);
                return outFrame;
            }
            if (hasCommonRows(mat)) {
                LargeTextArea com1 = new LargeTextArea("det(A) = 0, row number #" + commonRow1 + " & row number #" + commonRow2 + " are common");
                outFrame.getContainer().add(com1);
                return outFrame;
            }
            if (hasCommonColms(mat)) {
                LargeTextArea com1 = new LargeTextArea("det(A) = 0, column number #" + commonColm1 + " & column number #" + commonColm2 + " are common");
                outFrame.getContainer().add(com1);
                return outFrame;
            }
        double pivot[] = new double[size - 1];
        int counter = 0;
        int negCounter1 = 0; //to count number of negative signs
        boolean flag2 = false;
        while (counter < size - 1) {
            LargeTextArea text;
            pivot[counter] = mat[counter][counter];
            int checkCounter = counter + 1; //to check if all rows starts with 0
            while (pivot[counter] == 0) { //if the pivot equals 0
                flag2 = true;
                double swap; //to use for swapping
                if (checkCounter < mat.length && mat[checkCounter][counter] != 0) {
                    //if checkCounter(number of rows) didn't exceed the size
                    //and the next row doesn't start with 0
                    negCounter1++; //increase negative sign counter
                    pivot[counter] = mat[checkCounter][counter];
                    //pivot = element of the row after swapping
                    for (int i = 0; i < mat.length; i++) {
                        //to swap the two rows
                        swap = mat[counter][i];
                        mat[counter][i] = mat[checkCounter][i];
                        mat[checkCounter][i] = swap;
                    }
                }

                if (checkCounter == mat.length) { //if all rows start with 0, mat(A)=0
                    JPanel j0 = new JPanel(new GridLayout());
                    LargeTextArea t0 = new LargeTextArea("det(A)=0");
                    j0.add(t0);
                    outFrame.getContainer().add(j0);
                    return outFrame;
                }
                checkCounter++; //increase the counter if the row starts with 0
                if (pivot[counter] != 0) { //if we swapped two rows and didn't find 0
                    LargeTextArea swapT = new LargeTextArea("next pivot = 0, Swap " + (counter + 1) + "th row with the " + (checkCounter) + "th row");
                    outFrame.getContainer().add(swapT);
                    //to know which row 
                    JPanel jSwap = new JPanel(new GridLayout(1, 2));
                    LargeTextArea swapT2 = new LargeTextArea("A after swapping = ");
                    DetPanel dSwap = new DetPanel(size, size);
                    for (int i = 0; i < mat.length; i++) {
                        //to print the matrix after swapping
                        for (int j = 0; j < mat.length; j++) {
                            dSwap.nums[i][j].setText(String.format("%.2f", mat[i][j]));
                        }
                    }
                    jSwap.add(swapT2);
                    jSwap.add(dSwap);
                    outFrame.getContainer().add(jSwap);
                }
            }

            if (pivot[counter] != 0) {

                JPanel p1 = new JPanel();
                p1.setLayout(new GridLayout(1, 2));
                LargeTextArea text2;
                if (counter > 0) {
                    text2 = new LargeTextArea("pivot = " + pivot[counter] + ", divide new elements by " + pivot[counter - 1]);
                } else {
                    text2 = new LargeTextArea("pivot = " + pivot[counter]);
                }
                p1.add(text2);
                for (int i = counter + 1; i < mat.length; i++) {
                    for (int j = counter + 1; j < mat.length; j++) {
                        double a = mat[counter][counter] * mat[i][j];
                        double b = mat[counter][j] * mat[i][counter];
                        double c = a - b;
                        if (counter > 0) {
                            c /= pivot[counter - 1];//divide by previous pivot
                        }
                        mat[i][j] = c;//replace element by the new value
                    }
                }
                for (int i = counter + 1; i < mat.length; i++) {
                    mat[i][counter] = 0d;//set columns elements equal 0
                }
                DetPanel det = new DetPanel(size, size);
                for (int i = 0; i < mat.length; i++) {//print matrix
                    for (int j = 0; j < mat.length; j++) {
                        det.nums[i][j].setText(String.format("%.2f", mat[i][j]));
                    }

                }

                for (int i = counter; i < mat.length; i++) {
                    det.nums[i][counter].setForeground(Color.BLUE);
                }
                det.nums[counter][counter].setForeground(Color.red);
                p1.add(det);
                outFrame.getContainer().add(p1);
                counter++;
            }

        }
        LargeTextArea finalRes = new LargeTextArea("det(A) = " + mat[size - 1][size - 1]);

        double det = mat[size - 1][size - 1];
        LargeTextArea tff = new LargeTextArea();
        if (negCounter1 > 1) {
            tff = new LargeTextArea("multiply by (-1) " + negCounter1 + " times");
        } else if (negCounter1 == 1) {
            tff = new LargeTextArea("multiply by (-1) " + negCounter1 + " time");
        }
        for (int i = 0; i < negCounter1; i++) {
            det *= -1;
        }
        if (negCounter1 > 1) {
            finalRes = new LargeTextArea("det(A) = " + mat[size - 1][size - 1] + ", multiplied by (-1) " + negCounter1 + " times (numbers of swapping)");
        }
        if (negCounter1 == 1) {
            finalRes = new LargeTextArea("det(A) = " + mat[size - 1][size - 1] + ", multiplied by (-1) " + negCounter1 + " time (swapped once)");
        }
        outFrame.getContainer().add(finalRes);
        if (negCounter1 > 0) {
            outFrame.getContainer().add(new LargeTextArea("det(A) = " + det));//the last element in the matrix
        }

        return outFrame;
    }
    static int commonRow1 = 0, commonRow2 = 0, commonColm1 = 0, commonColm2 = 0, row0 = 0, colm0 = 0;

    public static boolean hasCommonRows(double a[][]) {
        double c = 0;
        double r1[] = new double[a.length];
        double r2[] = new double[a.length];
        boolean flag = false;
        for (int i = 0; i < r2.length; i++) {
            for (int j = 0; j < r2.length; j++) {
                if (i != j) {
                    copyRows(a, r1, r2, i, j);
                    if (equals(r1, r2)) {
                        flag = true;
                        commonRow1 = i + 1;
                        commonRow2 = j + 1;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    public static void copyRows(double a[][], double r1[], double[] r2, int index1, int index2) {
        for (int i = 0; i < r2.length; i++) {
            r1[i] = a[index1][i];
        }
        for (int i = 0; i < r2.length; i++) {
            r2[i] = a[index2][i];
        }
    }

    public static boolean equals(double a[], double b[]) {
        boolean flag = true;
        int counter = 0;
        int temCounter1 = 0;
        double m = 0d;

        for (int i = 0; i < b.length; i++) {
            if (temCounter1 == 0 && a[i] % b[i] == 0 && a[i] != 0 && b[i] != 0) {
                m = (double) a[i] / (double) b[i];
                counter++;
                temCounter1++;
            } else if (temCounter1 == 0 && b[i] % a[i] == 0 && a[i] != 0 && b[i] != 0) {
                m = a[i] / b[i];
                counter++;
                temCounter1++;
            } else if (temCounter1 > 0) {
                if (a[i] / b[i] == m && a[i] != 0 && b[i] != 0) {
                    counter++;
                }
            }
            if (counter == b.length) {
                flag = true;
                return flag;
            }
        }
        for (int i = 0; i < b.length; i++) {
            if (a[i] != b[i]) {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    public static boolean hasCommonColms(double a[][]) {
        int c = 0;
        double c1[] = new double[a.length];
        double c2[] = new double[a.length];
        boolean flag = false;
        for (int i = 0; i < c2.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                if (i != j) {
                    copyColms(a, c1, c2, i, j);
                    if (equals(c1, c2)) {
                        flag = true;
                        commonColm1 = i + 1;
                        commonColm2 = j + 1;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    public static void copyColms(double a[][], double c1[], double[] c2, int index1, int index2) {
        for (int i = 0; i < c2.length; i++) {
            c1[i] = a[i][index1];
        }
        for (int i = 0; i < c2.length; i++) {
            c2[i] = a[i][index2];
        }
    }

    public static boolean allRowZero(double a[][]) {
        int counter = 0;
        int check = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] != 0) {
                    counter = 0;
                    break;
                } else {
                    counter++;
                }
                if (counter == a.length) {
                    row0 = i + 1;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean allColmZero(double a[][]) {
        int counter = 0;
        int check = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j][i] != 0) {
                    counter = 0;
                    break;
                } else {
                    counter++;
                }
                if (counter == a.length) {
                    colm0 = i + 1;
                    return true;
                }
            }
        }
        return false;
    }
     public static boolean hasCommonRows(Double a[][]) {
        double c = 0;
        Double r1[] = new Double[a.length];
        Double r2[] = new Double[a.length];
        boolean flag = false;
        for (int i = 0; i < r2.length; i++) {
            for (int j = 0; j < r2.length; j++) {
                if (i != j) {
                    copyRows(a, r1, r2, i, j);
                    if (equals(r1, r2)) {
                        flag = true;
                        commonRow1 = i + 1;
                        commonRow2 = j + 1;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    public static void copyRows(Double a[][], Double r1[], Double[] r2, int index1, int index2) {
        for (int i = 0; i < r2.length; i++) {
            r1[i] = a[index1][i];
        }
        for (int i = 0; i < r2.length; i++) {
            r2[i] = a[index2][i];
        }
    }

    public static boolean equals(Double a[], Double b[]) {
        boolean flag = true;
        int counter = 0;
        int temCounter1 = 0;
        double m = 0d;

        for (int i = 0; i < b.length; i++) {
            if (temCounter1 == 0 && a[i] % b[i] == 0 && a[i] != 0 && b[i] != 0) {
                m = (double) a[i] / (double) b[i];
                counter++;
                temCounter1++;
            } else if (temCounter1 == 0 && b[i] % a[i] == 0 && a[i] != 0 && b[i] != 0) {
                m = a[i] / b[i];
                counter++;
                temCounter1++;
            } else if (temCounter1 > 0) {
                if (a[i] / b[i] == m && a[i] != 0 && b[i] != 0) {
                    counter++;
                }
            }
            if (counter == b.length) {
                flag = true;
                return flag;
            }
        }
        for (int i = 0; i < b.length; i++) {
            if (a[i] != b[i]) {
                flag = false;
                return flag;
            }
        }
        return flag;
    }

    public static boolean hasCommonColms(Double a[][]) {
        int c = 0;
        Double c1[] = new Double[a.length];
        Double c2[] = new Double[a.length];
        boolean flag = false;
        for (int i = 0; i < c2.length; i++) {
            for (int j = 0; j < c2.length; j++) {
                if (i != j) {
                    copyColms(a, c1, c2, i, j);
                    if (equals(c1, c2)) {
                        flag = true;
                        commonColm1 = i + 1;
                        commonColm2 = j + 1;
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    public static void copyColms(Double a[][], Double c1[], Double[] c2, int index1, int index2) {
        for (int i = 0; i < c2.length; i++) {
            c1[i] = a[i][index1];
        }
        for (int i = 0; i < c2.length; i++) {
            c2[i] = a[i][index2];
        }
    }

    public static boolean allRowZero(Double a[][]) {
        int counter = 0;
        int check = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[i][j] != 0) {
                    counter = 0;
                    break;
                } else {
                    counter++;
                }
                if (counter == a.length) {
                    row0 = i + 1;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean allColmZero(Double a[][]) {
        int counter = 0;
        int check = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                if (a[j][i] != 0) {
                    counter = 0;
                    break;
                } else {
                    counter++;
                }
                if (counter == a.length) {
                    colm0 = i + 1;
                    return true;
                }
            }
        }
        return false;
    }
}

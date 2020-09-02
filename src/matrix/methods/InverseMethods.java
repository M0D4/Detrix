/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.methods;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
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
public class InverseMethods {

    public static OFrame getInverseByCrossProduct(LargeTextArea[][] inputs, JFrame neighbour) {
        OFrame outFrame = new OFrame();
        outFrame.getContainer().setLayout(new GridLayout(7, 2, 5, 5));
        outFrame.setLocation(neighbour.getX() + 638, neighbour.getY());//638
        outFrame.setVisible(true);
        double det[][] = new double[3][3];
        JPanel input=new JPanel();
        input.setLayout(new GridLayout(1,2));
        input.add(new LargeTextArea("A = "));
        DetPanel inputDet =new DetPanel(3, 3);
        for (int i = 0; i < det.length; i++) {
            for (int j = 0; j < det.length; j++) {
                det[i][j] = HelpingMethods.toDouble(inputs[i][j].getText());
                inputDet.nums[i][j].setText(inputs[i][j].getText());
            }
        }
        input.add(inputDet);
        outFrame.getContainer().add(input);
        double detValue = HelpingMethods.getDet(det); //to calculate det
        outFrame.getContainer().add(new LargeTextArea("det(A)= " + detValue));
        if (detValue == 0) {
            outFrame.getContainer().add(new LargeTextArea("This matrix doesn't have an Inverse!"));
            return outFrame;

        }
        double c1[] = new double[3]; //to store column1 in it
        double c2[] = new double[3]; //to store column2 in it
        double c3[] = new double[3]; //to store column3 in it       
        setColumns(det, c1, c2, c3); //to set elements in columns
        double inv[][] = new double[3][3]; //to store the inverse matrix in it

        double c2Xc3[] = HelpingMethods.crossProduct(c2, c3); //to calculate the cross product of c2, c3
        JPanel c2Xc3Panel = new JPanel(new GridLayout(1, 2, 5, 5));
        c2Xc3Panel.add(new LargeTextArea("Cross Product of columns C2×C3 = "));
        DetPanel c2Xc3Vec = new DetPanel(1, 3);
        c2Xc3Vec.setBackground(null);
        for (int i = 0; i < c2Xc3.length; i++) {
            c2Xc3Vec.nums[0][i].setText(Double.toString(c2Xc3[i]));
            c2Xc3Vec.nums[0][i].setEditable(false);
        }
        c2Xc3Vec.setPreferredSize(new Dimension(34, 50));
        c2Xc3Panel.add(c2Xc3Vec);
        outFrame.getContainer().add(c2Xc3Panel);

        double c3Xc1[] = HelpingMethods.crossProduct(c3, c1); //to calculate the cross product of c3, c1
        JPanel c3Xc1Panel = new JPanel(new GridLayout(1, 2, 5, 5));
        c3Xc1Panel.add(new LargeTextArea("Cross Product of columns C3×C1 = "));
        DetPanel c3Xc1Vec = new DetPanel(1, 3);
        c3Xc1Vec.setBackground(null);
        c3Xc1Vec.setPreferredSize(new Dimension(34, 50));
        for (int i = 0; i < c2Xc3.length; i++) {
            c3Xc1Vec.nums[0][i].setText(Double.toString(c3Xc1[i]));
            c3Xc1Vec.nums[0][i].setEditable(false);
        }
        c3Xc1Panel.add(c3Xc1Vec);
        outFrame.getContainer().add(c3Xc1Panel);

        double c1Xc2[] = HelpingMethods.crossProduct(c1, c2); //to calculate the cross product of c1, c2
        JPanel c1Xc2Panel = new JPanel(new GridLayout(1, 2, 5, 5));
        c1Xc2Panel.add(new LargeTextArea("Cross Product of columns C1×C2 = "));
        DetPanel c1Xc2Vec = new DetPanel(1, 3);

        c1Xc2Vec.setPreferredSize(new Dimension(34, 50));
        c1Xc2Vec.setBackground(null);
        for (int i = 0; i < c1Xc2.length; i++) {
            c1Xc2Vec.nums[0][i].setText(Double.toString(c1Xc2[i]));
            c1Xc2Vec.nums[0][i].setEditable(false);
        }
        c1Xc2Panel.add(c1Xc2Vec);
        outFrame.getContainer().add(c1Xc2Panel);

        for (int i = 0; i < inv.length; i++) { //store elements in Inverse matrix
            for (int j = 0; j < inv.length; j++) {
                switch (i) {
                    case 0:
                        inv[i][j] = c2Xc3[j]; //for row1
                        break;
                    case 1:
                        inv[i][j] = c3Xc1[j]; //for row2
                        break;
                    case 2:
                        inv[i][j] = c1Xc2[j]; //for row3
                        break;
                    default:
                        break;
                }
            }
        }

        JPanel preFinalPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        LargeTextArea r = new LargeTextArea("A^(-1) =                               (1/" + detValue + ") *");
        preFinalPanel.add(r);
        DetPanel preFinalForm = new DetPanel(inv.length, inv.length);
        preFinalForm.setPreferredSize(new Dimension(100, 100));

        for (int i = 0; i < inv.length; i++) { //print matrix before multiplying by 1/det(A)
            for (int j = 0; j < inv.length; j++) {
                if ((int) inv[i][j] == inv[i][j]) {
                    preFinalForm.nums[i][j].setText(String.valueOf(inv[i][j]));

                } else {
                    preFinalForm.nums[i][j].setText(String.valueOf(inv[i][j]));
                }

            }
        }
        JPanel FinalPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        LargeTextArea r1 = new LargeTextArea("A^(-1) = ");
        FinalPanel.add(r1);
        DetPanel FinalForm = new DetPanel(inv.length, inv.length);
        FinalForm.setPreferredSize(new Dimension(100, 100));
        
        for (int i = 0; i < inv.length; i++) { //print matrix before multiplying by 1/det(A)
            for (int j = 0; j < inv.length; j++) {
                if ((int) inv[i][j] == inv[i][j]) {
                    FinalForm.nums[i][j].setText(HelpingMethods.toFraction(inv[i][j], detValue));

                } else {
                    FinalForm.nums[i][j].setText(HelpingMethods.toFraction(inv[i][j], detValue));
                }

            }
        }
        
        preFinalPanel.add(preFinalForm);
        outFrame.getContainer().add(preFinalPanel);
        FinalPanel.add(FinalForm);
        outFrame.getContainer().add(FinalPanel);
        return outFrame;
    }

    public static OFrame getInverseByAdJoint(LargeTextArea[][] inputs, JFrame neighbour) {
        OFrame outFrame = new OFrame();
        outFrame.getContainer().setLayout(new GridLayout(inputs.length+4, 2, 5, 5));
        outFrame.setLocation(neighbour.getX() + 638, neighbour.getY());
        outFrame.setVisible(true);
        int size = inputs.length; //store size
        double det; //to store det value
        Double mat[][] = new Double[size][size]; //to store instance matrix 
        Double minor[][] = new Double[size][size]; //for Minor matrix
        Double coFactor[][] = new Double[size][size]; //for Co-Factor Matrix
        Double adj[][] = new Double[size][size]; //for Adjoint matrix
        String inverse[][] = new String[size][size]; //for final inverse matrix
        DetPanel det2 = new DetPanel(size, size);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = HelpingMethods.toDouble(inputs[i][j].getText()); //to store values as decimal numbers
                det2.nums[i][j].setText(Double.toString(mat[i][j]));
            }
        }

        det = det(mat); //to calculate det value
        
        JPanel p = new JPanel(new GridLayout(1, 2));
        p.add(new LargeTextArea("A = "));
        p.add(det2);
        outFrame.getContainer().add(p);
        if (det(mat) == 0) { //singular matrix
            JPanel dett =new JPanel(new GridLayout(1,1));
        dett.add(new LargeTextArea("det(A) = "+"("+det+"), This matrix has no inverse!"));
        outFrame.getContainer().add(dett);
        } else if (mat.length == 2) {
            JPanel dett =new JPanel(new GridLayout(1,1));
        dett.add(new LargeTextArea("det(A) = "+"("+det+")"));
        outFrame.getContainer().add(dett);
            minor = calcMinor(mat); //to find Minor matrix
            JPanel stepPanel = new JPanel(new GridLayout(1, 2));
            stepPanel.add(new LargeTextArea("Step 1:(Swap L.Ad-Joint)& multiply R.diagonal *(-1) "));
            DetPanel dt = printMatrix(minor);
            stepPanel.add(dt); //print minor
            outFrame.getContainer().add(stepPanel);
            
            JPanel stepPane2 = new JPanel(new GridLayout(1, 2));
            LargeTextArea temp = new LargeTextArea("Inverse =        1/(" + det + ") * ");
            temp.setHorizontalAlignment(SwingConstants.CENTER);
            stepPane2.add(temp);
            DetPanel dt2 = printMatrix(minor);
            stepPane2.add(dt2); //print 1/det * inverse
            outFrame.getContainer().add(stepPane2);

            inverse = inverse(minor, det);//to find final inverse matrix
            JPanel inv = new JPanel(new GridLayout(1, 2));
            LargeTextArea inve=new LargeTextArea("Inverse = ");
            inv.add(inve);
            DetPanel invPanel = printMatrix(inverse);//print inverse
            inv.add(invPanel);
            outFrame.getContainer().add(inv);
        } else if (mat.length == 3) {
            JPanel dett =new JPanel(new GridLayout(1,1));
        dett.add(new LargeTextArea("det(A) = "+"("+det+")"));
        outFrame.getContainer().add(dett);
            minor = calcMinor(mat); //find Minor matrix
            JPanel minorPanel = new JPanel(new GridLayout(1, 2));
            minorPanel.add(new LargeTextArea("Minor = "));
            DetPanel invDetPanel = printMatrix(minor);//print Minor
            minorPanel.add(invDetPanel);
            outFrame.getContainer().add(minorPanel);

            coFactor = coFactor(minor);//find Co-Factor matrix
            JPanel coFactorPanel = new JPanel(new GridLayout(1, 2));
            coFactorPanel.add(new LargeTextArea("Co-Factor = "));
            DetPanel invDetPane2 = printMatrix(coFactor);//print coFactor
            coFactorPanel.add(invDetPane2);
            outFrame.getContainer().add(coFactorPanel);

            adj = adj(coFactor);//find Adjoint matrix
            JPanel adjPanel = new JPanel(new GridLayout(1, 2));
            adjPanel.add(new LargeTextArea("Adjoint = "));
            DetPanel invDetPane3 = printMatrix(adj);//print Adjoint
            adjPanel.add(invDetPane3);
            outFrame.getContainer().add(adjPanel);

            JPanel tempPanel = new JPanel(new GridLayout(1, 2));
            LargeTextArea last = new LargeTextArea("Inverse =         1/(" + det + ")* ");
            last.setHorizontalAlignment(SwingConstants.CENTER);
            tempPanel.add(last);/////
            DetPanel invDetPane4 = printMatrix(adj);
            tempPanel.add(invDetPane4);
            outFrame.getContainer().add(tempPanel);

            inverse = inverse(adj, det);//find final inverse matrix
            JPanel invPanel = new JPanel(new GridLayout(1, 2));
            invPanel.add(new LargeTextArea("Inverse = "));
            DetPanel invDetPane5 = printMatrix(inverse);//print final inverse
            invPanel.add(invDetPane5);
            outFrame.getContainer().add(invPanel);
            
            
        }
        return outFrame;
    }

    public static OFrame getInverseByAugmentedMatrix(LargeTextArea[][] input, JFrame neighbour,boolean withSteps) {
        OFrame outFrame = new OFrame();
        outFrame.getContainer().setLayout(new GridLayout(input.length+9, 2, 5, 5));
        outFrame.setLocation(neighbour.getX() + 638, neighbour.getY());
        outFrame.setVisible(true);
        int size = input.length;
        Double mat[][] = new Double[size][size];//to store as numbers
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                mat[i][j] = toDouble(input[i][j].getText()); //to take values
            }
        }

        double det = det(mat);//calculate det
        if (det == 0) {
            outFrame.getContainer().add(new LargeTextArea("det(A) = 0, This matrix has no Inverse!"));
            return outFrame;

        } else {
            Double bigMat[][] = setBigMat(mat);//to set bigMatrix as numbers
            JPanel p1bigMatFirst = new JPanel();
                p1bigMatFirst.setLayout(new GridLayout(1, 2));
                LargeTextArea textFirst = new LargeTextArea("[A I] = ");
                p1bigMatFirst.add(textFirst);
                DetPanel detBigMatFirst = new DetPanel(size, size*2);
                for (int i = 0; i < bigMat.length; i++) {
                    for (int j = 0; j < bigMat.length*2; j++) {
                         double s2=bigMat[i][j];
                         if(s2==0 || s2==-0) detBigMatFirst.nums[i][j].setText("0");
                         else  if(String.format("%.2f",s2).endsWith(".00"))
                   detBigMatFirst.nums[i][j].setText(String.format("%.2f",s2).replace(".00",""));
                     else detBigMatFirst.nums[i][j].setText(String.format("%.2f",s2));
                    }
            }
                p1bigMatFirst.add(detBigMatFirst);
                outFrame.getContainer().add(p1bigMatFirst);
            
            double pivot = 0d;
            int counter = 0;
            while (counter < size) {
                pivot = bigMat[counter][counter];//take the pivot from the Ad-Joint
                if(pivot==0d){
                boolean check=addTwoRows(bigMat, counter);
                if(!check){
                    LargeTextArea hasNo =new LargeTextArea("This matrix has no inverse!");
                    outFrame.getContainer().add(hasNo);
                    return outFrame;
                }
                LargeTextArea addT=new LargeTextArea("pivot = 0, add row "+(getIndex()+1)+" to row "+(counter+1));
                outFrame.getContainer().add(addT);
                JPanel afterAdd=new JPanel(new GridLayout(1,2));
                LargeTextArea aEq=new LargeTextArea("After adding rows = ");
                DetPanel detAfter=new DetPanel(size, size*2);
                    for (int i = 0; i < mat.length; i++) {
                        for (int j = 0; j < mat.length*2; j++) {
                            double s2=bigMat[i][j];
               if(s2==0 || s2==-0) detAfter.nums[i][j].setText("0");
               else  if(String.format("%.2f",s2).endsWith(".00"))
                   detAfter.nums[i][j].setText(String.format("%.2f",s2).replace(".00",""));
               
               else detAfter.nums[i][j].setText(String.format("%.2f",s2));
            }
        }
            pivot=bigMat[counter][counter];//take the pivot from the Ad-Joint
             afterAdd.add(aEq);
             afterAdd.add(detAfter);
             outFrame.getContainer().add(afterAdd);
            }        

                for (int i = 0; i < mat.length; i++) {
                    for (int j = 0; j < mat.length * 2; j++) {//doubled length of columns
                        if (i != counter && j != counter) {
                            double d1=bigMat[counter][counter]* bigMat[i][j];
                        double d2=bigMat[counter][j]* bigMat[i][counter];
                        double d3=d1- d2;
                        bigMat[i][j]=toDouble(String.format("%.2f",d3/pivot));
                        }
                    }
                }
                for (int i = 0; i < mat.length * 2; i++) {//doubled length of columns
                    if (i != counter) {
                        bigMat[counter][i] =(bigMat[counter][i]/ pivot);
                        //divide rows elements by pivot
                    }
                }
                for (int i = 0; i < mat.length; i++) {//set columns elements as 0 and 1
                    if (i != counter) {
                        bigMat[i][counter] = 0d;
                    } else {
                        bigMat[i][counter] = 1d;
                    }
                }

                counter++;
                JPanel p1bigMat = new JPanel();
                p1bigMat.setLayout(new GridLayout(1, 2));
                LargeTextArea text2 = new LargeTextArea("Step " + counter + " : pivot = "+bigMat[counter-1][counter-1]);
                p1bigMat.add(text2);
                DetPanel detBigMat = new DetPanel(size, size*2);
                for (int i = 0; i < bigMat.length; i++) {
                    for (int j = 0; j < bigMat.length*2; j++) {
                        if(bigMat[i][j]==0d || bigMat[i][j]==-0d)  detBigMat.nums[i][j].setText("0");
             else  if(String.format("%.2f",bigMat[i][j]).endsWith(".00"))
                   detBigMat.nums[i][j].setText(String.format("%.2f",bigMat[i][j]).replace(".00",""));
               
              else detBigMat.nums[i][j].setText(String.format("%.02f",bigMat[i][j]));
                    }
                }
                for (int i = 0; i < mat.length; i++) {//set columns elements as 0 and 1
                    if (i != counter-1) {
                        detBigMat.nums[i][counter-1].setForeground(Color.BLUE);
                }
                }
                detBigMat.nums[counter-1][counter-1].setForeground(Color.red);
                p1bigMat.add(detBigMat);
                outFrame.getContainer().add(p1bigMat);
            }//end while
            
            
            JPanel inv = new JPanel();
                inv.setLayout(new GridLayout(1, 2));
                LargeTextArea text3 = new LargeTextArea("[I  A^(-1)] = ");//print the final inverse
                inv.add(text3);
                DetPanel detInv = new DetPanel(size, size*2);
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat.length * 2; j++) {//doubled length of columns
                     if(bigMat[i][j]==0d || bigMat[i][j]==-0d)  detInv.nums[i][j].setText("0");
             else  if(String.format("%.2f",bigMat[i][j]).endsWith(".00"))
                   detInv.nums[i][j].setText(String.format("%.2f",bigMat[i][j]).replace(".00",""));
               
              else detInv.nums[i][j].setText(String.format("%.02f",bigMat[i][j]));
                }
            }
            inv.add(detInv);
            outFrame.getContainer().add(inv);
            
            JPanel finall=new JPanel();
            finall.setLayout(new GridLayout(1,2));
            LargeTextArea finalText =new LargeTextArea("A^-1 = ");
            finall.add(finalText);
            DetPanel finalMat =new DetPanel(size, size);
            for (int i = 0; i < mat.length; i++) {
                for (int j = size,k=0; j < mat.length * 2; j++,k++) {//doubled length of columns
                    if(bigMat[i][j]==0d || bigMat[i][j]==-0d)  finalMat.nums[i][k].setText("0");
             else  if(String.format("%.2f",bigMat[i][j]).endsWith(".00"))
                   finalMat.nums[i][k].setText(String.format("%.2f",bigMat[i][j]).replace(".00",""));
               
              else finalMat.nums[i][k].setText(String.format("%.02f",bigMat[i][j]));
                }
            }
            finall.add(finalMat);
            outFrame.getContainer().add(finall);
            
            if(!withSteps){
                OFrame noSteps = new OFrame();
                noSteps.getContainer().setLayout(new GridLayout(2, 2, 5, 5));
                LargeTextArea finalresNoSteps = new LargeTextArea("A^-1 = ");
                JPanel finalPanlesNoSteps=new JPanel();
                finalPanlesNoSteps.setLayout(new GridLayout(1,2));
                finalPanlesNoSteps.add(finalresNoSteps);
                DetPanel finalDetNosteps=new DetPanel(size, size);
                for (int i = 0; i < mat.length; i++) {
                for (int j = size,k=0; j < mat.length * 2; j++,k++) {//doubled length of columns
                     if(bigMat[i][j]==0d || bigMat[i][j]==-0d)  finalDetNosteps.nums[i][k].setText("0");
             else  if(String.format("%.2f",bigMat[i][j]).endsWith(".00"))
                   finalDetNosteps.nums[i][k].setText(String.format("%.2f",bigMat[i][j]).replace(".00",""));
               
              else finalDetNosteps.nums[i][k].setText(String.format("%.02f",bigMat[i][j]));
                    }
                }
                finalPanlesNoSteps.add(finalDetNosteps);
                noSteps.getContainer().add(finalPanlesNoSteps);
                noSteps.setVisible(true);
                noSteps.setLocation(outFrame.getLocation());
                outFrame.dispose();
                return noSteps;
            }
        }
        
        return outFrame;
    }
    
     public static boolean addTwoRows(Double a[][],int index){
        Double b[]=new Double[a.length*2];
        Double c[]=new Double[a.length*2];
        for (int i = 0; i < b.length; i++) {
           b[i]=toDouble(String.format("%.2f",a[index][i]));
        }
        boolean flag1=true;
        boolean row=false;
        for (int i = index+1; flag1 && i<a.length; i++) {
            if(a[i][index]!=0d){
                for (int j = 0; j < c.length; j++) {
                    c[j]=toDouble(String.format("%.2f",a[i][j]));
                }
                flag1=false;
                row=true;
                setIndex(i);
            }
        }
       
        if(row){
            
                for (int i = 0; i < c.length; i++) {
                    a[index][i]=toDouble(String.format("%.2f",c[i]+ b[i]));
                }
            
        }
        return row ;
    }
    static int in;
    public static void setIndex(int index){
       in=index;
    }
    public static int getIndex(){
        return in;
    }
    
    
    public static Double[][] setBigMat(Double mat[][]) {
        //to set the identity matrix next to the original one(Double)
        Double bigMat[][] = new Double[mat.length][mat.length * 2];//doubled length of columns
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat.length; j++) {
                bigMat[i][j] = mat[i][j];
            }
        }
        Double iden[][] = new Double[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < iden.length; j++) {
                iden[i][j] = 0d;
            }
        }
        for (int i = 0; i < iden.length; i++) {
            iden[i][i] = 1d;
        }
        for (int i = 0; i < iden.length; i++) {
            for (int j = mat.length, k = 0; j < iden.length * 2; j++, k++) {//doubled length of columns
                bigMat[i][j] = iden[i][k];
            }
        }
        return bigMat;
    }

    

    public static double toDouble(String a) { //to return fractional as decimal
        int c = 1;
        StringBuilder sb = new StringBuilder(a);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '/') {
                if (c > 1) {
                    sb.deleteCharAt(i);
                }
                c++;
            }
        }
        a = sb.toString();
        if (a.contains("/")) {
            String b[] = a.split("/");
            double m = Double.parseDouble(b[0]);
            double n = Double.parseDouble(b[1]);
            a = String.valueOf(m / n);
        }
        return Double.parseDouble(a);
    }

    public static double det(Double mat[][]) {//calculate det by Pivot method 
        int size = mat.length;
        double temp[][] = new double[size - 1][size - 1]; //temporary array
        double pivot[] = new double[size - 2]; //to store the pivots in it
        int pCounter = 0; //to count pivots
        double pMul = 1.0f; //to store the multiplication of the pivots
        int negCounter = 0; //to count number of negative signs

        while (size > 2) { //when size reaches 2, break the loop
            pivot[pCounter] = mat[0][0]; //pivot= the first element in matrix
            int checkCounter = 1; //to check if all rows starts with 0
            while (pivot[pCounter] == 0) { //if the pivot equals 0
                double swap; //to use for swapping
                if (checkCounter < mat.length && mat[checkCounter][0] != 0) {
                    //if checkCounter(number of rows) didn't exceed the size
                    //and the next row doesn't start with 0
                    negCounter++; //increase negative sign counter
                    pivot[pCounter] = mat[checkCounter][0];
                    //pivot =first element of the row after swapping
                    for (int i = 0; i < mat.length; i++) {
                        //to swap the two rows
                        swap = mat[0][i];
                        mat[0][i] = mat[checkCounter][i];
                        mat[checkCounter][i] = swap;
                    }
                }

                if (checkCounter == mat.length) { //if all rows start with 0, det(A)=0
                    return 0;
                }
                checkCounter++; //increase the counter if the row starts with 0

            }//end while
            for (int i = 0; i < temp.length; i++) { //to copy det[] in temp[]
                for (int j = 0; j < temp.length; j++) {
                    temp[i][j] = (pivot[pCounter] * mat[i + 1][j + 1] - (mat[0][j + 1] * mat[i + 1][0])) / pivot[pCounter];
                }
            }
            size--; //decrease matrix size
            mat = new Double[size][size]; //modify matrix size(-1)
            for (int i = 0; i < temp.length; i++) { //copy temp[] to det[]
                for (int j = 0; j < temp.length; j++) {
                    mat[i][j] = temp[i][j];
                }
            }

            temp = new double[size - 1][size - 1]; //modify temp size(-1)
            pCounter++; //increase pivots counter
        }

        //thus, the size = 2
        for (int i = 0; i < pivot.length; i++) {
            if (negCounter > 0) {
                pMul *= -1 * pivot[i]; //calculate the product of the pivots
                //according to the sign counter
                negCounter--; //decrease counter
            } else {
                pMul *= pivot[i];
                //multiply without "-"
            }
        }
        //print the final process part2 (2*2 matrix value)
        double result = (mat[0][0] * mat[1][1] - (mat[0][1] * mat[1][0])); //2*2 matrix value
        //A=[a b; cd] , det=(a*d-b*c)
        result *= (pMul); //multiply 2*2 matrix value by the product of pivots

        return result;
    }//end mthod det 

   

   

    public static Double[][] calcMinor(Double[][] mat) {///to calculate Minor
        Double minor[][] = new Double[0][0];//to store minor in it
        if (mat.length == 2) {
            minor = new Double[2][2];
            minor[0][0] = mat[1][1];
            minor[1][1] = mat[0][0];
            minor[0][1] = -mat[0][1];
            minor[1][0] = -mat[1][0];
        } else if (mat.length == 3) {
            minor = new Double[3][3];
            //row 1
            minor[0][0] = mat[1][1] * mat[2][2] - (mat[1][2] * mat[2][1]);
            minor[0][1] = mat[1][0] * mat[2][2] - (mat[1][2] * mat[2][0]);
            minor[0][2] = mat[1][0] * mat[2][1] - (mat[1][1] * mat[2][0]);

            //row 2
            minor[1][0] = mat[0][1] * mat[2][2] - (mat[0][2] * mat[2][1]);
            minor[1][1] = mat[0][0] * mat[2][2] - (mat[0][2] * mat[2][0]);
            minor[1][2] = mat[0][0] * mat[2][1] - (mat[0][1] * mat[2][0]);

            //row 3
            minor[2][0] = mat[0][1] * mat[1][2] - (mat[0][2] * mat[1][1]);
            minor[2][1] = mat[0][0] * mat[1][2] - (mat[0][2] * mat[1][0]);
            minor[2][2] = mat[0][0] * mat[1][1] - (mat[0][1] * mat[1][0]);
        }
        return minor;//return minor matrix
    }

    public static Double[][] coFactor(Double[][] minor) {//to find Co-Factor matrix
        Double coFactor[][] = new Double[3][3];//to store coFactor in it
        for (int i = 0; i < coFactor.length; i++) {
            for (int j = 0; j < coFactor.length; j++) {
                coFactor[i][j] = (double) Math.pow(-1, i + j) * minor[i][j];
                //signs rule
                /*
                
                + - +
                - + -
                + - +
                
                 */
            }
        }
        return coFactor;//return coFactor matrix
    }

    public static Double[][] adj(Double[][] coFactor) {//calculate adjoint matrix
        Double adj[][] = new Double[3][3];//store adj in it
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj.length; j++) {
                adj[i][j] = coFactor[j][i];
                //adjoint = transposition of coFactor 
            }
        }
        return adj;//return adjoint matrix
    }

    public static String[][] inverse(Double adj[][], double det) {//calculate inv

        String inverse[][] = new String[adj.length][adj.length];//store inv matrix
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj.length; j++) {
                inverse[i][j] = HelpingMethods.toFraction(adj[i][j], det);
                //divide each element by det value
            }

        }
        return inverse;//return final inverse matrix
    }

    public static DetPanel printMatrix(String a[][]) { //to print an array
        DetPanel result = new DetPanel(a.length, a.length);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                result.nums[i][j].setText(a[i][j]);
            }
        }
        return result;
    }

    public static DetPanel printMatrix(Object a[][]) { //to print an array
        DetPanel result = new DetPanel(a.length, a.length);
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                result.nums[i][j].setText(Double.toString((Double) a[i][j]));
            }
        }
        return result;
    }

    private static void setColumns(double mat[][], double c1[], double c2[], double c3[]) {
        for (int i = 0, j = 0; i < c1.length; i++) {
            c1[i] = mat[i][j];
        }
        for (int i = 0, j = 1; i < c2.length; i++) {
            c2[i] = mat[i][j];
        }
        for (int i = 0, j = 2; i < c3.length; i++) {
            c3[i] = mat[i][j];
        }
    }

}

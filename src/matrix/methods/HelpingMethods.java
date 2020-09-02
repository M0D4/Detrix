/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix.methods;

import java.awt.Color;
import matrix.GUIComponents.LargeTextArea;

/**
 * @author Mohamed Elmahdy
 * @author Moustafa Mohamed
 */
public class HelpingMethods {

    static HelpingMethods h = new HelpingMethods();

    public static boolean isEmpty(LargeTextArea[][] input) {
        for (LargeTextArea[] input1 : input) {
            for (int j = 0; j < input.length; j++) {
                if (!"".equals(input1[j].getText())) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isFull(LargeTextArea[][] input) {
        for (LargeTextArea[] input1 : input) {
            for (int j = 0; j < input.length; j++) {
                if ("".equals(input1[j].getText())) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void toBlack(LargeTextArea[][] input) {
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {
                input[i][j].setForeground(Color.BLACK);

            }

        }
    }

    public static double getDet(double mat[][]) { //to find the determinant
        double result;
        //find the results through row 0 only

        double res1 = mat[0][0] * (mat[1][1] * mat[2][2] - (mat[1][2] * mat[2][1]));//element0 in row0
        double res2 = -mat[0][1] * (mat[1][0] * mat[2][2] - (mat[1][2] * mat[2][0]));//element1 in row0
        double res3 = mat[0][2] * (mat[1][0] * mat[2][1] - (mat[1][1] * mat[2][0]));//element2 in row0
        result = res1 + res2 + res3; //sum of all results
        return (double) (result);
    }

    //to make cross product for two vectors
    public static double[] crossProduct(double a[], double b[]) {
        double res[] = new double[3];
        res[0] = a[1] * b[2] - (a[2] * b[1]);
        res[1] = -(a[0] * b[2] - (a[2] * b[0]));
        res[2] = a[0] * b[1] - (a[1] * b[0]);

        return res; //to return the result vector
    }

    public  String toFractionMul(String a, String b) {

        if (a.contains("/")) {
        } else {
            a += "/1";
        }
        if (b.contains("/")) {
        } else {
            b += "/1";
        }
        equation(a + "/" + b);
        return mul();
    }

    public String toFractionSum(String a, String b) {
        if (a.contains("/")) {
        } else {
            a += "/1";
        }
        if (b.contains("/")) {
        } else {
            b += "/1";
        }
        equation(a + "/" + b);
        return sum();
    }

    public  String toFractionSub(String a, String b) {
        if (a.contains("/")) {
        } else {
            a += "/1";
        }
        if (b.contains("/")) {
        } else {
            b += "/1";
        }
        equation(a + "/" + b);
        return sub();
    }

    public String toFraction(String a, String b) {
        if (a.contains("/")) {
        } else {
            a += "/1.0";
        }
        if (b.contains("/")) {
        } else {
            b += "/1.0";
        }
        equation(a + "/" + b);
        return div();
    }

    public String toFractionSub(double a, double b) {//to return a÷b as a/b irreducible
        String sub = "";
        String frac1 = "";
        String frac2 = "";
        boolean b1 = (int) a != a && (int) b == b;
        boolean b2 = (int) a == a && (int) b != b;
        boolean b3 = (int) a != a && (int) b != b;
        if (b1 || b2 || b3) {
            if (b1) {
                String s = String.valueOf(a);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                System.out.println("digitsDec sub= " + digitsDec);
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    a *= 10;
                    denom *= 10;
                }

                int num = (int) Math.round(a);
                frac1 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac2 = String.valueOf((int) b) + "/1";
            } else if (b2) {
                String s = String.valueOf(b);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                System.out.println("digitsDec sub= " + digitsDec);
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    b *= 10;
                    denom *= 10;
                }

                int num = (int)  Math.round(b);
                frac2 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac1 = String.valueOf((int) a) + "/1";
            } else if (b3) {
                String s1 = String.valueOf(a);
                String s2 = String.valueOf(b);
                int digitsDec1 = s1.length() - 1 - s1.indexOf('.');
                int digitsDec2 = s2.length() - 1 - s2.indexOf('.');
                System.out.println("digitsDec1 sub= " + digitsDec1);
                System.out.println("digitsDec2 sub= " + digitsDec2);
                int denom1 = 1;
                int denom2 = 1;
                for (int i = 0; i < digitsDec1; i++) {
                    a *= 10;
                    denom1 *= 10;
                }
                for (int i = 0; i < digitsDec2; i++) {
                    b *= 10;
                    denom2 *= 10;
                }

                int num1 = (int)  Math.round(a);
                int num2 = (int)  Math.round(b);
                frac1 = String.valueOf(num1) + "/" + String.valueOf(denom1);
                frac2 = String.valueOf(num2) + "/" + String.valueOf(denom2);
            }
            equation(frac1 + "/" + frac2);
            sub = sub();
            return sub;
        } else {
            sub = String.valueOf(a - b);
        }
        return sub; //return division result
    }

    public String toFractionSum(double a, double b) {//to return a÷b as a/b irreducible
        String sum = "";
        String frac1 = "";
        String frac2 = "";
        boolean b1 = (int) a != a && (int) b == b;
        boolean b2 = (int) a == a && (int) b != b;
        boolean b3 = (int) a != a && (int) b != b;
        if (b1 || b2 || b3) {
            if (b1) {
                String s = String.valueOf(a);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    a *= 10;
                    denom *= 10;
                }

                int num = (int)  Math.round(a);
                frac1 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac2 = String.valueOf((int) b) + "/1";
            } else if (b2) {
                String s = String.valueOf(b);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    b *= 10;
                    denom *= 10;
                }

                int num = (int)  Math.round(b);
                frac2 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac1 = String.valueOf((int) a) + "/1";
            } else if (b3) {
                String s1 = String.valueOf(a);
                String s2 = String.valueOf(b);
                int digitsDec1 = s1.length() - 1 - s1.indexOf('.');
                int digitsDec2 = s2.length() - 1 - s2.indexOf('.');
                int denom1 = 1;
                int denom2 = 1;
                for (int i = 0; i < digitsDec1; i++) {
                    a *= 10;
                    denom1 *= 10;
                }
                for (int i = 0; i < digitsDec2; i++) {
                    b *= 10;
                    denom2 *= 10;
                }

                int num1 = (int)  Math.round(a);
                int num2 = (int)  Math.round(b);
                frac1 = String.valueOf(num1) + "/" + String.valueOf(denom1);
                frac2 = String.valueOf(num2) + "/" + String.valueOf(denom2);
            }
            equation(frac1 + "/" + frac2);
            sum = sum();
            return sum;
        } else {
            sum = String.valueOf(a + b);
        }
        return sum; //return division result
    }

    public String toFractionMul(double a, double b) {//to return a÷b as a/b irreducible
        String mul = "";
        String frac1 = "";
        String frac2 = "";
        boolean b1 = (int) a != a && (int) b == b;
        boolean b2 = (int) a == a && (int) b != b;
        boolean b3 = (int) a != a && (int) b != b;
        if (b1 || b2 || b3) {
            if (b1) {
                String s = String.valueOf(a);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                System.out.println("digitsDec = " + digitsDec);
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    a *= 10;
                    denom *= 10;
                }

                int num = (int)  Math.round(a);
                frac1 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac2 = String.valueOf((int) b) + "/1";
            } else if (b2) {
                String s = String.valueOf(b);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                System.out.println("digitsDec = " + digitsDec);
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    b *= 10;
                    denom *= 10;
                }

                int num = (int)  Math.round(b);
                frac2 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac1 = String.valueOf((int) a) + "/1";
            } else if (b3) {
                String s1 = String.valueOf(a);
                String s2 = String.valueOf(b);
                int digitsDec1 = s1.length() - 1 - s1.indexOf('.');
                System.out.println("digitsDec1 = " + digitsDec1);
                int digitsDec2 = s2.length() - 1 - s2.indexOf('.');
                System.out.println("digitsDec2 = " + digitsDec2);
                int denom1 = 1;
                int denom2 = 1;
                for (int i = 0; i < digitsDec1; i++) {
                    a *= 10;
                    denom1 *= 10;
                }
                for (int i = 0; i < digitsDec2; i++) {
                    b *= 10;
                    denom2 *= 10;
                }

                int num1 = (int)  Math.round(a);
                int num2 = (int)  Math.round(b);
                frac1 = String.valueOf(num1) + "/" + String.valueOf(denom1);
                frac2 = String.valueOf(num2) + "/" + String.valueOf(denom2);
            }
            equation(frac1 + "/" + frac2);
            mul = mul();
            return mul;
        } else {

            if (a > 0 && b < 0) { //to make the negative sign in the top
                a = -a;
                b = -b;
            } else if (a < 0 && b < 0) { //to remove the negative sign
                a = -a;
                b = -b;
            }

            if ((int) a == a) {
                mul += (int) a + "/";
            } else {
                mul += a + "/";
            }
            if ((int) b == b) {
                mul += (int) b;
            } else {
                mul += b;
            }

            mul = String.valueOf(a * b);
        }

        return mul; //return division result
    }

    public static String toFraction(double a, double b) {//to return a÷b as a/b irreducible
        String div = "";
        String frac1 = "";
        String frac2 = "";
        boolean b1 = (int) a != a && (int) b == b;
        boolean b2 = (int) a == a && (int) b != b;
        boolean b3 = (int) a != a && (int) b != b;
        if (b1 || b2 || b3) {
            if (b1) {
                String s = String.valueOf(a);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    a *= 10;
                    denom *= 10;
                }

                int num = (int)  Math.round(a);
                frac1 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac2 = String.valueOf((int) b) + "/1";
            } else if (b2) {
                String s = String.valueOf(b);
                int digitsDec = s.length() - 1 - s.indexOf('.');
                int denom = 1;
                for (int i = 0; i < digitsDec; i++) {
                    b *= 10;
                    denom *= 10;
                }

                int num = (int)  Math.round(b);
                frac2 = String.valueOf(num) + "/" + String.valueOf(denom);
                frac1 = String.valueOf((int) a) + "/1";
            } else if (b3) {
                String s1 = String.valueOf(a);
                String s2 = String.valueOf(b);
                int digitsDec1 = s1.length() - 1 - s1.indexOf('.');
                int digitsDec2 = s2.length() - 1 - s2.indexOf('.');
                int denom1 = 1;
                int denom2 = 1;
                for (int i = 0; i < digitsDec1; i++) {
                    a *= 10;
                    denom1 *= 10;
                }
                for (int i = 0; i < digitsDec2; i++) {
                    b *= 10;
                    denom2 *= 10;
                }

                int num1 = (int)  Math.round(a);
                int num2 = (int)  Math.round(b);
                frac1 = String.valueOf(num1) + "/" + String.valueOf(denom1);
                frac2 = String.valueOf(num2) + "/" + String.valueOf(denom2);
            }
            h.equation(frac1 + "/" + frac2);
            div = h.div();
            return div;
        } else {
            int min = (int) Math.min(Math.abs(a), Math.abs(b));
            if (a != 0 && b != 0) {
                for (int i = min; i > 0; i--) {
                    if (Math.abs(a) % i == 0 && Math.abs(b) % i == 0) {
                        a /= i;
                        b /= i;
                        break;
                    }
                }

            }

            if (a > 0 && b < 0) { //to make the negative sign in the top
                a = -a;
                b = -b;
            } else if (a < 0 && b < 0) { //to remove the negative sign
                a = -a;
                b = -b;
            }

            if ((int) a == a) {
                div += (int) a + "/";
            } else {
                div += a + "/";
            }
            if ((int) b == b) {
                div += (int) b;
            } else {
                div += b;
            }
            if (a % b == 0) {
                div = "" + (int) (a / b); //to return integer value
            }
        }
        return div; //return division result
    }
    double[] numerator, denominator;
    String[] arr;

    private void equation(String n) { // 4/5/9/7/2/3
        arr = n.split("/"); //4 //5 //9 //7 //2 //3
        numerator = new double[arr.length / 2];
        denominator = new double[arr.length / 2];
        for (int i = 0, j = i + 1, c = 0; c < numerator.length; c++) {
            numerator[c] = Double.parseDouble(arr[i]); //4 //9 //2
            denominator[c] = Double.parseDouble(arr[j]);  //5 //7 //3
            double min = Math.abs(Math.min(numerator[c], denominator[c])); //4
            if (numerator[c] != 0 && denominator[c] != 0) {
                for (; min > 0; min--) {
                    if (numerator[c] % min == 0 && denominator[c] % min == 0) {
                        break;
                    }
                }
                numerator[c] /= min; // 4
                denominator[c] /= min; //5
            }
            i = i + 2;
            j = j + 2;
        }
    }

    private String div() {
        double a = 1;
        double b = 1;
        for (int i = 0, j = 1; i < numerator.length && j < denominator.length; i++, j++) {
            a = numerator[i] * denominator[j];
            b = denominator[i] * numerator[j];
            numerator[j] = a;
            denominator[j] = b;
        }

        double i = Math.abs(Math.min(a, b));
        if (a != 0 && b != 0) {
            for (; i > 0; i--) {
                if (a % i == 0 && b % i == 0) {
                    break;
                }
            }
            a /= i;
            b /= i;
        }
        if (a > 0 && b < 0) { //to make the negative sign in the top
            a = -a;
            b = -b;
        } else if (a < 0 && b < 0) { //to remove the negative sign
            a = -a;
            b = -b;
        }
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        return a + "/" + b;
    }

    public String mul() {
        double a = numerator[0];
        double b = denominator[0];
        for (int i = 1; i < numerator.length; i++) {
            a *= numerator[i];
            b *= denominator[i];
        }
        double i = Math.abs(Math.min(a, b));
        if (a != 0 && b != 0) {
            for (; i > 0; i--) {
                if (a % i == 0 && b % i == 0) {
                    break;
                }
            }
            a /= i;
            b /= i;
        }
        if (a > 0 && b < 0) { //to make the negative sign in the top
            a = -a;
            b = -b;
        } else if (a < 0 && b < 0) { //to remove the negative sign
            a = -a;
            b = -b;
        }
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        return a + "/" + b;
    }

    private String sum() {
        double a = 0, b = 0;
        for (int i = 0, j = 1; i < arr.length / 2 && j < arr.length / 2; i++, j++) {
            a = ((denominator[j] * numerator[i]) + (numerator[j] * denominator[i]));//73 //289
            b = denominator[i] * denominator[j];//35 //105

            numerator[j] = a;//num[1]=73 //num[2]=289
            denominator[j] = b;//deno[1]=35 //deno[2]=105
        }
        double i = Math.abs(Math.min(a, b));
        if (a != 0 && b != 0) {
            for (; i > 0; i--) {
                if (a % i == 0 && b % i == 0) {
                    break;
                }
            }
            a /= i;
            b /= i;
        }
        if (a > 0 && b < 0) { //to make the negative sign in the top
            a = -a;
            b = -b;
        } else if (a < 0 && b < 0) { //to remove the negative sign
            a = -a;
            b = -b;
        }
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        return a + "/" + b;
    }

    private String sub() {
        double a = 0, b = 0;
        for (int i = 0, j = 1; i < arr.length / 2 && j < arr.length / 2; i++, j++) {
            a = ((denominator[j] * numerator[i]) - (numerator[j] * denominator[i]));//73 //289
            b = denominator[i] * denominator[j];//35 //105
            numerator[j] = a;//num[1]=73 //num[2]=289
            denominator[j] = b;//deno[1]=35 //deno[2]=105
        }
        double i = Math.abs(Math.min(a, b));
        if (a != 0 && b != 0) {
            for (; i > 0; i--) {
                if (a % i == 0 && b % i == 0) {
                    break;
                }
            }
            a /= i;
            b /= i;
        }
        if (a > 0 && b < 0) { //to make the negative sign in the top
            a = -a;
            b = -b;
        } else if (a < 0 && b < 0) { //to remove the negative sign
            a = -a;
            b = -b;
        }
        if (a % b == 0) {
            return String.valueOf(a / b);
        }
        return a + "/" + b;
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

}

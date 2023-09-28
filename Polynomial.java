import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Polynomial{
    double [] coefficients;
    int [] exponents;

    public Polynomial(){
        // System.out.println("hello");
        coefficients = null;
        exponents = null;
    }

    public Polynomial(double [] coefficients, int [] exponents){
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    public Polynomial(File file){
        try{
            Scanner input = new Scanner(file);
            String data = input.nextLine();
            String [] c = data.split("(?=-|\\+)");

            coefficients = new double[c.length];
            exponents = new int[c.length];

            for (int i = 0; i < c.length; i++){
                if (c[i].indexOf("x") != -1){
                    try{
                        coefficients[i] = Double.parseDouble(c[i].substring(0, c[i].indexOf("x")));
                    }catch(Exception e){
                        coefficients[i] = 1;
                    }
                    if (c[i].indexOf("x") == c[i].length() -1) exponents[i] = 1;
                    else exponents[i] = Integer.parseInt(c[i].substring(c[i].indexOf("x") + 1, c[i].length()));
                }
                else{
                    if (c[i].indexOf("x") == 0) coefficients[i] = 1;
                    else coefficients[i] = Double.parseDouble(c[i]);
                    exponents[i] = 0;
                }
            }

            input.close();
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            coefficients = null;
            exponents = null;
        }
    }

    public void print(){
        System.out.println(Arrays.toString(coefficients));
        System.out.println(Arrays.toString(exponents));
    }

    public int maxExp(int [] e){
        if (e.length == 0) return 0;
        int max = e[0];
        for(int i = 0; i < e.length; i++)
        {
            if (e[i] > max) max = e[i];
        }
        return max;
    }

    public Polynomial add(Polynomial p){

        // Lab 1 Code
        // int p_length = p.coefficients.length;
        // int o_length = coefficients.length;
        // int max_length = Math.max(p_length, o_length);
        // double [] new_coefficients = new double[max_length];
        // for (int i = 0; i < max_length; i++){
        //     if (p_length >= i + 1 && o_length >= i + 1){
        //         new_coefficients[i] = p.coefficients[i] + coefficients[i];
        //     }
        //     else if (p_length >= i + 1){
        //         new_coefficients[i] = p.coefficients[i];
        //     }else{
        //         new_coefficients[i] = coefficients[i];
        //     }
        // }
        // return new Polynomial(new_coefficients);

        if (exponents == null) return p;
        if (p.exponents == null) return this;

        double [] coeff = new double[Math.max(maxExp(exponents), maxExp(p.exponents)) + 1];
        int [] exp = new int[Math.max(maxExp(exponents), maxExp(p.exponents)) + 1];

        for (int i = 0; i < exp.length; i++){
            exp[i] = i;
            coeff[i] = 0;
        }

        for (int i = 0; i < coefficients.length; i++){
            coeff[exponents[i]] += coefficients[i];
        }

        for (int i = 0; i < p.coefficients.length; i++){
            coeff[p.exponents[i]] += p.coefficients[i];
        }

        int new_length = 0;
        for (int i = 0; i < coeff.length; i++){
            if (coeff[i] != 0) new_length++;
        }

        double [] new_coefficients = new double[new_length];
        int [] new_exponents = new int[new_length];
        int counter = 0;
        for (int i = 0; i < exp.length; i++){
            if (coeff[i] != 0){
                new_coefficients[counter] = coeff[i];
                new_exponents[counter] = exp[i];
                counter++;
            }
        }

        if (new_coefficients.length == 0){
            return new Polynomial();
        }
        
        // System.out.println(Arrays.toString(new_coefficients));
        // System.out.println(Arrays.toString(new_exponents));
        return new Polynomial(new_coefficients, new_exponents);

    }

    public double evaluate(double x){
        // do we return null in this case?
        if (exponents == null) return 0;

        double total = 0;
        for (int i = 0; i < coefficients.length; i++){
            total += coefficients[i] * Math.pow(x, exponents[i]);
        }
        return total;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }

    public Polynomial multiply(Polynomial p){
        if (exponents == null || p.exponents == null){
            return new Polynomial();
        }
        Polynomial newPolynomial = new Polynomial(new double[] {0}, new int[] {0});
        for (int i = 0; i < exponents.length; i++){
            for (int j = 0; j < p.exponents.length; j++){
                Polynomial temp = new Polynomial(new double [] {coefficients[i] * p.coefficients[j]}, new int[] {exponents[i] + p.exponents[j]});
                newPolynomial = newPolynomial.add(temp);
            }
        }
        return newPolynomial;
    }

    public void saveToFile(String fileName){
        try {
            // File file = new File(fileName); // what is filename
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < exponents.length; i++){
                if (coefficients[i] != 1 && coefficients[i] != -1){
                    if (coefficients[i] > 0 && i != 0) writer.write("+" + Double.toString(coefficients[i]));
                    else writer.write(Double.toString(coefficients[i]));
                }
                else if (coefficients[i] == -1) writer.write("-");

                if (exponents[i] > 0){
                    writer.write("x" + Integer.toString(exponents[i]));
                }
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }

    }


}
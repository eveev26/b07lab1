public class Polynomial{
    double [] coefficients;

    public Polynomial(){
        // System.out.println("hello");
        coefficients = new double[] {0};
    }

    public Polynomial(double [] coefficients){
        this.coefficients = coefficients;
    }

    public Polynomial add(Polynomial p){
        int p_length = p.coefficients.length;
        int o_length = coefficients.length;
        int max_length = Math.max(p_length, o_length);
        double [] new_coefficients = new double[max_length];
        for (int i = 0; i < max_length; i++){
            if (p_length >= i + 1 && o_length >= i + 1){
                new_coefficients[i] = p.coefficients[i] + coefficients[i];
            }
            else if (p_length >= i + 1){
                new_coefficients[i] = p.coefficients[i];
            }else{
                new_coefficients[i] = coefficients[i];
            }
        }
        return new Polynomial(new_coefficients);
    }

    public double evaluate(double x){
        double total = 0;
        for (int i = 0; i < coefficients.length; i++){
            total += coefficients[i] * Math.pow(x, i);
        }
        return total;
    }

    public boolean hasRoot(double x){
        return evaluate(x) == 0;
    }
}
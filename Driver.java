import java.io.File;
public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));
        double [] c1 = {6,9,-3,5};
        Polynomial p1 = new Polynomial(c1, new int[] {2,3,7, 0});
        double [] c2 = {6,9,-3,5};
        Polynomial p2 = new Polynomial(c2, new int[] {2,3,7, 0});
        // double [] c2 = {1,-2,-6,2,-9};
        // Polynomial p2 = new Polynomial(c2, new int[] {5,3,2,7,4});
        // System.out.println(maxExp(p1.exponents));
        Polynomial s = p1.add(p2);
        System.out.println("s(1) = " + s.evaluate(-2));
        Polynomial a = p1.multiply(p2);
        // File n = new File("test.txt");
        // Polynomial b = new Polynomial(n);
        // b.print();
        s.print();
        s.saveToFile("test.txt");

        // if(s.hasRoot(1))
        //     System.out.println("1 is a root of s");
        // else
        //     System.out.println("1 is not a root of s");
    }
}
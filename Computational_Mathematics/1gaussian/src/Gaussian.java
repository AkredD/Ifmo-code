import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;


/**
 * Created by user on 25.04.2018.
 */


public class Gaussian {
    private static Gaussian instance;
    private Matrix m1;
    private Matrix m2;

    private Gaussian(){};

    public static Gaussian getInstance(){
        if (instance == null){
            instance = new Gaussian();
        }
        return instance;
    }

    private Pair<Integer, Integer> div(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        int x = a.getKey() * b.getValue();
        int y = a.getValue() * b.getKey();
        int c = gcd(x, y);
        return new Pair<>(x / c, y / c);
    }

    private Pair<Integer, Integer> mul(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        int x = a.getKey() * b.getKey();
        int y = a.getValue() * b.getValue();
        int c = gcd(x, y);
        return new Pair<>(x / c, y / c);
    }

    private Pair<Integer, Integer> sub(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        int x = a.getKey() * b.getValue() - b.getKey() * a.getValue();
        int y = a.getValue() * b.getValue();
        int c = gcd(x, y);
        return new Pair<>(x / c, y / c);
    }

    private Pair<Integer, Integer> add(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        int x = a.getKey() * b.getValue() + b.getKey() * a.getValue();
        int y = a.getValue() * b.getValue();
        int c = gcd(x, y);
        return new Pair<>(x / c, y / c);
    }

    private int gcd(int a,int b) {
        while (b !=0) {
            int tmp = a%b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private Boolean compare(Pair<Integer, Integer> a, Pair<Integer, Integer> b){
        double aD = ( a.getKey().doubleValue()) / (a.getValue().doubleValue());
        double bD = (b.getKey().doubleValue()) / (b.getValue().doubleValue());
        return aD > bD;
    }

    public Pair<Integer, Integer> determinant(Matrix m, int n){
        Pair<Integer, Integer> res = new Pair<>(0, 1);
        if (n == 2){
            return sub(mul(m.get(0,0), m.get(1,1)), mul(m.get(0,1), m.get(1,0)));
        }
        for (int i = 0; i < n; ++i){
            Matrix loc = new Matrix(n-1, n-1);
            for (int j = 1; j < n; ++j){
                int k = 0;
                for (int c = 0; c < n; ++c){
                    if (c != i){
                        loc.set(j - 1, k, m.get(j, c));
                        k++;
                    }
                }
            }
            if (i%2 == 0){
                res = add(res, mul(m.get(0, i), determinant(loc, n-1)));
            }else{
                res = sub(res, mul(m.get(0, i), determinant(loc, n-1)));
            }
        }
        return res;
    }

    private void findMax(int k, int n){
        Pair<Integer, Integer> max = new Pair(Math.abs(m1.get(k, k).getKey()), Math.abs(m1.get(k, k).getValue()));
        int maxIndex = k;
        for (int i = k + 1; i < n; ++i){
            if (compare(new Pair(Math.abs(m1.get(i, k).getKey()), Math.abs(m1.get(i, k).getValue())), max)){
                max = new Pair(Math.abs(m1.get(i, k).getKey()), Math.abs(m1.get(i, k).getValue()));
                maxIndex = i;
            }
        }
        if (maxIndex != 0) {
            m1.swap(maxIndex, k);
            m2.swap(maxIndex, k);
        }
    }

    public ArrayList<Pair<Integer, Integer>> evaluate(Matrix m1L, Matrix m2L, int n){
        Pair<Integer, Integer>  det = determinant(m1L, n);
        System.out.print(det.getKey());
        System.out.print('\\');
        System.out.print(det.getValue());
        System.out.print('\n');
        ArrayList <Pair<Integer, Integer>> res = new ArrayList<>();
        this.m1 = m1L;
        this.m2 = m2L;
        for (int i = 0; i < n; ++i){
            ArrayList<Pair<Integer, Integer>> multipliers = new ArrayList<>();
            findMax(i, n);
            for (int j = i + 1; j < n; ++j){
                multipliers.add(div(m1.get(j,i), m1.get(i, i)));
            }

            for (int j = i + 1; j < n; ++j){
                for (int k = i; k < n; ++k){
                    m1.set(j, k, sub(m1.get(j, k), mul(m1.get(i, k), multipliers.get(j - i - 1))));
                }
                m2.set(j, 0, sub(m2.get(j, 0), mul(m2.get(i, 0), multipliers.get(j - i - 1))));
            }
        }
        System.out.println("Triangular matrix");
        m1.print();
        System.out.println("Variable column");
        for (int i = 0; i < n; ++i) {
            System.out.println("x" + (i+1));
        }
        System.out.println("Value column");
        m2.print();

        for (int i = n - 1; i >= 0; i--){
            Pair<Integer, Integer> resL = m2.get(i, 0);
            for (int j = i + 1; j < n; ++j){
                resL = sub(resL, m1.get(i, j));
            }
            resL = div(resL, m1.get(i, i));
            for (int j = 0; j <= i; ++j){
                m1.set(j, i, mul(m1.get(j, i), resL));
            }
            res.add(resL);
        }
        Collections.reverse(res);
        System.out.println("Errors");
        printError(res);
        return res;
    }

    private void printError(ArrayList <Pair<Integer, Integer>> res){
        ArrayList<Double> errors = new ArrayList<>();
        for (int i = 0; i < res.size(); ++i){
            double error = 0;
            for (int j = i; j < res.size(); ++j){
                error  += (double) m1.get(i, j).getKey() / (double) m1.get(i,j).getValue();
            }
            error -= m2.get(i, 0).getKey() / m2.get(i, 0).getValue();
            System.out.print(error);
            System.out.print(" ");
        }
        System.out.println();

    }

}

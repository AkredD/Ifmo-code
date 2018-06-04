package matrix;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;


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

    private Double div(Double a, Double b){
        return a / b;
    }

    private Double mul(Double a, Double b){
        return a * b;
    }

    private Double sub(Double a, Double b){
        return a - b;
    }

    private Double add(Double a, Double b){
        return a + b;
    }


    public Double determinant(Matrix m, int n){
        Double res = 0.0;
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
        Double max = Math.abs(m1.get(k, k));
        int maxIndex = k;
        for (int i = k + 1; i < n; ++i){
            if (Math.abs(m1.get(i, k)) >  max){
                max = Math.abs(m1.get(i, k));
                maxIndex = i;
            }
        }
        if (maxIndex != 0) {
            m1.swap(maxIndex, k);
            m2.swap(maxIndex, k);
        }
    }

    public ArrayList<Double> evaluate(Matrix m1L, Matrix m2L, int n){
        Double  det = determinant(m1L, n);
        ArrayList <Double> res = new ArrayList<>();
        this.m1 = m1L;
        this.m2 = m2L;
        for (int i = 0; i < n; ++i){
            ArrayList<Double> multipliers = new ArrayList<>();
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
        for (int i = n - 1; i >= 0; i--){
            Double resL = m2.get(i, 0);
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
        return res;
    }

    private void printError(ArrayList <Double> res){
        ArrayList<Double> errors = new ArrayList<>();
        for (int i = 0; i < res.size(); ++i){
            double error = 0;
            for (int j = i; j < res.size(); ++j){
                error  += (double) m1.get(i, j) / (double) m1.get(i,j);
            }
            error -= m2.get(i, 0) / m2.get(i, 0);
            System.out.print(error);
            System.out.print(" ");
        }
        System.out.println();

    }

}

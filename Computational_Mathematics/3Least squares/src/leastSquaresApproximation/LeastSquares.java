package leastSquaresApproximation;

import function.Function;
import function.operators.binary.Multiplication;
import javafx.util.Pair;
import matrix.Gaussian;
import matrix.Matrix;
import util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class LeastSquares {

    public static ArrayList<Double> evaluate(Function function, int n, ArrayList<Pair<Integer, Integer>> dots){
        ArrayList<Double> result;
        ArrayList<Function> functions = new ArrayList<>();
        for (Integer i = 0; i < n; ++i){
            functions.add(Util.findParameterFunction(i.toString(), function));
        }
        Matrix m1 = new Matrix(n, n);
        Matrix m2 = new Matrix(n, 1);
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                Function<Double> local = new Multiplication(functions.get(i), functions.get(j));
                double value = 0;
                for (int c = 0; c < dots.size(); ++c){
                    value += local.evaluate(dots.get(c).getKey().doubleValue());
                }
                m1.set(i, j, value);
            }
            double value = 0;
            for (int c = 0; c < dots.size(); ++ c){
                value += dots.get(c).getValue().doubleValue() * functions.get(i).evaluate((Number) dots.get(c).getKey()).doubleValue();
            }
            m2.set(i, 0, value);
        }
        result = Gaussian.getInstance().evaluate(m1, m2, n);
        Util.setResult(result);
        return result;
    }
}

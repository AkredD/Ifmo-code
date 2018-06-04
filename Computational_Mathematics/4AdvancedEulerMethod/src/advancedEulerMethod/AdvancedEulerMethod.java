package advancedEulerMethod;

import function.Function;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class AdvancedEulerMethod {
    public static ArrayList<Pair<Double, Double>> evaluate(Function function, Double x0, Double y0, Double x, Double error){
        ArrayList<Pair<Double, Double>> result = new ArrayList<>();
        Double h = (x - x0) * error;
        Double xI = x0;
        Double yI = y0;
        while (xI < x){
            result.add(new Pair<>(xI + h, yI + h * (Double) function.evaluate(xI + h/2,
                    yI + h/2 * (Double) function.evaluate(xI, yI))));
            yI = result.get(result.size()-1).getValue();
            xI += h;
        }
        return result;
    }
}

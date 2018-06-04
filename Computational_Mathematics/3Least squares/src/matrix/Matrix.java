package matrix;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * Created by user on 25.04.2018.
 */


public class Matrix {
    private ArrayList<ArrayList<Double>> a;


    public Matrix(int a, int b){
        this.a = new ArrayList<>();
        for (int i = 0; i < a; ++i){
            this.a.add(new ArrayList<>());
            for (int j = 0; j < b; ++j){
                this.a.get(i).add(0.0);
            }
        }
    }


    public Double get(int x, int y){
        return a.get(x).get(y);
    }

    public void set(int x, int y, Double a){
        this.a.get(x).set(y, a);
    }


    public void swap(int y1, int y2){
        ArrayList <Double> b = a.get(y1);
        a.set(y1, a.get(y2));
        a.set(y2, b);
    }

}

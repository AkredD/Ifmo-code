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
    private ArrayList<ArrayList<Pair<Integer, Integer>>> a;


    public Matrix(int a, int b){
        this.a = new ArrayList<>();
        for (int i = 0; i < a; ++i){
            this.a.add(new ArrayList<>());
            for (int j = 0; j < b; ++j){
                this.a.get(i).add(new Pair<>(0, 0));
            }
        }
    }

    public Pair<Integer, Integer> get(int x, int y){
        return a.get(x).get(y);
    }

    public void set(int x, int y, Pair<Integer, Integer> a){
        this.a.get(x).set(y, a);
    }

    public void print(){
        for (int i = 0; i < a.size(); ++i){
            for (int j = 0; j < a.get(0).size(); ++j){
                String s;
                if (a.get(i).get(j).getKey() == 0){
                    s = "0 ";
                }else {
                    if (a.get(i).get(j).getValue() == 1){
                        s = a.get(i).get(j).getKey().toString() + " ";
                    }else{
                        s =a.get(i).get(j).getKey().toString() + '/' +a.get(i).get(j).getValue().toString() + " ";
                    }
                }
                System.out.print(s);
            }
            System.out.print('\n');
        }
    }

    public void swap(int y1, int y2){
        ArrayList <Pair<Integer, Integer>> b = a.get(y1);
        a.set(y1, a.get(y2));
        a.set(y2, b);
    }

}

package matrix;
import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


/**
 * Created by user on 26.04.2018.
 */


public class Fraction <A extends Number, B extends  Number> extends javafx.util.Pair{
    public final A _1;
    public final B _2;

    public Fraction(A key, B value){
        super(key, value);
        _1 = key;
        _2 = value;
    }

    @Override
    public String toString(){
        return _1.toString() + '/' + _2.toString();
    }

    public Boolean compareTo(Fraction b){
        return (this._1.doubleValue() / this._2.doubleValue()) > (b._1.doubleValue() / b._2.doubleValue());
    }
}

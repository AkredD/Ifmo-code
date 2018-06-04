package util;

import function.Function;
import function.operands.Variable;
import function.operators.binary.BinaryOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Util {
    public static ArrayList<Double> getResult() {
        return result;
    }

    public static void setResult(ArrayList<Double> res) {
        result = res;
    }

    private static ArrayList<Double> result;

    public static Function findParameterFunction(String name, Function function){

        if (function.getClass().getSuperclass().equals(BinaryOperator.class)){
            BinaryOperator a = (BinaryOperator) function;
            Function left = a.getLeft();
            Function right = a.getRight();
            if (left.getClass().isAssignableFrom(Variable.class)){
                Variable variable = (Variable) left;
                return (variable.getName().equals(name)) ? right : null;
            }
            if (right.getClass().isAssignableFrom(Variable.class)){
                Variable variable = (Variable) right;
                return (variable.getName().equals(name)) ? left : null;
            }
            left = findParameterFunction(name, left);
            right = findParameterFunction(name, right);
            if (left == null){
                return right;
            }else{
                return left;
            }
        }else{
            return null;
        }
    }

}

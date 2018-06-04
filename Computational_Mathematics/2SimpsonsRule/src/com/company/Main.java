package com.company;

import function.Function;
import function.operands.Value;
import function.operands.Variable;
import function.operators.*;
import javafx.util.Pair;
import simpsonsRule.SimpsonRule;

import java.io.Console;
import java.util.function.BinaryOperator;

public class Main{

    public static void main(String[] args) {
	// write your code here
        java.util.Scanner sc = new java.util.Scanner(System.in);
        while(true) {
            Function<Double> choosen;
            System.out.println("Choose function");
            System.out.println("1 - f(x) = sqrt(1 + 2 * x^2 - x^3)");
            Function<Double> a = new Exponentiation(new Summary<Double>(
                    new Value(1), new Subtraction(new Multiplication(new Value(2), new Exponentiation(new Variable("x"), new Value(2))),
                    new Exponentiation(new Variable("x"), new Value(3)))), new Value(0.5));
            System.out.println("2 - f(x) = 1 / x");
            Function<Double> b = new Division<>(new Value(1), new Variable("x"));
            System.out.println("3 - f(x) = 1 / sqrt(3 + x^5)");
            Function<Double> c = new Division<>(new Value(1), new Exponentiation(
                    new Summary(new Value(3), new Exponentiation(new Variable("5"), new Value(5))), new Value(0.5)));

            System.out.println("4 - f(x) = 2x");
            Function<Double> d = new Multiplication(new Value(2), new Variable<>("x"));

            System.out.println("5 - f(x) = x^3 + x^2");
            Function<Double> e = new Summary<>(new Exponentiation(new Variable("x"), new Value(3)),
                    new Exponentiation(new Variable("x"), new Value(2)));

            int n = sc.nextInt();
            switch (n) {
                case 1: {
                    choosen = a;
                    break;
                }
                case 2: {
                    choosen = b;
                    break;
                }
                case 3: {
                    choosen = c;
                    break;
                }
                case 4: {
                    choosen = d;
                    break;
                }
                case 5: {
                    choosen = e;
                    break;
                }
                default:
                    choosen = null;
                    break;
            }
            if (choosen == null) continue;
            System.out.println("Write period");
            System.out.print("Left: ");
            Double left = sc.nextDouble();
            System.out.print("Right: ");
            Double right = sc.nextDouble();
            SimpsonRule simpsonRule = new SimpsonRule(choosen, left, right);
            System.out.print("Write error");
            Double error = sc.nextDouble();
            simpsonRule.setError(error);
            System.out.print("Number of operations: ");
            try {
                Pair<Double, Double> result = simpsonRule.Evaluate(2);
                System.out.print("Final error: ");
                System.out.println(result.getValue());
                System.out.print("Result: ");
                System.out.println(result.getKey());
            }catch (Exception t){
                System.out.println("infinity");
                System.out.println(t.getMessage());
            }
        }
    }
}
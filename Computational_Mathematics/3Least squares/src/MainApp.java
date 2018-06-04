import function.Function;
import function.operands.Value;
import function.operands.Variable;
import function.operators.binary.Exponentiation;
import function.operators.binary.Multiplication;
import function.operators.binary.Summary;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import leastSquaresApproximation.LeastSquares;
import util.Util;
import view.ApproximationGraphController;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;


public class MainApp extends Application {
    private static ArrayList<Pair<Integer, Integer>> dots;
    private static Function function;
    private Stage primaryStage;
    private static ArrayList<Double> result1;
    private static ArrayList<Double> result2;
    private static ApproximationGraphController controller;
    @Override
    public void start(Stage stage){
        this.primaryStage = stage;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ApproximationGraphOverview.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();
            Scene scene = new Scene(anchorPane);
            primaryStage.setScene(scene);
            controller = loader.getController();
            Util.setResult(result1);
            controller.setColor(Color.RED);
            controller.draw(dots, function);
            Util.setResult(result2);
            controller.setColor(Color.GREEN);
            controller.draw(dots, function);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception  {
        Scanner sc = new Scanner(new File("input.txt"));
        //Scanner sc = new Scanner(new File(".in"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        //PrintWriter out = new PrintWriter(new File(".out"));
        int k = 3;
        System.out.println("1 - f(x) = a + bx + cx^2");
        Function a = new Summary(new Summary(new Multiplication(new Variable("0"), new Value(1)),
                new Multiplication(new Variable("1"), new Variable("x"))),
                new Multiplication(new Variable("2"), new Exponentiation(new Variable("x"), new Value(2))));
        System.out.println("2 - f(x) = a + bx");
        Function b = new Summary(new Multiplication(new Variable("0"), new Value(1)),
                        new Multiplication(new Variable("1"), new Variable("x")));
        System.out.println("3 - f(x) = a + b/x + cx");
        Function c = new Summary(new Multiplication(new Variable("0"), new Value(1)), new Summary(
                    new Multiplication(new Variable("1"), new Exponentiation(new Variable("x"), new Value(-1))),
                    new Multiplication(new Variable("2"), new Variable("x"))
        ));
        System.out.println("4 - f(x) = a + bx + cx^3 + dx^5");
        Function d = new Summary(new Summary(new Summary(new Multiplication(new Variable("0"), new Value(1)),
                new Multiplication(new Variable("1"),  new Variable("x"))),
                new Multiplication(new Variable("2"), new Exponentiation(new Variable("x"), new Value(3)))),
                new Multiplication(new Variable("3"), new Exponentiation(new Variable("x"), new Value(5))));
        java.util.Scanner scC = new java.util.Scanner(System.in);
        int j = scC.nextInt();
        switch (j){
            case (1) : {
                    function = a;
                    k = 3;
                    break;
            }
            case (2) :{
                function = b;
                k = 2;
                break;
            }
            case (3) :  {
                function = c;
                k = 3;
                break;
            }
            case (4) : {
                function = d;
                k = 4;
                break;
            }
            default: return;
        };
        int n = sc.nextInt();
        dots = new ArrayList<>();
        for (Integer i = 0; i < n; ++i){
            dots.add(new Pair<>(sc.nextInt(), sc.nextInt()));
        }

        result1 = LeastSquares.evaluate(function, k, dots);
        out.println("First function result:");
        for (int i = 0; i < result1.size(); ++i) out.print(result1.get(i) + " ");
        int min = 0;
        double minValue = Math.pow(function.evaluate((Number) dots.get(0).getKey()).doubleValue(), 2) -
                Math.pow(dots.get(0).getKey(), 2);
        for (int i = 1; i < dots.size(); ++i){
            double minLoc = Math.pow(function.evaluate((Number) dots.get(i).getKey()).doubleValue(), 2) -
                    Math.pow(dots.get(i).getKey(), 2);
            if (minLoc < minValue){
                minValue = minLoc;
                min = i;
            }
        }
        dots.sort(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
        Integer minX = dots.get(min).getKey();
        Integer minY = dots.get(min).getValue();
        dots.remove(dots.get(min));
        result2 = LeastSquares.evaluate(function, k, dots);
        dots.add(new Pair(minX, minY));
        dots.sort(new Comparator<Pair<Integer, Integer>>() {
            @Override
            public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
                return o1.getKey() - o2.getKey();
            }
        });
        out.println("\nSecond function result:");
        for (int i = 0; i < result2.size(); ++i) out.print(result2.get(i) + " ");
        launch(args);
        out.close();
        sc.close();

    }


    private static class Scanner {
        private BufferedReader br;
        private StringTokenizer t;

        public Scanner(File file) throws Exception {
            br = new BufferedReader(new FileReader(file));
            t = new StringTokenizer("");
        }

        public boolean hasNext() throws Exception {
            while (!t.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null)
                    return false;
                t = new StringTokenizer(line);
            }
            return true;
        }


        public String next() throws Exception {
            if (hasNext()) {
                return t.nextToken();
            }
            return null;
        }

        public int nextInt() throws Exception {
            return Integer.parseInt(next());
        }

        public double nextDouble() throws Exception {
            return Double.parseDouble(next());
        }

        public void close() throws Exception {
            br.close();
        }
    }
}

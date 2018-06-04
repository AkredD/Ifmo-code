import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Created by user on 25.04.2018.
 */


public class main {
    private static int n;
    private static Matrix m1,m2;
    private static ArrayList<Pair<Integer, Integer>> res;
    private static String type;

    public String getType(){
        return type;
    }

    private static void console() throws Exception{
        java.util.Scanner sc = new java.util.Scanner(System.in);
        n = sc.nextInt();
        m1 = new Matrix(n, n);
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                int x = sc.nextInt();
                m1.set(i, j, new Pair<>(x, 1));
            }
        }
        m2 = new Matrix(n, 1);
        for (int i = 0; i < n; ++i){
            int x = sc.nextInt();
            m2.set(i, 0, new Pair<>(x, 1));
        }
    }

    private static void file(Scanner sc) throws Exception{
        n = sc.nextInt();
        m1 = new Matrix(n, n);
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                int x = sc.nextInt();
                m1.set(i, j, new Pair<>(x, 1));
            }
        }
        m2 = new Matrix(n, 1);
        for (int i = 0; i < n; ++i){
            int x = sc.nextInt();
            m2.set(i, 0, new Pair<>(x, 1));
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("input.txt"));
        //Scanner sc = new Scanner(new File("main.in"));
        PrintWriter out = new PrintWriter(new File("output.txt"));
        //PrintWriter out = new PrintWriter(new File("main.out"));

        //file(sc);
        //console();

        java.util.Scanner scT = new java.util.Scanner(System.in);


        while (true) {
            System.out.println("Commands");
            System.out.println("console - to read from console");
            System.out.println("file - to read from input.txt");
            System.out.println("exit");
            type = scT.next();

            if (type.equals("console")) {
                console();
            } else if (type.equals("file")) {
                file(sc);
            } else if (type.equals("exit")) {
                break;
            }else continue;


            ArrayList<Pair<Integer, Integer>> res = Gaussian.getInstance().evaluate(m1, m2, n);
            if (type.equals("file")) {
                for (int i = 0; i < n; ++i) {
                    Pair<Integer, Integer> loc = res.get(i);
                    if (loc.getKey() == 0) {
                        out.print(0);
                        out.print(" ");
                        continue;
                    }
                    if (loc.getValue() == 1) {
                        out.print(loc.getKey());
                        out.print(" ");
                        continue;
                    }
                    out.print(loc.getKey());
                    out.print("/");
                    out.print(loc.getValue());
                    out.print(" ");
                }
            }else{
                System.out.println("Result");
                for (int i = 0; i < n; ++i) {
                    Pair<Integer, Integer> loc = res.get(i);
                    if (loc.getKey() == 0) {
                        System.out.print(0);
                        System.out.print(" ");
                        continue;
                    }
                    if (loc.getValue() == 1) {
                        System.out.print(loc.getKey());
                        System.out.print(" ");
                        continue;
                    }
                    System.out.print(loc.getKey());
                    System.out.print("/");
                    System.out.print(loc.getValue());
                    System.out.print(" ");

                }
                System.out.println();
            }
        }
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

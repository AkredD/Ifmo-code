package simpsonsRule;

import function.Function;
import javafx.util.Pair;

public class SimpsonRule {
    private Function<Double> function;
    private Double value;
    private double left;
    private double right;
    private double error;

    public SimpsonRule(Function<Double> function, double left, double right){
        if (left > right){
            double loc = right;
            right = left;
            left = loc;
        }
        this.left = left;
        this.right = right;
        this.function = function;
    }

    public void setError(double error){
        this.error = error;
    }

    public Pair<Double, Double> Evaluate(int n) throws Exception{
        Pair<Double, Double> res;
        double result = 0;
        double step = Math.abs(right - left) / n;
        result += function.evaluate(left);
        result += function.evaluate(right);
        for (int i = 1; i < n; ++i){
            result += (i % 2 == 0) ? 2*function.evaluate(left + step*i) : 4*function.evaluate(left + step*i);
        }
        result *= step / 3;
        if (value != null){
            if (result == Double.POSITIVE_INFINITY) throw new Exception("Divergent limit");
            if (Math.abs(result - value)/15 < error) {
                System.out.println(Math.log(n/2)/Math.log(2));
                return new Pair<>(value, Math.abs(result - value)/15);
            } else{
                this.value = result;
                return Evaluate(2*n);
            }
        }else{
            this.value = result;
            return Evaluate(2*n);
        }
    }
}

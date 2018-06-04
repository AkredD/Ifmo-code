package function.operators.binary;

import function.Function;
import function.operators.binary.BinaryOperator;

public class Subtraction<T extends Number> extends BinaryOperator<T> {

    public Subtraction(Function left, Function right) {
        super(left, right);
    }

    @Override
    public T evaluate(T variable){
        return (T) (Number) (super.getLeft().evaluate(variable).doubleValue() - super.getRight().evaluate(variable).doubleValue());
    }
}

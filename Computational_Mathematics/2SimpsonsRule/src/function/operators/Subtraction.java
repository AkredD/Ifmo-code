package function.operators;

import function.Function;

public class Subtraction<T extends Number> extends BinaryOperator<T> {

    public Subtraction(Function left, Function right) {
        super(left, right);
    }

    @Override
    public T evaluate(T variable){
        return (T) (Number) (super.getLeft().evaluate(variable).doubleValue() - super.getRight().evaluate(variable).doubleValue());
    }
}

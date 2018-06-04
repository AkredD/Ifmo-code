package function.operators.binary;

import function.Function;
import function.operators.binary.BinaryOperator;

public class Exponentiation<T extends Number> extends BinaryOperator<T> {

    public Exponentiation(Function left, Function right) {
        super(left, right);
    }

    @Override
    public T evaluate(T variable){
        return (T) (Number) (Math.pow(super.getLeft().evaluate(variable).doubleValue(), super.getRight().evaluate(variable).doubleValue()));
    }
}

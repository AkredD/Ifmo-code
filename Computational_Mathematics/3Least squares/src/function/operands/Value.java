package function.operands;

import function.Function;

public class Value<T extends Number> extends Operand<T> {
    public Value(T value){
        super(value);
    }

    @Override
    public T evaluate(T variable){
        return super.getOperand();
    }
}

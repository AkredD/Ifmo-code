package function.operands;

import function.Function;

public abstract class Operand<T extends Number> implements Function<T> {
    private T operand;

    public Operand(T operand){
        this.operand = operand;
    }

    protected T getOperand(){
        return operand;
    }

    @Override
    public abstract T evaluate(T variable);
}

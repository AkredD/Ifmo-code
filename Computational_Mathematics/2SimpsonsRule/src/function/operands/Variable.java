package function.operands;

public class Variable<T extends Number> extends Operand<T> {

    public Variable(String x){
        super(null);
    }

    @Override
    public T evaluate(T variable){
        return variable;
    }
}

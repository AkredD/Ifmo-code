package function.operators.binary;

import function.Function;

public abstract class BinaryOperator<T extends Number> implements Function<T> {

    private Function left;
    private Function right;

    public Function getLeft(){ return left;}
    public Function getRight(){ return right;}

    public BinaryOperator(Function left, Function right){
        this.left = left;
        this.right = right;
    }

    @Override
    public abstract T evaluate(T variable);
}

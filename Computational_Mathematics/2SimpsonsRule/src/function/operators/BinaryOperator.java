package function.operators;

import function.Function;

public abstract class BinaryOperator<T extends Number> implements Function<T> {

    private Function left;
    private Function right;

    protected Function getLeft(){ return left;}
    protected Function getRight(){ return right;}

    public BinaryOperator(Function left, Function right){
        this.left = left;
        this.right = right;
    }

    @Override
    public abstract T evaluate(T variable);
}

package function.operands;


import util.Util;

public class Variable<T extends Number> extends Operand<T> {

    private String name;

    public Variable(String name){
        super(null);
        this.name = name;
    }

    public String getName(){return name;}

    @Override
    public T evaluate(T variable){
        if (!name.equals("x")) {
            int i = Integer.parseInt(name);
            if (Util.getResult() != null){
                return (T) Util.getResult().get(i);
            }
        }
        return variable;
    }
}

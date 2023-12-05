package xxl.content.functions;

import xxl.content.Content;
import xxl.content.literal.Literal;
import xxl.content.literal.LiteralInteger;

public abstract class Function extends Content{

    protected abstract int compute();

    public abstract String getFunction();

    public String toString(){
        try {
            return compute() + getFunction();
        } catch (NumberFormatException | NullPointerException | ArithmeticException e){
            return "#VALUE" + getFunction();
        }
        
    }

    public Literal value(){
        return new LiteralInteger(compute());
    }

}

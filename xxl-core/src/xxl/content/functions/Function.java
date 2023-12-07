package xxl.content.functions;

import xxl.cells.Observer;
import xxl.content.Content;
import xxl.content.literal.Literal;
import xxl.content.literal.LiteralString;
import xxl.content.search.Visitor;

public abstract class Function extends Content implements Observer{

    public abstract String compute();

    public abstract void setValue(Literal value);

    public abstract String getFunction();

    public String toString(){
        return value().asString() + getFunction();
    }

    public String asString(){
        return getFunction();
    }

    public int asInt() throws NumberFormatException{
        return value().asInt();
    }

    public void update() {
        try{
            setValue(new LiteralString(compute()));
        } catch (NumberFormatException | ArithmeticException e){
            setValue(new LiteralString("#VALUE"));
        }
    }

    public void accept(Visitor v) { v.visit(this); }

}

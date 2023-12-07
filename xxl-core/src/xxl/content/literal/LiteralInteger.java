package xxl.content.literal;

import xxl.content.search.Visitor;

public class LiteralInteger extends Literal{
    private int _value;

    public LiteralInteger(int content){
        _value = content;
    }

    public String toString(){
        return "" + _value;
    }
    
    public int asInt(){
        return _value;
    }

    public String asString(){
        return "" + _value;
    }

    public Literal value(){
        return this;
    }

    public void update(){}

    public void accept(Visitor v) { v.visit(this); }

}

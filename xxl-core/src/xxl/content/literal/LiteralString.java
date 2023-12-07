package xxl.content.literal;

import xxl.content.search.Visitor;

public class LiteralString extends Literal{
    private String _value;

    public LiteralString(String value){
        _value = value;
    }

    public void setString(String value){
        _value = value;
    }

    public String toString(){
        return _value;
    }

    public String asString(){
        return _value;
    }

    public int asInt() throws NumberFormatException{
        try {
            return Integer.parseInt(_value);

        } catch (NumberFormatException e){
            throw new NumberFormatException();
        }

    }

    public Literal value(){
        return this;
    }

    public void update(){}

    public void accept(Visitor v) { v.visit(this); }

}

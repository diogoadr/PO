package xxl.content.literal;

public class LiteralString extends Literal{
    private String _value;

    public LiteralString(String value){
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


}

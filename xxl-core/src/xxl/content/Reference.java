package xxl.content;

import xxl.cells.AbstractStorage;
import xxl.cells.Observer;
import xxl.content.literal.Literal;
import xxl.content.literal.LiteralString;
import xxl.content.search.Visitor;

public class Reference extends Content implements Observer{
    
    private int _row;
    private int _column;
    private LiteralString _value = new LiteralString("");

    private AbstractStorage _storage;

    public Reference(String content, AbstractStorage storage){
        String[] range = content.replaceAll("=", "").split(";");

        _row = Integer.parseInt(range[0]);
        _column = Integer.parseInt(range[1]);

        _storage = storage;
    }

    public String toString(){
        
        return _value.toString() + "=" + _storage.toString(_row + ";" + _column).split("\\|")[0];
        
    }

    public String asString(){
        return "=" + _row + ";" + _column;
    }

    public int asInt(){
        return _value.asInt();
    }

    public Literal value(){
        return _value;
    }
    
    public void update(){

        _value.setString(_storage.searchCell(_row + ";" + _column).getContent().value().asString());

        if(_value.asString() == "")
            _value.setString("#VALUE");

    }

    public void accept(Visitor v) { v.visit(this); }

}

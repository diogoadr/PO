package xxl;

import java.io.Serial;
import java.io.Serializable;

import xxl.content.Content;
import xxl.content.Reference;
import xxl.content.functions.Add;
import xxl.content.functions.Div;
import xxl.content.functions.Mul;
import xxl.content.functions.Sub;
import xxl.content.literal.LiteralInteger;
import xxl.content.literal.LiteralString;

public class Cell implements Serializable{
    @Serial
    private static final long serialVersionUID = 202308312359L;

    private int _row;
    private int _column;
    private Storage _storage;
    private Content _content;

    /**
     * Cells constructor
     *
     * @param rangeString range of the cell
     * @param content
     * @param storage pointer to where is stored
     */
    
    public Cell(String rangeSpecification, String content, Storage storage){
        _storage = storage;

        String[] range = rangeSpecification.split(";");

        _row = Integer.parseInt(range[0]);
        _column = Integer.parseInt(range[1]);

        //empty
        if(content == "")
            _content = new LiteralString(content);
        
        else if(content.startsWith("=")){

            if(content.startsWith("=ADD"))
                //add
                _content = new Add(content, storage);

            else if(content.startsWith("=SUB"))
                //sub
                _content = new Sub(content, storage);

            else if(content.startsWith("=DIV"))
                //div
                _content = new Div(content, storage);

            else if(content.startsWith("=MUL"))
                //mul
                _content = new Mul(content, storage);
            
            else 
                //reference
                _content = new Reference(content, _storage);


        } else if(content.startsWith("'"))
            //string
            _content = new LiteralString(content);

        else
            //number
            _content = new LiteralInteger(Integer.parseInt(content));
        
    }

    public void setRange(int row, int column){
        _row = row;
        _column = column;
    }

    public Content getContent(){
        return _content;
    }

    public String toString(){
        return _row + ";" + _column + "|" + _content.toString();
    }

    public String asString(){
        return _content.asString();
    }

    public int asInt(){
        return _content.asInt();
    }
}

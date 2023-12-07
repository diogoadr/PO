package xxl.cells;

import java.io.Serial;
import java.io.Serializable;

import xxl.content.Content;
import xxl.content.Reference;
import xxl.content.functions.Add;
import xxl.content.functions.Average;
import xxl.content.functions.Coalesce;
import xxl.content.functions.Concat;
import xxl.content.functions.Div;
import xxl.content.functions.Mul;
import xxl.content.functions.Product;
import xxl.content.functions.Sub;
import xxl.content.literal.LiteralInteger;
import xxl.content.literal.LiteralString;

public class Cell implements Serializable, Observer{
    @Serial
    private static final long serialVersionUID = 202308312359L;
    
    private AbstractStorage _storage;
    private Content _content;

    public Cell(String content, AbstractStorage storage){
        this(content, storage, true);
    }

    public Cell(String content, AbstractStorage storage, boolean isUpdatable){
        _storage = storage;

        if(isUpdatable)
            _storage.registerObserver(this);
        
        if(content == "" || content.startsWith("'"))
            _content = new LiteralString(content);
        
        else if(content.startsWith("=")){

            if(content.startsWith("=ADD("))
                //add
                _content = new Add(content, storage);

            else if(content.startsWith("=SUB("))
                //sub
                _content = new Sub(content, storage);

            else if(content.startsWith("=DIV("))
                //div
                _content = new Div(content, storage);

            else if(content.startsWith("=MUL("))
                //mul
                _content = new Mul(content, storage);

            else if(content.startsWith("=AVERAGE("))
                //average
                _content = new Average(content, storage);
            
            else if(content.startsWith("=PRODUCT("))
                //product
                _content = new Product(content, storage);

            else if(content.startsWith("=COALESCE("))
                //coalesce
                _content = new Coalesce(content, storage);

            else if(content.startsWith("=CONCAT("))
                _content = new Concat(content, storage);
            
            else 
                //reference
                _content = new Reference(content, _storage);

        } else
            //number
            _content = new LiteralInteger(Integer.parseInt(content));
                
    }
    public void setStorage(AbstractStorage storage){
        _storage = storage;
    }

    public Content getContent(){
        return _content;
    }

    public String toString(){
        return _content.toString();
    }

    public String asString(){
        return _content.asString();
    }

    public int asInt(){
        return _content.asInt();
    }

    public void removeObserver(){
        _storage.removeObserver(this);
    }

    public void update() {
        _content.update();
    }

}

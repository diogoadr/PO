package xxl.content.functions;

import java.util.List;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

public class Coalesce extends IntervalFunction{
    private String _function;
    private AbstractStorage _storage;
    private int _functionLenght = 10;
    private Literal _value;

    public Coalesce(String function, AbstractStorage storage){
        _function = function;
        _storage = storage;

    }

    public void setValue(Literal value){
        _value = value;
    }

    public Literal value(){
        return _value;
    }

    @Override
    public String getFunction() {
        return _function;
    }

    @Override
    public AbstractStorage getStorage() {
        return _storage;
    }

    @Override
    public int getFunctionLenght() {
        return _functionLenght;
    }

    @Override
    public String calculate(List<Cell> cells) {
        for (Cell cell: cells){
            String content = cell.getContent().asString();

            if (content.startsWith("'"))
                return content;
        }

        return "'";
    }
    
    public void accept(Visitor v) { v.visit(this); }
}

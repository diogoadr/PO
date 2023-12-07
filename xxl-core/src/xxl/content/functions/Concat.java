package xxl.content.functions;

import java.util.List;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

public class Concat extends IntervalFunction{
    private String _function;
    private AbstractStorage _storage;
    private final int _functionLenght = 8;
    private Literal _value;

    public Concat(String function, AbstractStorage storage){
        _function = function;
        _storage = storage;

    }

    public void setValue(Literal value){
        _value = value;
    }

    public Literal value(){
        return _value;
    }

    public String getFunction() {
        return _function;
    }

    public AbstractStorage getStorage() {
        return _storage;
    }

    public int getFunctionLenght() {
        return _functionLenght;
    }

    public String calculate(List<Cell> cells) {
        String concat = "'";

        for (Cell cell: cells){
            String content = cell.getContent().asString();

            if (content.startsWith("'"))
                concat += content.substring(1);
        }

        return concat;
    }
    
    public void accept(Visitor v) { v.visit(this); }
}

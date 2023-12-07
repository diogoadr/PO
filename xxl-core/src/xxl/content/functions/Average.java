package xxl.content.functions;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

import java.util.List;

public class Average extends IntervalFunction{

    private int _functionLenght = 9;
    private AbstractStorage _storage;
    private String _function;
    private Literal _value;
    
    public Average(String function, AbstractStorage storage){
        _storage = storage;
        _function = function;

    }

    public void setValue(Literal value){
        _value = value;
    }

    public Literal value(){
        return _value;
    }

    public AbstractStorage getStorage(){
        return _storage;
    }

    public String getFunction(){
        return _function;
    }

    public int getFunctionLenght(){
        return _functionLenght;
    }

    public String calculate(List<Cell> cells) throws NumberFormatException{
        int sum = 0;

        for (Cell cell: cells){
            sum += cell.asInt();
        }

        return String.valueOf(sum/cells.size());
    }

    public void accept(Visitor v) { v.visit(this); }
    
}

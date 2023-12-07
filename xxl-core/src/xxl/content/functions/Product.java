package xxl.content.functions;

import java.util.List;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

public class Product extends IntervalFunction{

    private int _functionLenght = 9;
    private Literal _value;
    private AbstractStorage _storage;
    private String _function;
    
    public Product(String function, AbstractStorage storage){
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
        int sum = 1;

        for (Cell cell: cells){
            sum *= cell.asInt();
        }

        return String.valueOf(sum);
    }

    public void accept(Visitor v) { v.visit(this); }
}

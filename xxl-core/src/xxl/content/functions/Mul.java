package xxl.content.functions;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

public class Mul extends BinaryFunction {

    private Literal _value;
    private AbstractStorage _storage;
    private String _function;

    public Mul(String function, AbstractStorage storage){
        _function = function;
        _storage = storage;
    }

    public void setValue(Literal value){
        _value = value;
    }

    public Literal value(){
        return _value;
    }

    public String getFunction(){
        return _function;
    }

    public AbstractStorage getStorage(){
        return _storage;
    }

    public int calculate(Cell cell1, Cell cell2) throws NumberFormatException{
        return cell1.asInt() * cell2.asInt();
    }

    public int calculate(Cell cell, int number) throws NumberFormatException{
        return cell.asInt() * number;
    }

    public int calculate(int number, Cell cell) throws NumberFormatException{
        return number * cell.asInt();
    }

    public int calculate(int number1, int number2){
        return number1 * number2;
    }

    public void accept(Visitor v) { v.visit(this); }

}

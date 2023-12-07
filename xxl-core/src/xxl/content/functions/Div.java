package xxl.content.functions;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

public class Div extends BinaryFunction{

    private Literal _value;
    private AbstractStorage _storage;
    private String _function;

    public Div(String function, AbstractStorage storage){
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

    public int calculate(Cell cell1, Cell cell2) throws NumberFormatException, ArithmeticException{
        return cell1.asInt() / cell2.asInt();
    }

    public int calculate(Cell cell, int number) throws NumberFormatException, ArithmeticException{
        return cell.asInt() / number;
    }

    public int calculate(int number, Cell cell) throws NumberFormatException, ArithmeticException{
        return number / cell.asInt();
    }

    public int calculate(int number1, int number2) throws ArithmeticException{
        return number1 / number2;
    }

    public void accept(Visitor v) { v.visit(this); }

}

package xxl.content.functions;

import xxl.Cell;
import xxl.Storage;

public class Add extends BinaryFunction {
    Storage _storage;
    String _function;

    public Add(String function, Storage storage){
        _function = function;
        _storage = storage;
    }

    public String getFunction(){
        return _function;
    }

    public Storage getStorage(){
        return _storage;
    }

    public String asString(){
        return _function;
    }

    public int asInt(){
        return compute();
    }

    public int calculate(Cell cell1, Cell cell2) throws NumberFormatException{
        return cell1.asInt() + cell2.asInt();
    }

    public int calculate(Cell cell, int number) throws NumberFormatException{
        return cell.asInt() + number;
    }

        public int calculate(int number, Cell cell) throws NumberFormatException{
        return number + cell.asInt();
    }

    public int calculate(int number1, int number2){
        return number1 + number2;
    }

}

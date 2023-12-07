package xxl.content.functions;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

public abstract class BinaryFunction extends Function{
    public abstract void setValue(Literal value);

    public abstract AbstractStorage getStorage();

    public abstract int calculate(Cell cell1, Cell cell2);
    public abstract int calculate(Cell cell, int number);
    public abstract int calculate(int number1, int number2);
    public abstract int calculate(int number, Cell cell);
    
    public String compute() throws NumberFormatException, NullPointerException, ArithmeticException{
        String function = getFunction();
        AbstractStorage storage = getStorage();

        String range = function.substring(5 , function.length()-1);
        String[] interval = range.split(",");

        // sum of cells
        if(range.split(";").length == 3){

            Cell cell1 = storage.searchCell(interval[0]);
            Cell cell2 = storage.searchCell(interval[1]);

            return String.valueOf(calculate(cell1, cell2));

        } else if (range.split(";").length == 2){

            //one cell and a number
            if(function.indexOf(";") != -1){

                //first one is a cell
                if(interval[0].indexOf(";") != -1){
                    Cell cell = storage.searchCell(interval[0]);
                    int number = Integer.parseInt(interval[1]);

                    return String.valueOf(calculate(cell, number));

                } else {

                    //first one is a number
                    Cell cell = storage.searchCell(interval[1]);
                    int number = Integer.parseInt(interval[0]);
                    
                    return String.valueOf(calculate(number, cell));
                }
            }
            
        //two numbers
        }

        return String.valueOf(calculate(Integer.parseInt(interval[0]), Integer.parseInt(interval[1])));

    }

    public void accept(Visitor v) { v.visit(this); }
}

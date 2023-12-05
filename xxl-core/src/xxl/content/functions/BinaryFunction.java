package xxl.content.functions;

import xxl.Cell;
import xxl.Storage;

public abstract class BinaryFunction extends Function{
    public abstract String getFunction();

    public abstract Storage getStorage();

    public abstract int calculate(Cell cell1, Cell cell2);
    public abstract int calculate(Cell cell, int number);
    public abstract int calculate(int number1, int number2);
    public abstract int calculate(int number, Cell cell);

    /**
     * Computes and returns the result of a given binary function
     * 
     * @throws NumberFormatException if a string cannot be converted into a int
     * @throws NullPointerException if a cell isn't defined or a cell is out of bounds
     * @throws ArithmeticException if tried to devide by zero
     * @return result of the function
     */

    protected int compute() throws NumberFormatException, NullPointerException, ArithmeticException{
        String function = getFunction();
        Storage storage = getStorage();

        String range = function.substring(5 , function.length()-1);
        String[] interval = range.split(",");

        // sum of cells
        if(range.split(";").length == 3){

            Cell cell1 = storage.searchCell(interval[0]);
            Cell cell2 = storage.searchCell(interval[1]);

            if (cell1 == null || cell2 == null){
                throw new NullPointerException();
            }

            return calculate(cell1, cell2);

        } else if (range.split(";").length == 2){

            //one cell and a number
            if(function.indexOf(";") != -1){

                //first one is a cell
                if(interval[0].indexOf(";") != -1){
                    Cell cell = storage.searchCell(interval[0]);
                    int number = Integer.parseInt(interval[1]);

                    return calculate(cell, number);

                } else {

                    //first one is a number
                    Cell cell = storage.searchCell(interval[1]);
                    int number = Integer.parseInt(interval[0]);
                    
                    return calculate(number, cell);
                }
            }
            
        //two numbers
        }

        return calculate(Integer.parseInt(interval[0]), Integer.parseInt(interval[1]));

    }
}

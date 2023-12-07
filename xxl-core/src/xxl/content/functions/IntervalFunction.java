package xxl.content.functions;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.cells.Range;
import xxl.exceptions.UnrecognizedEntryException;

import java.util.List;

public abstract class IntervalFunction extends Function{
    
    public abstract AbstractStorage getStorage();
    public abstract int getFunctionLenght();
    
    public abstract String calculate(List<Cell> cells);

    public String compute() throws NullPointerException{

        String function = getFunction();

        Range range;

        String rangeSpecification = function.substring(getFunctionLenght() , function.length()-1);
        
        try{
            range = new Range(rangeSpecification, getStorage());

        } catch(UnrecognizedEntryException e){ 
            //the range doesn't exist in the cell
            throw new NullPointerException();
        }
        
        return calculate(range.getCells());
        
    }

}

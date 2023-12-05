package xxl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import xxl.exceptions.UnrecognizedEntryException;

public class Range implements Serializable{
    private Storage _storage;

    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;

    public int getBeginRow(){
        return _beginRow;
    }

    public int getBeginColumn(){
        return _beginColumn;
    }

    public int getEndRow(){
        return _endRow;
    }
    
    public int getEndColumn(){
        return _endColumn;
    }

    /**
     * Sets the range of the Range object to the desired one
     *
     * @param rangeSpecification range to be travelled
     * @param storage pointer to the storage to be travelled in the future
     */

    public void setRange(String rangeSpecification, Storage storage) throws UnrecognizedEntryException{
        String[] range = rangeSpecification.split(":");
        Integer[] interval = getInterval(range[0]);

        _beginRow = interval[0];
        _beginColumn = interval[1];

        if (range.length == 1){
            _endRow = _beginRow;
            _endColumn = _beginColumn;

        } else {
            interval = getInterval(range[1]);
            _endRow = interval[0];
            _endColumn = interval[1];
        }

        if(_endRow < _beginRow){
            int i = _beginRow;
            _beginRow = _endRow;
            _endRow = i;
        }

        if (_endColumn < _beginColumn){
            int i = _beginColumn;
            _beginColumn = _endColumn;
            _endColumn = i;
        }

        if(_beginRow != _endRow && _beginColumn != _endColumn){
            throw new UnrecognizedEntryException(rangeSpecification, null);
        }

        _storage = storage;

    }

    /**
     * Gets zero or more cells by the range attributed
     *
     * @return List of cells
     */

    public List<Cell> getCells(){
        List<Cell> cells = new LinkedList<Cell>();

        for(int i = _beginRow; i <= _endRow; i++){
            for(int j = _beginColumn; j <= _endColumn; j++){
                Cell cell = _storage.searchCell(i + ";" + j);

                //cell exists but its empty
                if (cell == null){
                    Cell _emptyCell = new Cell("1;1", "", _storage);
                    _emptyCell.setRange(i, j); 
                    cells.add(_emptyCell);

                //cell exists
                } else
                    cells.add(cell);
            }
        }

        return cells;
    }

    /**
     * Splits the range into a fixed array of intergers
     *
     * @param rangeString range to get splited
     * @return list with lenght 2 of the splited range
     */

    public Integer[] getInterval(String rangeString){
        String[] range = rangeString.split(";");
        Integer[] interval = new Integer[2];

        interval[0] = Integer.parseInt(range[0]);
        interval[1] = Integer.parseInt(range[1]);

        return interval;
    }

    /**
     * Verifies the range
     *
     * @param lines
     * @param columns 
     * @return boolean
     */

    public boolean verifyRange(int lines, int columns) throws UnrecognizedEntryException{
        if(_beginRow > lines || _endRow > lines || 
            _beginColumn> columns || _endColumn > columns ||
                _beginColumn < 1 || _endRow < 1 || _beginColumn < 1 || _endColumn < 1)
                return false;

        return true;
        
    }
    
}

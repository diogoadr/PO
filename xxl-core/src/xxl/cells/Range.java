package xxl.cells;

import java.io.Serializable;
import java.util.ArrayList;

import xxl.exceptions.UnrecognizedEntryException;

public class Range implements Serializable{
    private AbstractStorage _storage;

    private int _beginRow;
    private int _beginColumn;
    private int _endRow;
    private int _endColumn;

    public Range(String rangeSpecification, AbstractStorage storage) throws UnrecognizedEntryException{
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
            throw new UnrecognizedEntryException(rangeSpecification);
        }

        _storage = storage;
    }
    public int getBeginRow(){
        return _beginRow;
    }

    public int getBeginColumn(){
        return _beginColumn;
    }

    public void insert(String contentSpecification){

        for(int row = _beginRow; row <= _endRow; row++){
            for(int column = _beginColumn; column <= _endColumn; column++){
                _storage.add(row + ";" + column, new Cell(contentSpecification, _storage));
            }
        }

    }

    public void delete(){

        for(int row = _beginRow; row <= _endRow; row++){
            for(int column = _beginColumn; column <= _endColumn; column++){
                _storage.delete(row + ";" + column);
            }
        }

    }

    public ArrayList<Cell> getCells(){

        ArrayList<Cell> cells = new ArrayList<Cell>();

        for(int row = _beginRow; row <= _endRow; row++){
            for(int column = _beginColumn; column <= _endColumn; column++){
                
                Cell cell = _storage.searchCell(row + ";" + column);
                cells.add(cell);
                
            }
        }

        return cells;
    }

    public ArrayList<String> rangeToString(){
        ArrayList<String> storageToString = new ArrayList<>();

        for(int i = _beginRow; i <= _endRow; i++){
            for(int j = _beginColumn; j <= _endColumn; j++){
                storageToString.add(_storage.toString(i + ";" + j));
                
            }
        }

        return storageToString;
    }

    private Integer[] getInterval(String rangeString){
        String[] range = rangeString.split(";");
        Integer[] interval = new Integer[2];

        interval[0] = Integer.parseInt(range[0]);
        interval[1] = Integer.parseInt(range[1]);

        return interval;
    }

    public boolean verifyRange(int lines, int columns) throws UnrecognizedEntryException{
        if(_beginRow > lines || _endRow > lines || 
            _beginColumn> columns || _endColumn > columns ||
                _beginColumn < 1 || _endRow < 1 || _beginColumn < 1 || _endColumn < 1)
                return false;

        return true;
        
    }

    public String direction(){
        if(_beginRow == _endRow)
            return "horizontal";
        else
            return "vertical";
    }

    public int size(){
        //works independently of the direction of the range
        return _endColumn - _beginColumn + _endRow - _beginRow + 1;
    }
}

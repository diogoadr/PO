package xxl;

import xxl.cells.AbstractStorage;
import xxl.cells.Cell;
import xxl.cells.CellComparator;
import xxl.cells.Range;
import xxl.cells.Storage;
import xxl.content.search.FunctionSearchStrategy;
import xxl.content.search.ValueSearchStrategy;
import xxl.exceptions.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private int _lines;
    private int _columns; 

    private Storage _cutBuffer;
    private AbstractStorage _storage = new Storage();
    private Range _range;

    private boolean _changed = false;

    private List<User> _users = new ArrayList<User>();

    public Spreadsheet(int lines, int columns) {
        _lines = lines;
        _columns = columns;
    }

    public boolean changed(){
        return _changed;
    }

    public void setChanged(boolean changed) {
        _changed = changed;
    }
    
    public int size(){
        return _lines + _columns;
    }

    private void createRange(String rangeSpecification, AbstractStorage storage) throws UnrecognizedEntryException{
        _range = new Range(rangeSpecification, storage);

        if(_range.verifyRange(_lines, _columns))
            return;
            
        throw new UnrecognizedEntryException(rangeSpecification);
        
    }
    public void insertContent(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException{
        insertContent(rangeSpecification, contentSpecification, _storage);
    }

    public void insertContent(String rangeSpecification, String contentSpecification, AbstractStorage storage) throws UnrecognizedEntryException {

        createRange(rangeSpecification, storage);
        _range.insert(contentSpecification);

        setChanged(true);     

    }

    public void deleteContent(String rangeSpecification) throws UnrecognizedEntryException {

        createRange(rangeSpecification, _storage);

        _range.delete();
        setChanged(true);

    }

    //copy from the storage to the cutBuffer creating a new instance of the cells copied
    public void copy(String rangeSpecification) throws UnrecognizedEntryException{
        _cutBuffer = new Storage();

        createRange(rangeSpecification, _storage);

        if (_range.direction() == "horizontal"){
            putHorizontally(_range.getCells(), 1, 1, _cutBuffer);
            _cutBuffer.setDirection("horizontal");

        }else{
            putVertically(_range.getCells(), 1, 1, _cutBuffer);
            _cutBuffer.setDirection("vertical");
        }

        setChanged(true);
    }

    public void paste(String rangeSpecification) throws UnrecognizedEntryException{

        //empty cutbuffer
        if(_cutBuffer == null) return;

        createRange(rangeSpecification, _storage);

        int row = _range.getBeginRow();
        int column = _range.getBeginColumn();

        if((_cutBuffer.size() == _range.size() && _cutBuffer.getDirection() == _range.direction()) 
            || _range.size() == 1){

            if(_cutBuffer.getDirection() == "horizontal"){
                createRange("1;1:1;" + _cutBuffer.size(), _cutBuffer);
                putHorizontally(_range.getCells(), row, column, _storage);
            }

            if(_cutBuffer.getDirection() == "vertical"){
                createRange("1;1:" + _cutBuffer.size() + ";1", _cutBuffer);      
                putVertically(_range.getCells(),row, column, _storage);
            }

        }

        if(_cutBuffer.size() != _range.size()){ return;}

    }

    private void putHorizontally(ArrayList<Cell> cells, int row, int column, AbstractStorage storage) throws UnrecognizedEntryException{

        for (Cell cell: cells){
            Cell copiedCell = new Cell(cell.getContent().asString(), _storage);

            //updates the value in the copied cell
            _storage.add("0;0", copiedCell);
            //creates the cell in the cut buffer
            storage.add(row + ";" + column++, copiedCell);
            //deletes the copied cell from the spreadsheet
            _storage.delete("0;0");

            if(storage == _cutBuffer)
                copiedCell.removeObserver();

            if(column > _columns){
                column = 1;
                row += 1;

                if(row > _lines) return;
                
            }
        }
    }

    private void putVertically(ArrayList<Cell> cells, int row, int column, AbstractStorage storage) throws UnrecognizedEntryException{

        for (Cell cell: cells){
            Cell copiedCell = new Cell(cell.getContent().asString(), _storage);

            //updates the value in the copied cell
            _storage.add("0;0", copiedCell);
            //creates the cell in the cut buffer
            storage.add(row++ + ";" + column, copiedCell);
            //deletes the copied cell from the spreadsheet
            _storage.delete("0;0");

            if(storage == _cutBuffer)
                copiedCell.removeObserver();

            if(row > _lines){
                row = 1;
                column += 1;

                if(column > _columns) return;
                
            }
        }

    }

    public ArrayList<Cell> getCells(String rangeSpecification, AbstractStorage storage) throws UnrecognizedEntryException{
        createRange(rangeSpecification, storage);
        return _range.getCells();
    }

    public ArrayList<String> showCutBuffer(){
        if(_cutBuffer == null){
            return new ArrayList<String>();
        }
        
        return _cutBuffer.storageToString();
    }

    public ArrayList<String> showAbstractStorage(String rangeSpecification) throws UnrecognizedEntryException{

        createRange(rangeSpecification, _storage);

        ArrayList<String> toString = _range.rangeToString();
        toString.add(String.valueOf(_range.size()));

        return toString;


    }

    public void addUser(User user){
        _users.add(user);
    }

    public ArrayList<String> searchValue(String value){
        
        return _storage.search(value, new ValueSearchStrategy());
    }

    public ArrayList<String> searchFunction(String value){
        ArrayList<String> toString =_storage.search(value, new FunctionSearchStrategy());

        Collections.sort(toString, new CellComparator());

        return toString;
    }

}

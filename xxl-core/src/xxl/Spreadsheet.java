package xxl;

import xxl.exceptions.UnrecognizedEntryException;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a spreadsheet.
 */
public class Spreadsheet implements Serializable {

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private int _lines;
    private int _columns; 

    private Storage _storage = new Storage();
    private Range _range = new Range();

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

    /**
     * Insert specified content in specified range.
     *
     * @param rangeSpecification range to be traveled
     * @param contentSpecification content
     */

    public void insertContents(String rangeSpecification, String contentSpecification) throws UnrecognizedEntryException /* FIXME maybe add exceptions */ {

        _range.setRange(rangeSpecification, _storage);

        if(_range.verifyRange(_lines, _columns)){

            for(int i = _range.getBeginRow(); i <= _range.getEndRow(); i++){
                for(int j = _range.getBeginColumn(); j <= _range.getEndColumn(); j++){
                    _storage.addToStorage(i + ";" + j, contentSpecification, i, j);
                }
            }

            setChanged(true);

        } else
            throw new UnrecognizedEntryException(rangeSpecification);
        

    }

    /**
     * Gets a list of one or more cells
     *
     * @param rangeSpecification range to be traveled
     * @return List<Cell> list of cell/s
     */
    
    public List<Cell> showCell(String rangeSpecification) throws UnrecognizedEntryException{

        _range.setRange(rangeSpecification, _storage);

        if(_range.verifyRange(_lines, _columns)){

            return _range.getCells();

        } else
            throw new UnrecognizedEntryException(rangeSpecification);

        
    }

    /**
     * Adds user to spreadsheet
     *
     * @param user user to be added
     */

    public void addUser(User user){
        _users.add(user);
    }

}

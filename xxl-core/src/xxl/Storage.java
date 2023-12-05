package xxl;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Storage implements Serializable{

    @Serial
    private static final long serialVersionUID = 202308312359L;

    private Map<String, Cell> _storage = new HashMap<>();

    /**
     * Adds a new cell to storage
     *
     * @param rangeSpecification range to be traveled
     * @param contentSpecification content
     * @param row line of the new cell
     * @param column column of the new cell
     */

    public void addToStorage(String rangeSpecification, String contentSpecification, int row, int column){
        _storage.put(rangeSpecification, new Cell(rangeSpecification, contentSpecification, this));

    }

    /**
     * Searches a cell in the storage and returns it
     *
     * @param rangeSpecification range to be traveled
     * @return Cell returns the searched cell (null if the cell doesn't exist)
     */
    
    public Cell searchCell(String rangeSpecification){
        return _storage.get(rangeSpecification);
    }
}

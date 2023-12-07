package xxl.cells;

import java.io.Serializable;
import java.util.ArrayList;

import xxl.content.search.SearchStrategy;

public abstract class AbstractStorage implements Serializable{

    public abstract String getDirection();

    public abstract void setDirection(String direction);

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract void add(String rangeSpecification, Cell cell);
    
    public abstract Cell searchCell(String rangeSpecification);

    public abstract void delete(String rangeSpecification);

    public abstract ArrayList<String> search(String value, SearchStrategy searchStrategy);

    public abstract ArrayList<String> storageToString();

    public abstract String toString(String rangeSpecification);

    public abstract void registerObserver(Observer o);

    public abstract void removeObserver(Observer o);

    public abstract void notifyObservers();
}

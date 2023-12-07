package xxl.cells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xxl.content.search.SearchStrategy;
import xxl.content.search.SearchVisitor;

public class Storage extends AbstractStorage{

    private Map<String, Cell> _storage = new HashMap<>();
    private ArrayList<Observer> _observers = new ArrayList<>();

    private String _direction = "";

    public String getDirection(){
        return _direction;
    }

    public void setDirection(String direction){
        _direction = direction;
    }
    public boolean isEmpty(){
        return _storage.isEmpty();
    }

    public int size(){
        return _storage.size();
    }

    public void add(String rangeSpecification, Cell cell){
        _storage.put(rangeSpecification, cell);
        notifyObservers();
    }
    
    public Cell searchCell(String rangeSpecification){
        Cell cell = _storage.get(rangeSpecification);

        if (cell == null){
            cell = new Cell("", this, false);
            this.add(rangeSpecification, cell);
        }

        return cell;
    }

    public void delete(String rangeSpecification){

        removeObserver(searchCell(rangeSpecification));
        _storage.remove(rangeSpecification);
        notifyObservers();

    }

    public ArrayList<String> search(String value, SearchStrategy searchStrategy){
        ArrayList<String> toString = new ArrayList<>();

        SearchVisitor searchVisitor = new SearchVisitor(value, searchStrategy);

        for (String range: _storage.keySet()){

            Cell cell = searchCell(range);
            cell.getContent().accept(searchVisitor);
            if(searchVisitor.foundElement())
                toString.add(toString(range));
                                  
        }

        return toString;
    }

    //gives all the information about the storage in a arraylist of strings
    public ArrayList<String> storageToString(){
        ArrayList<String> storageToString = new ArrayList<>();

        for (String range: _storage.keySet()){
            storageToString.add(toString(range));
        }

        return storageToString;
    }

    public String toString(String rangeSpecification){

        Cell cell = searchCell(rangeSpecification);
        return rangeSpecification + "|" + cell.getContent().toString();

    }

    public void registerObserver(Observer o){
        _observers.add(o);
    }

    public void removeObserver(Observer o){
        int i = _observers.indexOf(o);
        if (i >= 0) 
            _observers.remove(i); 
    }

    public void notifyObservers() {
        for (Observer observer: _observers) {
          observer.update();
        }
    }
}

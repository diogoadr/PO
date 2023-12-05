package xxl.content;

import xxl.Cell;
import xxl.Storage;
import xxl.content.literal.Literal;

public class Reference extends Content{
    private int _row;
    private int _column;
    private Storage _storage;

    public Reference(String content, Storage storage){
        String[] range = content.replaceAll("=", "").split(";");

        _row = Integer.parseInt(range[0]);
        _column = Integer.parseInt(range[1]);
        _storage = storage;
    }

    public String toString(){
        Cell cell = _storage.searchCell(_row + ";" + _column);
        
        try {

            return cell.getContent().value().toString() + "=" + cell.toString().split("\\|")[0];

        } catch (NullPointerException e) {
            return "#VALUE=" + _row + ";" + _column;
        }
        
    }

    public String asString(){
        return _storage.searchCell(_row + ";" + _column).asString();
    }

    public int asInt(){
        return _storage.searchCell(_row + ";" + _column).asInt();
    }

    public Literal value(){

        return _storage.searchCell(_row + ";" + _column).getContent().value();
        
    }

}

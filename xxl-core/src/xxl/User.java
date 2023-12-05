package xxl;

import java.util.List;
import java.util.ArrayList;

public class User {
    private List<Spreadsheet> _spreadsheets = new ArrayList<Spreadsheet>();

    private String _name;

    public User(String name){
        _name = name;
    }

    public String getName(){
        return _name;
    }

    public boolean equals(Object o){
        if(o instanceof User user){
            return _name == user.getName();
        }
        return false;
    }

    public void add(Spreadsheet spreadsheet){
        _spreadsheets.add(spreadsheet);
    }
}

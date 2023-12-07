package xxl.cells;

import java.util.Comparator;

public class CellComparator implements Comparator<String>{

    @Override
    public int compare(String str1, String str2){
        String[] fieldsStr1 = str1.split("\\|", -1);
        String[] fieldsStr2 = str2.split("\\|", -1);

        //compares content
        int contentComparator = fieldsStr1[1].split("=")[1].compareTo(fieldsStr2[1].split("=")[1]);
        if (contentComparator != 0){
            return contentComparator;
        }

        // if contents are the same, compares lines
        int lineComparison = Integer.parseInt(fieldsStr1[0].split(";")[0]) - Integer.parseInt(fieldsStr2[0].split(";")[0]);
        if (lineComparison != 0) {
            return lineComparison;
        }

        // if contents and lines are the same, compares columns
        int columnComparison = Integer.parseInt(fieldsStr1[0].split(";")[1]) - Integer.parseInt(fieldsStr1[0].split(";")[1]);
        return columnComparison;        
    }
}

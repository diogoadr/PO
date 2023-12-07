package xxl.content.search;

import xxl.content.Content;

public class ValueSearchStrategy implements SearchStrategy{
    public boolean search(Content content, String targetValue){

        if(targetValue == "%2=0")
            try{
                return content.value().asInt() % 2 == 0;
            } catch(NumberFormatException e){}

        else if(targetValue == "=ref")
            return content.asString().contains("[0-9=;]");
        
        return content.value().asString().equals(targetValue);
    }
}

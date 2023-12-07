package xxl.content.search;

import xxl.content.Content;

public class FunctionSearchStrategy implements SearchStrategy{
    public boolean search(Content content, String targetValue){
        return content.asString().startsWith("=") && content.asString().contains(targetValue);
    }
}

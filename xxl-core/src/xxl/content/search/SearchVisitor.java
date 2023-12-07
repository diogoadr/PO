package xxl.content.search;

import xxl.content.Content;

public class SearchVisitor implements Visitor{

    private String _targetValue;
    private SearchStrategy _searchStrategy;
    private boolean _foundElement;

    public SearchVisitor(String targetValue, SearchStrategy searchStrategy){
        _targetValue = targetValue;
        _searchStrategy = searchStrategy;
    }

    public boolean foundElement() {
        return _foundElement;
    }

    @Override
    public void visit(Content content) {
        _foundElement = _searchStrategy.search(content, _targetValue);
    }


} 

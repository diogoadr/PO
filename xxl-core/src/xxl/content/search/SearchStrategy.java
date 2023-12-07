package xxl.content.search;

import xxl.content.Content;

public interface SearchStrategy {
    public boolean search(Content content, String targetValue);
}

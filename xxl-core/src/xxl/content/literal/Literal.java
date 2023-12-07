package xxl.content.literal;

import xxl.content.Content;
import xxl.content.search.Visitor;

public abstract class Literal extends Content{

    public abstract Literal value();

    public void accept(Visitor v) { v.visit(this); }
}

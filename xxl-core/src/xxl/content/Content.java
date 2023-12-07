package xxl.content;

import java.io.Serializable;

import xxl.content.literal.Literal;
import xxl.content.search.Visitor;

public abstract class Content implements Serializable{

    public abstract void accept(Visitor visitor);

    public abstract void update();

    public abstract String toString();

    public abstract String asString();

    public abstract Literal value();

    public abstract int asInt();
}

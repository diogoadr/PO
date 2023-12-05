package xxl.content;

import java.io.Serializable;

import xxl.content.literal.Literal;

public abstract class Content implements Serializable{

    public abstract String toString();

    public abstract String asString();

    public abstract Literal value();

    public abstract int asInt();
}

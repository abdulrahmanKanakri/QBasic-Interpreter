package nodes;

import compiler.ParseException;

import java.util.ArrayList;
import java.util.List;

abstract public class Node {
    protected String value;
    protected final List<Node> children = new ArrayList<>();

    public Node() {}

    public Node(String value) {
        this.value = value;
    }

    public abstract Object run(Context c) throws ParseException;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void addChild(Node child)
    {
        this.children.add(child);
    }

    public List<Node> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value='" + value + '\'' +
                ", children=" + children +
                '}';
    }
}

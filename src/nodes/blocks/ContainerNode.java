package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import nodes.Node;

public class ContainerNode extends Node {

    public ContainerNode() {}
    public ContainerNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {
        for(Node child : this.children) { child.run(c); }
        return this;
    }
}

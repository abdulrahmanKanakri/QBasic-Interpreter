package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import nodes.Node;

import java.util.ArrayList;
import java.util.List;

abstract public class BlockNode extends Node {

    public BlockNode() {}
    public BlockNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {
        for(Node child : this.children) { child.run(c); }
        return this;
    }
}

package nodes.blocks;

import nodes.Context;
import nodes.Node;

public class EndNode extends BlockNode {
    public EndNode() {}
    public EndNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) {
        System.exit(0);
        return this;
    }
}

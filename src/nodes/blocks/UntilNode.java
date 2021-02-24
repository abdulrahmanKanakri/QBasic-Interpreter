package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import utils.Util;

public class UntilNode extends BlockNode {
    public UntilNode() {}

    public UntilNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {

        // expression
        while (!Util.getCondition(this.children.get(0).run(c))) {
            // container
            this.children.get(1).run(c);
        }

        return this;
    }
}

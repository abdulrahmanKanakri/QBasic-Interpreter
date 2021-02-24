package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import utils.Util;

public class DoWhileNode extends BlockNode {
    public DoWhileNode() {}

    public DoWhileNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {

        do {
            // container
            this.children.get(0).run(c);
            // expression
        } while (Util.getCondition(this.children.get(1).run(c)));

        return this;
    }
}

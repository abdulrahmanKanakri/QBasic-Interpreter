package nodes;

import nodes.blocks.ExpressionNode;
import utils.Util;

public class StringNode extends ExpressionNode {
    public StringNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) {
        return Util.getStringContent(this.value);
    }
}

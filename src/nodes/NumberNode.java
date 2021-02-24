package nodes;

import nodes.blocks.ExpressionNode;
import utils.Util;

public class NumberNode extends ExpressionNode {
    public NumberNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) {
        if(Util.isInt(this.value)) {
            return Integer.parseInt(this.value);
        }
        return Double.parseDouble(this.value);
    }
}

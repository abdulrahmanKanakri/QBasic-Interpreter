package nodes;

import nodes.blocks.ExpressionNode;
import utils.Util;

public class OperationNode extends ExpressionNode {
    public OperationNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) {
        try {
            return Util.performOperation(c, this.value, this.children.get(0), this.children.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

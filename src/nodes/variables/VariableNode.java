package nodes.variables;

import nodes.Context;
import nodes.blocks.ExpressionNode;

public class VariableNode extends ExpressionNode {
    public VariableNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) {
        return c.getVariables().get(this.value);
    }
}

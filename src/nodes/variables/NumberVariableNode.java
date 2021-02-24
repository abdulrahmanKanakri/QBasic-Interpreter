package nodes.variables;

import nodes.Context;

public class NumberVariableNode extends VariableNode {
    public NumberVariableNode(String value) {
        super(value);
    }

    public Object run(Context c) {
        Object o = c.getVariables().get(this.value);
        return o != null ? o : 0;
    }
}

package nodes.variables;

import nodes.Context;

public class StringVariableNode extends VariableNode {
    public StringVariableNode(String value) {
        super(value);
    }

    public Object run(Context c) {
        Object o = c.getVariables().get(this.value);
        return o != null ? o : "";
    }
}

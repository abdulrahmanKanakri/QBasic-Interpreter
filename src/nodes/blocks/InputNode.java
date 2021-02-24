package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import nodes.Node;
import nodes.StringNode;
import nodes.variables.StringVariableNode;
import utils.Util;

public class InputNode extends BlockNode {
    public InputNode() {}

    public InputNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {
        Node variable;
        if(this.children.get(0) instanceof StringNode) {
            System.out.print(this.children.get(0).run(c));
            variable = this.children.get(1);
        } else {
            variable = this.children.get(0);
        }
        if(variable instanceof StringVariableNode) {
            c.addVariable(variable.getValue(), Util.readString());
        } else {
            switch (Util.getVariableType(variable.getValue())) {
                case INTEGER:
                    c.addVariable(variable.getValue(), Util.readInt());
                    break;
                case LONG:
                    c.addVariable(variable.getValue(), Util.readLong());
                    break;
                case SINGLE:
                case DOUBLE:
                default:
                    c.addVariable(variable.getValue(), Util.readDouble());
            }
        }
        return this;
    }
}

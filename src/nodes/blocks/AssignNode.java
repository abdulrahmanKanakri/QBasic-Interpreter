package nodes.blocks;

import compiler.ParseException;
import exceptions.NumericException;
import exceptions.StringException;
import nodes.Context;
import utils.Util;

public class AssignNode extends BlockNode {
    public AssignNode() {}

    public AssignNode(String value) {
        super(value);
    }

    public Object run(Context c) throws ParseException {
        String varName = this.children.get(0).getValue();
        Object varValue = this.children.get(1).run(c);
        switch (Util.getVariableType(varName)) {
            case STRING:
                if(varValue instanceof String) {
                    c.addVariable(varName, varValue);
                } else {
                    throw new StringException(varName);
                }
                break;
            case INTEGER:
            case LONG:
                if(varValue instanceof Number) {
                    if(varValue instanceof Integer || varValue instanceof Long) {
                        c.addVariable(varName, varValue);
                    } else {
                        c.addVariable(varName, Math.round((double) varValue));
                    }
                } else {
                    throw new NumericException(varName);
                }
                break;
            case SINGLE:
            case DOUBLE:
            default:
                if(varValue instanceof Number) {
                    c.addVariable(varName, Util.parseDouble(varValue));
                } else {
                    throw new NumericException(varName);
                }
        }
        return this;
    }
}

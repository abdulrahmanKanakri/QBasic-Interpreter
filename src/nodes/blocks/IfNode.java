package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import nodes.Node;
import utils.Util;

import java.util.ArrayList;
import java.util.List;

public class IfNode extends BlockNode {
    public IfNode() {}

    public IfNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {
        for (int i = 0; i < this.children.size(); i += 2) {
            if(this.children.get(i) instanceof ExpressionNode) {
                Object exp = this.children.get(i).run(c);
                if(Util.isNumeric(exp.toString())) {
                    if(Util.parseDouble(exp) != 0) {
                        // run the block
                        this.children.get(i + 1).run(c);
                        break;
                    }
                } else {
                    throw new ParseException("Expected numeric expression");
                }
            } else {
                // run the block
                this.children.get(i).run(c);
            }
        }
        return this;
    }
}

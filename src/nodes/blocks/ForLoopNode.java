package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import nodes.Node;
import utils.Util;

import java.util.ArrayList;
import java.util.List;

public class ForLoopNode extends BlockNode {
    public ForLoopNode() {}

    public ForLoopNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {
        // assign
        Node assign = (Node) this.children.get(0).run(c);
        String cntName = assign.getChildren().get(0).getValue();
        if(c.getVariables().get(cntName) instanceof String)
            throw new ParseException("Loop counter '" + cntName + "' must be numeric");
        double from = Util.parseDouble(c.getVariables().get(cntName));

        // check the var after <NEXT>
        String cntName1 = this.children.get(this.children.size() - 1).getValue();
        if(!cntName1.equals(cntName))
            throw new ParseException("Mismatched loop counter '" + cntName1 + "' in NEXT");

        // to
        if(this.children.get(1).run(c) instanceof String)
            throw new ParseException("TO must be number");
        double to = Util.parseDouble(this.children.get(1).run(c));

        // with step
        if(this.children.get(2) instanceof ExpressionNode) {
            if(this.children.get(2).run(c) instanceof String)
                throw new ParseException("STEP must be number");
            double step = Util.parseDouble(this.children.get(2).run(c));

            // perform the loop and run the block
            if(step > 0) {
                for(double i = from; i <= to; i += step) {
                    c.getVariables().put(cntName, i);
                    this.children.get(3).run(c);
                }
            } else {
                for(double i = from; i >= to; i += step) {
                    c.getVariables().put(cntName, i);
                    this.children.get(3).run(c);
                }
            }
        } else {    // without step
            for(double i = from; i <= to; i++) {
                c.getVariables().put(cntName, i);
                this.children.get(2).run(c);
            }
        }
        // removing the variable that used in loop
        c.getVariables().remove(cntName);
        return this;
    }
}

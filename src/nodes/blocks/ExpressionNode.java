package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import nodes.Node;

import java.util.ArrayList;
import java.util.List;

public class ExpressionNode extends BlockNode {
    public ExpressionNode() {}

    public ExpressionNode(String value) {
        super(value);
    }

    @Override
    public Object run(Context c) throws ParseException {
        List<Object> objects = new ArrayList<>();
        for(Node child : this.children) {
            objects.add(child.run(c));
        }
        return objects;
    }
}

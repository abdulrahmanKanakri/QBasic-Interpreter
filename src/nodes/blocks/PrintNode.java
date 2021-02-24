package nodes.blocks;

import compiler.ParseException;
import nodes.Context;
import nodes.Node;

public class PrintNode extends BlockNode {
    public PrintNode() {}

    public PrintNode(String value) {
        super(value);
    }

    public Object run(Context c) throws ParseException {
        for(Node child : this.children) {
            System.out.print(child.run(c));
        }
        System.out.println();
        return this;
    }
}

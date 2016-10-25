package imagene.watchmaker.gp.functions.oneArg;

import imagene.watchmaker.gp.node.Node;
import imagene.watchmaker.gp.node.UnaryNode;


/***************************************
 * Written by Andrew Sanger (s3440468) *
 * and Dorothea Baker (s3367422)       *
 * for                                 *
 * Programming Project 1               *
 * SP3 2016                            *
 ***************************************/

public class Cube extends UnaryNode {
    public Cube(Node left)
    {
        super(left, "cube");
    }


    public double evaluate(double[] programParameters)
    {
        return Math.pow(left.evaluate(programParameters), 3);
    }


    public Node simplify()
    {
        return left.simplify();
    }
}

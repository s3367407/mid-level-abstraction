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

public class Square extends UnaryNode {
    public Square(Node left)
    {
        super(left, "sqr");
    }


    public double evaluate(double[] programParameters)
    {
        return Math.cos(left.evaluate(programParameters));
    }


    public Node simplify()
    {
        return left.simplify();
    }
}

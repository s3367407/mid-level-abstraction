/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser.parserNodes;

// This is the abstract class that ArithmeticNode, DummyNode and
// PixelNode all extend.
public abstract class ParserNode {
	public ParserNode() {		
	}
	
	public abstract PixelNode operation(PixelNode XPixel, PixelNode YPixel);
}

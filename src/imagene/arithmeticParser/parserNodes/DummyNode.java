/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser.parserNodes;

// This DummyNode extends ParserNode and is essentially a place holder for
// the X and Y PixelNode objects.
public class DummyNode extends ParserNode {
	// Shows what PixelNode object this is a placeholder for.
	private DUMMY_PIXEL DummyType;
	
	// Enum for ease of readability.
	public enum DUMMY_PIXEL {
		X_PIXEL, Y_PIXEL
	}
	
	// Constructor.
	public DummyNode() {
	}
	
	// Getters and setters
	public DUMMY_PIXEL getType() {
		return this.DummyType;
	}
	
	public void setType(DUMMY_PIXEL type) {
		this.DummyType = type;
	}

	// Overriden operation function from ParserNode that is never used.
	@Override
	public PixelNode operation(PixelNode XPixel, PixelNode YPixel) {
		return null;
	}
}

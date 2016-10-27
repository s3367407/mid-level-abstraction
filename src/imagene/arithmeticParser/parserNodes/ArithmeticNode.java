/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser.parserNodes;

import imagene.arithmeticParser.IArithmetic;

// This node extends ParserNode and contains an arithmetic argument, plus
// links to its previous Node, and any Node it may contain inside
// it, which can be either another ArithmeticNode, a DummyNode or a
// PixelNode.
public class ArithmeticNode extends ParserNode {
	// If this is set to true, then testing text will appear when run.
	private boolean CURRENTLY_TESTING = false;
	
	// A link to the previous Node.
	private ArithmeticNode upNode = null;	
	
	// Links to this nodes arguments.
	private ParserNode firstArgument = null;
	private ParserNode secondArgument = null;
	
	// The lambda expression that this node consists of.
	private IArithmetic nodeArithmetic;
	
	// How many arguments this Node contains. eg sin would have 1 argument
	// whilst plus would have 2.
	private int numOfArgs;
	
	// Enum to help with code readability. Decides which color/s the
	// lambda expression will be run on.
	private enum COLOR_TYPE {
		RED, GREEN, BLUE, ALL
	}

	// Constructor, which utilizes link to previous Node.
	public ArithmeticNode(ArithmeticNode upNode) {
		super();
		this.upNode = upNode;
	}
	
	// Getters and Setters.
	public void setTesting(boolean isTesting) {
		this.CURRENTLY_TESTING = isTesting;
	}
	
	public ArithmeticNode getUpNode() {
		return this.upNode;
	}
	
	public void setUpNode(ArithmeticNode upNode) {
		this.upNode = upNode;
	}
	
	public ParserNode getFirstArg() {
		return this.firstArgument;
	}
	
	public void setFirstArg(ParserNode firstArg) {
		this.firstArgument = firstArg;
	}
	
	public ParserNode getSecondArg() {
		return this.secondArgument;
	}
	
	public void setSecondArg(ParserNode secondArg) {
		this.secondArgument = secondArg;
	}
	
	public IArithmetic getArithmetic() {
		return this.nodeArithmetic;
	}
	
	public void setNodeArithmetic(IArithmetic newArithmetic, 
			int numOfArgs) {
		this.nodeArithmetic = newArithmetic;
		this.numOfArgs = numOfArgs;
	}
	
	public int getNumOfArgs() {
		return this.numOfArgs;
	}
	
	// The following 4 operations each run this nodes arithmetic on
	// either the pixels red, green, or blue values, or all values..
	public double redOperation(PixelNode XPixel, PixelNode YPixel) {
		return this.operation(XPixel, YPixel, COLOR_TYPE.RED).getRedColor();
	}
	
	public double greenOperation(PixelNode XPixel, PixelNode YPixel) {
		return this.operation(XPixel, YPixel, COLOR_TYPE.GREEN).getGreenColor();
	}
	
	public double blueOperation(PixelNode XPixel, PixelNode YPixel) {
		return this.operation(XPixel, YPixel, COLOR_TYPE.BLUE).getBlueColor();
	}
	
	@Override
	public PixelNode operation(PixelNode XPixel, PixelNode YPixel) {
		return this.operation(XPixel, YPixel, COLOR_TYPE.ALL);
	}
	
	public double operation(int xValue, int yValue) {
		PixelNode xPixelValues = new PixelNode(xValue, 0, 0);
		PixelNode yPixelValues = new PixelNode(yValue, 0, 0);
		
		return this.operation(xPixelValues, yPixelValues, COLOR_TYPE.RED).getRedColor();
	}
	
	// This function completes this Nodes arithmetic on the supplied
	// Pixel values. It returns a PixelNode object which contains
	// a red, green and blue value.
	private PixelNode operation(PixelNode XPixel, PixelNode YPixel,
			COLOR_TYPE color) {
		PixelNode firstNode = null, secNode = null, newNode;
		double newRed = 0, newGreen = 0, newBlue = 0;		
		
		// The following checks to see whether this Node's first argument
		// is an instance of DummyNode, and if it is, replaces it with
		// the appropriate X or Y pixel object. Also checks to see whether
		// it is already a PixelNode (in the case of a rand, or pi
		// argument). If it is neither of these, then the first argument
		// is yet another ArithmeticNode, and the operation() function
		// is called for it recursively, which returns a PixelNode.
		if (this.firstArgument instanceof DummyNode) {
			DummyNode dumNode = (DummyNode)this.firstArgument;
			
			if (dumNode.getType() == DummyNode.DUMMY_PIXEL.X_PIXEL) {
				firstNode = XPixel;
			} else {
				firstNode = YPixel;
			}
		} else if (this.firstArgument instanceof PixelNode) {
			firstNode = (PixelNode)this.firstArgument;
		} else {
			firstNode = this.firstArgument.operation(XPixel, YPixel);
		}
		
		// As above, but with the second argument (if it exists for
		// this node.
		if (this.secondArgument instanceof DummyNode) {
			DummyNode dumNode = (DummyNode)this.secondArgument;
			
			if (dumNode.getType() == DummyNode.DUMMY_PIXEL.X_PIXEL) {
				secNode = XPixel;
			} else {
				secNode = YPixel;
			}
		} else if (this.secondArgument instanceof PixelNode) {
			secNode = (PixelNode)this.secondArgument;
		} else if (this.secondArgument != null){
			secNode = this.secondArgument.operation(XPixel, YPixel);
		}
		
		// First checks to see whether a second argument for this
		// Node actually exists, and if it doesn't (in the case of sin, cos
		// etc) will run this Nodes arithmetic operation on the finalised
		// PixelNode objects, returning color values.. The second part of
		// this code does the same, but for two argument operations (eg
		// plus, minus etc).
		if (this.secondArgument == null) {
			if (color == COLOR_TYPE.ALL) {
				newRed = this.nodeArithmetic.operation(firstNode.getRedColor());
				newGreen = this.nodeArithmetic.operation(firstNode.getGreenColor());
				newBlue = this.nodeArithmetic.operation(firstNode.getBlueColor());
			} else if (color == COLOR_TYPE.RED) {
				newRed = this.nodeArithmetic.operation(firstNode.getRedColor());
			} else if (color == COLOR_TYPE.GREEN) {
				newGreen = this.nodeArithmetic.operation(firstNode.getGreenColor());
			} else {
				newBlue = this.nodeArithmetic.operation(firstNode.getBlueColor());
			}		
		} else {
			if (color == COLOR_TYPE.ALL) {
				newRed = this.nodeArithmetic.operation(
						firstNode.getRedColor(), secNode.getRedColor());
				newGreen = this.nodeArithmetic.operation(
						firstNode.getGreenColor(), secNode.getGreenColor());
				newBlue = this.nodeArithmetic.operation(
						firstNode.getBlueColor(), secNode.getBlueColor());
			} else if (color == COLOR_TYPE.RED) {
				newRed = this.nodeArithmetic.operation(
						firstNode.getRedColor(), secNode.getRedColor());
			} else if (color == COLOR_TYPE.GREEN) {
				newGreen = this.nodeArithmetic.operation(
						firstNode.getGreenColor(), secNode.getGreenColor());
			} else {
				newBlue = this.nodeArithmetic.operation(
						firstNode.getBlueColor(), secNode.getBlueColor());
			}
		}
		
		// Test code, which shows the red green and blue values after
		// having the arithmetic operation carried out on them.
		if (this.CURRENTLY_TESTING == true) {
			System.out.println("Values before being normalized by PixelNode");
			System.out.println("New value for red: " + newRed);
			System.out.println("New value for green: " + newGreen);
			System.out.println("New value for blue: " + newBlue);
			System.out.println();
		}
		
		// Returns the appropriate color/s.
		if (color == COLOR_TYPE.ALL) {
			newNode = new PixelNode(newRed, newGreen, newBlue);
		} else if (color == COLOR_TYPE.RED) {
			newNode = new PixelNode(newRed, 0, 0);
		} else if (color == COLOR_TYPE.GREEN) {
			newNode = new PixelNode(0, newGreen, 0);
		} else {
			newNode = new PixelNode(0, 0, newBlue);
		}
		
		return newNode;
	}
}

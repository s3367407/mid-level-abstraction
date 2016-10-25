/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser.parserNodes;

// This PixelNode extends ParserNode, and is designed to hold RGB values
// for X and Y pixels.
//
// (Version 1.1)
// - Now accepts Integers
// - No longer fixes number so that it is between 0 - 255
public class PixelNode extends ParserNode{
	// RGB color values.
	private double redColor;
	private double blueColor;
	private double greenColor;
	
	// Constructor.
	//
	// (Version 1.1)
	// Included integer constructor.
	public PixelNode(double red, double green, double blue){
		this.redColor = this.fixColor(red);
		this.greenColor = this.fixColor(green);
		this.blueColor = this.fixColor(blue);
	}
	
	public PixelNode(int red, int green, int blue) {
		this.redColor = this.fixColor((double)red);
		this.greenColor = this.fixColor((double)green);
		this.blueColor = this.fixColor((double)blue);
	}
	
	// Setter to set all colors at once.
	//
	// (Version 1.1)
	// Included integer setters.
	public void setAllColor(double newRed, double newBlue, double newGreen) {
		this.setRedColor(newRed);
		this.setBlueColor(newBlue);
		this.setGreenColor(newGreen);
	}
	
	public void setAllColor(int newRed, int newBlue, int newGreen) {
		this.setAllColor((double) newRed, (double) newBlue, (double) newGreen);
	}
	
	// Getters and setters.
	//
	// (Version 1.1)
	// All setters now accept integers.
	public double getRedColor() {
		return this.redColor;
	}
	
	public void setRedColor(double newRed) {
		this.redColor = this.fixColor(newRed);
	}
	
	public void setRedColor(int newRed) {
		this.setRedColor((double)newRed);
	}
	
	public double getBlueColor() {
		return this.blueColor;
	}
	
	public void setBlueColor(double newBlue) {
		this.blueColor = this.fixColor(newBlue);
	}
	
	public void setBlueColor(int newBlue) {
		this.setBlueColor((double)newBlue);
	}
	
	public double getGreenColor() {
		return this.greenColor;
	}
	
	public void setGreenColor(double newGreen) {
		this.greenColor = this.fixColor(newGreen);
	}
	
	public void setGreenColor(int newGreen) {
		this.setGreenColor((double)newGreen);
	}
	
	// This function will normalize any double numbers that are received
	// by this object. This means that all values will always be between
	// 0 and 255. Negative numbers are turned into their positive
	// counterparts, 0 is turned into 1 (to stop issues with some Arithmetic
	// operations that don't like 0), and anything above 255 is returned
	// to 255.
	//
	// (Version 1.1)
	// Changed this section to accept any number, changes NaN to 1, and
	// positive or negative infinity to their min/max counterparts. No longer
	// normalizes to between 0 - 255
	public double fixColor(double color) {
		if (color == Double.NaN) {
			return 1;
		} else if (color == Double.NEGATIVE_INFINITY) {
			return Double.MIN_VALUE;
		} else if (color == Double.POSITIVE_INFINITY) {
			return Double.MAX_VALUE;
		/*} else if (color < 0) {
			return (Math.abs(color));*/
		} else if (color == 0) {
			return 1;
		} 
		
		else return color;
	}

	// This operation function is overriden, but never used for PixelNode
	@Override
	public PixelNode operation(PixelNode XPixel, PixelNode YPixel) {
		return null;
	}
	
	// Displays information about this Object, and the colors contained
	// within. Also normalizes the colors into 0 - 255 (
	//
	// NOTE: Normalizing the numbers was just a test i was trialing,
	// due to the fact that most numbers received by this code ended up
	// being very low, in the 0 - 5 range.
	//
	// (Version 1.1)
	// Removed the normalizing test.
	public void testAsString() {
		System.out.println("==============================");
		System.out.println("VALUES CURRENTLY IN PIXEL NODE");
		System.out.println("==============================");
		System.out.println("Red Color is : " + this.redColor);
		System.out.println("Green Color is : " + this.greenColor);
		System.out.println("Blue Color is : " + this.blueColor);
		// System.out.println("\nTEST OF VALUES NORMALIZED TO 0-255\n");
		// System.out.println("Red : " + (int)((this.redColor % 1) * 255));
		// System.out.println("Green : " + (int)((this.greenColor % 1) * 255));
		// System.out.println("Blue : " + (int)((this.blueColor % 1) * 255));
		System.out.println();
	}
}

/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser;

import imagene.arithmeticParser.parserExceptions.*;
import imagene.arithmeticParser.parserNodes.*;
import java.util.Random;

// This singleton is the access point for the Arithmetic Parser. Once created
// there are two functions that can be called. The setTesting() function will allow
// the user to allow testing text to be displayed while the code is being run.
// The getArithmetic() function is the meat and bones of this Parser, and when supplied
// with a string, will check it and create a tree of Arithmetic Nodes, that will
// contain IArtihmetic objects that can operated on Pixel RGB values.
//
// (Version 1.1)
// - Code changed to allow for different spacing in strings.
// - New arithmetic options added.
public class ParserInterface {
	
	// Final variable used in following arithmetic.
	private final double PI = 3.14159;
	private final int RAND_MIN = 0;
	private int RAND_MAX = 255;
	
	// Boolean value that decides whether testing code should be
	// allowed to be displayed while the code is being run.
	private boolean CURRENTLY_TESTING = false;
	
	// (Version 1.1)
	// Enum to help with formatting of string, this code is originally set up to work
	// with strings that have spaces between each part of the string. eg "( cos ( sin"
	// etc. After recent changes the new default format is "(cos (sin". Please read the
	// provided readme for more information.
	public enum STRING_SPACING {
		NONE, OPEN, CLOSE, OPEN_CLOSE
	}
	
	// (Version 1.1)
	// Private variable which tels the code what kind of string spacing to expect. As
	// above please read the provided readme for more information.
	private STRING_SPACING SPACING = STRING_SPACING.NONE;
	
	// The following IArithmetic operations are the basis for this code.
	// ArithmeticNodes are created containing one of the following lambda
	// operations.

	// The following arithmetics take two inputs.
	//
	// (Version 1.1)
	// - Code changed to match other modules of the program.
	// - cbrt/cubeRoot option added.
	private IArithmetic addition = (double[] input) 
			-> input[0] + input[1];
	private IArithmetic subtraction = (double[] input) 
			-> input[0] - input[1];
	private IArithmetic multiplication = (double[] input)
			-> input[0] * input[1];
	private IArithmetic division = (double[] input) 
			-> input[0] / input[1];
	private IArithmetic modulus = (double[] input) 
			-> input[0] % input[1];
	
	// The following arithmetics take 1 input.
	private IArithmetic squareRoot = (double[] input)
			-> Math.sqrt(input[0]);
	private IArithmetic squared = (double[] input) 
			-> input[0] * input[0];
	private IArithmetic cubed = (double[] input) 
			-> input[0] * input[0] * input[0];
	private IArithmetic cubeRoot = (double[] input)
			-> Math.cbrt(input[0]);
	private IArithmetic sine = (double[] input)
			-> Math.sin((input[0] % PI));
	private IArithmetic cosine = (double[] input) 
			-> Math.cos((input[0] % PI));
	private IArithmetic tangent = (double[] input)
			-> Math.tan(input[0]);
	private IArithmetic logarithm = (double[] input) 
			-> Math.log(Math.abs(input[0]));
	
	// Singleton code. Only one instance should ever be needed.
	private ParserInterface() {		
	}
	
	private static class SingletonHolder {
		private static final ParserInterface INSTANCE = new ParserInterface();
	}
	
	public static ParserInterface getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	// Sets whether this code is in testing mode. Determines whether
	// test text will be displayed as code is run.
	public void setTesting(boolean isTesting) {
		this.CURRENTLY_TESTING = isTesting;
	}
	
	// Included new option to change random max value to user defined.
	public void setRandMax(int newMax) {
		this.RAND_MAX = newMax;
	}
	
	// The main part of this code. Takes a string as input and returns
	// an ArithmeticNode object that is the root of the Arithmetic tree created.
	public ArithmeticNode getArithmetic(String initialString) 
			throws InvalidArgumentException, IncorrectVariablesException {
		// (Version 1.1)
		// Formats the string depending on current spacing.
		initialString = fixString(initialString);
		
		// Changes string to lowercase.
		initialString = initialString.toLowerCase();
		
		// Creates an array out of the string.
		String[] tokenizedStringArray = initialString.split(" ");
		
		// Holds the root node of the tree.
		ArithmeticNode firstNode = null;
		
		// Gets the results of the tree, can throw 2 different exceptions which
		// must be handled by the calling code. The InvalidArgumentException
		// is thrown when an argument in the string supplied is invalid. And 
		// the IncorrectVariablesException is thrown when there is an incorrect
		// variable (eg x and y) or there are too many or not enough variables 
		// eg (plus x y y)
		try {
			firstNode = checkStringArray(tokenizedStringArray);
		} catch (InvalidArgumentException e) {
			throw new InvalidArgumentException(e.toString());
		} catch (IncorrectVariablesException e) {
			throw new IncorrectVariablesException(e.toString());
		}
		
		// Returns the rootNode of the tree which contains links to all nodes.
		return firstNode;		
	}
	
	// This function iterates over the string array and creates
	// a tree of Nodes by parsing what is in the string. Returns the
	// root node of the tree.
	private ArithmeticNode checkStringArray(String[] stringArray) 
			throws InvalidArgumentException, IncorrectVariablesException {
		// Node references to help with navigation through the tree.
		ArithmeticNode firstNode = null;
		ArithmeticNode prevNode = null, currNode = null;
		
		// Integers containing the length of the string array, and the current
		// position of the parser in the array.
		int arrayLength = stringArray.length;
		int arrayPosition = 0;
		
		// String containing the current argument from the string being
		// checked by the parser. And a boolean deciding whether a new node
		// has just been created.
		String currentArg;
		boolean isNewNode = true;
		
		// This code is only run if testing is allowed.
		if (CURRENTLY_TESTING == true) {
			System.out.println("There are " + arrayLength + 
					" arguments in this string");
		}
		
		// Iterates through the string array, checking each string
		// as it goes.
		while (arrayPosition != arrayLength) {
			// Gets current argument from the current position in the string.
			currentArg = stringArray[arrayPosition];
			
			// Test code.
			if (CURRENTLY_TESTING == true) {
				System.out.println("\nCurrent Array Position: " + arrayPosition);
				System.out.println("Current String: " + currentArg);
			}

			// This piece of code is only run when the parser begins.. Creates
			// the initial root node of the tree.
			if (arrayPosition == 0) {
				// Holds the lambda function created by this part of the array.
				IArithmetic newFunction;
				
				// Checks to see whether first argument is valid and creates
				// lambda function if it is.
				
				if (currentArg.equals("(")) {
					arrayPosition++;
					currentArg = stringArray[arrayPosition];
					
					if (CURRENTLY_TESTING == true) {
						System.out.println("\nCurrent Array Position: " + arrayPosition);
						System.out.println("Current String: " + currentArg);
					}
				}
				
				try {
					newFunction = returnValidFunction(currentArg);
				} catch(InvalidArgumentException e) {
					throw new InvalidArgumentException(
							"Invalid Starting Argument: " + currentArg);
				}
				
				// Test code.
				if (CURRENTLY_TESTING == true) {
					System.out.println("Creating first node");
				}
				
				// Decides how many arguments this function can take.
				// eg sin takes 1, plus takes 2
				int numOfArgs = this.returnNumOfArgs(currentArg);
				
				// Creates the firstNode of the tree.
				firstNode = new ArithmeticNode(null);
				firstNode.setNodeArithmetic(newFunction, numOfArgs);
				
				// Test code.
				if (this.CURRENTLY_TESTING == true) {
					firstNode.setTesting(true);
				}
				
				// Sets first node as the current node, to assist with navigation.
				currNode = firstNode;
				
				isNewNode = true;
			// If this isn't the first argument of the string then the following
			// code is run.
			} else {
				// If current argument is open bracket, continues.
				if (currentArg.equals("(")) {
					isNewNode = false;
				// If current argument is close bracket, sets currNode to
				// previous node in tree.
				} else if (currentArg.equals(")")) {
					currNode = currNode.getUpNode();
					isNewNode = false;
				// If current argument is either x or y, creates a DummyNode
				// to act as a placeholder for future X and Y values.
				} else if (currentArg.equals("x") ||
						currentArg.equals("y")) {
					DummyNode newDummy = new DummyNode();
					
					// Sets the type of the DummyNode to appropriate type.
					if (currentArg.equals("x")) {
						newDummy.setType(DummyNode.DUMMY_PIXEL.X_PIXEL);
					} else {
						newDummy.setType(DummyNode.DUMMY_PIXEL.Y_PIXEL);					
					}
					
					// Sets Dummy node in appropriate position, and throws
					// exception if too many arguments already exist for 
					// the current node.
					if (currNode.getFirstArg() == null) {
						currNode.setFirstArg(newDummy);
					} else if (currNode.getSecondArg() == null) {
						currNode.setSecondArg(newDummy);
					} else {
						throw new IncorrectVariablesException(
								"Error: Too many arguments for Arithmetic at array " +
								"postion" + arrayPosition + " : " + currentArg);
					}
					
					isNewNode = true;
				// If current argument is "pi", creates a PixelNode with value for
				// pi in RGB values.
				} else if (currentArg.equals("pi")) {
					PixelNode newPiPixel = new PixelNode(PI, PI, PI);
					
					// Sets PixelNode in appropriate position, and throws
					// exception if too many arguments already exist for 
					// the current node.
					if (currNode.getFirstArg() == null) {
						currNode.setFirstArg(newPiPixel);
					} else if (currNode.getSecondArg() == null) {
						currNode.setSecondArg(newPiPixel);
					} else {
						throw new IncorrectVariablesException(
								"Error: Too many arguments for Arithmetic at array " +
								"postion" + arrayPosition + " : " + currentArg);
					}
				// If current argument is "rand", creates a PixelNode with random
				// values between 0 - 255 for all RGB values.
				} else if (currentArg.equals("rand")) {
					PixelNode newRandPixel = new PixelNode(
							this.randNum(), this.randNum(), this.randNum());
					
					// Sets PixelNode in appropriate position, and throws
					// exception if too many arguments already exist for 
					// the current node.
					if (currNode.getFirstArg() == null) {
						currNode.setFirstArg(newRandPixel);
					} else if (currNode.getSecondArg() == null) {
						currNode.setSecondArg(newRandPixel);
					} else {
						throw new IncorrectVariablesException(
								"Error: Too many arguments for Arithmetic at array " +
								"postion" + arrayPosition + " : " + currentArg);
					}
				// If current argument is anything other than the above, checks
				// whether it is a valid argument and creates a new ArithmeticNode if
				// so.
				} else {
					IArithmetic newFunction;
					ArithmeticNode newNode;
					
					// Tries to create a new Arithmetic Function, and if current
					// argument is invalid, throws an exception.
					try {
						newFunction = returnValidFunction(currentArg);
					} catch(InvalidArgumentException e) {
						throw new InvalidArgumentException(
								"Invalid Argument at array position " +
								arrayPosition + " : " + currentArg);
					}
					
					int numOfArgs = this.returnNumOfArgs(currentArg);
					
					// Creates a new node, and sets its arithmetic and
					// number of arguments.
					newNode = new ArithmeticNode(prevNode);
					newNode.setNodeArithmetic(newFunction, numOfArgs);
					
					// Test code.
					if (this.CURRENTLY_TESTING == true) {
						newNode.setTesting(true);
					}
					
					// Sets the newly created node into the correct position
					// in the argument list of the previous node. This allows
					// The previous node to access this new node from itself.
					// If there are too many arguments then an exception is thrown.
					if (prevNode.getFirstArg() == null) {
						prevNode.setFirstArg(newNode);
					} else if (prevNode.getSecondArg() == null && 
							prevNode.getNumOfArgs() == 2) {
						prevNode.setSecondArg(newNode);
					} else {
						throw new IncorrectVariablesException(
								"Error: Too many arguments for Arithmetic at array " +
								"postion" + arrayPosition + " : " + currentArg);
					}
							
					currNode = newNode;
					
					isNewNode = true;
				}
			}
			
			// Helps with ease of navigation around tree.
			if (isNewNode == false) {
				prevNode = currNode;
			}
			
			arrayPosition++;
		}
		
		// Returns the first node of the tree, which has recursive links to all
		// other nodes in the tree.
		return firstNode;
	}
	
	// (Version 1.1)
	// Sets spacing to user requirement.
	public void setSpacing(STRING_SPACING newSpacing) {
		this.SPACING = newSpacing;
	}
	
	// (Version 1.1)
	// This function will format the string so it will be accepted by the code.
	private String fixString(String currentString) {
		if (this.SPACING == STRING_SPACING.NONE) {
			currentString = currentString.replace("(", "( ");
			currentString = currentString.replace(")", " )");
		} else if (this.SPACING == STRING_SPACING.OPEN) {
			currentString = currentString.replace(")", " )");
		} else if (this.SPACING == STRING_SPACING.CLOSE) {
			currentString = currentString.replace("(", "( ");
		}		
		
		if (this.CURRENTLY_TESTING == true) {
			System.out.println("---+++Adding spaces to String+++---");
			System.out.println("Final String : " + currentString);
		}
		
		return currentString;
	}
	
	// Helper function which returns a random number between RAND_MAX and RAND_MIN.
	private double randNum() {
		Random rand = new Random();
		
		int randomNum = rand.nextInt((RAND_MAX - RAND_MIN) + 1) + RAND_MIN;
		
		return (double)randomNum;
	}
	
	// Takes a string, and checks to see how many arguments it takes
	// if it is a valid argument. eg sqrt etc take 1 argument while plus
	// etc take 2. Returns -1 if it is an invalid argument, but this never
	// occurs because returnValidFunction will throw an exception before this
	// happens.
	//
	// (Version 1.1)
	// - Code changed to match other modules of the program.
	// - cbrt option added.
	private int returnNumOfArgs(String currentArg) {
		switch (currentArg) {
		case "sqrt" :
		case "sqr" :
		case "cube" :
		case "cbrt" :
		case "sin" :
		case "cos" :
		case "tan" :
		case "log" : return 1;
		case "add" :
		case "sub" :
		case "times" :
		case "div" : 
		case "mod" : return 2;
		default : return -1;
		}
	}
	
	// Takes a string, and checks to see whether it is a valid argument. And
	// if it is, then it returns the appropriate IArithmetic argument. Throws
	// and exception if the argument is not valid.
	//
	// (Version 1.1)
	// - Code changed to match other modules of the program.
	// - cbrt option added.
	private IArithmetic returnValidFunction(String funcType) 
			throws InvalidArgumentException {
		switch (funcType) {
		case "sqrt" : return this.squareRoot;
		case "sqr" : return this.squared;
		case "cube" : return this.cubed;
		case "cbrt" : return this.cubeRoot;
		case "sin" : return this.sine;
		case "cos" : return this.cosine;
		case "tan" : return this.tangent;
		case "log" : return this.logarithm;
		case "add" : return this.addition;
		case "sub" : return this.subtraction;
		case "times" : return this.multiplication;
		case "div" : return this.division;
		case "mod" : return this.modulus;
		default : throw new InvalidArgumentException(
				"Invalid Argument");		
		}
	}
}

/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser.parserExceptions;

// This Exception is thrown when the supplied argument string is invalid
// As an example, instead of receiving sin as input, it may get zin or si
// this exception would then be thrown.

@SuppressWarnings("serial")
public class InvalidArgumentException extends Exception {
	public InvalidArgumentException(String message) {
		super(message);
	}
}

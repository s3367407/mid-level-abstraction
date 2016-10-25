/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser.parserExceptions;

// This Exception is thrown when there are either too many or not enough
// variables in an argument. eg sine needs 1 argument, if there are 0 or
// 2 arguments, then this exception is thrown.
@SuppressWarnings("serial")
public class IncorrectVariablesException extends Exception{
	public IncorrectVariablesException(String message) {
		super(message);
	}
}

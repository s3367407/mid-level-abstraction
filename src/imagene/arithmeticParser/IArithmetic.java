/****************************
 * Arithmetic Parser v1.1   *
 *                          *
 * Created by Andrew Sanger *
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 ****************************/

package imagene.arithmeticParser;

// This interface allows for lambda Arithmetic functions to be stored
// as objects, and then run on code when the operation() function is
// called.
@FunctionalInterface
public interface IArithmetic {
	public double operation(double... input);
}

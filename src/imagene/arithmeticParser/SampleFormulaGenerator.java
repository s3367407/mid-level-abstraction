/****************************
 * SampleFormulaGenerator   *
 *                          *
 * Created by Dorothea Baker*
 * for                      *
 * Programming Project 1    *
 * SP3 2016                 *
 *                          *
 * Used with permisson      *
 ****************************/

package imagene.arithmeticParser;

import java.util.*;

/*
  getFormulaArray(populationSize)
  getFormulaArray(populationSize, parent1Index, parent2Index)
  Parameters
  ----------
  - populationSize: an int specifying the number of images you want to generate.
    Vic wants the code to be written this way for extensibility, but we'll just
    be passing the number 4 for now.
  - parent1Index, parent2Index: ints specifying the index of an image in an array,
    used to identify the parents for the next generation.
    No parents are provided for the first generation, hence the overload.
  Return value
  ------------
  Both methods return a String[][] array containing the number of subarrays
  specified by populationSize.
  Each subarray consists of 3 formula strings (one for each color channel
  in the image - R,G,B).
*/

public class SampleFormulaGenerator {

    private String[] sampleFormulas = {
        "(sqrt (add (cube (add (cos rand) (sqrt y))) (cbrt (sqr (sin pi)))))",
        "(sqrt (sqr (cbrt (add (sqr rand) (sqrt y)))))",
        "(add (add (sqrt (sqrt y)) (tan (cube rand))) (cos (tan (cbrt x))))",
        "(sqr (cbrt (cbrt (sin (add rand x)))))",
        "(cos (cbrt (cbrt (sin (cbrt y)))))",
        "(tan (add (cos y) (sqr pi)))",
        "(add (sqr (cube (tan pi))) (tan (tan (cube rand))))",
        "(add (tan x) (add (cbrt rand) (cos pi)))",
        "(cube (sqr (cos (cube (cos rand)))))",
        "(cube (cos (tan y)))",
        "sin (add (sqr (sqr (sqr rand))) (sin (sqr (sqr y)))))",
        "(add (tan (sqr (cbrt x))) (tan (add (add rand x) (cos x))))"
    };

    // For the initial generation, we don't provide any parents.
    public String[][] getFormulaArray(int populationSize) {
        String[][] formulaArray = new String[populationSize][3];
        Random rand = new Random();

        for (int i = 0; i < populationSize; i++) {
            String[] innerArray = new String[3];
            for (int j = 0; j < 3; j++) {
                int randomIndex = rand.nextInt(sampleFormulas.length);
                innerArray[j] = sampleFormulas[randomIndex];
            }
            formulaArray[i] = innerArray;
        }

        return formulaArray;
    }

    // For subsequent generations, we pass the IDs of 2 images to be used as the parents.
    // This is just a dummy method, so it doesn't do anything with them, but
    // the real code will pass the IDs along to ECJ to do stuff with.
    public String[][] getFormulaArray(int populationSize, int parent1, int parent2) {
        return getFormulaArray(populationSize);
    }

    // Call this to get a string you can print out for testing.
    // Tells you the number of images requested and pretty-prints the formula array.
    public String getPrintableInfo(int populationSize) {
        String[][] sampleFormulaArray = getFormulaArray(populationSize);

        String output = "\nRequested number of images: " + populationSize;

        output += "\n\nFormulas: \n[\n";

        for (int i = 0; i < sampleFormulaArray.length; i++) {
            output += "    [\n";

            for (int j = 0; j < sampleFormulaArray[i].length; j++) {
                output += "        " + sampleFormulaArray[i][j];

                if (j < sampleFormulaArray[i].length - 1) { output += ","; }
                output += "\n";
            }

            output += "    ]";

            if (i < sampleFormulaArray.length - 1) { output += ",\n\n"; }
            else { output += "\n"; }
        }

        return output + "]\n";
    }

    // Call this to get a string you can print out for testing.
    // Tells you the number of images requested, parents requested,
    // and pretty-prints the formula array.
    public String getPrintableInfo(int populationSize, int parent1Index, int parent2Index) {
        return "\nRequested parent indexes: " + parent1Index + ", " + parent2Index + getPrintableInfo(populationSize);
    }
}
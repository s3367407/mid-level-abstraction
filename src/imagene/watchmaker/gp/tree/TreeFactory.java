package imagene.watchmaker.gp.tree;

//=============================================================================
// Copyright 2006-2010 Daniel W. Dyer
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//=============================================================================

import imagene.watchmaker.gp.functions.oneArg.*;
import imagene.watchmaker.gp.functions.twoArg.*;
import imagene.watchmaker.gp.node.Node;
import imagene.watchmaker.gp.terminals.*;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.Random;


/**
 * {@link org.uncommons.watchmaker.framework.CandidateFactory} for generating
 * trees of {@link Node}s for the genetic programming example application.
*/

/********************************************************
 * Written by Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 * based on Watchmaker example class by Daniel Dyer
 *********************************************************/

public class TreeFactory extends AbstractCandidateFactory<Node>
{
    // The number of program parameters that each program gp.tree will be provided.
    private final int parameterCount;

    // The maximum depth of a program gp.tree.  No function nodes will be created below
    // this depth (branches will be terminated with parameters or constants).
    private final int maxDepth;

    // Probability that a created gp.node is a function gp.node rather
    // than a value gp.node.
    private final Probability oneArgFunctionProbability;

    // Probability that a value (non-function) gp.node is a parameter
    // gp.node rather than a constant gp.node.
    private final Probability parameterProbability;


    /**
     * @param parameterCount The number of program parameters that each
     * generated program tree can will be provided when executed.
     * @param maxDepth The maximum depth of generated trees.
     * @param oneArgFunctionProbability The probability (between 0 and 1) that a
     * randomly-generated node will be a function node rather than a value
     * (parameter or constant) node.
     * @param parameterProbability The probability that a randomly-generated
     * non-function node will be a parameter node rather than a constant node.
     */
    public TreeFactory(int parameterCount,
                       int maxDepth,
                       Probability oneArgFunctionProbability,
                       Probability parameterProbability)
    {
        if (parameterCount < 0)
        {
            throw new IllegalArgumentException("gp.gp.terminals.gp.terminals.Parameter count must be greater than or equal to 0.");
        }
        if (maxDepth < 1)
        {
            throw new IllegalArgumentException("Max depth must be at least 1.");
        }

        this.parameterCount = parameterCount;
        this.maxDepth = maxDepth;
        this.oneArgFunctionProbability = oneArgFunctionProbability;
        this.parameterProbability = parameterProbability;
    }


    /**
     * {@inheritDoc}
     */
    public Node generateRandomCandidate(Random rng)
    {
        return makeNode(rng, maxDepth);
    }


    /**
     * Recursively constructs a tree of Nodes, up to the specified maximum depth.
     * @param rng The RNG used to random create nodes.
     * @param maxDepth The maximum depth of the generated tree.
     * @return A tree of nodes.
     */
    private Node makeNode(Random rng, int maxDepth)
    {
        maxDepth--;

        if (maxDepth > 1)
        {

            if (oneArgFunctionProbability.nextEvent(rng)) {

                switch (rng.nextInt(8))
                {
                    case 0: return new Cos(makeNode(rng, maxDepth));
                    case 2: return new Tan(makeNode(rng, maxDepth));
                    case 3: return new Sin(makeNode(rng, maxDepth));

                    case 4: return new Square(makeNode(rng, maxDepth));
                    case 5: return new SquareRoot(makeNode(rng, maxDepth));
                    case 6: return new Cube(makeNode(rng, maxDepth));
                    case 7: return new CubeRoot(makeNode(rng, maxDepth));
                    default: return new Log(makeNode(rng, maxDepth));
                }

            } else {
                switch (rng.nextInt(4))
                {
                    case 0: return new Addition(makeNode(rng, maxDepth), makeNode(rng, maxDepth));
                    case 1: return new Subtraction(makeNode(rng, maxDepth), makeNode(rng, maxDepth));
                    case 2: return new Division(makeNode(rng, maxDepth), makeNode(rng, maxDepth));
                    default: return new Multiplication(makeNode(rng, maxDepth), makeNode(rng, maxDepth));
                }
            }

        } else if (parameterProbability.nextEvent(rng)) {
            return new Parameter(rng.nextInt(parameterCount));
        } else {
            return new Constant(Math.PI);
        }

    }
}

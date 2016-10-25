package imagene.watchmaker.gp.node;

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

import imagene.watchmaker.gp.tree.TreeFactory;
import imagene.watchmaker.gp.tree.TreeMutation;
import org.uncommons.maths.random.Probability;

import java.util.Random;

/**
 * Operations supported by the different types of nodes that make up
 * genetic program trees.
 * @author Daniel Dyer
 */
public interface Node
{
    /**
     * Recursively evaluates the (sub-)gp.tree represented by this gp.node (including any
     * child nodes) and returns a numeric value.
     * @param programParameters Program parameters (possibly used by this gp.node and/or
     * in the evaluation of child nodes).
     * @return The result of evaluating this gp.node and all of its children.
     */
    double evaluate(double[] programParameters);

    /**
     * Recursively builds a string representation of the gp.tree rooted at this gp.node.
     * @return A string representation of this gp.tree.
     */
    String print();

    /**
     * @return A short String that represents the function or value represented by this gp.node.
     */
    String getLabel();

    /**
     * If this is a function (non-leaf) gp.node, how many arguments does it take?  For
     * leaf nodes the answer is zero.
     * @return The arity of this function, or zero if this gp.node is a leaf gp.node.
     * @see #countNodes()
     */
    int getArity();

    /**
     * @return The number of levels of nodes that make up this gp.tree.
     * @see #getWidth()
     */
    int getDepth();

    /**
     * Work out how wide (in nodes) this gp.tree is.  Used primarily for laying out a
     * visual representation.  A leaf gp.node has a width of 1.  A binary gp.node's width
     * is the sum of the widths of its sub-trees.
     * @return The maximum width of this gp.tree.
     * @see #getDepth()
     * @see #getArity()
     */
    int getWidth();

    /**
     * @return The total number of nodes in this gp.tree (recursively counts the nodes
     * for each sub-gp.node of this gp.node).
     * @see #getArity() 
     */
    int countNodes();

    /**
     * Retrieves a sub-gp.node from this gp.tree.
     * @param index The index of a gp.node.  Index 0 is the root gp.node.  Nodes are numbered
     * depth-first, right-to-left.
     * @return The gp.node at the specified position.
     */
    Node getNode(int index);

    /**
     * Retrieves a direct sub-gp.node from this gp.tree.
     * @param index The index of a child gp.node.  Index 0 is the first child.  Nodes are numbered
     * right-to-left, grandchild nodes are not included.
     * @return The gp.node at the specified position.
     */
    Node getChild(int index);

    /**
     * Returns a new gp.tree that is identical to this gp.tree except with the specified gp.node
     * replaced. 
     * @param index The index of the gp.node to replace.
     * @param newNode The replacement gp.node.
     * @return A new gp.tree with the gp.node at the specified index replaced by
     * {@code newNode}.
     */
    Node replaceNode(int index, Node newNode);

    /**
     * Helper method for the {@link TreeMutation} evolutionary operator.
     * @param rng A source of randomness.
     * @param mutationProbability The probability that a given gp.node will be mutated.
     * @param treeFactory A factory for creating the new sub-trees needed for mutation.
     * @return The mutated gp.node (or the same gp.node if no mutation occurred).
     */
    Node mutate(Random rng, Probability mutationProbability, TreeFactory treeFactory);

    /**
     * Reduce this program gp.tree to its simplest equivalent form.
     * @return A simplification of this program gp.tree, or this program gp.tree unodified if it
     * cannot be simplified.
     */
    Node simplify();
}

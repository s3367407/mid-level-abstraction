package imagene.watchmaker.gp.node;

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

import imagene.watchmaker.gp.tree.TreeFactory;
import org.uncommons.maths.random.Probability;
import org.uncommons.util.reflection.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Random;

/**
 * Convenient base class for Nodes that have one subtree.
 */

/***************************************
 * Written by Andrew Sanger (s3440468) *
 * for                                 *
 * Programming Project 1               *
 * SP3 2016                            *
 ***************************************/

public abstract class UnaryNode implements Node
{
    protected static final double[] NO_ARGS = new double[0];

    protected final Node left;
    
    private final String symbol;
    
    protected UnaryNode(Node left, String symbol) {
    	this.left = left;
    	this.symbol = symbol;
    }
    
    public String getLabel() {
    	return symbol;
    }
    
    public int getArity() {
    	return 1;
    }
    
    public int getDepth() {
    	return 1 + left.getDepth();
    }
    
    public int getWidth() {
    	return left.getWidth();
    }
    
    public int countNodes() {
    	return 1 + left.countNodes();
    }
    
    public Node getNode(int index) {
    	if (index == 0) {
    		return this;
    	} else {
    		return left.getNode(index - 1);
    	}
    }
    
    public Node getChild(int index) {
    	if (index == 0) {
    		return left;
    	} else {
    		throw new IndexOutOfBoundsException("Invalid child index: " + index);
    	}
    }
    
    public Node replaceNode(int index, Node newNode) {
    	if (index == 0) {
    		return newNode;
    	} else {
    		return newInstance(left.replaceNode(index - 1, newNode));
    	}   	
    }
    
    public String print() {
    	StringBuilder buffer = new StringBuilder("(");
    	buffer.append(symbol);
    	buffer.append(' ');
    	buffer.append(left.print());
    	buffer.append(")");
    	return buffer.toString();
    }
    
    public Node mutate(Random rng, Probability mutationProbability, TreeFactory treeFactory) {
    	if (mutationProbability.nextEvent(rng)) {
    		return treeFactory.generateRandomCandidate(rng);
    	} else {
    		Node newLeft = left.mutate(rng,  mutationProbability, treeFactory);
    		
    		if (newLeft != left) {
    			return newInstance(newLeft);
    		} else {
    			return this;
    		}
    	}
    }
    
    private Node newInstance(Node newLeft) {
    	Constructor<? extends UnaryNode> constructor = ReflectionUtils.findKnownConstructor(
    			this.getClass(), Node.class);
    	
    	return ReflectionUtils.invokeUnchecked(constructor, newLeft);
    }
    
    @Override
    public String toString() {
    	return print();
    }
}

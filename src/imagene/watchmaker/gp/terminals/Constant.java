package imagene.watchmaker.gp.terminals;

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

import imagene.watchmaker.gp.node.LeafNode;

/***************************************
 * Written by Dorothea Baker (s3367422)*
 * for                                 *
 * Programming Project 1               *
 * SP3 2016                            *
 ***************************************/

public class Constant extends LeafNode
{
    private final double constant;
    private final String label = "pi"; // TODO pretty sure this is not meant to be hardcoded

    /**
     * Creates a constant-valued node.
     * @param constant The value that this node will always evaluate to.
     */
    public Constant(double constant)
    {
        this.constant = constant;
    }


    /**
     * @param programParameters The parameters passed to the program (ignored by this node).
     * @return The numeric value of this constant.
     */
    public double evaluate(double[] programParameters)
    {
        return constant;
    }


    /**
     * {@inheritDoc}
     */
    public String getLabel()
    {
        return label;
    }


    /**
     * @return The String representation of this constant.
     */
    public String print()
    {
        return label;
    }


    /**
     * Two constants are equal if they have the same numeric value.
     * @param other The object that this object is compared to.
     * @return True if the constants are equivalent, false otherwise.
     */
    @Override
    public boolean equals(Object other)
    {
        if (this == other)
        {
            return true;
        }
        if (other == null || getClass() != other.getClass())
        {
            return false;
        }
        return Double.compare(((Constant) other).constant, constant) == 0;
    }
}

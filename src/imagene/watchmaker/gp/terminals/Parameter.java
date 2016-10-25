package imagene.watchmaker.gp.terminals;//=============================================================================
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

import imagene.watchmaker.gp.node.*;

/***************************************
 * Written by Dorothea Baker (s3367422)*
 * for                                 *
 * Programming Project 1               *
 * SP3 2016                            *
 ***************************************/

public class Parameter extends LeafNode
{
    private final int parameterIndex;
    String[] paramTypes = new String[] {"x", "y", "rand"};

    /**
     * @param parameterIndex Which of the program's (zero-indexed) parameter
     * values should be returned upon evaluation of this node.
     */
    public Parameter(int parameterIndex)
    {
        this.parameterIndex = parameterIndex;
    }


    /**
     * Returns the value of one of the program parameters.
     * @param programParameters The parameters to this program.
     * @return The program parameter at the index configured for this node.
     */
    public double evaluate(double[] programParameters)
    {
        if (parameterIndex >= programParameters.length)
        {
            throw new IllegalArgumentException("Invalid parameter index: " + parameterIndex);
        }
        return programParameters[parameterIndex];
    }

    public String getLabel()
    {
        return paramTypes[parameterIndex];
    }

    public String print()
    {
        return paramTypes[parameterIndex];
    }


    /**
     * Two parameters are equal if they evaluate to the same program argument
     * (i.e. they have the same parameter index).
     * @param other The object that this object is compared to.
     * @return True if the parameters are equivalent, false otherwise.
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
        return parameterIndex == ((Parameter) other).parameterIndex;
    }


}

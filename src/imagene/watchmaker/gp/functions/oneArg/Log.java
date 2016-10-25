package imagene.watchmaker.gp.functions.oneArg;

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

import imagene.watchmaker.gp.node.BinaryNode;
import imagene.watchmaker.gp.node.Node;
import imagene.watchmaker.gp.node.UnaryNode;
import imagene.watchmaker.gp.terminals.Constant;


/***************************************
 * Written by Dorothea Baker (s3367422)*
 * for                                 *
 * Programming Project 1               *
 * SP3 2016                            *
 ***************************************/

public class Log extends UnaryNode
{
    public Log(Node left)
    {
        super(left, "log");
    }


    public double evaluate(double[] programParameters)
    {
        return Math.log(left.evaluate(programParameters));
    }

    public Node simplify()
    {
        return left.simplify();
    }
}

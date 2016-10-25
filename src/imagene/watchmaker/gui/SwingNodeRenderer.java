package imagene.watchmaker.gui;

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

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

import imagene.watchmaker.gp.node.*;
import org.uncommons.watchmaker.framework.interactive.Renderer;

/**
 * Renders formula trees as Swing components.
 */
// TODO additional credits

/********************************************************
 * Written by Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 * based on Watchmaker example class by Daniel Dyer
 *********************************************************/

public class SwingNodeRenderer implements Renderer<Node, JComponent>
{
    /**
     * Renders an evolved node as a component that can be displayed
     * in a Swing GUI.
     * @param node The node to render.
     * @return A component that displays a visual representation of the
     * node.
     */
    public JComponent render(Node node)
    {
        return new NodeView(node);
    }


    /**
     * A Swing component that can display a visual representation of a
     * node
     */
    private static final class NodeView extends JComponent
    {
        private final Node node;

        NodeView(Node node)
        {
            this.node = node;
            Dimension size = new Dimension(200, 200);
            setMinimumSize(size);
            setPreferredSize(size);
            System.out.println(node.toString()); // print current population to console for testing
        }


        @Override
        protected void paintComponent(Graphics graphics)
        {
            super.paintComponent(graphics);
            drawTree(graphics, node);
        }

        private void drawTree(Graphics graphics, Node node)
        {
            // TODO imagegen+arithmeticparser will go here!
            graphics.drawString(node.toString(), 10, 20);
        }

    }
}

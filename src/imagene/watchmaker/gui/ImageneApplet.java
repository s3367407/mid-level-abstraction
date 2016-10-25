package imagene.watchmaker.gui;

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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import imagene.watchmaker.gp.node.Node;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.swing.SpringUtilities;
import org.uncommons.swing.SwingBackgroundTask;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionObserver;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.interactive.InteractiveSelection;
import org.uncommons.watchmaker.framework.interactive.Renderer;
import org.uncommons.watchmaker.framework.termination.UserAbort;
import org.uncommons.watchmaker.swing.SwingConsole;

import imagene.watchmaker.gp.tree.*;
// TODO additional credits
/********************************************************
 * Written by Dorothea Baker (s3367422)
 * for
 * Programming Project 1
 * SP3 2016
 * based on Watchmaker example class by Daniel Dyer
 *********************************************************/

public class ImageneApplet extends AbstractExampleApplet
{
    private Renderer<Node, JComponent> renderer;
    private SwingConsole console;
    private JPanel selectionPanel;
    private ControlPanel settingsPanel;


    /**
     * Initialise and layout the GUI.
     * @param container The Swing component that will contain the GUI controls.
     */
    @Override
    protected void prepareGUI(Container container)
    {
        renderer = new SwingNodeRenderer();
        console = new SwingConsole(5);
        selectionPanel = new JPanel();
        selectionPanel.add(console);

        settingsPanel = new ControlPanel();

        container.add(settingsPanel, BorderLayout.WEST);
        container.add(selectionPanel, BorderLayout.EAST);
    }


    /**
     * Helper method to create a background task for running the interactive evolutionary
     * algorithm.
     * @param populationSize How big the population used by the created evolution engine
     * should be.
     * @return A Swing task that will execute on a background thread and update
     * the GUI when it is done.
     */
    private SwingBackgroundTask<Node> createTask(final int populationSize)
    {
        UserAbort terminationCondition = new UserAbort();

        int parameterCount, maxDepth, elitismNumber, maxSelectionsPerGen;
        Probability oneArgFuncProb, paramProb, mutationProb;

        parameterCount = 3; // x, y and rand
        maxDepth = 10;

        // TODO these may change
        oneArgFuncProb = new Probability(0.2);
        paramProb = new Probability(0.3);
        mutationProb = new Probability(0.5); // 50% chance of random mutation

        //maxSelectionsPerGen = 2;
        maxSelectionsPerGen = 1; // TODO this is just for the time being, until we can figure out how to make 2 work

        // Keep 2 individuals from the current population in the next generation.
        // Hopefully these are the 2 the user chose!
        elitismNumber = 2;

        return new SwingBackgroundTask<Node>()
        {
            @Override
            protected Node performTask()
            {
                TreeFactory factory = new TreeFactory(parameterCount, maxDepth, oneArgFuncProb, paramProb);

                //EvolutionaryOperator<Node> mutation = new TreeCrossover();
                // TODO this is just until we can figure out how to select 2 parents
                EvolutionaryOperator<Node> mutation = new TreeMutation(factory, mutationProb);

                InteractiveSelection<Node> selection = new InteractiveSelection<Node>(console, renderer, populationSize, maxSelectionsPerGen);
                EvolutionEngine<Node> engine = new GenerationalEvolutionEngine<Node>(factory, mutation, selection, new MersenneTwisterRNG());

                engine.addEvolutionObserver(new GenerationTracker());

                return engine.evolve(populationSize,
                                     elitismNumber,
                                     terminationCondition);
            }

            @Override
            protected void postProcessing(Node result)
            {
                terminationCondition.abort(); // end the evolution when user closes window
            }
        };
    }


    /**
     * Entry point for running this example as an application rather than an applet.
     * @param args Program arguments (ignored).
     */
    public static void main(String[] args)
    {
        new ImageneApplet().displayInFrame("Imagene");
    }


    /**
     * Simple observer to update the dialog title every time the evolution advances
     * to a new generation.
     */
    private final class GenerationTracker implements EvolutionObserver<Node>
    {
        public void populationUpdate(final PopulationData<? extends Node> populationData)
        {
            SwingUtilities.invokeLater(new Runnable()
            {
                public void run()
                {
                    String generationInfo = "Imagene - Generation "
                            + (populationData.getGenerationNumber() + 1);

                    // TESTING ONLY
                    System.out.println(); // print newline to console between generations
                    System.out.println(generationInfo);

                    // TODO this doesn't do anything atm. need to set title of main window instead
                    settingsPanel.setTitle(generationInfo);
                }
            });
        }
    }


    /**
     * Panel for controlling the evolutionary algorithm parameters.
     */
    private final class ControlPanel extends JPanel
    {
        private String windowTitle;
        private JSpinner populationSpinner;

        ControlPanel()
        {
            super(new BorderLayout());
            add(createInputPanel(), BorderLayout.NORTH);
            add(createButtonPanel(), BorderLayout.SOUTH);
            setBorder(BorderFactory.createTitledBorder("Evolution Controls"));
        }


        private JComponent createInputPanel()
        {
            JPanel inputPanel = new JPanel(new SpringLayout());
            JLabel populationLabel = new JLabel("Population Size: ");
            populationSpinner = new JSpinner(new SpinnerNumberModel(12, 2, 25, 1));
            populationLabel.setLabelFor(populationSpinner);
            inputPanel.add(populationLabel);
            inputPanel.add(populationSpinner);

            SpringUtilities.makeCompactGrid(inputPanel, 1, 2, 30, 6, 6, 6);

            return inputPanel;
        }


        private JComponent createButtonPanel()
        {
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton startButton = new JButton("Start");
            startButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent actionEvent)
                {
                    createTask( (Integer) populationSpinner.getValue() ).execute();
                }
            });
            buttonPanel.add(startButton);
            return buttonPanel;
        }

        public void setTitle(String title) {
            this.windowTitle = title;
        }


    }
}

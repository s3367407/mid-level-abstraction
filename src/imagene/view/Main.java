package imagene.view;
import javax.swing.*;

import imagene.viewmodel.ImageneViewModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by avishkar on 10/22/2016.
 */


public class Main {
	
	private static ImageneViewModel ivm;
	
    public static void main(String [] args)
    {
    	ivm = new ImageneViewModel();
    	
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new MainFrame("ImageEvolver v1.0");

                JPanel panelAbout=new JPanel();
                JPanel panelInstruction=new JPanel();
                JMenuBar menuBar=new JMenuBar();
                JMenu file=new JMenu("File");
                JMenu help=new JMenu("Help");
                JMenuItem close=new JMenuItem("Close");


                close.setMnemonic(KeyEvent.VK_Q);
                close.setAccelerator(KeyStroke.getKeyStroke(
                        KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
                close.setToolTipText("Exit application");
                close.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        System.exit(0);
                    }

                });


                JMenuItem about=new JMenuItem("About");
                JMenuItem instruction=new JMenuItem("Instruction");

                about.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JFrame frameAbout=new JFrame("About");

                        frameAbout.add(panelAbout);
                        panelAbout.setBackground(Color.GRAY);

                        frameAbout.setVisible(true);
                        frameAbout.setSize(400,500);
                        frameAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    }

                });

                instruction.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        JFrame frameInstruction=new JFrame("Instruction");

                        frameInstruction.add(panelInstruction);
                        panelInstruction.setBackground(Color.GRAY);

                        frameInstruction.setVisible(true);
                        frameInstruction.setSize(400,500);
                        frameInstruction.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    }

                });

                file.add(close);
                help.add(about);
                help.add(instruction);
                menuBar.add(file);
                menuBar.add(help);
                frame.setJMenuBar(menuBar);


                frame.setVisible(true);
                frame.setSize(900,650);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
    }


}

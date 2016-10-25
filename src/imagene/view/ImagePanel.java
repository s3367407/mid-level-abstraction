package imagene.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by avishkar on 10/23/2016.
 */
public class ImagePanel extends JPanel implements ConstantArrayField {

    private JLabel []  holdImage =new JLabel[ARRAY_INDEX] ;
    private JPanel [] holdImageLabel =new JPanel[ARRAY_INDEX] ;
    private ImageIcon [] icon;
    private JLabel label;
    private JButton btnGenerate;
    private JPanel panelForButton;
    private JPanel panelForLabel;
    private Insets insets=new Insets(20,10,10,10);



    public ImagePanel(){
        Dimension size = getPreferredSize();
        size.width=580;
        setPreferredSize(size);




        setBackground(Color.lightGray);
        setBorder(BorderFactory.createTitledBorder(" "));

        setLayout(new GridBagLayout()); // layout type GridLayout
        GridBagConstraints constraint=new GridBagConstraints();

        setBorder(new EmptyBorder(0,25,0,0));

        label=new JLabel("Please click to select 2 Images before generating.");
        btnGenerate=new JButton("Generate");
        btnGenerate.setBackground(Color.cyan);

        panelForButton=new JPanel();
        panelForLabel=new JPanel();

        for(int i=0;i<ARRAY_INDEX;i++) {

            holdImage[i] = new JLabel();
            holdImage[i].setPreferredSize(new Dimension(200,200));
            holdImageLabel[i] = new JPanel();
            holdImageLabel[i].setPreferredSize(new Dimension(200,200));
            holdImageLabel[i].add(holdImage[i]);

        }

        panelForButton.add(btnGenerate);
        panelForButton.setBackground(Color.lightGray);
        panelForButton.setBorder(new EmptyBorder(0,200,0,0));

        panelForLabel.add(label);
        panelForLabel.setBackground(Color.lightGray);
        panelForLabel.setBorder(new EmptyBorder(0,0,0,0));

        constraint.anchor=GridBagConstraints.LINE_START;
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.insets=insets;

        constraint.gridx=0;
        constraint.gridy=0;
        add(holdImageLabel[0],constraint);

        constraint.gridx=0;
        constraint.gridy=1;
        add(holdImageLabel[1],constraint);


        constraint.gridx=0;
        constraint.gridy=2;
        add(panelForLabel,constraint);


        constraint.gridx=0;
        constraint.gridy=3;
        add(panelForButton,constraint);



        constraint.gridx=1;
        constraint.gridy=0;
        add(holdImageLabel[2],constraint);

//        constraint.weightx=2;
//        constraint.weighty=2;
        constraint.gridx=1;
        constraint.gridy=1;
        add(holdImageLabel[3],constraint);

    }
}

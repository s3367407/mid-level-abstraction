package imagene.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by avishkar on 10/23/2016.
 */
public class SettingPanel extends JPanel {

    private JLabel labelCoordinate;
    private JLabel labelSymmetry;

    private JLabel labelDimension;

    private SettingPanelCoordinate coordinate;
    private SettingPanelSymmetry symmetry;
    private SettingPanelDimension imageDimension;
    private SettingPanelButton settingButton;
    private SettingPanelInfo info;

    public SettingPanel(){

        Dimension size = getPreferredSize();
        size.width=300;
        setPreferredSize(size);



        setBorder(BorderFactory.createTitledBorder("Settings"));

          labelCoordinate=new JLabel("Coordinate:");
          labelCoordinate.setForeground(Color.GRAY);

          labelSymmetry=new JLabel("Symmetry:");
        labelSymmetry.setForeground(Color.GRAY);

          labelDimension=new JLabel("Dimension:");
        labelDimension.setForeground(Color.GRAY);

        coordinate=new SettingPanelCoordinate();
        symmetry=new SettingPanelSymmetry();
        imageDimension=new SettingPanelDimension();
        settingButton=new SettingPanelButton();
        info=new SettingPanelInfo();

        setBorder(new EmptyBorder(10,40,10,10));
        setLayout(new GridBagLayout()); // layout type GridLayout
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;

        //set window coordinate to 0,0
        //set components to column 0
        constraint.gridx=0;
        constraint.gridy=0;
        add(labelCoordinate,constraint); //set labelCoordinate to 0,0

        constraint.gridx=0;
        constraint.gridy=1;
        add(coordinate,constraint);//set radioButtonCartesian to 0,1


        constraint.gridx=0;
        constraint.gridy=2;
        add(labelSymmetry,constraint);//set labelSymmetry to 0,2

        constraint.gridx=0;
        constraint.gridy=3;
        add(symmetry,constraint);//set radioButtonSymmetric to 0,3


        constraint.gridx=0;
        constraint.gridy=4;
        add(labelDimension,constraint);//set labelDimension to 0,4

        constraint.gridx=0;
        constraint.gridy=5;
        add(imageDimension,constraint);//set imageWidth(text field) to 0,5


        constraint.gridx=0;
        constraint.gridy=6;
        add(settingButton,constraint);//set btnSave(button) to 0,6

        constraint.weighty=2;
        constraint.weightx=2;
        constraint.gridx=0;
        constraint.gridy=7;
        add(info,constraint);//set btnSave(button) to 0,6

    }
}

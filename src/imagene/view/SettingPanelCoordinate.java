package imagene.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by avishkar on 10/23/2016.
 */


public class SettingPanelCoordinate extends JPanel {

    private JRadioButton radioButtonCartesian;
    private JRadioButton radioButtonPolar;

    public SettingPanelCoordinate()
    {
        radioButtonCartesian=new JRadioButton("Cartesian");
        radioButtonPolar=new JRadioButton("Polar");

        setBorder(new EmptyBorder(10,10,0,0));

        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_END; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.gridx=0;
        constraint.gridy=0;
        add(radioButtonCartesian,constraint); //set labelCoordinate to 0,0

        constraint.gridx=1;
        constraint.gridy=0;
        add(radioButtonPolar,constraint);//set radioButtonCartesian to 0,1

    }
}

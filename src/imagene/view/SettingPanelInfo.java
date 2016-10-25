package imagene.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by avishkar on 10/23/2016.
 */
public class SettingPanelInfo extends JPanel {

    private JLabel infoCoordinate;
    private JLabel infoSymmetry;
    private JLabel infoDimension;
    private JLabel info;

   public  SettingPanelInfo()
    {
        infoCoordinate=new JLabel();
        infoSymmetry=new JLabel();
        infoDimension=new JLabel();
        info=new JLabel("Info:");

        infoCoordinate.setForeground(Color.BLUE);
        infoSymmetry.setForeground(Color.BLUE);
        infoDimension.setForeground(Color.BLUE);
        info.setForeground(Color.BLUE);

        infoCoordinate.setText("Coordinate: " +"Cartesian");
        infoSymmetry.setText("Symmetry: " +"Symmetric");
        infoDimension.setText("Dimension: " +"800");

        setBorder(new EmptyBorder(10,10,0,0));

        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor=GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;

        constraint.gridx=0;
        constraint.gridy=0;
        add(info,constraint); //set labelCoordinate to 0,0


        constraint.gridx=0;
        constraint.gridy=1;
        add(infoCoordinate,constraint); //set labelCoordinate to 0,0

        constraint.gridx=0;
        constraint.gridy=2;
        add(infoSymmetry,constraint);//set radioButtonCartesian to 0,1

       // constraint.anchor=GridBagConstraints.LINE_START;
        constraint.gridx=0;
        constraint.gridy=3;
        add(infoDimension,constraint);//set radioButtonCartesian to 0,1

    }


}

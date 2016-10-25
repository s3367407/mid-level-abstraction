package imagene.view;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by avishkar on 10/23/2016.
 */
public class SettingPanelDimension extends JPanel {

    private JTextField imageWidth;
    private JLabel imageHeight;

    public SettingPanelDimension()
    {
        imageWidth=new JTextField("800");
        imageHeight=new JLabel(" x 800");
        imageWidth.setPreferredSize(new Dimension(50,20));

        setBorder(new EmptyBorder(10,10,10,10));
        setLayout(new GridBagLayout());
        GridBagConstraints constraint=new GridBagConstraints();

        constraint.anchor= GridBagConstraints.LINE_START; //0 column, texts display at the right
        constraint.weightx=0.5;
        constraint.weighty=0.5;
        constraint.gridx=0;
        constraint.gridy=0;
        add(imageWidth,constraint); //set labelCoordinate to 0,0

        constraint.gridx=1;
        constraint.gridy=0;
        add(imageHeight,constraint);//set radioButtonCartesian to 0,1
    }
}

package imagene.view;
/**
 * Created by avishkar on 10/22/2016.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class MainFrame extends JFrame implements ConstantArrayField{


    private Container container;
    private SettingPanel settingPanel;
    private ImagePanel imagePanel;


    public MainFrame(String title)    {
        super(title);
        setLayout(new BorderLayout());
        settingPanel=new SettingPanel();
        imagePanel=new ImagePanel();
        container=getContentPane();

        container.add(settingPanel,BorderLayout.WEST);
        container.add(imagePanel,BorderLayout.EAST);


    }



}

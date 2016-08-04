package ca.cmpt213.courseplanner.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import ca.cmpt213.courseplanner.model.*;

/**
 * Created by Thomas_Ngo on 2016-08-03.
 */
abstract class GUIPanel extends JPanel{

    // Fields
    // Same color border

    // JPanel variable
    JPanel panel = new JPanel();
    private CoursePlanner coursePlanner;
    private String title;

    public GUIPanel (CoursePlanner coursePlanner, String title){
        this.coursePlanner = coursePlanner;
        this.title = title;
    }

    // Required methods
    Component getCoursePanel(){
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);
        return panel;
    }

    JLabel getLabel(){
        JLabel label = new JLabel(title,JLabel.LEFT);
        label.setForeground(Color.blue);
        return label;
    }


    // return panel


}

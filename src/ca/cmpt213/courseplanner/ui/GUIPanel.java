package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-08-03.
 */
abstract public class GUIPanel extends JPanel {

    // Fields
    // Same color border

    // JPanel variable
    JPanel panel = new JPanel();
    CoursePlanner coursePlanner;
    private String title;

    public GUIPanel(CoursePlanner coursePlanner) {
        this.coursePlanner = coursePlanner;
    }

    // Required methods
    protected Component getComponent() {
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);
        return panel;
    }

    JLabel getLabel() {
        JLabel label = new JLabel(title, JLabel.LEFT);
        label.setForeground(Color.blue);
        return label;
    }

    void setLabel(String title) {
        this.title = title;
    }

    protected JPanel getPanel() {
        panel.setLayout(new BorderLayout());
        panel.add(this.getLabel());
        panel.add(this.getComponent());
        return panel;
    }


    // return panel


}

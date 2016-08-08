package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-08-03.
 */
abstract public class GUIPanel extends JPanel {

    //    JPanel panel = new JPanel();
    private CoursePlanner coursePlanner;
    private JPanel internalPanel;
//    private String title;

    public GUIPanel(CoursePlanner coursePlanner, String title) {
        this.coursePlanner = coursePlanner;
//        this.title = title;
        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setForeground(Color.blue);
        this.setLayout(new BorderLayout());
        this.add(titleLabel, BorderLayout.NORTH);
        this.internalPanel = new JPanel();
//        this.add(internalPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
    }

    // Required methods
//    protected Component getComponent() {
//        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
//        panel.setBackground(Color.white);
//        return panel;
//    }

//    JLabel getLabel() {
//        JLabel label = new JLabel(title, JLabel.LEFT);
//        label.setForeground(Color.blue);
//        return label;
//    }

//    void setLabel(String title) {
//        this.title = title;
//    }

    protected void setInternalPanel(JPanel internalPanel) {
        this.internalPanel = internalPanel;
        this.add(internalPanel, BorderLayout.CENTER);
    }

    protected void preventHorizontalResizing() {
        Dimension prefSize = getPreferredSize();
        Dimension newSize = new Dimension(
                Integer.MAX_VALUE,
                (int) prefSize.getHeight()
        );
        setMaximumSize(newSize);
    }

    protected CoursePlanner getModel() {
        return coursePlanner;
    }


    // return panel


}

package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class OfferingDetailsPanel extends GUIPanel {

    private static final String TITLE = "Details of Course Offering";

    public OfferingDetailsPanel(CoursePlanner coursePlanner){
        super(coursePlanner,TITLE);
    }

    protected JPanel getComponentPanel(){

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.PAGE_AXIS));
        labelsPanel.add(new JLabel("Course Name:"));
        labelsPanel.add(new JLabel("Semester:"));
        labelsPanel.add(new JLabel("Location:"));
        labelsPanel.add(new JLabel("Instructors:"));

        JPanel hiddenPanel = new JPanel();
        hiddenPanel.add(new JLabel("Section Type:"));
        hiddenPanel.add(new JLabel("LAB"));
        hiddenPanel.add(new JLabel("LEC"));

        JPanel offeringDetailsPanel = new JPanel();
        offeringDetailsPanel.setLayout(new GridLayout(0,2));
        offeringDetailsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        offeringDetailsPanel.setBackground(Color.white);
        offeringDetailsPanel.add(labelsPanel);
        offeringDetailsPanel.add(hiddenPanel);
        offeringDetailsPanel.setPreferredSize(new Dimension(250,175));

        return offeringDetailsPanel;
    }

}

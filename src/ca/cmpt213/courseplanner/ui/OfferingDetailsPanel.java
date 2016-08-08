package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class OfferingDetailsPanel extends GUIPanel {

    public OfferingDetailsPanel(CoursePlanner coursePlanner){
        super(coursePlanner);
        this.setLabel("Details of Course Offering");
    }

    @Override
    protected Component getComponent() {
        panel.setLayout(new GridLayout(0,2));
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);

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
        // Store course name, semester, location, instructors, section type, lab and lec

        panel.add(labelsPanel);

        return panel;
    }

    @Override
    protected JPanel getPanel(){
        JPanel offeringDetailsPanel = new JPanel();
        offeringDetailsPanel.setLayout(new BorderLayout());
        offeringDetailsPanel.add(getLabel(),BorderLayout.NORTH);
        offeringDetailsPanel.add(getComponent(),BorderLayout.CENTER);
        offeringDetailsPanel.setPreferredSize(new Dimension(250,300));
        return offeringDetailsPanel;
    }

}

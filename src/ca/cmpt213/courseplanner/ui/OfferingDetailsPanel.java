package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import java.awt.*;

/**
 * OfferingDetailsPanel contains the active offering's course name, semester, location, instructors,
 * Section Type and Enrollment.
 */

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class OfferingDetailsPanel extends GUIPanel {

    private static final String TITLE = "Details of Course Offering";


    public OfferingDetailsPanel(CoursePlanner coursePlanner){
        super(coursePlanner,TITLE);
        setInternalPanel(getContentPanel());
        registerAsObserver();
    }

    protected JPanel getContentPanel(){

        JPanel courseSemesterLocationInstructorsPanel = new JPanel();

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.PAGE_AXIS));
        labelsPanel.add(new JLabel("Course Name:"));
        labelsPanel.add(new JLabel("Semester:"));
        labelsPanel.add(new JLabel("Location:"));
        labelsPanel.add(new JLabel("Instructors:"));

        labelsPanel.setPreferredSize(new Dimension(100,80));

        // Placeholder for information from active offering
        JPanel offeringInfoPanel = new JPanel();
        offeringInfoPanel.setLayout(new BoxLayout(offeringInfoPanel, BoxLayout.PAGE_AXIS));
        offeringInfoPanel.setBackground(Color.white);
        offeringInfoPanel.add(new JLabel("Current Course"));
        offeringInfoPanel.add(new JLabel("Current Semester"));
        offeringInfoPanel.add(new JLabel("Current Location"));
        offeringInfoPanel.add(new JLabel("Current Instructors"));

        offeringInfoPanel.setPreferredSize(new Dimension(150,80));

        courseSemesterLocationInstructorsPanel.add(labelsPanel);
        courseSemesterLocationInstructorsPanel.add(offeringInfoPanel);


        JPanel componentCodePanel = new JPanel();

        JPanel componentCodeLabels = new JPanel();
        componentCodeLabels.setLayout(new BoxLayout(componentCodeLabels, BoxLayout.PAGE_AXIS));
        componentCodeLabels.add(new JLabel("Section Type"));
        componentCodeLabels.add(new JLabel("LAB"));
        componentCodeLabels.add(new JLabel("LEC"));
        componentCodeLabels.add(new JLabel("SEC"));

        componentCodeLabels.setPreferredSize(new Dimension(100,95));

        JPanel componentCodeInfo = new JPanel();
        componentCodeInfo.setLayout(new BoxLayout(componentCodeInfo, BoxLayout.PAGE_AXIS));
        componentCodeInfo.add(new JLabel("Enrollment (filled/cap)"));
        componentCodeInfo.add(new JLabel("Placeholder"));
        componentCodeInfo.add(new JLabel("Placeholder"));
        componentCodeInfo.add(new JLabel("Placeholder"));

        componentCodeInfo.setPreferredSize(new Dimension(150,95));

        componentCodePanel.add(componentCodeLabels);
        componentCodePanel.add(componentCodeInfo);

        JPanel offeringDetailsPanel = new JPanel();
        //offeringDetailsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        //offeringDetailsPanel.setBackground(Color.white);

        offeringDetailsPanel.add(courseSemesterLocationInstructorsPanel);
        offeringDetailsPanel.add(componentCodePanel);
        offeringDetailsPanel.setPreferredSize(new Dimension(250,175));

        return offeringDetailsPanel;
    }

    private void registerAsObserver(){
        getModel().addActiveOfferingObserver(
                ()->updateOfferingDetails()
        );
    }

    private void updateOfferingDetails(){

    }

}

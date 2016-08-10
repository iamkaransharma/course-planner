package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CourseComponent;
import ca.cmpt213.courseplanner.model.CoursePlanner;
import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * OfferingDetailsPanel contains the active offering's course name, semester, location, instructors,
 * Section Type and Enrollment.
 */
public class OfferingDetailsPanel extends GUIBasePanel {

    private static final String TITLE = "Details of Course Offering";
    Offering activeOffering;

    JTextArea offeringInfoPanel;
    JPanel componentCodeInfo;
    JPanel componentCodeLabels;

    String activeCourse;
    String activeSemester;

    public OfferingDetailsPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);
        setInternalPanel(getContentPanel());
//        resizeHorizontallyOnly(this);
        registerAsObserver();
    }

    protected JPanel getContentPanel() {

        JPanel courseSemesterLocationInstructorsPanel = new JPanel(new GridLayout(1, 1));

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.PAGE_AXIS));
        labelsPanel.add(new JLabel("Course Name:"));
        labelsPanel.add(new JLabel("Semester:"));
        labelsPanel.add(new JLabel("Location:"));
        labelsPanel.add(new JLabel("Instructors:"));

        labelsPanel.setPreferredSize(new Dimension(110, 80));


        // Placeholder for information from active offering
        offeringInfoPanel = new JTextArea();
        offeringInfoPanel.setLayout(new GridLayout(1, 2));
        offeringInfoPanel.setBackground(Color.white);
        offeringInfoPanel.setLineWrap(true);
        offeringInfoPanel.setWrapStyleWord(true);
//        offeringInfoPanel.setText("");
//        offeringInfoPanel.setLayout(new BoxLayout(offeringInfoPanel, BoxLayout.PAGE_AXIS));
//        offeringInfoPanel.add(new JLabel("Current Course"));
//        offeringInfoPanel.add(new JLabel("Current Semester"));
//        offeringInfoPanel.add(new JLabel("Current Location"));
//        offeringInfoPanel.add(new JLabel("Current Instructors"));

        offeringInfoPanel.setPreferredSize(new Dimension(120, 80));

        courseSemesterLocationInstructorsPanel.add(labelsPanel);
        courseSemesterLocationInstructorsPanel.add(offeringInfoPanel);

        componentCodeLabels = new JPanel(new GridLayout(2, 1));
        componentCodeLabels.setLayout(new BoxLayout(componentCodeLabels, BoxLayout.PAGE_AXIS));

        componentCodeLabels.setMinimumSize(new Dimension(80, 80));

        componentCodeInfo = new JPanel(new GridLayout(2, 2));
        componentCodeInfo.setLayout(new BoxLayout(componentCodeInfo, BoxLayout.PAGE_AXIS));

        componentCodeInfo.setMinimumSize(new Dimension(150, 80));


        JPanel offeringDetailsPanel = new JPanel();
        //offeringDetailsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        //offeringDetailsPanel.setBackground(Color.white);

        offeringDetailsPanel.add(labelsPanel);
        offeringDetailsPanel.add(offeringInfoPanel);
        offeringDetailsPanel.add(componentCodeLabels);
        offeringDetailsPanel.add(componentCodeInfo);

        offeringDetailsPanel.setPreferredSize(new Dimension(250, 175));

        return offeringDetailsPanel;
    }

    private void registerAsObserver() {

        getModel().addActiveCourseObserver(
                ()->clearOfferingDetails()
        );
        getModel().addActiveOfferingObserver(
                ()->clearOfferingDetails()
        );

        getModel().addActiveLocationObserver(
                () -> updateOfferingDetails()
        );
    }

    private void updateOfferingDetails() {
        activeOffering = getModel().getActiveOffering();
        if (activeOffering != null) {

            activeCourse = getModel().getActiveCourse().getFullName();
            String activeSemester = activeOffering.getSemester().getSemesterCode();
            String activeLocation = getModel().getActiveLocation().getName();

            String activeInstructors = "";

            Set<String> instructorsFromOffering = getModel().getActiveLocation().getInstructors();
            for (String i : instructorsFromOffering) {
                activeInstructors = activeInstructors + i;
            }

            offeringInfoPanel.setText(activeCourse + "\n" + activeSemester + "\n" + activeLocation + "\n" + activeInstructors);

            Set<CourseComponent> courseComponentsFromLocation = getModel().getActiveLocation().getCourseComponents();

            ArrayList<String> componentCodes = new ArrayList<>();
            ArrayList<String> enrollments = new ArrayList<>();

            for (CourseComponent c : courseComponentsFromLocation) {
                componentCodes.add(c.getComponentCode());
                enrollments.add(c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity());
            }

            componentCodeLabels.add(new JLabel("Section Type"));
            componentCodeInfo.add(new JLabel("Enrollment (filled/cap)"));

            for (String c : componentCodes) {
                componentCodeLabels.add(new JLabel(c));
            }
            for (String e : enrollments) {
                componentCodeInfo.add(new JLabel(e));
            }
        }


        this.updateUI();
    }

    private void clearOfferingDetails(){

        activeOffering = null;

        offeringInfoPanel.setText("");

        componentCodeLabels.removeAll();
        componentCodeInfo.removeAll();

        componentCodeLabels.revalidate();
        componentCodeInfo.revalidate();

        componentCodeLabels.repaint();
        componentCodeInfo.repaint();

        this.updateUI();
    }

}

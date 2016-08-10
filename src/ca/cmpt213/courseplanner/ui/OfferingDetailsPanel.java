package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * OfferingDetailsPanel contains the active offering's course name, semester, location, instructors,
 * Section Type and Enrollment.
 */
public class OfferingDetailsPanel extends GUIBasePanel {
    private static final String TITLE = "Details of Course Offering";

    JTextArea offeringInfoPanel;
    JPanel componentTypeLabels;
    JPanel enrollmentLabels;
    Offering activeOffering;
    Location activeLocation;

    public OfferingDetailsPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);
        setInternalPanel(getContentPanel());
        registerAsObserver();
    }

    protected JPanel getContentPanel() {
        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.PAGE_AXIS));
        labelsPanel.add(new JLabel("Course Name:"));
        labelsPanel.add(new JLabel("Semester:"));
        labelsPanel.add(new JLabel("Location:"));
        labelsPanel.add(new JLabel("Instructors:"));

        offeringInfoPanel = new JTextArea(4, 10);
        offeringInfoPanel.setBackground(Color.white);
        offeringInfoPanel.setLineWrap(true);
        offeringInfoPanel.setWrapStyleWord(true);
        offeringInfoPanel.setEditable(false);

        componentTypeLabels = new JPanel();
        componentTypeLabels.setLayout(new BoxLayout(componentTypeLabels, BoxLayout.PAGE_AXIS));
        enrollmentLabels = new JPanel();
        enrollmentLabels.setLayout(new BoxLayout(enrollmentLabels, BoxLayout.PAGE_AXIS));

        JPanel gridContainer = new JPanel(new GridLayout(2, 2));
        gridContainer.add(labelsPanel);
        gridContainer.add(offeringInfoPanel);
        gridContainer.add(componentTypeLabels);
        gridContainer.add(enrollmentLabels);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(gridContainer, BorderLayout.NORTH);
        resizeHorizontallyOnly(contentPanel);

        return contentPanel;
    }

    private void registerAsObserver() {
        getModel().addCourseListObserver(
                () -> clearOfferingDetails()
        );
        getModel().addActiveCourseObserver(
                () -> clearOfferingDetails()
        );
        getModel().addActiveLocationObserver(
                () -> updateOfferingDetails()
        );
    }

    private void updateOfferingDetails() {
        activeOffering = getModel().getActiveOffering();
        activeLocation = getModel().getActiveLocation();
        Course activeCourse = getModel().getActiveCourse();

        assert activeOffering != null && activeLocation != null;

        offeringInfoPanel.setText(activeCourse.getFullName() + "\n" +
                activeOffering.getSemester().getSemesterCode() + "\n" +
                activeLocation.getName() + "\n" +
                activeLocation.getInstructorsAsString()
        );

        componentTypeLabels.removeAll();
        enrollmentLabels.removeAll();

        componentTypeLabels.add(new JLabel("Section Type"));
        enrollmentLabels.add(new JLabel("Enrollment (filled/cap)"));

        for (CourseComponent c : activeLocation.getCourseComponents()) {
            String componentLabel = c.getComponentCode();
            componentTypeLabels.add(new JLabel(componentLabel));
            String enrollmentLabel = c.getEnrollmentTotal() + "/" + c.getEnrollmentCapacity();
            enrollmentLabels.add(new JLabel(enrollmentLabel));
        }
        this.updateUI();
    }

    private void clearOfferingDetails() {
        componentTypeLabels.removeAll();
        enrollmentLabels.removeAll();
        offeringInfoPanel.setText("");
        this.updateUI();
    }

}

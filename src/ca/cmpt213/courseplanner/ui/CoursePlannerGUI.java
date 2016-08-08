package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CoursePlannerGUI extends JFrame {
    private static final String WINDOW_TITLE = "FAS Course Planner";
    private CoursePlanner coursePlanner;

    public CoursePlannerGUI(CoursePlanner coursePlanner) {
        this.coursePlanner = coursePlanner;
        new JFrame(WINDOW_TITLE);
    }

    public void startProgram() {
        // Stores the entire GUI
        JPanel windowContainer = new JPanel();
        windowContainer.setLayout(new BorderLayout());
        windowContainer.setBorder(new EmptyBorder(5, 5, 5, 5));

        // West Side
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.PAGE_AXIS));
        westPanel.add(new CourseListFilterPanel(coursePlanner).getPanel());
        westPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        westPanel.add(new CourseListPanel(coursePlanner).getPanel());
        windowContainer.add(westPanel, BorderLayout.WEST);

        // Center
        JPanel centerPanel = new SemesterOfferingsPanel(coursePlanner).getPanel();
        centerPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
        windowContainer.add(centerPanel, BorderLayout.CENTER);

        // East Side
        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.PAGE_AXIS));
        eastPanel.add(new BarGraphPanel(coursePlanner).getPanel());
        eastPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        eastPanel.add(new OfferingDetailsPanel(coursePlanner).getPanel());
        windowContainer.add(eastPanel, BorderLayout.EAST);

        add(windowContainer);

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void displayDialogBox(String text) {
        JOptionPane.showMessageDialog(null, text);
    }

}

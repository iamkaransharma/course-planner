package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;


import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CoursePlannerGUI extends JPanel {

    // Receive instances of CourseListFilterPanel, CourseListPanel, CoursePlannerPanel, OfferingDetailsPanel
    // and SemesterOfferingsPanel. Use them as arguments for JPanels and put those JPanels into JFrame.

    private CoursePlanner coursePlanner;

    public CoursePlannerGUI(CoursePlanner coursePlanner){
        this.coursePlanner = coursePlanner;
    }

    public void startProgram(){

        JFrame frame = new JFrame();
        frame.setTitle("FAS Course Planner");

        // Stores the entire GUI
        JPanel coursePlannerUI = new JPanel();
        coursePlannerUI.setLayout(new BoxLayout(coursePlannerUI,BoxLayout.PAGE_AXIS));

        // Stores all of the panels in CoursePlanner
        JPanel coursePlannerPanels = new JPanel();
        coursePlannerPanels.setLayout(new BoxLayout(coursePlannerPanels,BoxLayout.LINE_AXIS));

        // Top Padding
        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        // West Side
        JPanel westSide = new JPanel();
        westSide.setLayout(new BoxLayout(westSide, BoxLayout.PAGE_AXIS));
        westSide.add(new CourseListFilterPanel(coursePlanner).getPanel());
        westSide.add(Box.createRigidArea(new Dimension(0,5)));
        westSide.add(new CourseListPanel(coursePlanner).getPanel());
        coursePlannerPanels.add(westSide);
        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        // Center
        coursePlannerPanels.add(new SemesterOfferingsPanel(coursePlanner).getPanel());
        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        // East Side

        JPanel eastSide = new JPanel();
        eastSide.setLayout(new BoxLayout(eastSide, BoxLayout.PAGE_AXIS));
        eastSide.add(new BarGraphPanel(coursePlanner).getPanel());
        eastSide.add(Box.createRigidArea(new Dimension(0,5)));
        eastSide.add(new OfferingDetailsPanel(coursePlanner).getPanel());
        coursePlannerPanels.add(eastSide);
        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        // Add everything into coursePlannerUI
        coursePlannerUI.add(coursePlannerPanels);
        coursePlannerUI.add(Box.createRigidArea(new Dimension(0,5)));

        frame.add(coursePlannerUI);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}

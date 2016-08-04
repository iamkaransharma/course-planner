package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;


import javax.swing.*;
import javax.swing.border.Border;
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

        JPanel coursePlannerUI = new JPanel();
        coursePlannerUI.setLayout(new BoxLayout(coursePlannerUI,BoxLayout.PAGE_AXIS));

        JPanel coursePlannerPanels = new JPanel();
        coursePlannerPanels.setLayout(new BoxLayout(coursePlannerPanels,BoxLayout.LINE_AXIS));

        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        // West Side

        JPanel westSide = new JPanel();

        westSide.setLayout(new BoxLayout(westSide, BoxLayout.PAGE_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        CourseListFilterPanel clfp = new CourseListFilterPanel(coursePlanner,"Course List Filter");
        panel1.add(clfp.getLabel(),BorderLayout.NORTH);
        panel1.add(clfp.getCoursePanel(),BorderLayout.CENTER);
        panel1.setPreferredSize(new Dimension(200,300));
        westSide.add(panel1);

        westSide.add(Box.createRigidArea(new Dimension(0,5)));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());

        CourseListPanel clp = new CourseListPanel(coursePlanner, "Course List");
        panel2.add(clp.getLabel(),BorderLayout.NORTH);
        panel2.add(clp.getCoursePanel(),BorderLayout.CENTER);
        panel2.setPreferredSize(new Dimension(200,300));
        westSide.add(panel2);

        coursePlannerPanels.add(westSide);

        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        // Center

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());

        SemesterOfferingsPanel sop = new SemesterOfferingsPanel(coursePlanner, "Course Offerings by Semester");
        panel3.add(sop.getLabel(),BorderLayout.NORTH);
        panel3.add(sop.getCoursePanel(),BorderLayout.CENTER);
        panel3.setPreferredSize(new Dimension(800,HEIGHT));

        coursePlannerPanels.add(panel3);

        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        // East Side

        JPanel eastSide = new JPanel();
        eastSide.setLayout(new BoxLayout(eastSide, BoxLayout.PAGE_AXIS));

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());

        BarGraphPanel barGraph = new BarGraphPanel(coursePlanner, "Statistics");
        panel4.add(barGraph.getLabel(),BorderLayout.NORTH);
        panel4.add(barGraph.getCoursePanel(),BorderLayout.CENTER);

        panel4.setPreferredSize(new Dimension(250,300));
        eastSide.add(panel4);

        eastSide.add(Box.createRigidArea(new Dimension(0,5)));

        JPanel panel5 = new JPanel();
        panel5.setLayout(new BorderLayout());
        OfferingDetailsPanel odp = new OfferingDetailsPanel(coursePlanner, "Details of Course Offering");
        panel5.add(odp.getLabel(),BorderLayout.NORTH);
        panel5.add(odp.getCoursePanel(),BorderLayout.CENTER);

        panel5.setPreferredSize(new Dimension(250,300));

        eastSide.add(panel5);

        coursePlannerPanels.add(eastSide);

        coursePlannerPanels.add(Box.createRigidArea(new Dimension(5,0)));

        coursePlannerUI.add(coursePlannerPanels);
        coursePlannerUI.add(Box.createRigidArea(new Dimension(0,5)));

        frame.add(coursePlannerUI);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}

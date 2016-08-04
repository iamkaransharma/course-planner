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

        frame.setLayout(new BorderLayout());

        // West Side

        JPanel westSide = new JPanel();
        westSide.setLayout(new BoxLayout(westSide, BoxLayout.PAGE_AXIS));

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));

        CourseListFilterPanel clfp = new CourseListFilterPanel(coursePlanner,"Course List Filter Panel");
        panel1.add(clfp.getLabel(),BorderLayout.NORTH);
        panel1.add(clfp.getCoursePanel(),BorderLayout.CENTER);
        panel1.setPreferredSize(new Dimension(200,200));
        westSide.add(panel1);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));

        CourseListPanel clp = new CourseListPanel(coursePlanner, "Course List");
        panel2.add(clp.getLabel(),BorderLayout.NORTH);
        panel2.add(clp.getCoursePanel(),BorderLayout.CENTER);
        panel2.setPreferredSize(new Dimension(200,500));
        westSide.add(panel2);

        frame.add(westSide,BorderLayout.WEST);

        // Center

        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));

        SemesterOfferingsPanel sop = new SemesterOfferingsPanel(coursePlanner, "Course Offerings by Semester");
        panel3.add(sop.getLabel());
        panel3.add(sop.getCoursePanel());
        panel3.setPreferredSize(new Dimension(800,250));
        frame.add(panel3,BorderLayout.CENTER);

        // East Side

        JPanel eastSide = new JPanel();
        eastSide.setLayout(new BoxLayout(eastSide, BoxLayout.PAGE_AXIS));

        JPanel panel4 = new JPanel();
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.PAGE_AXIS));

        BarGraphPanel barGraph = new BarGraphPanel(coursePlanner, "Statistics");
        panel4.add(barGraph.getLabel(),BorderLayout.NORTH);
        panel4.add(barGraph.getCoursePanel(),BorderLayout.CENTER);

        panel4.setPreferredSize(new Dimension(250,350));
        eastSide.add(panel4);

        JPanel panel5 = new JPanel();
        panel5.setLayout(new BoxLayout(panel5, BoxLayout.PAGE_AXIS));
        OfferingDetailsPanel odp = new OfferingDetailsPanel(coursePlanner, "Details of Course Offering");
        panel5.add(odp.getLabel(),BorderLayout.NORTH);
        panel5.add(odp.getCoursePanel(),BorderLayout.CENTER);

        panel5.setPreferredSize(new Dimension(250,500));

        eastSide.add(panel5);

        frame.add(eastSide,BorderLayout.EAST);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}

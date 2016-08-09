package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;
import ca.cmpt213.bargraph.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class BarGraphPanel extends GUIPanel {

    private static final String TITLE = "Statistics";

    public BarGraphPanel(CoursePlanner coursePlanner){
        super(coursePlanner,TITLE);
        setInternalPanel(getContentPanel());
    }

    protected JPanel getContentPanel() {

        JPanel graphs = new JPanel();
        graphs.setLayout(new BoxLayout(graphs, BoxLayout.PAGE_AXIS));

        // Testing out the graphs

        JPanel semesterGraphs = new JPanel();
        semesterGraphs.setLayout(new BoxLayout(semesterGraphs, BoxLayout.PAGE_AXIS));

        int[] semesterData = {8,7,9};
        String[] titles = {"Spring","Summer","Fall"};

        BarGraphModel semesterGraphModel = new BarGraphModel(semesterData,titles);
        semesterGraphModel.setData(semesterData);

        JLabel semesterOfferingsLabel = new JLabel("Semester Offerings:");

        BarGraphIcon semesterGraphIcon = new BarGraphIcon(semesterGraphModel,225,180);
        JLabel semesterGraph = new JLabel(semesterGraphIcon);

        semesterGraphs.add(semesterOfferingsLabel);
        semesterGraphs.add(semesterGraph);

        JPanel locationGraphs = new JPanel();
        locationGraphs.setLayout(new BoxLayout(locationGraphs, BoxLayout.PAGE_AXIS));

        int[] locationData = {7,5,3,0};
        String[] campuses = {"Bby","Sry","Van","Other"};
        BarGraphModel testGraphModel2 = new BarGraphModel(locationData,campuses);
        testGraphModel2.setData(locationData);

        BarGraphIcon testGraph2 = new BarGraphIcon(testGraphModel2,225,180);
        JLabel campusOfferingsLabel = new JLabel("Campus Offerings:");
        locationGraphs.add(campusOfferingsLabel);
        locationGraphs.add(new JLabel(testGraph2));

        String selectedCourse = "CMPT 213";
        JLabel courseLabel = new JLabel("Course: " + selectedCourse);

        // Adding elements to graphs
        graphs.add(courseLabel);
        graphs.add(semesterGraphs);
        graphs.add(locationGraphs);

        JPanel barGraphPanel = new JPanel();
        barGraphPanel.setLayout(new BoxLayout(barGraphPanel, BoxLayout.PAGE_AXIS));
        barGraphPanel.setBackground(Color.white);
        barGraphPanel.setLayout(new BorderLayout());
        barGraphPanel.add(graphs);
        barGraphPanel.setPreferredSize(new Dimension(250,425));
        return barGraphPanel;
    }
}

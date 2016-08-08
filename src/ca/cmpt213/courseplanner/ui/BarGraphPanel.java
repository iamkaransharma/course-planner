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
    }

    protected Component getContentPanel() {

        JPanel graphs = new JPanel();
        graphs.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Testing out the graphs

        JPanel semesterGraphs = new JPanel();
        int[] semesterData = {8,7,9};
        String[] titles = {"Spring","Summer","Fall"};
        BarGraphModel testGraphModel = new BarGraphModel(semesterData,titles);
        testGraphModel.setData(semesterData);

        BarGraphIcon testGraph = new BarGraphIcon(testGraphModel,200,150);
        semesterGraphs.add(new JLabel(testGraph));

        JPanel locationGraphs = new JPanel();
        int[] locationData = {7,5,3,0};
        String[] campuses = {"Bby","Sry","Van","Other"};
        BarGraphModel testGraphModel2 = new BarGraphModel(locationData,campuses);
        testGraphModel2.setData(locationData);

        BarGraphIcon testGraph2 = new BarGraphIcon(testGraphModel2,200,150);
        locationGraphs.add(new JLabel(testGraph2));

        String selectedCourse = "CMPT 213";
        JLabel courseLabel = new JLabel("Course: " + selectedCourse);
        JLabel semesterOfferingsLabel = new JLabel("Semester Offerings:",JLabel.LEFT);
        JLabel campusOfferingsLabel = new JLabel("Campus Offerings:",JLabel.LEFT);

        // Adding elements to graphs
        graphs.add(courseLabel);
        graphs.add(semesterOfferingsLabel);
        graphs.add(semesterGraphs);
        graphs.add(campusOfferingsLabel);
        graphs.add(locationGraphs);

        JPanel barGraphPanel = new JPanel();
        barGraphPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        barGraphPanel.setBackground(Color.white);
        barGraphPanel.setLayout(new BoxLayout(barGraphPanel, BoxLayout.PAGE_AXIS));
        barGraphPanel.add(graphs);
        barGraphPanel.setPreferredSize(new Dimension(250,425));
        return barGraphPanel;
    }
}

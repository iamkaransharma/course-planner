package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.bargraph.BarGraphIcon;
import ca.cmpt213.bargraph.BarGraphModel;
import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * BarGraphPanel contains statistics that show how many offerings are offered in Spring, Summer and Fall, and
 * the locations of the offerings at Burnaby, Surrey, Harbor Center and other locations.
 */
public class BarGraphPanel extends GUIBasePanel {

    private static final String TITLE = "Statistics";

    Course activeCourse;

    BarGraphModel semesterGraphModel;
    BarGraphModel locationsGraphModel;

    JLabel courseLabel;
    JPanel barGraphPanel = new JPanel();

    public BarGraphPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);
        resizeHorizontallyOnly(this);
        registerAsObserver();
        initializeBarGraphs();
        setInternalPanel(getContentPanel());
    }

    private void initializeBarGraphs() {
        String[] titles = {"Spring", "Summer", "Fall"};
        semesterGraphModel = new BarGraphModel(new int[]{0, 0, 0}, titles);
        String[] campuses = {"Bby", "Sry", "Van", "Other"};
        locationsGraphModel = new BarGraphModel(new int[]{0, 0, 0, 0}, campuses);
    }

    protected JPanel getContentPanel() {

        JPanel graphs = new JPanel();
        graphs.setLayout(new BoxLayout(graphs, BoxLayout.PAGE_AXIS));

        JPanel semesterGraphs = new JPanel();
        semesterGraphs.setLayout(new BoxLayout(semesterGraphs, BoxLayout.PAGE_AXIS));

        JLabel semesterOfferingsLabel = new JLabel("Semester Offerings:");

        BarGraphIcon semesterGraphIcon = new BarGraphIcon(semesterGraphModel, 250, 180);
        JLabel semesterGraph = new JLabel(semesterGraphIcon);

        semesterGraphs.add(semesterOfferingsLabel);
        semesterGraphs.add(semesterGraph);

        JPanel locationGraphs = new JPanel();
        locationGraphs.setLayout(new BoxLayout(locationGraphs, BoxLayout.PAGE_AXIS));

        BarGraphIcon testGraph2 = new BarGraphIcon(locationsGraphModel, 250, 180);
        JLabel campusOfferingsLabel = new JLabel("Campus Offerings:");
        locationGraphs.add(campusOfferingsLabel);
        locationGraphs.add(new JLabel(testGraph2));

        courseLabel = new JLabel("");
        graphs.add(courseLabel);

        // Adding elements to graphs
        graphs.add(Box.createRigidArea(new Dimension(0, 5)));
        graphs.add(semesterGraphs);
        graphs.add(locationGraphs);

        //JPanel barGraphPanel = new JPanel();
        barGraphPanel.setLayout(new BoxLayout(barGraphPanel, BoxLayout.PAGE_AXIS));
        barGraphPanel.setBackground(Color.white);
        barGraphPanel.setLayout(new BorderLayout());
        barGraphPanel.add(graphs);
        barGraphPanel.setPreferredSize(new Dimension(250, 400));
        return barGraphPanel;
    }

    private void registerAsObserver() {
        getModel().addCourseListObserver(
                () -> updateBarGraphs()
        );
        getModel().addActiveCourseObserver(
                () -> updateBarGraphs()
        );
    }

    private void updateBarGraphs() {
        activeCourse = getModel().getActiveCourse();

        int springCoursesCount = 0;
        int summerCoursesCount = 0;
        int fallCoursesCount = 0;
        if (activeCourse != null) {
            springCoursesCount = activeCourse.getOfferingsBySeason(Season.SPRING).size();
            summerCoursesCount = activeCourse.getOfferingsBySeason(Season.SUMMER).size();
            fallCoursesCount = activeCourse.getOfferingsBySeason(Season.FALL).size();
        }

        int[] semesterData = {springCoursesCount, summerCoursesCount, fallCoursesCount};

        semesterGraphModel.setData(semesterData);

        int burnabyCoursesCount = 0;
        int surreyCoursesCount = 0;
        int vancouverCoursesCount = 0;
        int otherCoursesCount = 0;
        if (activeCourse != null) {

            String selectedCourse = activeCourse.getFullName();
            courseLabel.setText("Course: "+selectedCourse);

            for (Offering offering : activeCourse.getOfferings()) {
                for (Location location : offering.getLocations()) {
                    switch (location.getName()) {
                        case "BURNABY":
                            burnabyCoursesCount++;
                            break;
                        case "SURREY":
                            surreyCoursesCount++;
                            break;
                        case "HRBRCNTR":
                            vancouverCoursesCount++;
                            break;
                        case "OTHER":
                            otherCoursesCount++;
                            break;
                        default:
                            throw new RuntimeException("Unexpected location name");
                    }
                }
            }
        } else {
            courseLabel.setText("Course: ");
        }

        int[] locationData = {burnabyCoursesCount, surreyCoursesCount, vancouverCoursesCount, otherCoursesCount};
        locationsGraphModel.setData(locationData);
        barGraphPanel.setPreferredSize(new Dimension(250, 410));
        this.updateUI();
    }
}

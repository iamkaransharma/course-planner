package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.bargraph.BarGraphPanel;
import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;

/**
 * StatisticsPanel contains statistics that show how many offerings are offered in Spring, Summer and Fall, and
 * the locations of the offerings at Burnaby, Surrey, Harbor Center and other locations.
 */
public class StatisticsPanel extends GUIBasePanel {

    private static final String TITLE = "Statistics";
    private static final String[] SEASON_NAMES = {"Spring", "Summer", "Fall"};
    private static final String[] CAMPUS_NAMES = {"Bby", "Sry", "Van", "Other"};

    Course activeCourse;
    BarGraphPanel semesterGraph;
    BarGraphPanel locationGraph;
    JLabel courseLabel;

    public StatisticsPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);
        resizeHorizontallyOnly(this);
        registerAsObserver();
        initializeBarGraphs();
        setInternalPanel(getContentPanel());
    }

    private void initializeBarGraphs() {
        semesterGraph = new BarGraphPanel("Semester Offerings:", SEASON_NAMES);
        locationGraph = new BarGraphPanel("Campus Offerings:", CAMPUS_NAMES);
    }

    private JPanel getContentPanel() {

        JPanel graphs = new JPanel();
        graphs.setLayout(new BoxLayout(graphs, BoxLayout.PAGE_AXIS));

        courseLabel = new JLabel("");
        graphs.add(courseLabel);
        graphs.add(semesterGraph);
        graphs.add(locationGraph);

        return graphs;
    }

    private void registerAsObserver() {
        getModel().addCourseListObserver(
                () -> clearBarGraphs()
        );
        getModel().addActiveCourseObserver(
                () -> updateBarGraphs()
        );
    }

    private void clearBarGraphs() {
        courseLabel.setText("Course: ");
        locationGraph.resetGraph();
        semesterGraph.resetGraph();
        this.revalidate();
        this.repaint();
    }

    private int[] getSemesterFrequencyData() {
        return new int[]{
                activeCourse.countOfferingsBySeason(Season.SPRING),
                activeCourse.countOfferingsBySeason(Season.SUMMER),
                activeCourse.countOfferingsBySeason(Season.FALL)
        };
    }

    private int[] getLocationFrequencyData() {
        int burnabyCoursesCount = 0;
        int surreyCoursesCount = 0;
        int vancouverCoursesCount = 0;
        int otherCoursesCount = 0;

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
        return new int[]{
                burnabyCoursesCount,
                surreyCoursesCount,
                vancouverCoursesCount,
                otherCoursesCount
        };
    }

    private void updateBarGraphs() {
        activeCourse = getModel().getActiveCourse();
        assert activeCourse != null;
        String selectedCourse = activeCourse.getFullName();
        courseLabel.setText("Course: " + selectedCourse);
        semesterGraph.updateGraph(getSemesterFrequencyData());
        locationGraph.updateGraph(getLocationFrequencyData());
        this.updateUI();
    }
}

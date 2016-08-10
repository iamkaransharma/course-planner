package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CoursePlanner;
import ca.cmpt213.courseplanner.model.Location;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class SemesterOfferingsPanel extends GUIBasePanel {

    private static final String TITLE = "Course Offerings by Semester";
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;
    private static final int DEFAULT_FIRST_YEAR = 2000;
    private static final int DEFAULT_LAST_YEAR = 2010;

    private static final String[] SEASON_NAMES = new String[]{"Spring", "Summer", "Fall"};
    Course activeCourse;
    JPanel internalPanel;

    public SemesterOfferingsPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);
        this.internalPanel = getContentPanel();
        setInternalPanel(internalPanel);
        registerAsObserver();
    }

    private JPanel getOfferingsTable() {

        JPanel offeringsTable = new JPanel();
        offeringsTable.setBackground(Color.white);

        offeringsTable.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        // display season column names
        final int FIRST_ROW = 0;
        final int FIRST_COLUMN_OFFSET = 1;
        for (int column = 0; column < SEASON_NAMES.length; column++) {
            JLabel seasonLabel = new JLabel(SEASON_NAMES[column]);
            seasonLabel.setOpaque(false);
            seasonLabel.setPreferredSize(new Dimension(50, 30));
            resizeHorizontallyOnly(seasonLabel);

            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.SOUTHWEST;
            constraints.weightx = 1.0;
            constraints.weighty = 0.0;
            constraints.gridx = column + FIRST_COLUMN_OFFSET;
            constraints.gridy = FIRST_ROW;

            offeringsTable.add(seasonLabel, constraints);
        }

        int minYear = DEFAULT_FIRST_YEAR;
        int maxYear = DEFAULT_LAST_YEAR;
        if (activeCourse != null) {
            minYear = activeCourse.getOldestOffering().getSemester().getYear();
            maxYear = activeCourse.getNewestOffering().getSemester().getYear();
        }

        final int MAX_ROWS = maxYear - minYear + 1;
        final int ROW_OFFSET = 1;
        for (int row = ROW_OFFSET; row <= MAX_ROWS; row++) {

            // Year label
            int currentYear = minYear + row - 1;
            String yearText = currentYear + "";
            JLabel yearLabel = new JLabel(yearText);
            yearLabel.setOpaque(false);
            yearLabel.setPreferredSize(new Dimension(35, 10));

            constraints.fill = GridBagConstraints.NONE;
            constraints.anchor = GridBagConstraints.NORTHEAST;
            constraints.weighty = 0.0;
            constraints.weightx = 0.0;
            constraints.gridx = 0;
            constraints.gridy = row;

            offeringsTable.add(yearLabel, constraints);

            for (int column = 0; column < SEASON_NAMES.length; column++) {
                JPanel cellLabel = new JPanel();
                cellLabel.setBorder(BorderFactory.createLineBorder(Color.black));
                cellLabel.setLayout(new BoxLayout(cellLabel, BoxLayout.PAGE_AXIS));

                constraints.fill = GridBagConstraints.BOTH;
                constraints.anchor = GridBagConstraints.NORTH;
                constraints.weighty = 1.0;
                constraints.weightx = 1.0;
                constraints.gridx = column + FIRST_COLUMN_OFFSET;
                constraints.gridy = row;

                //TODO: add buttons panel instead of celllabel
                if (activeCourse != null) {
                    String cellSemesterCode = calculateSemesterCode(currentYear, SEASON_NAMES[column]);
                    Set<Location> locationsSet = activeCourse.getLocationsBySemesterCode(cellSemesterCode);
//                    JPanel buttonsPanel = new JPanel();
//                    buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.PAGE_AXIS));
                    for (Location location : locationsSet) {
                        String buttonText = activeCourse.getFullName() + " - " + location.getName();
                        JButton locationButton = new JButton(buttonText);
                        locationButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                getModel().selectLocation(location);
                            }
                        });
//                        resizeHorizontallyOnly(locationButton);
                        cellLabel.add(locationButton);
                    }
//                    cellLabel.add(buttonsPanel);
                }

                offeringsTable.add(cellLabel, constraints);
            }
        }
        return offeringsTable;
    }

    // TODO: Improve if possible
    private String calculateSemesterCode(int currentYear, String seasonName) {
        final String CENTURY_PREFIX = "1";
        String currentYearInfix = String.valueOf(currentYear % 100);
        String semesterCode;

        switch (seasonName) {
            case "Spring":
                semesterCode = CENTURY_PREFIX + currentYearInfix + "1";
                break;
            case "Summer":
                semesterCode = CENTURY_PREFIX + currentYearInfix + "4";
                break;
            case "Fall":
                semesterCode = CENTURY_PREFIX + currentYearInfix + "7";
                break;
            default:
                throw new RuntimeException("Unknown season name received");
        }
        return semesterCode;
    }

    private JPanel getContentPanel() {

        JPanel semesterOfferingsPanel = new JPanel();

        semesterOfferingsPanel.setLayout(new BorderLayout());
        semesterOfferingsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        JPanel offeringsPanel = getOfferingsTable();

        semesterOfferingsPanel.add(offeringsPanel);

        semesterOfferingsPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
//        semesterOfferingsPanel.revalidate();
//        semesterOfferingsPanel.repaint();
        return semesterOfferingsPanel;
    }

    private void registerAsObserver() {
        getModel().addCourseListObserver(
                () -> clearOfferingsTable()
        );
        getModel().addActiveCourseObserver(
                () -> updateOfferingsTable()
        );
    }

    private void clearOfferingsTable() {
        System.out.println("clear table");//TODO: remove!
    }

    private void updateOfferingsTable() {
        System.out.println("update table");//TODO: remove!
        activeCourse = getModel().getActiveCourse();
        internalPanel.removeAll();
        internalPanel = getContentPanel();
        internalPanel.revalidate();
        internalPanel.repaint();
        internalPanel.updateUI();
        this.setInternalPanel(internalPanel);
    }
}

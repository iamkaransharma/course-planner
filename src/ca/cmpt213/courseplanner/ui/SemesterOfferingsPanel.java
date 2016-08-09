package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class SemesterOfferingsPanel extends GUIBasePanel {

    private static final String TITLE = "Course Offerings by Semester";
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 600;

    public SemesterOfferingsPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);
        setInternalPanel(getContentPanel());
        registerAsObserver();
    }

    protected JPanel getOfferingsTable() {

        JPanel offeringsTable = new JPanel();
        offeringsTable.setBackground(Color.white);
//        panel.setBackground(Color.white);

        // Add buttons into the table, button for each course offering

        // Testing it out
//        JButton testButton = new JButton("CMPT 213 - SURREY");
//        JButton testButton2 = new JButton("CMPT 213 - BURNABY");
//        JButton testButton3 = new JButton("CMPT 213 - VANCOUVER");
//        JButton testButton4 = new JButton("CMPT 373 - SURREY");

        offeringsTable.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
//        c.weighty = 1;
//        c.anchor = GridBagConstraints.NORTHWEST;

        // 550 is the maximum height of the table

        int maximum_height = 550;

        int years = 10;

        int cell_height = maximum_height / years;

        for (int i = 0; i <= years; i++) {

            if (i == 0) {

                JLabel springLabel = new JLabel("Spring");
                springLabel.setOpaque(false);
                springLabel.setPreferredSize(new Dimension(Integer.MIN_VALUE, 20));
                resizeHorizontallyOnly(springLabel);

                JLabel summerLabel = new JLabel("Summer");
                summerLabel.setOpaque(false);
                springLabel.setPreferredSize(new Dimension(Integer.MIN_VALUE, 20));
                resizeHorizontallyOnly(summerLabel);

                JLabel fallLabel = new JLabel("Fall");
                fallLabel.setOpaque(false);
                springLabel.setPreferredSize(new Dimension(Integer.MIN_VALUE, 20));
                resizeHorizontallyOnly(fallLabel);
                c.fill = GridBagConstraints.BOTH;
                c.anchor = GridBagConstraints.SOUTHWEST;
                c.weightx = 1.0;
                c.weighty = 0.0;

                c.gridx = 1;
                c.gridy = i;
                offeringsTable.add(springLabel, c);

                c.gridx = 2;
                c.gridy = i;
                offeringsTable.add(summerLabel, c);

                c.gridx = 3;
                c.gridy = i;
                offeringsTable.add(fallLabel, c);
            }

            // Years
            else if (i >= 1) {

                int year = 2000 + i - 1;
                String yearText = year + "";
                JLabel yearLabel = new JLabel(yearText, SwingConstants.LEFT);
                yearLabel.setOpaque(false);
                yearLabel.setPreferredSize(new Dimension(35, 10));

                JPanel spring = new JPanel();
                spring.setBorder(BorderFactory.createLineBorder(Color.black));

                JPanel summer = new JPanel();
                summer.setBorder(BorderFactory.createLineBorder(Color.black));

                JPanel fall = new JPanel();
                fall.setBorder(BorderFactory.createLineBorder(Color.black));


                c.fill = GridBagConstraints.NONE;
                c.anchor = GridBagConstraints.NORTHEAST;
                c.weighty = 0.2;
                c.weightx = 0.0;
                c.gridx = 0;
                c.gridy = i;
                offeringsTable.add(yearLabel, c);

                c.fill = GridBagConstraints.BOTH;
                c.anchor = GridBagConstraints.NORTH;
                c.weighty = 1.0;
                c.weightx = 1.0;

                c.gridx = 1;
                c.gridy = i;
                offeringsTable.add(spring, c);

                c.gridx = 2;
                c.gridy = i;
                offeringsTable.add(summer, c);

                c.gridx = 3;
                c.gridy = i;
                offeringsTable.add(fall, c);
            }
        }

        // Default number of years is 10 years.

        return offeringsTable;
    }

    protected JPanel getContentPanel() {

        JPanel semesterOfferingsPanel = new JPanel();

        semesterOfferingsPanel.setLayout(new BorderLayout());
        semesterOfferingsPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        JPanel offeringsPanel = getOfferingsTable();

        semesterOfferingsPanel.add(offeringsPanel);

        semesterOfferingsPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

        return semesterOfferingsPanel;
    }

    private void registerAsObserver() {
        getModel().addCourseListObserver(
                () -> updateOfferingsTable()
        );
        getModel().addActiveCourseObserver(
                () -> updateOfferingsTable()
        );
    }

    private void updateOfferingsTable() {
        System.out.println("notified!!!");
    }
}

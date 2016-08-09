package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class SemesterOfferingsPanel extends GUIPanel {

    private static final String TITLE = "Course Offerings by Semester";

    public SemesterOfferingsPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);
        setInternalPanel(getContentPanel());
        registerAsObserver();
    }

    protected JPanel getOfferingsTable() {

        JPanel offeringsTable = new JPanel();
//        panel.setBackground(Color.white);

        // Add buttons into the table, button for each course offering

        // Testing it out
//        JButton testButton = new JButton("CMPT 213 - SURREY");
//        JButton testButton2 = new JButton("CMPT 213 - BURNABY");
//        JButton testButton3 = new JButton("CMPT 213 - VANCOUVER");
//        JButton testButton4 = new JButton("CMPT 373 - SURREY");

        offeringsTable.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTHWEST;

        // 550 is the maximum height of the table

        int maximum_height = 550;

        int years = 10;

        int cell_height = maximum_height / years;

        for (int i = 0; i < years + 1; i++) {

            if (i == 0) {

                JLabel emptyLabel = new JLabel("");
                emptyLabel.setOpaque(true);

                JLabel springLabel = new JLabel("Spring", SwingConstants.LEFT);
                springLabel.setOpaque(true);

                JLabel summerLabel = new JLabel("Summer", SwingConstants.LEFT);
                summerLabel.setOpaque(true);

                JLabel fallLabel = new JLabel("Fall", SwingConstants.LEFT);
                fallLabel.setOpaque(true);
                c.fill = GridBagConstraints.BOTH;

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = 0;
                c.gridy = i;
                offeringsTable.add(emptyLabel, c);
                offeringsTable.add(Box.createVerticalGlue());

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = 1;
                c.gridy = i;
                offeringsTable.add(springLabel, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = 2;
                c.gridy = i;
                offeringsTable.add(summerLabel, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = 3;
                c.gridy = i;
                offeringsTable.add(fallLabel, c);
            }

            // Years
            else if (i >= 1) {

                int year = 2000 + i - 1;
                String yearText = year + "";
                JLabel yearLabel = new JLabel(yearText, SwingConstants.LEFT);
                yearLabel.setOpaque(true);
                yearLabel.setPreferredSize(new Dimension(45, cell_height));

                JPanel spring = new JPanel();
                spring.setBackground(Color.white);
                spring.setBorder(BorderFactory.createLineBorder(Color.black));
                spring.setPreferredSize(new Dimension(220, cell_height));

                JPanel summer = new JPanel();
                summer.setBackground(Color.white);
                summer.setBorder(BorderFactory.createLineBorder(Color.black));
                summer.setPreferredSize(new Dimension(220, cell_height));

                JPanel fall = new JPanel();
                fall.setBackground(Color.white);
                fall.setBorder(BorderFactory.createLineBorder(Color.black));
                fall.setPreferredSize(new Dimension(220, cell_height));

                c.fill = GridBagConstraints.BOTH;

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = 0;
                c.gridy = i;
                offeringsTable.add(yearLabel, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = 1;
                c.gridy = i;
                offeringsTable.add(spring, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
                c.gridx = 2;
                c.gridy = i;
                offeringsTable.add(summer, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.BOTH;
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

        semesterOfferingsPanel.setPreferredSize(new Dimension(800, HEIGHT));

        return semesterOfferingsPanel;
    }

    private void registerAsObserver() {

    }

    private void updateOfferingsTable() {

    }
}

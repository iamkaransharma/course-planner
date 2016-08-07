package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class SemesterOfferingsPanel extends GUIPanel{

    public SemesterOfferingsPanel (CoursePlanner coursePlanner){
        super(coursePlanner);
        this.setLabel("Course Offerings by Semester");
    }
    @Override
    protected Component getComponent() {
//        panel.setBackground(Color.white);

        // Add buttons into the table, button for each course offering

        // Testing it out
//        JButton testButton = new JButton("CMPT 213 - SURREY");
//        JButton testButton2 = new JButton("CMPT 213 - BURNABY");
//        JButton testButton3 = new JButton("CMPT 213 - VANCOUVER");
//        JButton testButton4 = new JButton("CMPT 373 - SURREY");

        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        int rows = 10;
        for (int i = 0; i < rows; i++){

            JLabel spring = new JLabel("Spring");
            spring.setBackground(Color.white);
            spring.setBorder(BorderFactory.createLineBorder(Color.black));
            spring.setOpaque(true);

            JLabel summer = new JLabel("Summer");
            summer.setBackground(Color.white);
            summer.setBorder(BorderFactory.createLineBorder(Color.black));
            summer.setOpaque(true);

            JLabel fall = new JLabel("Fall");
            fall.setBackground(Color.white);
            fall.setBorder(BorderFactory.createLineBorder(Color.black));
            fall.setOpaque(true);

            c.fill = GridBagConstraints.HORIZONTAL;

            c.weightx = 0.5;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = i;
            panel.add(spring,c);

            c.weightx = 0.5;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 1;
            c.gridy = i;
            panel.add(summer,c);

            c.weightx = 0.5;
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = i;
            panel.add(fall,c);
        }

        // Default number of rows is 10 years.

        return panel;
    }

    @Override
    protected JPanel getPanel(){

        JPanel semesterOfferingsPanel = new JPanel();

        semesterOfferingsPanel.setLayout(new BorderLayout());

        semesterOfferingsPanel.add(getLabel(),BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        tablePanel.add(getComponent(),BorderLayout.CENTER);
        semesterOfferingsPanel.add(tablePanel);

        semesterOfferingsPanel.setPreferredSize(new Dimension(800,HEIGHT));
        return semesterOfferingsPanel;
    }
}

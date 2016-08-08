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

    protected Component getTableRowOfLabels(){
        JPanel tableRow = new JPanel();
        tableRow.setLayout(new BoxLayout(tableRow,BoxLayout.LINE_AXIS));

        JLabel springLabel = new JLabel("Spring", SwingConstants.CENTER);
        springLabel.setBackground(Color.white);
        springLabel.setOpaque(true);
        springLabel.setPreferredSize(new Dimension(200,15));

        JLabel summerLabel = new JLabel("Summer", SwingConstants.CENTER);
        summerLabel.setBackground(Color.white);
        summerLabel.setOpaque(true);
        summerLabel.setPreferredSize(new Dimension(200,15));

        JLabel fallLabel = new JLabel("Fall", SwingConstants.CENTER);
        fallLabel.setBackground(Color.white);
        fallLabel.setOpaque(true);
        fallLabel.setPreferredSize(new Dimension(200,15));

        tableRow.add(springLabel);
        tableRow.add(summerLabel);
        tableRow.add(fallLabel);

        return tableRow;
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

        // 550 is the maximum height of the table

        int maximum_height = 550;

        int years = 10;

        int cell_height = maximum_height/years;

        for (int i = 0; i < years+1; i++){

            if (i == 0){

                JLabel emptyLabel = new JLabel("");
                emptyLabel.setOpaque(true);

                JLabel springLabel = new JLabel("Spring", SwingConstants.LEFT);
                springLabel.setOpaque(true);

                JLabel summerLabel = new JLabel("Summer", SwingConstants.LEFT);
                summerLabel.setOpaque(true);

                JLabel fallLabel = new JLabel("Fall", SwingConstants.LEFT);
                fallLabel.setOpaque(true);
                c.fill = GridBagConstraints.HORIZONTAL;

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = i;
                panel.add(emptyLabel, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = i;
                panel.add(springLabel, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 2;
                c.gridy = i;
                panel.add(summerLabel, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 3;
                c.gridy = i;
                panel.add(fallLabel, c);
            }

            // Years
            else if (i >= 1) {

                int year = 2000 + i - 1;
                String yearText = year + "";
                JLabel yearLabel = new JLabel(yearText, SwingConstants.LEFT);
                yearLabel.setOpaque(true);
                yearLabel.setPreferredSize(new Dimension(45,cell_height));

                JLabel spring = new JLabel("", SwingConstants.CENTER);
                spring.setBackground(Color.white);
                spring.setBorder(BorderFactory.createLineBorder(Color.black));
                spring.setOpaque(true);
                spring.setPreferredSize(new Dimension(225, cell_height));

                JLabel summer = new JLabel("", SwingConstants.CENTER);
                summer.setBackground(Color.white);
                summer.setBorder(BorderFactory.createLineBorder(Color.black));
                summer.setOpaque(true);
                summer.setPreferredSize(new Dimension(225, cell_height));

                JLabel fall = new JLabel("", SwingConstants.CENTER);
                fall.setBackground(Color.white);
                fall.setBorder(BorderFactory.createLineBorder(Color.black));
                fall.setOpaque(true);
                fall.setPreferredSize(new Dimension(225, cell_height));

                c.fill = GridBagConstraints.HORIZONTAL;

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = i;
                panel.add(yearLabel, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 1;
                c.gridy = i;
                panel.add(spring, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 2;
                c.gridy = i;
                panel.add(summer, c);

                c.weightx = 0.5;
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 3;
                c.gridy = i;
                panel.add(fall, c);
            }
        }

        // Default number of years is 10 years.

        return panel;
    }

    @Override
    protected JPanel getPanel(){

        JPanel semesterOfferingsPanel = new JPanel();

        semesterOfferingsPanel.setLayout(new BorderLayout());

        semesterOfferingsPanel.add(getLabel(),BorderLayout.NORTH);

        JPanel tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        tablePanel.add(getComponent());
        semesterOfferingsPanel.add(tablePanel,BorderLayout.CENTER);

        semesterOfferingsPanel.setPreferredSize(new Dimension(800,HEIGHT));

        return semesterOfferingsPanel;
    }
}

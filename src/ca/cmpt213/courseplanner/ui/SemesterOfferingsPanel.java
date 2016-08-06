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
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);

        // Add buttons into the table, button for each course offering

        // Testing it out
        JButton testButton = new JButton("CMPT 213 - SURREY");
        JButton testButton2 = new JButton("CMPT 213 - BURNABY");
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;

        panel.add(testButton,c);

        panel.add(testButton2,c);

        return panel;
    }
    @Override
    protected JPanel getPanel(){
        JPanel semesterOfferingsPanel = new JPanel();
        semesterOfferingsPanel.setLayout(new BorderLayout());
        semesterOfferingsPanel.add(getLabel(),BorderLayout.NORTH);
        semesterOfferingsPanel.add(getComponent(),BorderLayout.CENTER);
        semesterOfferingsPanel.setPreferredSize(new Dimension(800,HEIGHT));
        return semesterOfferingsPanel;
    }
}

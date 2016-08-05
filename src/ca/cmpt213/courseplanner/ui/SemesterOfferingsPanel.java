package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
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
    protected JPanel getPanel(){
        JPanel semesterOfferingsPanel = new JPanel();
        semesterOfferingsPanel.setLayout(new BorderLayout());
        semesterOfferingsPanel.add(getLabel(),BorderLayout.NORTH);
        semesterOfferingsPanel.add(getComponent(),BorderLayout.CENTER);
        semesterOfferingsPanel.setPreferredSize(new Dimension(800,HEIGHT));
        return semesterOfferingsPanel;
    }
}

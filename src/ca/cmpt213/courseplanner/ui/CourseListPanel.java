package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListPanel extends GUIPanel{

    public CourseListPanel(CoursePlanner coursePlanner){
        super(coursePlanner);
        this.setLabel("Course List");
    }

    @Override
    protected JPanel getPanel(){
        JPanel courseListPanel = new JPanel();
        courseListPanel.setLayout(new BorderLayout());
        courseListPanel.add(getLabel(),BorderLayout.NORTH);
        courseListPanel.add(getComponent(),BorderLayout.CENTER);
        courseListPanel.setMinimumSize(new Dimension(200,300));
        courseListPanel.setPreferredSize(new Dimension(200,300));
        return courseListPanel;
    }

}

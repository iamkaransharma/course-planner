package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListPanel extends GUIPanel{

    private JList listOfCourses;

    public CourseListPanel(CoursePlanner coursePlanner){
        super(coursePlanner);
        this.setLabel("Course List");
    }

    @Override
    protected Component getComponent(){
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);


        // Need to insert list of courses from selected department inside this parameter.
        String[] temporarytestdata = {"CMPT 213","CMPT 307","CMPT 213","CMPT 307","CMPT 213","CMPT 307",
                "CMPT 213","CMPT 307","CMPT 213","CMPT 307","CMPT 213","CMPT 307","CMPT 213","CMPT 307"};
        listOfCourses = new JList(temporarytestdata);
//        listOfCourses.setSize(150,300);
//        listOfCourses.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        listOfCourses.setVisibleRowCount(-1);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(listOfCourses);

//        listOfCourses.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//
//            }
//         });


        panel.add(listOfCourses);

        return panel;
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

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
        String[] temporarytestdata = {"CMPT 213","CMPT 307","CMPT 225","CMPT 300","CMPT 250","CMPT 354"
        ,"CMPT 471","CMPT 470","CMPT 454","CMPT 125","CMPT 150","CMPT 310"};
        listOfCourses = new JList(temporarytestdata);
        listOfCourses.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        listOfCourses.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        listOfCourses.setFixedCellWidth(90);
        listOfCourses.setVisibleRowCount(-1);

        JScrollPane scrollPane = new JScrollPane(listOfCourses);
        scrollPane.setPreferredSize(new Dimension(200,400));



//        listOfCourses.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//
//            }
//         });


        panel.add(scrollPane);

        return panel;
    }

    @Override
    protected JPanel getPanel(){
        JPanel courseListPanel = new JPanel();
        courseListPanel.setLayout(new BorderLayout());
        courseListPanel.add(getLabel(),BorderLayout.NORTH);
        courseListPanel.add(getComponent(),BorderLayout.CENTER);
        courseListPanel.setMinimumSize(new Dimension(200,425));
        courseListPanel.setPreferredSize(new Dimension(200,425));
        return courseListPanel;
    }

}

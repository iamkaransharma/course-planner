//package ca.cmpt213.courseplanner.ui;
//
//import ca.cmpt213.courseplanner.model.*;
//import ca.cmpt213.courseplanner.model.CoursePlannerObserver;
//
//import javax.swing.*;
//import javax.swing.border.BevelBorder;
//import java.awt.*;
//import java.awt.event.*;
//import java.util.List;
//
///**
// * Created by Thomas_Ngo on 2016-07-30.
// */
//public class CourseListPanel extends GUIPanel{
//
//    private JList listOfCourses;
//    private List<Course> coursesInsideSelectedDepartment;
//    private String[] courses;
//
//    public CourseListPanel(CoursePlanner coursePlanner){
//        super(coursePlanner);
//        this.setLabel("Course List");
//        registerAsObserver();
//    }
//
//    @Override
//    protected Component getComponent(){
//        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
//        panel.setBackground(Color.white);
//
//        // Need to insert list of courses from selected department inside this parameter.
//
//        listOfCourses = new JList();
//        listOfCourses.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
//        listOfCourses.setLayoutOrientation(JList.HORIZONTAL_WRAP);
//        listOfCourses.setFixedCellWidth(90);
//        listOfCourses.setVisibleRowCount(-1);
//
//        JScrollPane scrollPane = new JScrollPane(listOfCourses);
//        scrollPane.setPreferredSize(new Dimension(200,400));
//
////        listOfCourses.addMouseListener(new MouseAdapter() {
////            public void mouseClicked(MouseEvent e) {
////
////            }
////         });
//
//
//        panel.add(scrollPane);
//
//        return panel;
//    }
//
//    @Override
//    protected JPanel getPanel(){
//        JPanel courseListPanel = new JPanel();
//        courseListPanel.setLayout(new BorderLayout());
//        courseListPanel.add(getLabel(),BorderLayout.NORTH);
//        courseListPanel.add(getComponent(),BorderLayout.CENTER);
//        courseListPanel.setMinimumSize(new Dimension(200,425));
//        courseListPanel.setPreferredSize(new Dimension(200,425));
//        return courseListPanel;
//    }
//
//
//    private void registerAsObserver() {
//        coursePlanner.addCourseListObserver(
//                new CoursePlannerObserver() {
//                    @Override
//                    public void modelStateChanged() {
//                        updateCourseList();
//                    }
//                }
//        );
//    }
//
//    private void updateCourseList(){
//        coursesInsideSelectedDepartment = coursePlanner.getActiveDepartment().getCourses();
//        courses = new String[coursesInsideSelectedDepartment.size()];
//
//        System.out.println(coursesInsideSelectedDepartment.size());
//
//        for (int i = 0; i < coursesInsideSelectedDepartment.size(); i++){
//            courses[i] = coursesInsideSelectedDepartment.get(i).getFullName();
//        }
//
//        listOfCourses.setListData(courses);
//    }
//
//    //modelStateChanged is where you get the actual data.
//
//}

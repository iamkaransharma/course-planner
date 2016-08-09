package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.Course;
import ca.cmpt213.courseplanner.model.CoursePlanner;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CourseListPanel contains a list of undergrad/grad courses from the selected department and displays them in a list
 * that the user can click on to display all of the offerings for that course
 */

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListPanel extends GUIPanel {

    private static final String TITLE = "Course List";

    private List<Course> selectedCourseList;
    private DefaultListModel<String> defaultListModel;

    public CourseListPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);

        this.selectedCourseList = new ArrayList<>();
        this.defaultListModel = new DefaultListModel<>();
        setInternalPanel(getContentPanel());
        registerAsObserver();
    }

    private JPanel getContentPanel() {
        // Split list view of courses
        updateListModelWithCourses();

        JList<String> courseListView = new JList<>(defaultListModel);
        courseListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseListView.setFixedCellWidth(90);
        courseListView.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        courseListView.setVisibleRowCount(-1);

        courseListView.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedIndex = courseListView.getSelectedIndex();
                if (selectedIndex != -1) {
                    Course selectedCourse = selectedCourseList.get(selectedIndex);
                    getModel().selectCourse(selectedCourse);
                }
            }
        });

        JScrollPane listScroller = new JScrollPane(courseListView);
        listScroller.setPreferredSize(new Dimension(200, 400));

        JPanel listContainer = new JPanel();
        listContainer.add(listScroller);

        listContainer.setMinimumSize(new Dimension(200, 425));
        listContainer.setPreferredSize(new Dimension(200, 425));

        return listContainer;
    }

    private String[] createCourseNamesList() {
        String[] courseNames = new String[selectedCourseList.size()];
        for (int i = 0; i < selectedCourseList.size(); i++) {
            courseNames[i] = selectedCourseList.get(i).getFullName();
        }
        return courseNames;
    }

    private void registerAsObserver() {
        getModel().addCourseListObserver(
                () -> updateCourseList()
        );
    }

    private void updateCourseList() {
        selectedCourseList.clear();
        selectedCourseList.addAll(getModel().getActiveCourseList());
        updateListModelWithCourses();
    }

    private void updateListModelWithCourses() {
        defaultListModel.clear();
        for (Course currentCourse : selectedCourseList) {
            defaultListModel.addElement(currentCourse.getFullName());
        }
    }
}

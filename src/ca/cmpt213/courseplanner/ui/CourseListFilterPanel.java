package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CourseListFilter;
import ca.cmpt213.courseplanner.model.CoursePlanner;
import ca.cmpt213.courseplanner.model.Department;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CourseListFilterPanel contains a list of departments that the user can select from the JComboBox.
 * It also lets the users choose if they want to see only undergrad courses, only grad courses, none of
 * them or both. The user presses the "Update Course List" to change the CourseListPanel's display.
 */

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListFilterPanel extends GUIPanel {

    private static final String TITLE = "Course List Filter";

    private List<Department> departmentList;

    //    JCheckBox undergradCoursesButton;
//    JCheckBox gradCoursesButton;
//
//    JComboBox<String> departmentList;
//
//    Department selectedDepartment;
//    Integer selectedIndex;
    private Department selectedDepartment;
    private CourseListFilter selectedFilter;

    public CourseListFilterPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);

        Set<Department> departmentSet = coursePlanner.getDepartmentManager().getDepartments();
        this.departmentList = new ArrayList<>();
        this.departmentList.addAll(departmentSet);

        selectedDepartment = null;
        selectedFilter = null;
        setInternalPanel(getContentPanel());
    }

    private JPanel getContentPanel() {
        // Dropdown
        String[] departmentNames = createDepartmentNamesList();
        JComboBox<String> departmentNamesBox = new JComboBox<>(departmentNames);
        selectedDepartment = departmentList.get(0);
        departmentNamesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = departmentNamesBox.getSelectedIndex();
                selectedDepartment = departmentList.get(selectedIndex);
            }
        });
        JPanel dropdownPanel = new JPanel();
        dropdownPanel.setLayout(new BoxLayout(dropdownPanel, BoxLayout.LINE_AXIS));
        dropdownPanel.add(new JLabel("Department"));
        dropdownPanel.add(departmentNamesBox);

        // Checkboxes
        JCheckBox undergradSelectButton = new JCheckBox("Include undergrad courses");
        undergradSelectButton.setMnemonic(KeyEvent.VK_C);
//        undergradSelectButton.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (undergradSelectButton.isSelected()) {
//                    selectedFilter = CourseListFilter.UNDERGRADUATE_COURSES;
//                }
//            }
//        });
        undergradSelectButton.setSelected(true);

        JCheckBox gradSelectButton = new JCheckBox("Include grad courses");
        gradSelectButton.setMnemonic(KeyEvent.VK_C);
//        gradSelectButton.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (gradSelectButton.isSelected()) {
//                    selectedFilter = CourseListFilter.GRADUATE_COURSES;
//                }
//            }
//        });
        gradSelectButton.setSelected(false);

        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));
        checkBoxes.add(undergradSelectButton);
        checkBoxes.add(gradSelectButton);

        // Button to update the list
        JButton updateListButton = new JButton("Update Course List");

        updateListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undergradSelectButton.isSelected() && gradSelectButton.isSelected()) {
                    selectedFilter = CourseListFilter.ALL_COURSES;
                } else if (undergradSelectButton.isSelected()) {
                    selectedFilter = CourseListFilter.UNDERGRADUATE_COURSES;
                } else if (gradSelectButton.isSelected()) {
                    selectedFilter = CourseListFilter.GRADUATE_COURSES;
                } else {
                    selectedFilter = CourseListFilter.NO_COURSES;
                }
                getModel().selectDepartment(selectedDepartment, selectedFilter);
            }
        });

//        panel.add(new JLabel("Department:"));
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(dropdownPanel, BorderLayout.NORTH);
        contentPanel.add(checkBoxes, BorderLayout.CENTER);
        contentPanel.add(updateListButton, BorderLayout.SOUTH);
        return contentPanel;
    }
//
//    @Override
//    protected Component getComponent() {
//        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
//        panel.setBackground(Color.white);
//
//        String[] departmentNameList = createDepartmentNamesList();
//        departmentList = new JComboBox(departmentNameList);
//
//        departmentList.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                selectedIndex = (Integer) departmentList.getSelectedIndex();
//                selectedDepartment = getModel().getDepartmentManager().getDepartments().get(selectedIndex);
//                //System.out.println(selectedDepartment.getCourses().size());
//            }
//        });
//
//        // Check-boxes for undergrad and grad courses
//
//        JPanel checkBoxes = new JPanel();
//        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));
//        checkBoxes.setBackground(Color.white);
//
//        undergradCoursesButton = new JCheckBox("Include undergrad courses");
//        undergradCoursesButton.setMnemonic(KeyEvent.VK_C);
//        undergradCoursesButton.setSelected(true);
//        undergradCoursesButton.addItemListener(this);
//
//        gradCoursesButton = new JCheckBox("Include grad courses");
//        gradCoursesButton.setMnemonic(KeyEvent.VK_C);
//        gradCoursesButton.setSelected(false);
//        gradCoursesButton.addItemListener(this);
//
//        checkBoxes.add(undergradCoursesButton);
//        checkBoxes.add(gradCoursesButton);
//
//        // Button to update the list
//        JButton updateListButton = new JButton("Update Course List");
//
//        updateListButton.addActionListener(
//                event -> updateSelectedDepartment()
//        );
//
//        panel.add(new JLabel("Department:"));
//        panel.add(departmentList);
//        panel.add(checkBoxes);
//        panel.add(updateListButton);
//
//        return panel;
//    }

    private String[] createDepartmentNamesList() {
        String[] departmentNames = new String[departmentList.size()];
        for (int i = 0; i < departmentList.size(); i++) {
            departmentNames[i] = departmentList.get(i).getName();
        }
        return departmentNames;
    }

//    @Override
//    protected JPanel getPanel(){
//        JPanel courseListFilterPanel = new JPanel();
//        courseListFilterPanel.setLayout(new BorderLayout());
//        courseListFilterPanel.add(getLabel(),BorderLayout.NORTH);
//        courseListFilterPanel.add(getComponent(),BorderLayout.CENTER);
//        courseListFilterPanel.setMinimumSize(new Dimension(225,175));
//        courseListFilterPanel.setPreferredSize(new Dimension(225,175));
//        return courseListFilterPanel;
//    }

//    @Override
//    public void itemStateChanged(ItemEvent e) {
//
//    }

//    private void updateSelectedDepartment() {
//        getModel().selectDepartment(selectedDepartment, selectedFilter);
//        System.out.println(getModel().getActiveCourseList());
//    }
}

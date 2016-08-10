package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.CourseListFilter;
import ca.cmpt213.courseplanner.model.CoursePlanner;
import ca.cmpt213.courseplanner.model.Department;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CourseListFilterPanel contains a list of departments that the user can select from the JComboBox.
 * It also lets the users choose if they want to see only undergrad courses, only grad courses, none of
 * them or both. The user presses the "Update Course List" to change the CourseListPanel's display.
 */
public class CourseListFilterPanel extends GUIBasePanel {

    private static final String TITLE = "Course List Filter";
    private static final int DEFAULT_WIDTH = 90;
    private static final int DEFAULT_HEIGHT = 105;

    private List<Department> departmentList;
    private Department selectedDepartment;
    private CourseListFilter selectedFilter;

    public CourseListFilterPanel(CoursePlanner coursePlanner) {
        super(coursePlanner, TITLE);

        Set<Department> departmentSet = coursePlanner.getDepartmentManager().getDepartments();
        this.departmentList = new ArrayList<>();
        this.departmentList.addAll(departmentSet);

        selectedDepartment = null;
        selectedFilter = null;
        resizeHorizontallyOnly(this);
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
        dropdownPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        dropdownPanel.add(departmentNamesBox);

        // Checkboxes
        JCheckBox undergradSelectButton = new JCheckBox("Include undergrad courses");
        undergradSelectButton.setMnemonic(KeyEvent.VK_C);
        undergradSelectButton.setSelected(true);

        JCheckBox gradSelectButton = new JCheckBox("Include grad courses");
        gradSelectButton.setMnemonic(KeyEvent.VK_C);
        gradSelectButton.setSelected(false);

        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));
        checkBoxes.add(undergradSelectButton);
        checkBoxes.add(gradSelectButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(dropdownPanel, BorderLayout.NORTH);
        contentPanel.add(checkBoxes, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(makeUpdateButton(undergradSelectButton, gradSelectButton));
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        contentPanel.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        return contentPanel;
    }

    private JButton makeUpdateButton(final JCheckBox undergradSelectButton, final JCheckBox gradSelectButton) {
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
        return updateListButton;
    }


    private String[] createDepartmentNamesList() {
        String[] departmentNames = new String[departmentList.size()];
        for (int i = 0; i < departmentList.size(); i++) {
            departmentNames[i] = departmentList.get(i).getName();
        }
        return departmentNames;
    }
}

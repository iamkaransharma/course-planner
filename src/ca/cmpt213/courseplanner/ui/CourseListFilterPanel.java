package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListFilterPanel extends GUIPanel implements ItemListener{

    private List<Department> departments;

    JCheckBox undergradCoursesButton;
    JCheckBox gradCoursesButton;

    JComboBox <String> departmentList;

    Department selectedDepartment;
    Integer selectedIndex;

    public CourseListFilterPanel(CoursePlanner coursePlanner){
        super(coursePlanner);
        departments = coursePlanner.getDepartmentManager().getDepartments();
        this.setLabel("Course List Filter");
    }

    @Override
    protected Component getComponent() {
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);

        String[] departmentNameList = createDepartmentNamesList();
        departmentList = new JComboBox(departmentNameList);

        // Default selected department
        selectedDepartment = coursePlanner.getDepartmentManager().getDepartments().get(0);

        departmentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedIndex = (Integer) departmentList.getSelectedIndex();
                selectedDepartment = coursePlanner.getDepartmentManager().getDepartments().get(selectedIndex);
                //System.out.println(selectedDepartment.getCourses().size());
            }
        });

        // Check-boxes for undergrad and grad courses

        JPanel checkBoxes = new JPanel();
        checkBoxes.setLayout(new BoxLayout(checkBoxes, BoxLayout.PAGE_AXIS));
        checkBoxes.setBackground(Color.white);

        undergradCoursesButton = new JCheckBox("Include undergrad courses");
        undergradCoursesButton.setMnemonic(KeyEvent.VK_C);
        undergradCoursesButton.setSelected(true);
        undergradCoursesButton.addItemListener(this);

        gradCoursesButton = new JCheckBox("Include grad courses");
        gradCoursesButton.setMnemonic(KeyEvent.VK_C);
        gradCoursesButton.setSelected(false);
        gradCoursesButton.addItemListener(this);

        checkBoxes.add(undergradCoursesButton);
        checkBoxes.add(gradCoursesButton);

        // Button to update the list
        JButton updateListButton = new JButton("Update Course List");

        updateListButton.addActionListener(
                event -> updateSelectedDepartment()
        );

        panel.add(new JLabel("Department:"));
        panel.add(departmentList);
        panel.add(checkBoxes);
        panel.add(updateListButton);

        return panel;
    }

    private String[] createDepartmentNamesList(){
        String[] departmentNames = new String[departments.size()];
        for (int i = 0; i < departments.size(); i++){
            departmentNames[i] = departments.get(i).getName();
        }
        //Arrays.sort(departmentNames);
        return departmentNames;
    }

    @Override
    protected JPanel getPanel(){
        JPanel courseListFilterPanel = new JPanel();
        courseListFilterPanel.setLayout(new BorderLayout());
        courseListFilterPanel.add(getLabel(),BorderLayout.NORTH);
        courseListFilterPanel.add(getComponent(),BorderLayout.CENTER);
        courseListFilterPanel.setMinimumSize(new Dimension(225,175));
        courseListFilterPanel.setPreferredSize(new Dimension(225,175));
        return courseListFilterPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    private void updateSelectedDepartment(){
        coursePlanner.selectDepartment(selectedDepartment);
    }



}

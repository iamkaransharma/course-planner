package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListFilterPanel extends GUIPanel implements ItemListener{

    private ArrayList<Department> departments = new ArrayList<>();

    JCheckBox undergradCoursesButton;
    JCheckBox gradCoursesButton;

    public CourseListFilterPanel(CoursePlanner coursePlanner){
        super(coursePlanner);
        departments = coursePlanner.getDepartments();
        this.setLabel("Course List Filter");

    }

    @Override
    protected Component getComponent() {
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.gray));
        panel.setBackground(Color.white);

        String[] departmentNameList = createDepartmentNamesList();
        JComboBox <String> departmentList = new JComboBox(departmentNameList);

        departmentList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedDepartment = (String) departmentList.getSelectedItem();
                Integer selectedIndex = (Integer) departmentList.getSelectedIndex();
                System.out.println(selectedDepartment + " at index: " + selectedIndex);
            }
        });

        // Check-boxes for undergrad and grad courses
        undergradCoursesButton = new JCheckBox("Include undergrad courses");
        undergradCoursesButton.setMnemonic(KeyEvent.VK_C);
        undergradCoursesButton.setSelected(true);
        undergradCoursesButton.addItemListener(this);

        gradCoursesButton = new JCheckBox("Include grad courses");
        gradCoursesButton.setMnemonic(KeyEvent.VK_C);
        gradCoursesButton.setSelected(false);
        gradCoursesButton.addItemListener(this);

        // Button to update the list
        JButton updateListButton = new JButton("Update Course List");

        updateListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        panel.add(new JLabel("Department:"));
        panel.add(departmentList);
        panel.add(undergradCoursesButton);
        panel.add(gradCoursesButton);
        panel.add(updateListButton);

        return panel;
    }

    private String[] createDepartmentNamesList(){
        String[] departmentNames = new String[departments.size()];
        for (int i = 0; i < departments.size(); i++){
            departmentNames[i] = departments.get(i).getDepartmentName();
        }
        Arrays.sort(departmentNames);
        return departmentNames;
    }

    @Override
    protected JPanel getPanel(){
        JPanel courseListFilterPanel = new JPanel();
        courseListFilterPanel.setLayout(new BorderLayout());
        courseListFilterPanel.add(getLabel(),BorderLayout.NORTH);
        courseListFilterPanel.add(getComponent(),BorderLayout.CENTER);
        courseListFilterPanel.setMinimumSize(new Dimension(200,300));
        courseListFilterPanel.setPreferredSize(new Dimension(200,300));
        return courseListFilterPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}

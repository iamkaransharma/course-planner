package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListFilterPanel extends GUIPanel{

    private ArrayList<Department> departments = new ArrayList<>();

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

        panel.add(departmentList);

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

}

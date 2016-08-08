package ca.cmpt213.courseplanner.ui;

import ca.cmpt213.courseplanner.model.*;

import javax.swing.*;
import java.util.*;
import java.awt.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseListFilterPanel extends GUIPanel{

    private ArrayList<Department> departments = new ArrayList<>();

    public CourseListFilterPanel(CoursePlanner coursePlanner, String title){
        super(coursePlanner,title);
//        departments = coursePlanner.getDepartments();
    }

}

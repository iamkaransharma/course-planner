package ca.cmpt213.courseplanner.model;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Department {

    private ArrayList<Course> courses;

    private String departmentName;

    public Department (String departmentName){
        this.departmentName = departmentName;
    }

    public ArrayList<Course> getCourses(){
        return courses;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    @Override
    public String toString(){
        return "Department: " + departmentName;
    }

}

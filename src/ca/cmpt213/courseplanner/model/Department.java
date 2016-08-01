package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Department {

    private ArrayList<Course> courses;

    private String departmentName;

    public Department (ArrayList<Course> courses, String departmentName){
        this.courses = courses;
        this.departmentName = departmentName;
    }



}

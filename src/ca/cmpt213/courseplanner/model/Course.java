package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Course {

    private ArrayList<CourseOffering> courseOfferings = new ArrayList<>();

    private String catalogNumber;

    public Course (String catalogNumber){
        this.courseOfferings = courseOfferings;
        this.catalogNumber = catalogNumber;
    }

    public ArrayList<CourseOffering> getCourseOfferings(){
        return courseOfferings;
    }

    public void addCourseOffering(CourseOffering courseOffering){
        courseOfferings.add(courseOffering);
    }

    public String getCatalogNumber(){
        return catalogNumber;
    }


}

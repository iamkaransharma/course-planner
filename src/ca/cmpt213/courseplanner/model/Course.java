package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Course {

    private ArrayList<CourseOffering> courseOfferings;

    private int catalogNumber;

    public Course (ArrayList<CourseOffering> courseOfferings, int catalogNumber){
        this.courseOfferings = courseOfferings;
        this.catalogNumber = catalogNumber;
    }

    public ArrayList<CourseOffering> getCourseOfferings(){
        return courseOfferings;
    }

    public int getCatalogNumber(){
        return catalogNumber;
    }


}

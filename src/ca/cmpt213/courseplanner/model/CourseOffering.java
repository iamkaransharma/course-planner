package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseOffering {

    private String location;
    private int enrolmentCapacity;
    private int enrolmentTotal;
    private ArrayList<String> instructors;
    private String componentCode;

    public CourseOffering(String location, int enrolmentCapacity, int enrolmentTotal,
                          ArrayList<String> instructors, String componentCode){

        this.location = location;
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;

    }

}

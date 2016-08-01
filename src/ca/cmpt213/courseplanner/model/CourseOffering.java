package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CourseOffering {

    private String semester;
    private String departmentName;
    private String catalogNumber;
    private String location;
    private String enrolmentCapacity;
    private String enrolmentTotal;
    private String instructors;
    private String componentCode;

    public CourseOffering(String semester, String departmentName, String catalogNumber,
                          String location, String enrolmentCapacity, String enrolmentTotal,
                          String instructors, String componentCode){
        this.semester = semester;
        this.departmentName = departmentName;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.enrolmentCapacity = enrolmentCapacity;
        this.enrolmentTotal = enrolmentTotal;
        this.instructors = instructors;
        this.componentCode = componentCode;

    }

    public String getSemester(){
        return semester;
    }

    public String getDepartmentName(){
        return departmentName;
    }

    public int getCatalogNumber(){
        return Integer.parseInt(catalogNumber);
    }

    public int getEnrolmentCapacity(){
        return Integer.parseInt(enrolmentCapacity);
    }

    public int getEnrolmentTotal(){
        return Integer.parseInt(enrolmentTotal);
    }

    public String getInstructors(){
        return instructors;
    }

    public String getComponentCode(){
        return componentCode;
    }

    @Override
    public String toString(){
        return "Semester: " + this.semester + " Department: " + this.departmentName +
                " Catalog Number: " + this.catalogNumber + " Location: " + this.location
                + " Enrolment Capacity: " + this.enrolmentCapacity + " Enrolment Total: "
                + this.enrolmentTotal + "\n" + "Instructors: " + this.instructors + "\n" + "Component Code: "
                + this.componentCode;
    }


}

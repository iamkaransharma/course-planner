package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CoursePlanner {

    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Course> courses;
    private ArrayList<CourseOffering> importedData;

    private String csvFileName;

    public CoursePlanner (String csvFileName){
        this.csvFileName = csvFileName;

        CSVCourseReader csvfile = new CSVCourseReader(csvFileName);
        this.importedData = csvfile.scanCSVFile();
    }

    public void createDepartments(){

        Iterator <CourseOffering> itr = importedData.iterator();
        while (itr.hasNext()){
            CourseOffering element = itr.next();
            if (departments == null){
                departments.add(new Department(element.getDepartmentName()));
            }
            else if (!checkInsideDepartments(element.getDepartmentName())){
                departments.add(new Department(element.getDepartmentName()));
            }
        }

        for (Department d: departments){
            System.out.println(d.getDepartmentName());
        }
    }

    private boolean checkInsideDepartments(String departmentname){
        boolean containsDepartment = false;
            for (Department d : departments) {
                if (d.getDepartmentName().equals(departmentname)) {
                    containsDepartment = true;
                }
            }

        return containsDepartment;
    }
}

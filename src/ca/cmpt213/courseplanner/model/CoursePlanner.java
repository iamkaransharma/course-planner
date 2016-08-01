package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CoursePlanner {

    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<CourseOffering> importedData;

    private String csvFileName;

    public CoursePlanner (String csvFileName){
        this.csvFileName = csvFileName;

        CSVCourseReader csvfile = new CSVCourseReader(csvFileName);
        this.importedData = csvfile.scanCSVFile();
    }

    public void createDepartments(){

        Iterator <CourseOffering> courseOfferingIterator = importedData.iterator();
        while (courseOfferingIterator.hasNext()){
            CourseOffering offer = courseOfferingIterator.next();
            if (departments == null){
                departments.add(new Department(offer.getDepartmentName()));
            }
            else if (!checkInsideDepartments(offer.getDepartmentName())){
                departments.add(new Department(offer.getDepartmentName()));
            }
        }

        // Test Code
//        for (Department d: departments){
//            System.out.println(d.getDepartmentName());
//        }
    }

    public void insertCoursesIntoDepartments(){

        Iterator <Department> departmentIterator = departments.iterator();

        while (departmentIterator.hasNext()){

            Department currentDepartment = departmentIterator.next();

            for (CourseOffering currentOffer: importedData){
                if (currentDepartment.getDepartmentName().equals(currentOffer.getDepartmentName())) {
                    if (!checkForCourses(currentDepartment, currentOffer.getCatalogNumber())) {
                        currentDepartment.addCourse(new Course(currentOffer.getCatalogNumber()));
                    }
                }
            }
        }

        // Test Code
//         for (Department d: departments){
//             for (Course c: d.getCourses()) {
//                 System.out.println(d.getDepartmentName() + " " + c.getCatalogNumber());
//             }
//        }

    }

    public void insertCourseOfferingsIntoCourses(){
        Iterator <Department> departmentIterator = departments.iterator();

        while (departmentIterator.hasNext()){
            Department currentDepartment = departmentIterator.next();

            Iterator <Course> courseIterator = currentDepartment.getCourses().iterator();

            while (courseIterator.hasNext()){
                Course currentCourse = courseIterator.next();

                for (CourseOffering c: importedData){
                    if (c.getDepartmentName().equals(currentDepartment.getDepartmentName()) &&
                            c.getCatalogNumber().equals(currentCourse.getCatalogNumber())){

                        currentCourse.addCourseOffering(new CourseOffering(c.getSemester(),
                                c.getDepartmentName(), c.getCatalogNumber(), c.getLocation(),
                                c.getEnrolmentCapacity(),c.getEnrolmentTotal(),
                                c.getInstructors(), c.getComponentCode()));
                    }
                }
            }

        }

        // Test Code
//        for (Department d: departments){
//             for (Course c: d.getCourses()) {
//                 for (CourseOffering o: c.getCourseOfferings()) {
//                     System.out.println(o.toString());
//                 }
//             }
//        }

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

    private boolean checkForCourses(Department department, String catalogNumber){
        boolean containsCourse = false;
        for (Course c: department.getCourses()){
            if (c.getCatalogNumber().equals(catalogNumber)){
                containsCourse = true;
            }
        }
        return containsCourse;
    }

}

package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Karan on 06/08/2016.
 */
public class DepartmentManager implements Iterable<Department> {
    private List<Department> departments;

    public DepartmentManager() {
        departments = new ArrayList<>();
    }

    public void addOffering(String departmentName, String semesterCode, String catalogNumber,
                            String location, int enrollmentTotal, int enrollmentCapacity,
                            List<String> instructors, String componentCode) {
        Location newLocation = new Location(location);
        newLocation.addInstructors(instructors);

        CourseComponent newComponent = new CourseComponent(componentCode, enrollmentTotal, enrollmentCapacity);
        newLocation.addCourseComponent(newComponent);

        Semester newSemester = new Semester(semesterCode);
        Offering newOffering = new Offering(catalogNumber, newSemester);
        newOffering.addLocation(newLocation);

        Course newCourse = new Course(departmentName, catalogNumber);
        newCourse.addOffering(newOffering);

        Department newDepartment = new Department(departmentName);
        newDepartment.addCourse(newCourse);

        addDepartment(newDepartment);
    }

    private void addDepartment(Department newDepartment) {
        final int NOT_FOUND = -1;
        int currentIndex = this.departments.indexOf(newDepartment);
        if (currentIndex != NOT_FOUND) {
            Department currentDepartment = this.departments.get(currentIndex);
            currentDepartment.merge(newDepartment);
        } else {
            this.departments.add(newDepartment);
        }
    }

    public List<String> getDepartmentNames() {
        List<String> departmentNames = new ArrayList<>();
        for (Department department : departments) {
            departmentNames.add(department.getName());
        }
        return departmentNames;
    }

    public List<Department> getDepartments() {
        return departments;
    }

//    public Course getCourse(String departmentName, String catalogNumber) {
//        for (Department department : departments) {
//            if (department.getName().equals(departmentName)) {
//
//            }
//        }
//    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Department department : departments) {
            stringBuilder.append(department.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public Iterator<Department> iterator() {
        return departments.iterator();
    }
}

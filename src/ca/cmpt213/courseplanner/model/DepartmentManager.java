package ca.cmpt213.courseplanner.model;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * DepartmentManager adds departments into the "departments" set, and adds offerings into each course under their
 * respective department.
 */
public class DepartmentManager {
    private Set<Department> departments;

    public DepartmentManager() {
        departments = new TreeSet<>();
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
        boolean foundDepartment = false;
        for (Department currentDepartment : departments) {
            if (currentDepartment.equals(newDepartment)) {
                currentDepartment.merge(newDepartment);
                foundDepartment = true;
                break;
            }
        }
        if (!foundDepartment) {
            this.departments.add(newDepartment);
        }
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Department department : departments) {
            stringBuilder.append(department.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}

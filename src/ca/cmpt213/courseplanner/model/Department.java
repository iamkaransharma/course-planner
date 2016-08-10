package ca.cmpt213.courseplanner.model;

import java.util.Set;
import java.util.TreeSet;

/**
 * Department class models a department/faculty in a university. It contains a tree set of courses under its department,
 * adds courses into its tree set, and merges with other departments that share the same name as itself.
 */
public class Department implements Comparable<Department> {
    private String name;
    private TreeSet<Course> courses;

    public Department(String name) {
        this.name = name;
        courses = new TreeSet<>();
    }

    public void addCourse(Course newCourse) {
        boolean foundCourse = false;
        for (Course currentCourse : courses) {
            if (currentCourse.equals(newCourse)) {
                currentCourse.merge(newCourse);
                foundCourse = true;
                break;
            }
        }
        if (!foundCourse) {
            this.courses.add(newCourse);
        }
    }

    public void merge(Department other) {
        if (other.equals(this)) {
            for (Course otherCourse : other.getCourses(CourseListFilter.ALL_COURSES)) {
                addCourse(otherCourse);
            }
        }
    }

    public Set<Course> getCourses(CourseListFilter filter) {
        final String GRADUATE_COURSES_BEGIN = "500";
        Course firstGraduateCourse = new Course(name, GRADUATE_COURSES_BEGIN);
        Set<Course> filteredCourses;
        switch (filter) {
            case UNDERGRADUATE_COURSES:
                filteredCourses = courses.headSet(firstGraduateCourse, false);
                break;
            case GRADUATE_COURSES:
                filteredCourses = courses.tailSet(firstGraduateCourse, true);
                break;
            case ALL_COURSES:
                filteredCourses = courses;
                break;
            case NO_COURSES:
                filteredCourses = new TreeSet<>();
                break;
            default:
                throw new RuntimeException("Unexpected CourseListFilter received");
        }
        return filteredCourses;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Course currentCourse : courses) {
            stringBuilder.append(name);
            stringBuilder.append(" ");
            stringBuilder.append(currentCourse.toString());
            stringBuilder.append("\n\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != this.getClass()) {
            return false;
        }
        Department other = (Department) otherObject;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 19;
    }

    @Override
    public int compareTo(Department other) {
        return name.compareTo(other.name);
    }
}

package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Department implements Iterable<Course> {
    private static final String GRADUATE_COURSES_BEGIN = "500";
    private String name;
    private List<Course> courses;

    public Department(String name) {
        this.name = name;
        courses = new ArrayList<>();
    }

    public void addCourse(Course newCourse) {
        final int NOT_FOUND = -1;
        int currentIndex = this.courses.indexOf(newCourse);
        if (currentIndex != NOT_FOUND) {
            Course currentCourse = this.courses.get(currentIndex);
            currentCourse.merge(newCourse);
        } else {
            this.courses.add(newCourse);
        }
        Collections.sort(courses);
    }

    public void merge(Department other) {
        if (other.equals(this)) {
            for (Course otherCourse : other.getCourses()) {
                addCourse(otherCourse);
            }
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Course> getUndergraduateCourses() {
        Course firstGraduateCourse = new Course(name, GRADUATE_COURSES_BEGIN);
        int firstGraduateCourseIdx = courses.indexOf(firstGraduateCourse);
        return courses.subList(0, firstGraduateCourseIdx - 1);
    }

    public List<Course> getGraduateCourses() {
        Course firstGraduateCourse = new Course(name, GRADUATE_COURSES_BEGIN);
        int firstGraduateCourseIdx = courses.indexOf(firstGraduateCourse);
        return courses.subList(firstGraduateCourseIdx, courses.size() - 1);
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
    public Iterator<Course> iterator() {
        return Collections.unmodifiableList(courses).iterator();
    }
}

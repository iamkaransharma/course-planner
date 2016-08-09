package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Location class contains a tree set of CourseComponents and Instructors. It adds a CourseComponent if it finds
 * a CourseComponent that has the same values as itself. It also returns the instructors and course components
 * at its location.
 */
public class Location implements Comparable<Location> {
    private static final Set<String> recognizedNames = new HashSet<>(
            Arrays.asList("BURNABY", "SURREY", "HRBRCNTR")
    );
    private String name;
    private Set<String> instructors;
    private Set<CourseComponent> courseComponents;

    public Location(String name) {
        this.name = normalizeName(name);
        courseComponents = new TreeSet<>();
        instructors = new TreeSet<>();
    }

    public void addCourseComponent(CourseComponent newComponent) {
        boolean foundComponent = false;
        for (CourseComponent currentComponent : courseComponents) {
            if (currentComponent.equals(newComponent)) {
                currentComponent.merge(newComponent);
                foundComponent = true;
                break;
            }
        }
        if (!foundComponent) {
            this.courseComponents.add(newComponent);
        }
    }

    public void merge(Location other) {
        if (other.equals(this)) {
            this.instructors.addAll(other.getInstructors());
            for (CourseComponent otherComponent : other.getCourseComponents()) {
                addCourseComponent(otherComponent);
            }
        }
    }

    // Convenience method
    public void addInstructors(List<String> newInstructors) {
        this.instructors.addAll(newInstructors);
    }

    private String normalizeName(String name) {
        if (recognizedNames.contains(name)) {
            return name;
        } else {
            return "OTHER";
        }
    }

    public String getName() {
        return name;
    }

    public Set<String> getInstructors() {
        return instructors;
    }

    public Set<CourseComponent> getCourseComponents() {
        return courseComponents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (CourseComponent component : courseComponents) {
            stringBuilder.append("\n\t");
            stringBuilder.append(component);
        }
        return stringBuilder.toString();
    }

    private void appendInstructors(StringBuilder stringBuilder) {
        String commaPrefix = "";
        for (String instructor : instructors) {
            stringBuilder.append(commaPrefix);
            commaPrefix = ", ";
            stringBuilder.append(instructor);
        }
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != this.getClass()) {
            return false;
        }
        Location location = (Location) otherObject;
        return name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Location other) {
        return this.name.compareTo(other.name);
    }
}

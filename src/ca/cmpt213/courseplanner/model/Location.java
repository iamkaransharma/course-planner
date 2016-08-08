package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Created by Karan on 01/08/2016.
 */
public class Location {
    private String name;
    private List<String> instructors;
    private List<CourseComponent> courseComponents;
    private static final Set<String> recognizedNames = new HashSet<>(
            Arrays.asList("BURNABY", "SURREY", "HRBRCNTR")
    );

    public Location(String name) {
        this.name = normalizeName(name);
        courseComponents = new ArrayList<>();
        instructors = new ArrayList<>();
    }

    public void addCourseComponent(CourseComponent newComponent) {
        final int NOT_FOUND = -1;
        int currentIndex = this.courseComponents.indexOf(newComponent);
        if (currentIndex != NOT_FOUND) {
            CourseComponent currentComponent = this.courseComponents.get(currentIndex);
            currentComponent.merge(newComponent);
        } else {
            this.courseComponents.add(newComponent);
        }
    }

    public void merge(Location other) {
        if (other.equals(this)) {
            addInstructors(other.getInstructors());

            for (CourseComponent otherComponent : other.getCourseComponents()) {
                addCourseComponent(otherComponent);
            }
        }
    }

    public void addInstructors(List<String> newInstructors) {
        this.instructors = mergeDistinct(this.instructors, newInstructors);
    }

    private List<String> mergeDistinct(List<String> list1, List<String> list2) {
        assert list1 != null && list2 != null;
        list1.addAll(list2);
        Set<String> distinctSet = new LinkedHashSet<>(list1);
        return new ArrayList<>(distinctSet);
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

    public List<String> getInstructors() {
        return instructors;
    }

    public List<CourseComponent> getCourseComponents() {
        return courseComponents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append( + name + " by ");
//        appendInstructors(stringBuilder);
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

    //    BURNABY("BURNABY"),
//    SURREY("SURREY"),
//    VANCOUVER("HRBRCNTR"),
//    OTHER(null);
//
//    private String locationName;
//
//    Location(String locationName) {
//        this.locationName = locationName;
//    }
//
//    public static Location getEnum(String otherLocationName) {
//        if (otherLocationName == null) {
//            throw new IllegalArgumentException();
//        }
//
//        for (Location location : Location.values()) {
//            if (location.locationName.equals(otherLocationName)) {
//                return location;
//            }
//        }
//        return Location.OTHER;
//    }
}

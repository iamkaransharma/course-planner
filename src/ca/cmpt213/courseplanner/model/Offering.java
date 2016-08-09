package ca.cmpt213.courseplanner.model;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Offering class contains the catalog number, semester and locations of where the offering courses are at.
 * It returns the semester and catalog number and adds locations to itself if it finds a course offering
 * that is located in the same location as itself.
 */
public class Offering implements Comparable<Offering> {
    private String catalogNumber;
    private Set<Location> locations;
    private Semester semester;

    public Offering(String catalogNumber, Semester semester) {
        this.catalogNumber = catalogNumber;
        this.semester = semester;
        this.locations = new TreeSet<>();
    }

    public void addLocation(Location newLocation) {
        boolean foundLocation = false;
        for (Location currentLocation : locations) {
            if (currentLocation.equals(newLocation)) {
                currentLocation.merge(newLocation);
                foundLocation = true;
                break;
            }
        }
        if (!foundLocation) {
            this.locations.add(newLocation);
        }
    }

    public void merge(Offering other) {
        if (other.equals(this)) {
            for (Location otherLocation : other.getLocations()) {
                addLocation(otherLocation);
            }
        }
    }

    public boolean belongsToYear(int year) {
        return this.semester.matchesYear(year);
    }

    public Semester getSemester() {
        return semester;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int lastElementIndex = locations.size() - 1;
        Iterator<Location> iterator = locations.iterator();
        while (iterator.hasNext()) {
            Location location = iterator.next();
            stringBuilder.append(semester.getSemesterCode() + " in " + location.getName() + " by ");
            appendCommaSeparatedWords(stringBuilder, location.getInstructors());
            stringBuilder.append(location.toString());
            if (lastElementIndex > 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append("\t");
            lastElementIndex--;
        }
        return stringBuilder.toString();
    }

    private void appendCommaSeparatedWords(StringBuilder stringBuilder, Set<String> words) {
        String commaPrefix = "";
        for (String word : words) {
            stringBuilder.append(commaPrefix);
            commaPrefix = ", ";
            stringBuilder.append(word);
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
        Offering other = (Offering) otherObject;
        return (this.semester.equals(other.semester) && this.catalogNumber.equals(other.catalogNumber));
    }

    @Override
    public int hashCode() {
        return semester.hashCode() * 43 + catalogNumber.hashCode() * 101;
    }

    @Override
    public int compareTo(Offering other) {
        String semesterCode = this.semester.getSemesterCode();
        String otherSemesterCode = other.semester.getSemesterCode();
        return semesterCode.compareTo(otherSemesterCode);
    }
}

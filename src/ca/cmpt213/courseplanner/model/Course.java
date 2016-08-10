package ca.cmpt213.courseplanner.model;

import java.util.Set;
import java.util.TreeSet;

/**
 * Course class models a course under its respective department and contains a set of offerings for each year
 * and semester. It contains methods that adds offerings, returns a list of offerings, merges offerings,
 * filters offerings by seasons, returns full name of the course, hashes catalog numbers, and compares
 * itself with other courses to see if it shares the same catalog number with them.
 */
public class Course implements Comparable<Course> {
    private String departmentName;
    private String catalogNumber;
    private TreeSet<Offering> offerings;

    public Course(String departmentName, String catalogNumber) {
        this.departmentName = departmentName;
        this.catalogNumber = catalogNumber;
        offerings = new TreeSet<>();
    }

    public void addOffering(Offering newOffering) {
        boolean foundOffering = false;
        for (Offering currentOffering : offerings) {
            if (currentOffering.equals(newOffering)) {
                currentOffering.merge(newOffering);
                foundOffering = true;
                break;
            }
        }
        if (!foundOffering) {
            this.offerings.add(newOffering);
        }
    }

    public void merge(Course other) {
        if (other.equals(this)) {
            for (Offering otherOffering : other.getOfferings()) {
                addOffering(otherOffering);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(catalogNumber);
        for (Offering offering : offerings) {
            stringBuilder.append("\n\t");
            stringBuilder.append(offering.toString());
        }
        return stringBuilder.toString();
    }

    public Offering getOldestOffering() {
        return offerings.first();
    }

    public Offering getNewestOffering() {
        return offerings.last();
    }

    public Set<Location> getLocationsBySemesterCode(Semester searchSemester) {
        for (Offering currentOffering : offerings) {
            if (searchSemester.equals(currentOffering.getSemester())) {
                return currentOffering.getLocations();
            }
        }
        return new TreeSet<>();
    }

    public Set<Offering> getOfferings() {
        return offerings;
    }

    public int countOfferingsBySeason (Season seasonFilter) {
        int offeringCount = 0;
        for (Offering currentOffering : offerings) {
            if (currentOffering.getSemester().getSeason().equals(seasonFilter)) {
                offeringCount += currentOffering.getLocations().size();
            }
        }
        return offeringCount;
    }

    public String getFullName() {
        return departmentName + " " + catalogNumber;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || otherObject.getClass() != this.getClass()) {
            return false;
        }
        Course other = (Course) otherObject;
        return this.catalogNumber.equals(other.catalogNumber);
    }

    @Override
    public int hashCode() {
        return catalogNumber.hashCode() * 17;
    }

    @Override
    public int compareTo(Course other) {
        // treat catalog numbers like "XX1" as "001"
        String thisCatalogNumber = convertWildcardsToZeroes(this.catalogNumber);
        String otherCatalogNumber = convertWildcardsToZeroes(other.catalogNumber);
        return thisCatalogNumber.compareTo(otherCatalogNumber);
    }

    private String convertWildcardsToZeroes(String rawString) {
        final String WILDCARD_CHARACTER = "X";
        return rawString.replaceAll(WILDCARD_CHARACTER, "0");
    }
}

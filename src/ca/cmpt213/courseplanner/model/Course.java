package ca.cmpt213.courseplanner.model;

import java.util.*;

/**
 * Course class models a course under its respective department and contains a set of offerings for each year
 * and semester. It contains methods that adds offerings, returns a list of offerings, merges offerings,
 * filters offerings by seasons, returns full name of the course, hashes catalog numbers, and compares
 * itself with other courses to see if it shares the same catalog number with them.
*/

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Course implements Comparable<Course> {
    private String departmentName;
    private String catalogNumber;
    private Set<Offering> offerings;

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
//        final int NOT_FOUND = -1;
//        int currentIndex = this.offerings.indexOf(newOffering);
//        if (currentIndex != NOT_FOUND) {
//            Offering currentOffering = this.offerings.get(currentIndex);
//            currentOffering.merge(newOffering);
//        } else {
//            this.offerings.add(newOffering);
//        }
//        Collections.sort(offerings);
    }

    public void merge(Course other) {
        if (other.equals(this)) {
            for (Offering otherOffering : other.getOfferings()) {
                addOffering(otherOffering);
            }
        }
    }

//    public void sortBySemester() {
//        Collections.sort(offerings);
//    }

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

    public Set<Offering> getOfferings() {
        return offerings;
    }

    public Set<Offering> getOfferingsBySeason(Season seasonFilter) {
        return filterOfferingsBySeason(seasonFilter);
    }

    private Set<Offering> filterOfferingsBySeason(Season seasonFilter) {
        Set<Offering> filteredOfferings = new TreeSet<>();
        for (Offering currentOffering : offerings) {
            String semesterCode = currentOffering.getSemester().getSemesterCode();
            if (semesterCode.endsWith(seasonFilter.value())) {
                filteredOfferings.add(currentOffering);
            }
        }
        return filteredOfferings;
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

//    @Override
//    public Iterator<Offering> iterator() {
//        return Collections.unmodifiableList(offerings).iterator();
//    }

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

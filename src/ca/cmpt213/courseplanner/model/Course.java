package ca.cmpt213.courseplanner.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Course implements Iterable<Offering>, Comparable<Course> {
    private String departmentName;
    private String catalogNumber;
    private ArrayList<Offering> offerings;

    public Course(String departmentName, String catalogNumber) {
        this.departmentName = departmentName;
        this.catalogNumber = catalogNumber;
        offerings = new ArrayList<>();
    }

    public void addOffering(Offering newOffering) {
        final int NOT_FOUND = -1;
        int currentIndex = this.offerings.indexOf(newOffering);
        if (currentIndex != NOT_FOUND) {
            Offering currentOffering = this.offerings.get(currentIndex);
            currentOffering.merge(newOffering);
        } else {
            this.offerings.add(newOffering);
        }
        Collections.sort(offerings);
    }

    public void merge(Course other) {
        if (other.equals(this)) {
            for (Offering otherOffering : other.getOfferings()) {
                addOffering(otherOffering);
            }
        }
    }

    public void sortBySemester() {
        Collections.sort(offerings);
    }

    @Override
    public String toString() {
//        sortBySemester();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(catalogNumber);
        for (Offering offering : offerings) {
            stringBuilder.append("\n\t");
            stringBuilder.append(offering.toString());
        }
        return stringBuilder.toString();
    }

    public ArrayList<Offering> getOfferings() {
        return offerings;
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
    public Iterator<Offering> iterator() {
        return Collections.unmodifiableList(offerings).iterator();
    }

    @Override
    public int compareTo(Course other) {
        String otherCatalogNumber = other.catalogNumber;
        // treat catalog numbers like "XX1" as "001"
        final String WILDCARD_CHARACTER = "X";
        otherCatalogNumber = otherCatalogNumber.replaceAll(WILDCARD_CHARACTER, "0");
        return this.catalogNumber.compareTo(otherCatalogNumber);
    }
}

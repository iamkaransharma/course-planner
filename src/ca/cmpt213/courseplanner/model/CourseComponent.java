package ca.cmpt213.courseplanner.model;

/**
 * CourseComponent class contains comparable methods for Course class to use. It returns the
 * enrollment total, enrollment capacity, merges enrollment capacity & enrollment total with
 * offerings that share the same catalog number. It also checks to see if there are offerings that
 * share the same catalog number.
 */
public class CourseComponent implements Comparable<CourseComponent> {
    private String componentCode;
    private int enrollmentTotal;
    private int enrollmentCapacity;

    public CourseComponent(String componentCode, int enrollmentTotal, int enrollmentCapacity) {
        this.componentCode = componentCode;
        this.enrollmentCapacity = enrollmentCapacity;
        this.enrollmentTotal = enrollmentTotal;
    }

    public void merge(CourseComponent other) {
        if (other.equals(this)) {
            this.enrollmentCapacity += other.getEnrollmentCapacity();
            this.enrollmentTotal += other.getEnrollmentTotal();
        }
    }

    public String getComponentCode(){ return componentCode; }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public int getEnrollmentCapacity() {
        return enrollmentCapacity;
    }

    @Override
    public String toString() {
        return "\tType=" + componentCode + ", Enrollment=" + this.enrollmentTotal + "/" + this.enrollmentCapacity;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (otherObject.getClass() != this.getClass()) {
            return false;
        }
        CourseComponent other = (CourseComponent) otherObject;
        return (this.componentCode.equals(other.componentCode));
    }

    @Override
    public int hashCode() {
        return componentCode.hashCode() * 43;
    }

    @Override
    public int compareTo(CourseComponent other) {
        return this.componentCode.compareTo(other.componentCode);
    }
}

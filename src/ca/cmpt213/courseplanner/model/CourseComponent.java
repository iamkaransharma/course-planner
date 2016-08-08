package ca.cmpt213.courseplanner.model;

/**
 * Created by Karan on 02/08/2016.
 */
public class CourseComponent {
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
//
//    public void increaseEnrollment(String enrollmentTotal, String enrollmentCapacity) {
//        this.enrollmentCapacity += enrollmentCapacity;
//        this.enrollmentTotal += enrollmentTotal;
//    }

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
}

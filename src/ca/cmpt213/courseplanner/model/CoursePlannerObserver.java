package ca.cmpt213.courseplanner.model;

/**
 * CoursePlannerObserver interface provides a method that must be used and overridden
 * by all observers when the state of the model changes.
 */
public interface CoursePlannerObserver {
    void modelStateChanged();
}

package ca.cmpt213.courseplanner.model;

/**
 * CoursePlannerObserver interface provides a method that must be used and overridden
 * by all observers when the state of the model changes.
 */

/**
 * Created by Karan on 07/08/2016.
 */
public interface CoursePlannerObserver {
    void modelStateChanged();
}

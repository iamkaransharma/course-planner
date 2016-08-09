package ca.cmpt213.courseplanner.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * CoursePlanner models the logic of the Course Planner application and provides the observers, the course,
 * course list & offering that is currently being used, and departmentManager. It also reads in the CSV file
 * and throws an exception if the file is not found. It also provides the implementation code that notifies
 * all observers when the model changes.
 */
public class CoursePlanner {
    private DepartmentManager departmentManager;
    private Set<Course> activeCourseList;
    private Course activeCourse;
    private Offering activeOffering;
    private Location activeLocation;
    private List<CoursePlannerObserver> courseListObservers;
    private List<CoursePlannerObserver> activeCourseObservers;
    private List<CoursePlannerObserver> activeOfferingObserver;
    private List<CoursePlannerObserver> activeLocationObserver;

    public CoursePlanner(String csvFileName) throws FileNotFoundException {
        CSVParser csvParser = new CSVParser(csvFileName);
        departmentManager = csvParser.getDepartmentManager();
        courseListObservers = new ArrayList<>();
        activeCourseObservers = new ArrayList<>();
        activeOfferingObserver = new ArrayList<>();
        activeLocationObserver = new ArrayList<>();
    }

    public void selectDepartment(Department selectedDepartment, CourseListFilter filter) {
        assert selectedDepartment != null;
        activeCourseList = selectedDepartment.getCourses(filter);
        activeCourse = null;
        notifyCourseListObservers();
    }

    public void selectCourse(Course selectedCourse) {
        activeCourse = selectedCourse;
        notifyActiveCourseObservers();
    }

    public void selectOffering(Offering selectedOffering) {
        activeOffering = selectedOffering;
        notifyActiveOfferingObservers();
    }

    public void selectLocation(Location selectedLocation){
        activeLocation = selectedLocation;
        notifyActiveLocationObservers();
    }

    public void dumpModel() {
        System.out.println(departmentManager.toString());
    }

    /*
     * Getter methods
     * --------------
     * */
    public DepartmentManager getDepartmentManager() {
        return departmentManager;
    }

    public Set<Course> getActiveCourseList() {
        return activeCourseList;
    }

    public Course getActiveCourse() {
        return activeCourse;
    }

    public Offering getActiveOffering() {
        return activeOffering;
    }

    public Location getActiveLocation() {
        return activeLocation;
    }

    /*
     * Methods to support being observable
     * -----------------------------------
     * */
    public void addCourseListObserver(CoursePlannerObserver observer) {
        courseListObservers.add(observer);
    }

    public void addActiveCourseObserver(CoursePlannerObserver observer) {
        activeCourseObservers.add(observer);
    }

    public void addActiveOfferingObserver(CoursePlannerObserver observer) {
        activeOfferingObserver.add(observer);
    }

    public void addActiveLocationObserver(CoursePlannerObserver observer){
        activeLocationObserver.add(observer);
    }

    private void notifyCourseListObservers() {
        for (CoursePlannerObserver observer : courseListObservers) {
            observer.modelStateChanged();
        }
    }

    private void notifyActiveCourseObservers() {
        for (CoursePlannerObserver observer : activeCourseObservers) {
            observer.modelStateChanged();
        }
    }

    private void notifyActiveOfferingObservers() {
        for (CoursePlannerObserver observer : activeOfferingObserver) {
            observer.modelStateChanged();
        }
    }

    public void notifyActiveLocationObservers() {
        for (CoursePlannerObserver observer : activeLocationObserver) {
            observer.modelStateChanged();
        }
    }
}

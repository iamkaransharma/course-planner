package ca.cmpt213.courseplanner.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CoursePlanner{
    private DepartmentManager departmentManager;
    private Set<Course> activeCourseList;
//    private Department activeDepartment;
    private Course activeCourse;
    private Offering activeOffering;
//    private List<CoursePlannerObserver> departmentListObservers;
    private List<CoursePlannerObserver> courseListObservers;
    private List<CoursePlannerObserver> activeCourseObservers;
    private List<CoursePlannerObserver> activeOfferingObserver;

    public CoursePlanner(String csvFileName) throws FileNotFoundException {
        CSVParser csvParser = new CSVParser(csvFileName);
        departmentManager = csvParser.getDepartmentManager();
//        departmentListObservers = new ArrayList<>();
        courseListObservers = new ArrayList<>();
        activeCourseObservers = new ArrayList<>();
        activeOfferingObserver = new ArrayList<>();
    }

    public void selectDepartment(Department selectedDepartment, CourseListFilter filter) {
        switch (filter) {
            case UNDERGRADUATE_COURSES:
                activeCourseList = selectedDepartment.getUndergraduateCourses();
                break;
            case GRADUATE_COURSES:
                activeCourseList = selectedDepartment.getGraduateCourses();
                break;
            case ALL_COURSES:
                activeCourseList = selectedDepartment.getCourses();
                break;
            default:
                throw new RuntimeException("Unexpected CourseListFilter received");
        }
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

    /*
     * Methods to support being observable
     * -----------------------------------
     * */
//    public void addDepartmentListObserver(CoursePlannerObserver observer) {
//        departmentListObservers.add(observer);
//    }

    public void addCourseListObserver(CoursePlannerObserver observer) {
        courseListObservers.add(observer);
    }

    public void addActiveCourseObserver(CoursePlannerObserver observer) {
        activeCourseObservers.add(observer);
    }

    public void addActiveOfferingObserver(CoursePlannerObserver observer) {
        activeOfferingObserver.add(observer);
    }

//    private void notifyDepartmentListObservers() {
//        for (CoursePlannerObserver observer : departmentListObservers) {
//            observer.modelStateChanged();
//        }
//    }

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
}

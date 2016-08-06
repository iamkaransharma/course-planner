package ca.cmpt213.courseplanner;


import ca.cmpt213.courseplanner.model.CSVCourseReader;
import ca.cmpt213.courseplanner.model.*;
import ca.cmpt213.courseplanner.ui.CoursePlannerGUI;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Main {

    public static void main(String[] args) {

        CoursePlanner coursePlanner = new CoursePlanner("data/course_data_2016.csv");
        coursePlanner.createDepartments();
        coursePlanner.insertCoursesIntoDepartments();
        coursePlanner.insertCourseOfferingsIntoCourses();
        CoursePlannerGUI coursePlannerGUI = new CoursePlannerGUI(coursePlanner);
        coursePlannerGUI.startProgram();
    }
}

package ca.cmpt213.courseplanner;

import ca.cmpt213.courseplanner.model.CoursePlanner;
import ca.cmpt213.courseplanner.ui.CoursePlannerGUI;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Main {

    public static void main(String[] args) {

        try {
//            CoursePlanner coursePlanner = new CoursePlanner("D:\\Karan\\Documents\\GitHub Repos\\asn4-course-planner\\data\\course_data_2016.csv");
            CoursePlanner coursePlanner = new CoursePlanner("data/course_data_2016.csv");
            coursePlanner.dumpModel();
            CoursePlannerGUI coursePlannerGUI = new CoursePlannerGUI(coursePlanner);
            coursePlannerGUI.startProgram();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

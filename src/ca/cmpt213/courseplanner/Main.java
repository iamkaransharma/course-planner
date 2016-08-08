package ca.cmpt213.courseplanner;

import ca.cmpt213.courseplanner.model.CoursePlanner;
import ca.cmpt213.courseplanner.ui.CoursePlannerGUI;

import java.io.FileNotFoundException;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Main {

    public static void main(String[] args) {

//        final String CSV_FILE_PATH = "data/course_data_2016.csv";
        final String CSV_FILE_PATH = "D:\\Karan\\Documents\\GitHub Repos\\asn4-course-planner\\data\\course_data_2016.csv";

        try {
            CoursePlanner coursePlanner = new CoursePlanner(CSV_FILE_PATH);
//            coursePlanner.dumpModel();
            CoursePlannerGUI coursePlannerGUI = new CoursePlannerGUI(coursePlanner);
            coursePlannerGUI.start();
        } catch (FileNotFoundException e) {
            CoursePlannerGUI.displayDialogBox("Data file (" + CSV_FILE_PATH + ") not found.");
        }
    }
}

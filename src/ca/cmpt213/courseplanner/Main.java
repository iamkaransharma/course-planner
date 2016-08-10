package ca.cmpt213.courseplanner;

import ca.cmpt213.courseplanner.model.*;
import ca.cmpt213.courseplanner.ui.CoursePlannerGUI;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Main {

    public static void main(String[] args) {

        final String CSV_FILE_PATH = "data/course_data_2016.csv";
//        final String CSV_FILE_PATH = "D:\\Karan\\Documents\\GitHub Repos\\asn4-course-planner\\data\\course_data_2016.csv";

        try {
            CoursePlanner coursePlanner = new CoursePlanner(CSV_FILE_PATH);
//            coursePlanner.dumpModel();
            CoursePlannerGUI coursePlannerGUI = new CoursePlannerGUI(coursePlanner);
            coursePlannerGUI.start();
            //TODO: REmove
//            TreeSet<Department> departments = (TreeSet<Department>) coursePlanner.getDepartmentManager().getDepartments();
//            TreeSet<Course> courses = null;
//            for (Department department :
//                    departments) {
//                if (department.getName().equals("CMPT")) {
//                    courses = (TreeSet<Course>)department.getCourses(CourseListFilter.ALL_COURSES);
//                }
//            }
//            Course firstCourse = null;
//            for (Course course :
//                    courses) {
//                if (course.getFullName().equals("CMPT 225")) {
//                    firstCourse = course;
//                }
//            }
//
//            Set<Offering> summerOfferings = firstCourse.getOfferingsBySeason(Season.SUMMER);
//            Set<Offering> fallOfferings = firstCourse.getOfferingsBySeason(Season.FALL);
//            Set<Offering> springOfferings = firstCourse.getOfferingsBySeason(Season.SPRING);
////            for (Offering offering: filteredOfferings) {
////                System.out.println(offering.getSemester().getSeason());
////            }
//            System.out.println(summerOfferings.size() + fallOfferings.size() + springOfferings.size());
//            System.out.println(firstCourse.getOfferings().size());
//            System.out.println("Finished printing");


        } catch (FileNotFoundException e) {
            CoursePlannerGUI.displayDialogBox("Data file (" + CSV_FILE_PATH + ") not found.");
        }
    }
}

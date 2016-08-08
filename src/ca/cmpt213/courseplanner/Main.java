package ca.cmpt213.courseplanner;

//import ca.cmpt213.courseplanner.model.CoursePlanner;

//<<<<<<< Updated upstream
//import ca.cmpt213.courseplanner.model.CSVCourseReader;
//import ca.cmpt213.courseplanner.model.*;
//import ca.cmpt213.courseplanner.ui.CoursePlannerGUI;
//=======

import ca.cmpt213.courseplanner.model.CoursePlanner;
import ca.cmpt213.courseplanner.model.DepartmentManager;
//>>>>>>> Stashed changes

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Main {

    public static void main(String[] args) {

        try {
            CoursePlanner coursePlanner = new CoursePlanner("D:\\Karan\\Documents\\GitHub Repos\\asn4-course-planner\\data\\course_data_2016.csv");
//            DepartmentManager departmentManager = coursePlanner.getDepartmentManager();
            coursePlanner.dumpModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Location loc1 = new Location("SURREY");
//        CourseComponent comp1 = new CourseComponent("101", 1, 2);
//        List<String> ins1 = new ArrayList<>();
//        ins1.add("Brian Fraser");
//        ins1.add("Leafy");
//        loc1.addInstructors(ins1);
//        loc1.addCourseComponent(comp1);
//
//
//        Location loc2 = new Location("OFF");
//        CourseComponent comp2 = new CourseComponent("101", 3, 7);
//        List<String> ins2 = new ArrayList<>();
//        ins2.add("Karan Sharma");
//        ins2.add("Aman");
//        loc2.addInstructors(ins2);
//        loc2.addCourseComponent(comp2);
//
//        Location loc3 = new Location("SURREY");
//        CourseComponent comp3 = new CourseComponent("101", 3, 7);
//        List<String> ins3 = new ArrayList<>();
//        ins3.add("Trevor");
//        loc3.addInstructors(ins3);
//        loc3.addCourseComponent(comp3);
//
//        Offering off1 = new Offering("105W", new Semester("1047"));
//        off1.addLocation(loc1);
//        off1.addLocation(loc2);
//        off1.addLocation(loc3);
//
//        System.out.println(off1);

//        System.out.println(loc1);
//        System.out.println(loc2);
//        System.out.println(loc3);
//        System.out.println("++++++++++++++");
//        loc1.merge(loc2);
//        loc1.merge(loc3);
//        System.out.println("++++++++++++++");
//        System.out.println(loc1);
//        System.out.println(loc2);
//        System.out.println(loc3);
    }
}

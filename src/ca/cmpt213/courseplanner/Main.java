package ca.cmpt213.courseplanner;


import ca.cmpt213.courseplanner.model.CSVCourseReader;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Main {

    public static void main(String[] args) {

        CSVCourseReader csvfile = new CSVCourseReader("data/course_data_2016.csv");
        csvfile.scanCSVFile();

    }
}

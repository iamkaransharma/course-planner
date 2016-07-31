package ca.cmpt213.courseplanner;


import ca.cmpt213.courseplanner.model.CSVReader;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class main {

    public static void main(String[] args) {

        CSVReader csvfile = new CSVReader("data/course_data_2016.csv");
        csvfile.scanCSVFile();
    }
}

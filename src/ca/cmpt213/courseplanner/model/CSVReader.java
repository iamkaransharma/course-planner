package ca.cmpt213.courseplanner.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CSVReader {

    private String csvFileName;

    public CSVReader(String csvFileName){
        this.csvFileName = csvFileName;
    }

    // Return array of course offerings that would be sorted by CoursePlanner
    public void scanCSVFile(){

        ArrayList <CourseOffering> courseOfferingsInsideCSV = new ArrayList<CourseOffering>();
        Path pathToCSVFile = Paths.get(csvFileName);

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(csvFileName));

            br.readLine(); // Reading and skipping the column headers of CSV file

            String row = null;

            while ((row = br.readLine()) != null){
                String[] offerInfo = row.split(",");

                courseOfferingsInsideCSV.add(new CourseOffering(offerInfo[0],offerInfo[1],offerInfo[2],
                        offerInfo[3],offerInfo[4],offerInfo[5],offerInfo[6],offerInfo[7]));
            }

            br.close();

        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}

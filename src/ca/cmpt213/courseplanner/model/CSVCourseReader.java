package ca.cmpt213.courseplanner.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CSVCourseReader {

    private String csvFileName;

    public CSVCourseReader(String csvFileName){
        this.csvFileName = csvFileName;
    }

    // Return array of course offerings that would be sorted by CoursePlanner
    public ArrayList<CourseOffering> scanCSVFile(){

        ArrayList <CourseOffering> courseOfferingsInsideCSV = new ArrayList<CourseOffering>();
        Path pathToCSVFile = Paths.get(csvFileName);

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(csvFileName));

            br.readLine(); // Reading and skipping the column headers of CSV file

            String row = null;

            while ((row = br.readLine()) != null){
                //System.out.println(row);

                String instructors = "";
                String[] offerInfo;
                String componentCode = "";

                // Handles course offerings with more than 1 instructor
                if (row.contains("\"")) {

                    String[] infoForInstructors;

                    // Contains information on columns that are not Instructors
                    offerInfo = row.split(",");

                    // This String array deals with column that have more than 1 instructor
                    infoForInstructors = row.split(", ");
                    String[] offerInfo1 = infoForInstructors[0].split(",");

                    // First instructor
                    String firstInstructor = offerInfo1[offerInfo1.length-1].substring(1,offerInfo1[offerInfo1.length-1].length());
                    instructors = instructors + firstInstructor + ", ";

                    for (int i = 1; i < infoForInstructors.length; i++){
                        // Adding instructors between the first and last instructor
                        if (!infoForInstructors[i].contains("\"")){
                            instructors = instructors + infoForInstructors[i] + ", ";
                        }
                        // Adding the last instructor and getting the component code
                        else {
                            String[] lastPartOfRow = infoForInstructors[i].split("\",");
                            instructors = instructors + lastPartOfRow[0];
                            componentCode = componentCode + lastPartOfRow[lastPartOfRow.length-1];
                        }
                    }

                    //System.out.println(instructors);

                    courseOfferingsInsideCSV.add(new CourseOffering(offerInfo[0],offerInfo[1],offerInfo[2],
                            offerInfo[3],offerInfo[4],offerInfo[5],instructors,componentCode));

                }

                // Handles course offerings with only 1 instructor
                else {
                    offerInfo = row.split(",");
                    courseOfferingsInsideCSV.add(new CourseOffering(offerInfo[0],offerInfo[1],offerInfo[2],
                            offerInfo[3],offerInfo[4],offerInfo[5],offerInfo[6],offerInfo[7]));
                }

            }

            br.close();

//            for (CourseOffering courseO : courseOfferingsInsideCSV){
//                System.out.println(courseO.toString());
//            }

        }

        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return courseOfferingsInsideCSV;
    }

}

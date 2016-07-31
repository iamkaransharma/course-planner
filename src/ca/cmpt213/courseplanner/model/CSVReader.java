package ca.cmpt213.courseplanner.model;

import java.io.*;
import java.util.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class CSVReader {

    private String csvFileName;

    public CSVReader(String csvFileName){
        this.csvFileName = csvFileName;
    }

    void scanCSVFile(){
        try {
            Scanner csvScanner = new Scanner(new File(csvFileName));
            while(csvScanner.hasNext()){

            }
            csvScanner.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

}

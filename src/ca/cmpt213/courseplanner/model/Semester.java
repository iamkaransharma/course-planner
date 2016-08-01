package ca.cmpt213.courseplanner.model;

import javax.swing.*;

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Semester {

    private String semester;

    private Season season;
    private String year;

    private String twenty_first_century = "20";

    public Semester(String semester){
        this.semester = semester;
    }

    public Season getSeason(){

        if (semester.charAt(semester.length()) == 1) {
            return Season.Spring;
        } else if (semester.charAt(semester.length()) == 4){
            return Season.Summer;
        } else {
            return Season.Fall;
        }
    }

    public String getYear(){
        year = twenty_first_century + semester.substring(1,2);
        return year;
    }


}

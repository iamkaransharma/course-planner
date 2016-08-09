package ca.cmpt213.courseplanner.model;

/**
 * Semester class models a semester and contains the semester code, season and year. It returns the season it takes
 * place in, the year it takes place in, and its semester code.
 */

/**
 * Created by Thomas_Ngo on 2016-07-30.
 */
public class Semester {
    private static final int CENTURY_INDEX = 0;
    private static final int YEAR_SECOND_LAST_DIGIT = 1;
    private static final int YEAR_LAST_DIGIT = 2;
    private static final int SEASON_ID_INDEX = 3;

    private String semesterCode;
    private Season season;
    private String year;

    public Semester(String semesterCode) {
        this.semesterCode = semesterCode;
        this.season = getSeasonFromSemesterCode(semesterCode);
        this.year = getYearFromSemesterCode(semesterCode);
    }

    private Season getSeasonFromSemesterCode(String semesterCode) {
        int seasonId = parseIntFromStringElement(semesterCode, SEASON_ID_INDEX);
        return Season.valueOf(seasonId);
    }

    private String getYearFromSemesterCode(String semesterCode) {
        final int CENTURY_OFFSET = 19;

        int centuryId = parseIntFromStringElement(semesterCode, CENTURY_INDEX);
        String firstTwoDigits = Integer.toString(centuryId + CENTURY_OFFSET);

        String lastTwoDigits = semesterCode.substring(YEAR_SECOND_LAST_DIGIT, YEAR_LAST_DIGIT + 1);
        return firstTwoDigits + lastTwoDigits;
    }

    private int parseIntFromStringElement(String string, int index) {
        return Character.getNumericValue(string.charAt(index));
    }

    public Season getSeason() {
        return season;
    }

    public String getYear() {
        return year;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject == null) {
            return false;
        }
        if (otherObject.getClass() != this.getClass()) {
            return false;
        }
        Semester other = (Semester) otherObject;
        return (this.semesterCode.equals(other.semesterCode));
    }
}

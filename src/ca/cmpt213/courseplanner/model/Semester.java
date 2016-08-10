package ca.cmpt213.courseplanner.model;

/**
 * Semester class models a semester and contains the semester code, season and year. It returns the season it takes
 * place in, the year it takes place in, and its semester code.
 */
public class Semester {
    private static final int CENTURY_INDEX = 0;
    private static final int YEAR_SECOND_LAST_DIGIT = 1;
    private static final int YEAR_LAST_DIGIT = 2;
    private static final int SEASON_ID_INDEX = 3;
    private static final int CENTURY_OFFSET = 19;

    private String semesterCode;
    private Season season;
    private String year;

    public Semester(String semesterCode) {
        this.semesterCode = semesterCode;
        this.season = getSeasonFromSemesterCode(semesterCode);
        this.year = getYearFromSemesterCode(semesterCode);
    }

    public Semester(Season season, String year) {
        this.season = season;
        this.year = year;
        this.semesterCode = calculateSemesterCode(season, year);
    }

    private String calculateSemesterCode(Season season, String year) {
        String centuryPrefix = getCenturyPrefixFromYear(year);
        String decadeAndYear = year.substring(2, 4);
        String semesterCode = centuryPrefix + decadeAndYear + season.value();
        return semesterCode;
    }

    private Season getSeasonFromSemesterCode(String semesterCode) {
        int seasonId = parseIntFromStringElement(semesterCode, SEASON_ID_INDEX);
        return Season.valueOf(seasonId);
    }

    private String getCenturyPrefixFromYear(String year) {
        final int FIRST_DIGIT = 0;
        final int SECOND_DIGIT = 1;
        String centuryString = year.substring(FIRST_DIGIT, SECOND_DIGIT + 1);
        int century = Integer.parseInt(centuryString);
        return String.valueOf(century - CENTURY_OFFSET);
    }

    private String getYearFromSemesterCode(String semesterCode) {
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

    public int getYear() {
        return Integer.parseInt(year);
    }

    public String getCenturyPrefix() {
        return semesterCode.substring(0, CENTURY_INDEX + 1);
    }

    public String getYearInfix() {
        return semesterCode.substring(YEAR_SECOND_LAST_DIGIT, YEAR_LAST_DIGIT + 1);
    }

    public String getSeasonSuffix() {
        return semesterCode.substring(SEASON_ID_INDEX, SEASON_ID_INDEX + 1);
    }

    public boolean matchesYear(int year) {
        return this.year.equals(String.valueOf(year));
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

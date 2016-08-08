package ca.cmpt213.courseplanner.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Karan on 01/08/2016.
 */
public class CSVParser {

    private static final int SEMESTER_INDEX = 0;
    private static final int SUBJECT_INDEX = 1;
    private static final int CATALOG_NUMBER_INDEX = 2;
    private static final int LOCATION_INDEX = 3;
    private static final int ENROLLMENT_CAPACITY_INDEX = 4;
    private static final int ENROLLMENT_TOTAL_INDEX = 5;
    private static final int INSTRUCTORS_INDEX = 6;
    private static final int COMPONENT_CODE_INDEX = 7;

    private CSVReader csvReader;

    public CSVParser(String csvFileName) throws FileNotFoundException {
        csvReader = new CSVReader(csvFileName);
    }

    public DepartmentManager getDepartmentManager() {

        DepartmentManager departments = new DepartmentManager();

        try {
            csvReader.readNextLineTokens(); // skip header line containing column names

            String[] currentLineTokens = null;
            while ((currentLineTokens = csvReader.readNextLineTokens()) != null) {

                List<String> instructors = getInstructors(currentLineTokens[INSTRUCTORS_INDEX]);
                int enrollmentTotal = Integer.parseInt(currentLineTokens[ENROLLMENT_TOTAL_INDEX]);
                int enrollmentCapacity = Integer.parseInt(currentLineTokens[ENROLLMENT_CAPACITY_INDEX]);

                departments.addOffering(
                        currentLineTokens[SUBJECT_INDEX],
                        currentLineTokens[SEMESTER_INDEX],
                        currentLineTokens[CATALOG_NUMBER_INDEX],
                        currentLineTokens[LOCATION_INDEX],
                        enrollmentTotal,
                        enrollmentCapacity,
                        instructors,
                        currentLineTokens[COMPONENT_CODE_INDEX]
                );
            }

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                csvReader.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return departments;
    }

    private List<String> getInstructors(String rawString) {
        final String NO_INSTRUCTOR_STRING = "(null)";
        final String INSTRUCTOR_STRING_DELIMITER = ", ";

        String[] instructors = null;
        if (rawString.equals(NO_INSTRUCTOR_STRING)) {
            instructors = new String[]{};
        } else if (rawString.charAt(0) == '"') {
            String unquotedNames = rawString.substring(1, rawString.length() - 1);
            instructors = unquotedNames.split(INSTRUCTOR_STRING_DELIMITER);
        } else {
            instructors = new String[]{rawString};
        }
        return Arrays.asList(instructors);
    }
}
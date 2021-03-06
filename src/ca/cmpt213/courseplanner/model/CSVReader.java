package ca.cmpt213.courseplanner.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * CSVReader takes in the CSV file and reads each row inside the CSV File. It throws an exception
 * if it cannot find the file. It also can identify commas that are not inside double quotations.
 */
public class CSVReader extends BufferedReader {
    // Regex that uses positive lookahead to identify commas that are not contained within double-quotes
    private static final String DELIMITER_REGEX = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public CSVReader(String csvFileName) throws FileNotFoundException {
        super(new FileReader(csvFileName));
    }

    public String[] readNextLineTokens() throws IOException {
        String[] tokens = null;
        String currentLine = this.readLine();
        if (currentLine != null) {
            tokens = currentLine.split(DELIMITER_REGEX, -1);
        }
        return tokens;
    }
}

package eu.waldonia.labs.teleprinter;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Produces scores for a particular date by scanning files of historical results
 * @author sih
 */
public class Teleprinter {

    public Teleprinter() {
    }

    /**
     * Print all results that are in the data file and match the input params
     * @param startDate The date to start reading from
     * @param endDate The date to end reading from
     * @param dataFile The file with the list of results
     * @return
     */
    public Stream<String> print(LocalDate startDate, LocalDate endDate, Path dataFile) {
        Stream<String> results = Stream.empty();
        try {

            List<String> data = Files.readAllLines(dataFile);
            results =  data
                    .stream()
                    .skip(1)                                                // skip header
                    .filter(line -> !line.startsWith(","))                  // skip empty lines
                    .filter(line -> onOrAfter(line, startDate))
                    .filter(line -> onOrBefore(line, endDate));
        } catch (IOException ioe) {
            // TODO something
        }
        return results;
    }

    private boolean onOrAfter(String line, LocalDate date) {
        String matchday = line.split(",")[1];
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");
        formatter.withPivotYear(1993);
        LocalDate lineDate = LocalDate.parse(matchday, formatter);
        return lineDate.isAfter(date) || lineDate.isEqual(date) ;
    }

    private boolean onOrBefore(String line, LocalDate date) {
        String matchday = line.split(",")[1];
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yy");
        formatter.withPivotYear(1993);
        LocalDate lineDate = LocalDate.parse(matchday, formatter);

        return  lineDate.isBefore(date) || lineDate.isEqual(date);
    }

}

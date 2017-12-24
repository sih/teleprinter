package eu.waldonia.labs.teleprinter;

import org.joda.time.LocalDate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author sih
 */
public class Simulator {
    private Teleprinter teleprinter;

    private LocalDate startDate;
    private LocalDate endDate;
    private String dataDir;

    private static final String DEFAULT_DATA_DIR = "./data";

    private static final String MIN_DATE = "1992-01-01";
    private static final String MAX_DATE = "2018-01-01";

    /**
     *
     */
    public Simulator() {
        this(DEFAULT_DATA_DIR,MIN_DATE,MAX_DATE);
    }

    /**
     *
     * @param dataDir
     * @param start
     * @param end
     */
    public Simulator(String dataDir, String start, String end) {
        this.dataDir = dataDir;
        startDate = LocalDate.parse(start);
        endDate = LocalDate.parse(end);
        this.teleprinter = new Teleprinter();
    }



    /**
     * Iterate through each of the days outputting the results
     * @return
     */
    public Stream<String> flow() {
        Stream<String> results = Stream.empty();

        int startSeason = getSeason(startDate);
        int endSeason = getSeason(endDate);

        try {
            List<Path> dataFiles = new ArrayList<>();
            // get all the data files
            for (int season = startSeason; season <= endSeason; season++) {
                final String thisSeaon = String.valueOf(season);
                dataFiles.addAll(
                        Files.list(Paths.get(dataDir))
                        .filter(p -> p.toString().contains(thisSeaon))
                        .collect(Collectors.toList()));


                List<String> res = new ArrayList<>();
                for (Path p: dataFiles) {
                    res.addAll(teleprinter.print(startDate,endDate,p).collect(Collectors.toList()));
                }
                results = res.stream();
            }


        } catch(IOException ioe) {
            // TODO something
        }


        return results;
    }

    private int getSeason(LocalDate date) {
        int year = 0;
        if (date != null) {
            year = (date.getMonthOfYear() > 7) ? date.getYear() : date.getYear()-1;
        }
        return year;
    }

    /**
     * Run through the premier league years (and the Championship too)
     * @param args
     */
    public static void main(String[] args) {
        // take all the defaults
        Simulator sim = new Simulator();
        sim.flow().forEach(result -> System.out.println(result));
    }
}

package eu.waldonia.labs.teleprinter;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.Assert.fail;

/**
 * @author sih
 */
public class TeleprinterTest {

    private Teleprinter teleprinter;
    private static final Logger LOGGER = LoggerFactory.getLogger(TeleprinterTest.class);

    private static final String TEST_DATA_FILE = "./src/test/resources/pl-1994.csv";
    private static final String TEST_MINI_DATA_FILE = "./src/test/resources/pl-mini-1994.csv";

    private Path dataPath;

    private Path miniDataPath;

    @Before
    public void setUp() throws Exception {
        teleprinter = new Teleprinter();
        dataPath = Paths.get(TEST_DATA_FILE);
        miniDataPath = Paths.get(TEST_MINI_DATA_FILE);
    }

    @Test
    public void shouldReturnResultsForValidDay() {
        try {
            LocalDate startDate = new LocalDate(1994,8,20);
            LocalDate endDate = new LocalDate(1994,8,20);
            Stream<String> results = teleprinter.print(startDate,endDate,dataPath);
            results.forEach(s -> {
                System.out.println(s);
            });
        } catch(Exception e) {
            e.printStackTrace();
            fail("Shouldn't throw "+e.getMessage());
        }

    }

    @Test
    public void shouldReturnResultsForValidRange() {
        try {
            LocalDate startDate = new LocalDate(1995,1,01);
            LocalDate endDate = new LocalDate(1995,4,04);
            Stream<String> results = teleprinter.print(startDate,endDate,miniDataPath);
            results.forEach(s -> {
                System.out.println(s);
            });
        } catch(Exception e) {
            e.printStackTrace();
            fail("Shouldn't throw "+e.getMessage());
        }

    }

}
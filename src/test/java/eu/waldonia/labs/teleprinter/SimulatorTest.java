package eu.waldonia.labs.teleprinter;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sih
 */
public class SimulatorTest {

    private Simulator simulator;

    @Before
    public void setUp() {
        simulator = new Simulator("./data", "1995-01-01", "1998-01-04");
    }

    @Test
    public void flowShouldOutputResults() {
        List<String> results = simulator.flow().collect(Collectors.toList());
        for (String s: results) {
            System.out.println(s);
        }
    }

}
package eu.waldonia.labs.teleprinter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author sih
 */
public class SimFilePublisher {
    private static final String OUTPUT_FILE_PATH = "./output/results.csv";

    private Simulator simulator;

    public SimFilePublisher() {
        simulator = new Simulator();
    }

    public void publish() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get( OUTPUT_FILE_PATH), StandardCharsets.UTF_8)) {
            simulator
                    .flow()
                    .forEach(s -> {
                        try {
                            writer.write(s);
                        } catch (IOException ioe) {
                            // TODO somethnig
                        }
                    });


        } catch (IOException ioe) {
            // TODO something
        }
    }

    public static void main(String[] args) {
        SimFilePublisher simFilePublisher = new SimFilePublisher();
        simFilePublisher.publish();
    }

}

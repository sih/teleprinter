package eu.waldonia.labs.teleprinter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author sih
 */
public class SimSocketPublisher {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimSocketPublisher.class);
    private int port = 33333;

    private Simulator simulator;

    public SimSocketPublisher() {
        simulator = new Simulator();
    }

    /**
     * Publish the predictions on to the port initially set
     */
    public void publish() {
        try (ServerSocket server = new ServerSocket(port);
             Socket clientSocket = server.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true)) {

            simulator
                    .flow()
                    .forEach(result -> {
                        out.write(result);
                    });

        } catch(IOException ioe) {
            LOGGER.error("Couldn't open socket", ioe);
        }
    }

    public static void main(String[] args) {
        SimSocketPublisher simSocketPublisher = new SimSocketPublisher();
        simSocketPublisher.publish();
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Simulator getSimulator() {
        return simulator;
    }

    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
    }
}

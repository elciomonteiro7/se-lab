package hu.bme.mit.spaceship;

import java.util.Random;

/**
 * Class storing and managing the torpedoes of a ship.
 */
public class TorpedoStore {

    // Rate of failing to fire torpedoes [0.0, 1.0]
    private final Random generator = new Random();
    private double FAILURE_RATE = 0.0; // NOSONAR

    private int torpedoCount = 0;

    public TorpedoStore(int numberOfTorpedos) {
        // Initialize the available torpedoes.
        this.torpedoCount = numberOfTorpedos;

        // Update failure rate if it was specified in an environment variable.
        String failureEnv = System.getenv("IVT_RATE");

        if (failureEnv != null) {
            try {
                FAILURE_RATE = Double.parseDouble(failureEnv);
            } catch (NumberFormatException nfe) {
                FAILURE_RATE = 0.0;
            }
        }
    }

    public boolean fire(int numberOfTorpedos) {
        if (numberOfTorpedos < 1
                || numberOfTorpedos > this.torpedoCount) {
            throw new IllegalArgumentException("numberOfTorpedos");
        }

        // Simulate random overheating of the launcher bay.
        double r = generator.nextDouble();

        if (r >= FAILURE_RATE) {
            // Successful firing.
            this.torpedoCount -= numberOfTorpedos;
            return true;
        }

        // Simulated firing failure: inventory remains unchanged.
        return false;
    }

    public boolean isEmpty() {
        return this.torpedoCount <= 0;
    }

    public int getTorpedoCount() {
        return this.torpedoCount;
    }
}
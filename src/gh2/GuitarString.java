package gh2;

import deque.ArrayDeque;
import deque.Deque;
import deque.LinkedListDeque;

import static org.eclipse.jetty.util.IO.bufferSize;
// TODO: maybe more imports

//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new ArrayDeque(); //array
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this division operation into an int. For
        //       better accuracy, use the Math.round() function before casting.
        //       Your should initially fill your buffer array with zeros.
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int capacity = buffer.size();
        for (int i = 0; i < capacity; i++) {
            double r = Math.random() - 0.5;
            buffer.removeFirst();
            buffer.addLast(r);
        }
    }

    public void tic() {
        if (buffer.isEmpty()) {
            return;
        }

        double frontSample = buffer.removeFirst();
        double nextSample = buffer.get(0);
        double newSample = (DECAY * 0.5 * (frontSample + nextSample));
        buffer.addLast(newSample);
    }


    /* Return the double at the front of the buffer. */
    public double sample() {

        return buffer.get(0);
    }
}

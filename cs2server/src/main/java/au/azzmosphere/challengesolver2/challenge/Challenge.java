package au.azzmosphere.challengesolver2.challenge;
import java.util.HashMap;

/**
 * Each challenge must comply to this interface which controls
 * the way the challenge will communicate back to the interface.
 *
 * The interface is a direct copy from pgprog with just the package
 * changed, the original code was first written  14/5/17
 *
 * Created by aaron.spiteri on 9/7/17.
 */
public interface Challenge {
    /**
     * hashmap that contains the inputs sent in the request
     *
     * @param inputMap
     */
    void putInput(HashMap inputMap);

    /**
     * triggers the challenge to do any heavy lifting.
     * @throws Exception
     */
    void process() throws Exception;

    /**
     * The result of processing.
     *
     * @return
     */
    HashMap returnValues();

    /**
     * perform any clean up work that ensures each run will not be affected
     * by the last one.
     */
    void reset();
}

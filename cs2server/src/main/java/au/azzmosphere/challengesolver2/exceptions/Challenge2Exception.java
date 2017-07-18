package au.azzmosphere.challengesolver2.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aaron.spiteri on 10/7/17.
 */
public class Challenge2Exception extends Exception {

    private Logger logger = LoggerFactory.getLogger(Challenge2Exception.class);
    public Challenge2Exception(String msg) {
        super(msg);
        logger.error(msg);
    }
}

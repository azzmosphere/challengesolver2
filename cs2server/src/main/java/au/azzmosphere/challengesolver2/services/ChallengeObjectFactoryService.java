package au.azzmosphere.challengesolver2.services;

import au.azzmosphere.challengesolver2.challenge.Challenge;
import au.azzmosphere.challengesolver2.exceptions.C2InstantiationException;
import au.azzmosphere.challengesolver2.exceptions.Challenge2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

/**
 *
 * Creates a new instance of a challenge which can be process,
 * it will first load the object from cache and only initalize the
 * the new object if it has not all ready been loaded in cache.
 *
 * Created by aaron.spiteri on 11/7/17.
 */
@Service
public class ChallengeObjectFactoryService {
    private Hashtable<String, Challenge> cache = new Hashtable<>();
    private Logger logger = LoggerFactory.getLogger(ChallengeObjectFactoryService.class);

    public Challenge retrieveChallenge(String clazz) throws Challenge2Exception {
        logger.debug("attempting to create a instance of " + clazz);
        Challenge ch;

        if (cache.containsKey(clazz)) {
            return cache.get(clazz);
        }

        try {
            ClassLoader classLoader = ChallengeObjectFactoryService.class.getClassLoader();
            Class thisChallenge = classLoader.loadClass(clazz);
            ch = (Challenge) thisChallenge.newInstance();
        }
        catch (java.lang.InstantiationException e) {
            logger.error("the class " + clazz + " can not be created ", e);
            throw new C2InstantiationException("the class " + clazz + " can not be created ");
        }
        catch (Exception e) {
            logger.error("an unchecked exception has occurred ", e);
            throw new Challenge2Exception("an unchecked exception has occurred");
        }

        return ch;
    }
}

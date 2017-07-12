package au.azzmosphere.challengesolver2.services;

import au.azzmosphere.challengesolver2.exceptions.Challenge2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import au.azzmosphere.challengesolver2.challenge.Challenge;

/**
 *
 * Challenge processor provides a service that executes a challenge.
 *
 * used by the process controller.
 *
 * Created by aaron.spiteri on 9/7/17.
 */
@Service
public class ChallengeProcessorService {
    private ChallengeObjectFactoryService challengeObjectFactoryService;
    private ConfigEntityManagerService configEntityManagerService;
    private Logger logger = LoggerFactory.getLogger(ChallengeProcessorService.class);

    @Autowired
    public void setChallengeObjectFactoryService(ChallengeObjectFactoryService challengeObjectFactoryService) {
        this.challengeObjectFactoryService = challengeObjectFactoryService;
    }

    @Autowired
    public void setConfigEntityManagerService(ConfigEntityManagerService configEntityManagerService) {
        this.configEntityManagerService = configEntityManagerService;
    }

    public HashMap executeChallenge(int categoryId, int challengeId, HashMap input) throws Challenge2Exception {
        HashMap<String, Object> challengeHash = configEntityManagerService.retrieveChallenge(categoryId, challengeId);
        Challenge challenge = challengeObjectFactoryService.retrieveChallenge((String) challengeHash.get(ConfigEntityManagerService.CONFIG_KEYS.CLAZZ));
        HashMap rv;

        try {
            challenge.reset();
            challenge.putInput(input);
            challenge.process();
            rv = challenge.returnValues();
        }
        catch (Exception e) {
            logger.error("unable to process categoryId:" + categoryId + " challengeId:" + challengeId, e);
            throw new Challenge2Exception("unable to process categoryId:" + categoryId + " challengeId:" + challengeId + ":" + e.getMessage());
        }
        return rv;
    }
}

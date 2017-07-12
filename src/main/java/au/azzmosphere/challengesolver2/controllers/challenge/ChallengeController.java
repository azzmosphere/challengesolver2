package au.azzmosphere.challengesolver2.controllers.challenge;

import au.azzmosphere.challengesolver2.controllers.AbstractChallengeController;
import au.azzmosphere.challengesolver2.services.ChallengeProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * Created by aaron.spiteri on 12/7/17.
 */
public class ChallengeController extends AbstractChallengeController<HashMap<String, Object>, HashMap<String, Object>> {

    private ChallengeProcessorService challengeProcessorService;
    private final static String CATEGORY_KEY = "categoryId";
    private final static String CHALLENGE_KEY = "challengeId";
    private final static String DATA_KEY = "data";

    @Autowired
    public void setChallengeProcessorService(ChallengeProcessorService challengeProcessorService) {
        this.challengeProcessorService = challengeProcessorService;
    }

    @Override
    protected HashMap<String, Object> handler(HashMap<String, Object> input) throws Exception {

        return challengeProcessorService.executeChallenge((int) input.get(CATEGORY_KEY), (int) input.get(CHALLENGE_KEY), (HashMap) input.get(DATA_KEY));


    }

    @RequestMapping(value = "/challenge/{categoryId}/{challengeId}", method = RequestMethod.POST)
    @ResponseBody public final HashMap process(@RequestParam(value = "data") String data, @PathVariable("categoryId") Integer categoryId, @PathVariable Integer challengeId) throws Exception {
        HashMap<String, Object> input = new HashMap<>();
        input.put(CATEGORY_KEY, categoryId);
        input.put(CHALLENGE_KEY, challengeId);
        input.put(DATA_KEY, data);

        HashMap<String, Object> response = super.executor(input);
        if (isError()) {
            response = new HashMap<>();
            response.put("error", getEstring());
        }

        return response;
    }

}

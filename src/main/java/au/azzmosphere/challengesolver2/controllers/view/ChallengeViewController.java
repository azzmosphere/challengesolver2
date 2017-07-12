package au.azzmosphere.challengesolver2.controllers.view;

import au.azzmosphere.challengesolver2.controllers.AbstractChallengeController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by aaron.spiteri on 12/7/17.
 */
public class ChallengeViewController extends AbstractChallengeController<ArrayList<Integer>, HashMap<String, Object>> {
    @Override
    protected HashMap<String, Object> handler(ArrayList<Integer> input) throws Exception {
        return getConfigEntityManagerService().retrieveChallenge(input.get(0), input.get(1));
    }

    @RequestMapping(value = "/view/{categoryId}/{challengeId}", method = RequestMethod.GET)
    @ResponseBody
    public final HashMap process(@PathVariable("categoryId") Integer categoryId, @PathVariable("challengeId") Integer challengeId) {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(categoryId);
        ids.add(challengeId);

        HashMap response = super.executor(ids);

        if (isError()) {
            response = new HashMap<String, Object>();
            response.put("error", getEstring());
        }

        return response;
    }
}

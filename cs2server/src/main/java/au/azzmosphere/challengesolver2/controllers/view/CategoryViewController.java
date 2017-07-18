package au.azzmosphere.challengesolver2.controllers.view;

import au.azzmosphere.challengesolver2.controllers.AbstractChallengeController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * Created by aaron.spiteri on 12/7/17.
 */
public class CategoryViewController extends AbstractChallengeController<Integer, HashMap<String, Object>> {
    @Override
    protected HashMap<String, Object> handler(Integer input) throws Exception {
        return getConfigEntityManagerService().retrieveCategory(input);
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    @ResponseBody
    public final HashMap process(@PathVariable("id") Integer id) {
        HashMap response = super.executor(id);

        if (isError()) {
            response = new HashMap<String, Object>();
            response.put("error", getEstring());
        }

        return response;
    }
}

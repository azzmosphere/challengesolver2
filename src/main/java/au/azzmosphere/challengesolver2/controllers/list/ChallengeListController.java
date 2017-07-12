package au.azzmosphere.challengesolver2.controllers.list;

import au.azzmosphere.challengesolver2.controllers.AbstractChallengeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by aaron.spiteri on 12/7/17.
 */
@Controller
public class ChallengeListController extends AbstractListController<Integer> {

    @Override
    protected List handler(Integer input) throws Exception {
        return getConfigEntityManagerService().retrieveChallenges(input);
    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
    @ResponseBody
    public final List process(@PathVariable("id") Integer id) {
        List response = super.executor(id);

        if (isError()) {
            response = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> m = new HashMap<>();
            m.put("error", getEstring());
            response.add(m);
        }

        return response;
    }
}

package au.azzmosphere.challengesolver2.controllers.list;

import au.azzmosphere.challengesolver2.controllers.AbstractChallengeController;
import au.azzmosphere.challengesolver2.services.ConfigEntityManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

/**
 * Created by aaron.spiteri on 12/7/17.
 */
@Controller
public class CategoryListController extends AbstractListController<HashMap<String,Object>> {


    @Override
    protected List handler(HashMap<String, Object> input) throws Exception {
        return getConfigEntityManagerService().retrieveCategories();
    }


    @RequestMapping(value = "/list/", method = RequestMethod.GET)
    @ResponseBody public final List process() {
        List response = super.executor(null);

        if (isError()) {
            response = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> m = new HashMap<>();
            m.put("error", getEstring());
            response.add(m);
        }

        return response;
    }
}

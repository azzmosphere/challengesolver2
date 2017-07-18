package au.azzmosphere.xmlcs2configmgr;

import au.azzmosphere.challengesolver2.persist.config.CategoryDAO;
import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;
import au.azzmosphere.challengesolver2.persist.config.ConfigEntityManager;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * Created by aaron.spiteri on 18/7/17.
 */
public class XmlcCs2ConfigManager implements ConfigEntityManager {
    @Override
    public List<Object> getCategories() {
        return null;
    }

    @Override
    public void setCategories(List<CategoryDAO> categories) {

    }

    @Override
    public List<Object> getChallenges(int categorieId) {
        return null;
    }

    @Override
    public void setChallenges(int categoryId, List<ChallengeDAO> challenges) {

    }

    @Override
    public CategoryDAO getCategory(int id) {
        return null;
    }

    @Override
    public void setCategory(int id, CategoryDAO categoryDAO) {

    }

    @Override
    public ChallengeDAO getChallenge(int categorId, int challengeId) {
        return null;
    }

    @Override
    public void setChallenge(int categorId, ChallengeDAO challenge) {

    }

    @Override
    public void setConfiguration(Environment environment) {

    }
}

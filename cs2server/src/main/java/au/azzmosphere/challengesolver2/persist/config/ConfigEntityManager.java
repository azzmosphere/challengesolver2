package au.azzmosphere.challengesolver2.persist.config;

import org.springframework.core.env.Environment;

import java.util.List;

/**
 * used by persistence objects/libraries to as a method of communicating
 * challenges and categories from and to persistemt storage.
 *
 * Created by aaron.spiteri on 9/7/17.
 */

public interface ConfigEntityManager {
    List<Object> getCategories();
    void setCategories(List<CategoryDAO> categories);

    List<Object> getChallenges(int categorieId);
    void setChallenges(int categoryId, List<ChallengeDAO> challenges);

    CategoryDAO getCategory(int id);
    void setCategory(int id, CategoryDAO categoryDAO);

    ChallengeDAO getChallenge(int categorId, int challengeId);
    void setChallenge(int categorId, ChallengeDAO challenge);

    void setConfiguration(Environment environment);
}

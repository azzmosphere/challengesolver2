package au.azzmosphere.challengesolver2.persist.config;

import java.net.URI;
import java.util.List;

/**
 * Created by aaron.spiteri on 9/7/17.
 */
public interface CategoryDAO extends ConfigBaseDAO {
    void setUri(URI uri);
    URI getUri();

    void setChallenges(List<ChallengeDAO> challenges);
    List<ChallengeDAO> getChallenges();
}

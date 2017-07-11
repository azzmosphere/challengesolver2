package au.azzmosphere.challengesolver2.persist.config;

/**
 * Created by aaron.spiteri on 9/7/17.
 */
public interface ChallengeDAO extends ConfigBaseDAO {

    void setClazz(String clazz);
    String getClazz();

}

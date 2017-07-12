package au.azzmosphere.challengesolver2;

import au.azzmosphere.challengesolver2.application.ChallengeSolver2Config;
import au.azzmosphere.challengesolver2.persist.config.ConfigEntityManager;
import org.springframework.boot.SpringApplication;

/**
 *
 * starts the framework and web binding engine. ALso the integration point for
 * persistent entities.
 *
 * Created by aaron.spiteri on 12/7/17.
 */

public class ChallengeSolver2 {
    public ChallengeSolver2(ConfigEntityManager configEntityManager) {
        new ChallengeSolver2Config(configEntityManager);
    }

    public void init(String[] args) {
        SpringApplication.run(ChallengeSolver2.class, args);
    }
}

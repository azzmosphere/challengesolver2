package au.azzmosphere.challengesolver2.application;

import au.azzmosphere.challengesolver2.persist.config.ConfigEntityManager;
import au.azzmosphere.challengesolver2.services.ConfigEntityManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aaron.spiteri on 12/7/17.
 */
@Configuration @ComponentScan("au.azzmosphere.challengesolver2.services")
public class ChallengeSolver2Config {
    private ConfigEntityManager configEntityManager;

    public ChallengeSolver2Config(ConfigEntityManager configEntityManager) {
        this.configEntityManager = configEntityManager;
    }

    @Bean
    public ConfigEntityManagerService configEntityManagerService() {
        return new ConfigEntityManagerService(configEntityManager);
    }
}

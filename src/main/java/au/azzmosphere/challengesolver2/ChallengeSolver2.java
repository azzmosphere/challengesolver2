package au.azzmosphere.challengesolver2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * starts the framework and web binding engine. ALso the integration point for
 * persistent entities.
 *
 * Created by aaron.spiteri on 12/7/17.
 *
 * TODO: To avoid the painful and unessary pain of trying to inject entity manager
 * pre construction. I am going to autowire the configuration service in
 * here and then set the entity manager pre run if that is possible.
 */

@SpringBootApplication
@ComponentScan("au.azzmosphere.challengesolver2")
public class ChallengeSolver2 {
    public static void main(String[] args) {
        SpringApplication.run(ChallengeSolver2.class, args);
    }

}

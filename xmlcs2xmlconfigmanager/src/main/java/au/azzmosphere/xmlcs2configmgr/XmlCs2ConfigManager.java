package au.azzmosphere.xmlcs2configmgr;

import au.azzmosphere.challengesolver2.persist.config.CategoryDAO;
import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;
import au.azzmosphere.challengesolver2.persist.config.ConfigEntityManager;
import org.springframework.core.env.Environment;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;

/**
 *
 * provides interface into XML based configuration service.
 *
 * Created by aaron.spiteri on 18/7/17.
 */
public class XmlCs2ConfigManager implements ConfigEntityManager {
    private String xmlConfigFile;
    private String xmlConfigSchema;
    private final Logger logger = LoggerFactory.getLogger(XmlCs2ConfigManager.class);

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
    public ChallengeDAO getChallenge(int categoryId, int challengeId) {
        return null;
    }

    @Override
    public void setChallenge(int categoryId, ChallengeDAO challenge) {

    }

    /**
     * sets configuration properties which are defined in application.properties
     *
     * These properties are:
     *    challenges.xmlconfig : The XML file which contains categories and challenges
     *    challenges.xmlschema : Schema file enforcing structure of configuration.
     *
     * @param environment
     */
    @Override
    public void setConfiguration(Environment environment) {
        this.xmlConfigFile = environment.getProperty("challenges.xmlconfig");
        this.xmlConfigSchema = environment.getProperty("challenges.xmlschema");
    }

    /**
     * reads XML file.
     */
    @Override
    public void initalise() throws Exception {
        logger.debug("reading categories and challenges file");
    }
}

package au.azzmosphere.challengesolver2.services;

import au.azzmosphere.challengesolver2.exceptions.C2InstantiationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;
import org.springframework.core.env.Environment;

import au.azzmosphere.challengesolver2.exceptions.Challenge2Exception;
import au.azzmosphere.challengesolver2.exceptions.CodeReflectionException;
import au.azzmosphere.challengesolver2.exceptions.ObjectNotFoundException;
import au.azzmosphere.challengesolver2.persist.config.ConfigBaseDAO;
import au.azzmosphere.challengesolver2.persist.config.ConfigEntityManager;

import static au.azzmosphere.challengesolver2.utils.ChallengeIntrospection.hasMethod;
import static au.azzmosphere.challengesolver2.utils.StringUtils.toUCFirst;

/**
 * Retrieve config resources from the entity manager and convert them in
 * a way that is friendly for the base class.
 *
 * used by the list controller.
 *
 * <p>
 * Created by aaron.spiteri on 10/7/17.
 */

@Service
public class ConfigEntityManagerService {
    private Logger logger = LoggerFactory.getLogger(ConfigEntityManagerService.class);


    private String configEntityManagerClass;
    private ConfigEntityManager configEntityManager;


    public void setConfigEntityManager(ConfigEntityManager configEntityManager) {
        this.configEntityManager = configEntityManager;
    }

    @Autowired
    public ConfigEntityManagerService(Environment environment) throws C2InstantiationException {
        logger.debug("configuration service: Environment set = true");

        if (environment != null && environment.getProperty("challengeconfigservice") != null) {
            try {
                configEntityManagerClass = environment.getProperty("challengeconfigservice");
                initConfigService();
                configEntityManager.setConfiguration(environment);
                configEntityManager.initalise();
            }
            catch (C2InstantiationException e) {
                logger.error("caught instantiation exception", e);
                throw e;
            }
            catch (Exception e) {
                logger.error("could not create create config manager instance", e);
                throw new C2InstantiationException(e.getMessage());
            }
        }
    }

    public ConfigEntityManagerService() {
        logger.debug("configuration service: Environment set = false");
    }


    /**
     * the constructor attempts to create a instance of the service manager.
     *
     * @throws C2InstantiationException
     */
    private void initConfigService() throws C2InstantiationException {
        try {
            if (configEntityManagerClass == null) {
                throw new C2InstantiationException("no entity manager class has been defined");
            }

            logger.debug("attempting to load configEntityMangerClass " + configEntityManagerClass);
            ClassLoader classLoader = this.getClass().getClassLoader();
            Class configEntityMangerClassObj = classLoader.loadClass(configEntityManagerClass);
            configEntityManager = (ConfigEntityManager) configEntityMangerClassObj.newInstance();
        }
        catch (C2InstantiationException e) {
            throw e;
        }
        catch (Exception e) {
            logger.error("and unchecked exception has occurred ", e);
            throw new C2InstantiationException(e.getMessage());
        }
    }

    public ConfigEntityManagerService(ConfigEntityManager configEntityManager) {
        this.configEntityManager = configEntityManager;
    }

    /**
     * used for hashtable keys.
     */
    public enum CONFIG_KEYS {
        URI {
            @Override
            public String toString() {
                return "uri";
            }
        },
        CLAZZ {
            @Override
            public String toString() {
                return "clazz";
            }
        },
        ID {
            @Override
            public String toString() {
                return "id";
            }
        },
        HEADING {
            @Override
            public String toString() {
                return "heading";
            }
        },
        DESCRIPTION {
            @Override
            public String toString() {
                return "description";
            }
        },
        VIEW {
            @Override
            public String toString() {
                return "view";
            }
        },
        ENABLED {
            @Override
            public String toString() {
                return "enabled";
            }
        }
    }

    /**
     * return a hashtable that contains all categories where isEnabled is true.
     * The hashtable returned is in the following order:
     * [
     * {id => id, heading => heading},
     * {id => n,  heading => n}
     * ]
     *
     * @return
     * @throws ObjectNotFoundException
     */
    public List<HashMap<String, Object>> retrieveCategories() throws Challenge2Exception {
        return createList(configEntityManager.getCategories());
    }

    /**
     * Given a category ID retrieve all challenges within that category.
     *
     * @param categoryId
     * @return
     * @throws ObjectNotFoundException
     */
    public List<HashMap<String, Object>> retrieveChallenges(int categoryId) throws Challenge2Exception {
        return createList(configEntityManager.getChallenges(categoryId));
    }

    /**
     * converts a category into a hashtable.
     *
     * @param categoryId
     * @return
     * @throws Challenge2Exception
     */
    public HashMap<String, Object> retrieveCategory(int categoryId) throws  Challenge2Exception
    {
        String keys[] = new String[] {
                CONFIG_KEYS.URI.toString(),
                CONFIG_KEYS.ID.toString(),
                CONFIG_KEYS.HEADING.toString(),
                CONFIG_KEYS.DESCRIPTION.toString(),
                CONFIG_KEYS.VIEW.toString(),
                CONFIG_KEYS.ENABLED.toString(),
        };

        return configObject2Hash(keys, configEntityManager.getCategory(categoryId));
    }

    /**
     * given a categoryId and challengeId return a hashtable representing the challenge.
     *
     * @param categorId
     * @param challengeId
     * @return
     * @throws Challenge2Exception
     */
    public HashMap<String, Object> retrieveChallenge(int categorId, int challengeId) throws Challenge2Exception
    {
        String keys[] = new String[] {
                CONFIG_KEYS.CLAZZ.toString(),
                CONFIG_KEYS.ID.toString(),
                CONFIG_KEYS.HEADING.toString(),
                CONFIG_KEYS.DESCRIPTION.toString(),
                CONFIG_KEYS.VIEW.toString(),
                CONFIG_KEYS.ENABLED.toString(),
        };
        return configObject2Hash(keys, configEntityManager.getChallenge(categorId, challengeId));
    }


    /*
     * convert a configuration object to a hashtable. the keys handed to this
     * object should represent the getters within the object.
     */
    private HashMap<String, Object> configObject2Hash(String[] keys, Object obj) throws Challenge2Exception {
        if (obj == null) {
            throw new ObjectNotFoundException("could not find category");
        }

        HashMap<String, Object> rv = new HashMap<>();
        try {

            for (String key : keys) {
                String mname = (hasMethod(obj, "get" + toUCFirst(key))) ? "get" + toUCFirst(key) : "is" + toUCFirst(key);
                Method method = null;

                logger.debug("setting method to " + mname);

                try {
                    method = obj.getClass().getMethod(mname);
                    if (method == null) {
                        throw new CodeReflectionException("method for key:" + key + " not found");
                    }

                }
                catch (java.lang.NoSuchMethodException e) {
                    logger.error("method with mname " + mname + " for key " + key + " could not be found", e);
                    throw new CodeReflectionException("method for key:" + key + " not found");
                }

                rv.put(key, method.invoke(obj));
            }

        }
        catch (Challenge2Exception e) {
            logger.error(e.getMessage(), e);
            throw e;
        }
        catch (Exception e) {
            String em = "unchecked exception has occurred";
            logger.error(em, e);
            throw new Challenge2Exception(em);
        }
        return rv;
    }

    /*
     * converts a list of config objects into a hashtable.
     */
    private List<HashMap<String, Object>> createList(List<Object> list) throws Challenge2Exception {
        logger.debug("attempting to retrieve all Objects");
        List<HashMap<String, Object>> rv;

        try {
            if (list.size() == 0) {
                String em = "no categories could be found";
                logger.error(em);
                throw new ObjectNotFoundException(em);
            }
            rv = retrieveList(list);
        } catch (Exception e) {
            String em = "unchecked exception has occurred";
            logger.error(em, e);
            throw new Challenge2Exception(em);
        }

        return rv;
    }

    private List<HashMap<String, Object>> retrieveList(List<Object> list) throws Exception {
        List<HashMap<String, Object>> rv = new ArrayList<>();
        for (Object configObject : list) {
            ConfigBaseDAO configItem = (ConfigBaseDAO) configObject;
            if (configItem.isEnabled()) {
                logger.debug("adding object with id " + configItem.getId() + " and heading " + configItem.getHeading());
                HashMap<String, Object> values = new HashMap<>();
                values.put(CONFIG_KEYS.ID.toString(), configItem.getId());
                values.put(CONFIG_KEYS.HEADING.toString(), configItem.getHeading());
                rv.add(values);
            }
        }

        return rv;
    }
}
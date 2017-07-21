package au.azzmosphere.xmlcs2configmgr.mapper;

import au.azzmosphere.challengesolver2.exceptions.C2InstantiationException;
import au.azzmosphere.challengesolver2.persist.config.CategoryDAO;
import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;
import au.azzmosphere.xmlcs2configmgr.dao.Cs2XMLCategoryDAO;
import au.azzmosphere.xmlcs2configmgr.xmlbindings.XmlCs2Category;
import au.azzmosphere.xmlcs2configmgr.xmlbindings.XmlCs2Challenge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class XMLCs2CategoryMapper extends AbstractCs2Mapper {
    private Map<Integer, Map<Integer, ChallengeDAO>> challenges;
    private XMLCs2ChallengeMapper xmlCs2ChallengeMapper = new XMLCs2ChallengeMapper();
    private final Logger logger = LoggerFactory.getLogger(XMLCs2CategoryMapper.class);

    public XMLCs2CategoryMapper(Map<Integer, Map<Integer, ChallengeDAO>> challenges) {
        this.challenges = challenges;
    }

    public CategoryDAO mapItem(XmlCs2Category objectIn)  throws C2InstantiationException {
        logger.debug("mapping category object");
        String[] attributes = {
                "id", "enabled", "uri",
                "heading", "description", "view",
        };

        CategoryDAO dao = new Cs2XMLCategoryDAO();

        try {
            mapAttributes(objectIn, dao, attributes);
            Map<Integer, ChallengeDAO> challengeDAOMap = new HashMap<>();
            challenges.put(dao.getId(), challengeDAOMap);

            for (XmlCs2Challenge challenge : objectIn.getChallenges()) {
                ChallengeDAO challengeDAO = xmlCs2ChallengeMapper.mapItem(challenge);
                challengeDAOMap.put(challengeDAO.getId(), challengeDAO);
            }
        }
        catch(NoSuchMethodException e) {
            String es = "there is a problem with method mapping. a method could not be found";
            logger.error(es, e);
            throw new C2InstantiationException(es);
        }
        catch (IllegalAccessException e) {
            String es = "check variable modifiers on concrete implementation for attributes - they appear to be incorrect";
            logger.error(es, e);
            throw new C2InstantiationException(es);
        }
        catch (InvocationTargetException e) {
            String es = "oh dear - this is not good at all, check logs and hope for the best";
            logger.error(es, e);
            throw new C2InstantiationException(es);
        }

        return dao;
    }
}

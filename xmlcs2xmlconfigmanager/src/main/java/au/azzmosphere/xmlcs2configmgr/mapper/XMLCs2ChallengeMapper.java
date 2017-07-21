package au.azzmosphere.xmlcs2configmgr.mapper;

import au.azzmosphere.challengesolver2.exceptions.C2InstantiationException;
import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;
import au.azzmosphere.xmlcs2configmgr.dao.Cs2XMLChallengeDAO;
import au.azzmosphere.xmlcs2configmgr.xmlbindings.XmlCs2Challenge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class XMLCs2ChallengeMapper extends AbstractCs2Mapper {
    private final Logger logger = LoggerFactory.getLogger(XMLCs2ChallengeMapper.class);

    public ChallengeDAO mapItem(XmlCs2Challenge objectIn) throws C2InstantiationException {
        logger.debug("mapping challenge object");

        String[] attributes = {
                "id",
                "clazz", "heading", "description", "view",
                "enabled"
        };

        ChallengeDAO dao = new Cs2XMLChallengeDAO();
        try {
            mapAttributes(objectIn, dao, attributes);
        }
        catch (NoSuchMethodException e) {
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

package au.azzmosphere.xmlcs2configmgr.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.el.MethodNotFoundException;

import java.lang.reflect.InvocationTargetException;

import static au.azzmosphere.challengesolver2.utils.ChallengeIntrospection.hasMethod;
import static au.azzmosphere.challengesolver2.utils.StringUtils.toUCFirst;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public abstract class AbstractCs2Mapper {
    private final Logger logger = LoggerFactory.getLogger(AbstractCs2Mapper.class);

    Object mapAttributes(Object objectIn, Object dao, String[] attributes)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (String attribute : attributes) {
            String getMethodName = "get" + toUCFirst(attribute);
            String setMethodName = "set" + toUCFirst(attribute);

            if (!hasMethod(dao, setMethodName)) {
                throw new MethodNotFoundException("unable to find method " + setMethodName + " for class " + dao.getClass().toString());
            }

            if (!hasMethod(objectIn, getMethodName)) {
                logger.debug(objectIn.getClass() + " does not have method " + getMethodName + " attempting to find different method");
                getMethodName = "is" + toUCFirst(attribute);

                if (!hasMethod(objectIn, getMethodName)) {
                    throw new MethodNotFoundException("unable to find method " + getMethodName + " for class " + objectIn.getClass());
                }
            }

            logger.debug("assigning " + attribute);
            dao.getClass().getMethod(
                    setMethodName,
                    objectIn.getClass().getMethod(getMethodName).invoke(objectIn).getClass()
            ).invoke(dao, objectIn.getClass().getMethod(getMethodName).invoke(objectIn));
        }

        return dao;
    }
}

package au.azzmosphere.challengesolver2.utils;

import java.lang.reflect.Method;

/**
 * Created by aaron.spiteri on 11/7/17.
 */
public class ChallengeIntrospection {
    public static boolean hasMethod(Object obj, String methodName) {
        boolean rv = false;

        try {
            for (Method method : obj.getClass().getMethods()) {
                if (method.getName().equals(methodName)) {
                    return true;
                }
            }
        }
        catch (Exception e) {
            rv = false;
        }

        return rv;
    }
}

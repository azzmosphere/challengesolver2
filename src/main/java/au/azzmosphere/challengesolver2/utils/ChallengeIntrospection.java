package au.azzmosphere.challengesolver2.utils;

import java.lang.reflect.Method;

/**
 * Created by aaron.spiteri on 11/7/17.
 */
public class ChallengeIntrospection {
    public static boolean hasMethod(Object obj, String methodName) {
        boolean rv = true;

        try {
            Method method = obj.getClass().getMethod(methodName);

            if (method == null) {
                rv = false;
            }
        }
        catch (Exception e) {
            rv = false;
        }

        return rv;
    }
}

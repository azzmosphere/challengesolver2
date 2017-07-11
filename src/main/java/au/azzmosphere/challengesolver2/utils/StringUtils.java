package au.azzmosphere.challengesolver2.utils;

/**
 * Created by aaron.spiteri on 11/7/17.
 */
public class StringUtils {
    public static String toUCFirst(String s1) {
        return Character.toUpperCase(s1.charAt(0)) + s1.substring(1);
    }
}

package au.azzmosphere.challengesolver2.exceptions;

/**
 * Created by aaron.spiteri on 10/7/17.
 */
public class CodeReflectionException extends Challenge2Exception {
    public CodeReflectionException(String msg) {
        super("Error when trying create a instane of class:" + msg);
    }
}

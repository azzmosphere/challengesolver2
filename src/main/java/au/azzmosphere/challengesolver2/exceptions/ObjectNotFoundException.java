package au.azzmosphere.challengesolver2.exceptions;

/**
 * Created by aaron.spiteri on 10/7/17.
 */
public class ObjectNotFoundException extends Challenge2Exception {
    public ObjectNotFoundException(String msg) {
        super("object could not be found:" + msg);
    }
}

package au.azzmosphere.challengesolver2.exceptions;

/**
 * Created by aaron.spiteri on 11/7/17.
 */
public class C2InstantiationException extends Challenge2Exception {
    public C2InstantiationException(String msg) {
        super("unable to create challenge class " + msg);
    }
}

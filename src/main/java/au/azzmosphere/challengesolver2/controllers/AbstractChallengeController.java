package au.azzmosphere.challengesolver2.controllers;

import au.azzmosphere.challengesolver2.exceptions.Challenge2Exception;
import au.azzmosphere.challengesolver2.services.ConfigEntityManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by aaron.spiteri on 12/7/17.
 */
public abstract class AbstractChallengeController<I, O> {
    private final Logger logger = LoggerFactory.getLogger(AbstractChallengeController.class);
    private boolean error = false;
    private String estring = "";

    public boolean isError() {
        return error;
    }

    public String getEstring() {
        return estring;
    }

    private ConfigEntityManagerService configEntityManagerService;

    @Autowired
    public void setConfigEntityManagerService(ConfigEntityManagerService configEntityManagerService) {
        this.configEntityManagerService = configEntityManagerService;
    }

    protected ConfigEntityManagerService getConfigEntityManagerService() {
        return configEntityManagerService;
    }

    protected abstract O handler(I input) throws Exception;

    protected O executor(I input) {
        O response = null;

        estring = "";
        error = false;

        try {
           response = handler(input);
        }
        catch (Challenge2Exception e) {
           logger.error("challenge error occured - stack trace will be recorded in log");
           estring = e.getMessage();
           error = true;
        }
        catch (Exception e) {
           logger.error("unchecked error has occurred", e);
           estring = e.getMessage();
           error = true;
        }

        return response;
    }
}

package au.azzmosphere.challengesolver2.persist.config;

/**
 * Created by aaron.spiteri on 9/7/17.
 */
public interface ConfigBaseDAO {
    void setId(Integer id);
    int getId();

    void setHeading(String heading);
    String getHeading();

    void setDescription(String description);
    String getDescription();

    void setView(String view);
    String getView();

    void setEnabled(Boolean enabled);
    boolean isEnabled();
}

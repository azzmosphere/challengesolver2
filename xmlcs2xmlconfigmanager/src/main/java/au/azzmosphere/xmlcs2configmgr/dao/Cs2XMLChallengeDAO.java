package au.azzmosphere.xmlcs2configmgr.dao;

import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class Cs2XMLChallengeDAO implements ChallengeDAO {
    private Integer id;
    private String clazz, heading, description, view;
    private boolean enabled;

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setHeading(String heading) {
        this.heading = heading;
    }

    @Override
    public String getClazz() {
        return clazz;
    }

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setView(String view) {
        this.view = view;
    }

    @Override
    public String getView() {
        return view;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

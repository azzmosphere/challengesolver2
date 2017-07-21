package au.azzmosphere.xmlcs2configmgr.dao;

import au.azzmosphere.challengesolver2.persist.config.CategoryDAO;
import au.azzmosphere.challengesolver2.persist.config.ChallengeDAO;

import java.net.URI;
import java.util.List;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class Cs2XMLCategoryDAO implements CategoryDAO {
    int id;
    boolean enabled;
    URI uri;
    String heading, description, view;

    @Override
    public void setId(Integer id) {
        this.id = id;
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
    public void setUri(URI uri) {
        this.uri = uri;
    }

    @Override
    public String getHeading() {
        return heading;
    }

    @Override
    public URI getUri() {
        return uri;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    // this could possibly be removed. Since the manager does
    // it and probally better, but may come in handy for
    // RDBMS systems
    public void setChallenges(List<ChallengeDAO> challenges) {
        // not used for this.
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<ChallengeDAO> getChallenges() {
        return null;
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

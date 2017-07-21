package au.azzmosphere.xmlcs2configmgr.xmlbindings;

import javax.xml.bind.annotation.XmlElement;
import java.net.URI;
import java.util.List;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class XmlCs2Category {
    int id;
    boolean enabled;
    URI uri;
    String heading, description, view;
    List<XmlCs2Challenge> challenges;

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    @XmlElement
    public void setUri(URI uri) {
        this.uri = uri;
    }

    public URI getUri() {
        return uri;
    }

    @XmlElement
    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setView(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    @XmlElement
    public void setChallenges(List<XmlCs2Challenge> challenges) {
        this.challenges = challenges;
    }

    public List<XmlCs2Challenge> getChallenges() {
        return challenges;
    }
}

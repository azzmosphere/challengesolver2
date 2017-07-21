package au.azzmosphere.xmlcs2configmgr.xmlbindings;


import javax.xml.bind.annotation.XmlElement;

/**
 * Created by aaron.spiteri on 20/7/17.
 */
public class XmlCs2Challenge {
    private int id;
    private String clazz, heading, description, view;
    private boolean enabled;

    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    @XmlElement
    public void setHeading(String heading) {
        this.heading = heading;
    }


    public String getClazz() {
        return clazz;
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
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public boolean isEnabled() {
        return enabled;
    }
}

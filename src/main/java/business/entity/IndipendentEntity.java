package business.entity;

import javax.xml.bind.annotation.XmlElement;

public interface IndipendentEntity extends Entity {
    @XmlElement
    String getId();

    void setId(String id);
}

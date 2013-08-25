package business.entity;

import utility.SerialClone;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
public class Journaling implements Entity {

    /**
     *
     */
    @XmlTransient
    private static final long serialVersionUID = 919250330191402749L;


    private List<InterventoCompleto> intervento;

    @XmlElements({
            @XmlElement(name = "intervento", type = InterventoCompleto.class)
    })
    public List<InterventoCompleto> getIntervento() {
        return SerialClone.clone(intervento);
    }

    public void setIntervento(List<InterventoCompleto> intervento) {
        this.intervento = SerialClone.clone(intervento);
    }
}

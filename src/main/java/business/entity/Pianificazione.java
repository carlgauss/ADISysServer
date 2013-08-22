package business.entity;

import utility.SerialClone;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlRootElement
public class Pianificazione implements Entity {

    /**
     *
     */
    @XmlTransient
    private static final long serialVersionUID = 919250330191402749L;


    private List<Intervento> intervento;

    @XmlElements({
            @XmlElement(name = "intervento", type = Intervento.class)
    })
    public List<Intervento> getIntervento() {
        return SerialClone.clone(intervento);
    }

    public void setIntervento(List<Intervento> intervento) {
        this.intervento = SerialClone.clone(intervento);
    }
}

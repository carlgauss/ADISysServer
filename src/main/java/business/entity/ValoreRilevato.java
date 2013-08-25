package business.entity;

import integration.xml.adapter.XMLTimeAdapter;
import org.joda.time.LocalTime;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class ValoreRilevato implements Entity {
    /**
     *
     */
    private static final long serialVersionUID = 2796107324385873233L;
    private String misura;
    private LocalTime tempoOperazione;

    @XmlValue
    public String getMisura() {
        return misura;
    }

    public void setMisura(String misura) {
        this.misura = misura;
    }

    @XmlAttribute
    @XmlJavaTypeAdapter(XMLTimeAdapter.class)
    public LocalTime getTempoOperazione() {
        return tempoOperazione;
    }

    public void setTempoOperazione(LocalTime tempoOperazione) {
        this.tempoOperazione = tempoOperazione;
    }

}

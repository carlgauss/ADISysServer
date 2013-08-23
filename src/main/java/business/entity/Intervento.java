package business.entity;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import utility.SerialClone;
import utility.xml.adapter.XMLDateAdapter;
import utility.xml.adapter.XMLTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"id", "citta", "cap", "indirizzo", "data", "ora", "operazione", "paziente", "infermiere"})
public class Intervento implements IndipendentEntity {
    /**
     *
     */
    @XmlTransient
    private static final long serialVersionUID = 3822067935716362954L;

    private String id;

    private Paziente paziente;

    private Infermiere infermiere;

    private String citta;

    private String cap;

    private String indirizzo;

    private LocalDate data;

    private LocalTime ora;

    private List<Operazione> operazione;

    @XmlElement
    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta.trim();
    }

    @XmlElement
    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap.trim();
    }

    @XmlElement
    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo.trim();
    }

    @XmlJavaTypeAdapter(XMLDateAdapter.class)
    @XmlElement(type = String.class)
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @XmlJavaTypeAdapter(XMLTimeAdapter.class)
    @XmlElement(type = String.class)
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElements({
            @XmlElement(name = "operazione", type = Operazione.class)
    })
    @XmlElementWrapper(name = "listaOperazioni")
    public List<Operazione> getOperazione() {
        return SerialClone.clone(operazione);
    }

    public void setOperazione(List<Operazione> operazione) {
        this.operazione = SerialClone.clone(operazione);
    }

    @XmlElement
    public Paziente getPaziente() {
        return SerialClone.clone(paziente);
    }

    public void setPaziente(Paziente paziente) {
        this.paziente = SerialClone.clone(paziente);
    }

    @XmlElement
    public Infermiere getInfermiere() {
        return SerialClone.clone(infermiere);
    }

    public void setInfermiere(Infermiere infermiere) {
        this.infermiere = SerialClone.clone(infermiere);
    }

    @XmlTransient
    public boolean isEditable() {
        return isEditable;
    }

    @XmlTransient
    public void setEditable(boolean editable) {
        isEditable = editable;
    }

    @XmlTransient
    private boolean isEditable = true;
}

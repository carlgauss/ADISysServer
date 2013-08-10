package business.entity;

import java.util.List;

import javax.xml.bind.annotation.*;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import util.SerialClone;

@XmlRootElement
public class Intervento implements Entity {
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 3822067935716362954L;
	@XmlElement
	private String id;
	@XmlElement
	private Paziente paziente;
	@XmlElement
	private Infermiere infermiere;
	@XmlElement
	private String citta;
	@XmlElement
	private String cap;
	@XmlElement
	private String indirizzo;
	@XmlElement(type = String.class)
	private LocalDate data;
	@XmlElement(type = String.class)
	private LocalTime ora;
	
	@XmlElements({ 
	    @XmlElement(name="operazione", type=Operazione.class)
	})
	@XmlElementWrapper(name = "listaOperazioni")
	private List<Operazione> operazione;
	
	public Paziente getPaziente() {
		return SerialClone.clone(paziente);
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = SerialClone.clone(paziente);
	}
	public Infermiere getInfermiere() {
		return SerialClone.clone(infermiere);
	}
	public void setInfermiere(Infermiere infermiere) {
		this.infermiere = SerialClone.clone(infermiere);
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getOra() {
		return ora;
	}
	public void setOra(LocalTime ora) {
		this.ora = ora;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Operazione> getOperazione() {
		return SerialClone.clone(operazione);
	}
	public void setOperazione(List<Operazione> operazione) {
		this.operazione = SerialClone.clone(operazione);
	}
}

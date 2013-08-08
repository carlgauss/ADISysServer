package business.entity;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import util.SerialClone;

public class Intervento implements Entity {
	/**
	 * 
	 */
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
	
	public Paziente getPaziente() {
		return SerialClone.clone(paziente);
	}
	public void setPaziente(Paziente paziente) {
		this.paziente = paziente;
	}
	public Infermiere getInfermiere() {
		return SerialClone.clone(infermiere);
	}
	public void setInfermiere(Infermiere infermiere) {
		this.infermiere = infermiere;
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
		this.operazione = operazione;
	}
}

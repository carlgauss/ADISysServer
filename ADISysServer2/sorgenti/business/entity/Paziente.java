package business.entity;

import java.util.List;

import org.joda.time.LocalDate;

import util.SerialClone;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Paziente implements Entity{
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 5047761740352062543L;
	@XmlElement
	private String id;
	@XmlElement
	private String nome;
	@XmlElement
	private String cognome;
	@XmlElement(type = String.class)
	private LocalDate date;
	
	@XmlElements({ 
	    @XmlElement(name="numero", type=String.class, required = false)
	})
	@XmlElementWrapper(name = "rubrica")
	private List<String> numeroCellulare;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<String> getNumeroCellulare() {
		return SerialClone.clone(numeroCellulare);
	}
	public void setNumeroCellulare(List<String> numeroCellulare) {
		this.numeroCellulare = SerialClone.clone(numeroCellulare);
	}
}

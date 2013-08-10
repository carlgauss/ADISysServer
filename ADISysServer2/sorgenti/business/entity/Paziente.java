package business.entity;

import java.util.List;

import org.joda.time.LocalDate;

import util.SerialClone;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType (propOrder={"id", "nome", "cognome", "data", "numeroCellulare"})
public class Paziente implements Entity{
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 5047761740352062543L;
	
	private String id;
	
	private String nome;
	
	private String cognome;
	private LocalDate data;
	
	private List<String> numeroCellulare;
	
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@XmlElement
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@XmlElement(type = String.class)
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate date) {
		this.data = date;
	}
	@XmlElements({ 
	    @XmlElement(name="numero", type=String.class, required = false)
	})
	@XmlElementWrapper(name = "rubrica")
	public List<String> getNumeroCellulare() {
		return SerialClone.clone(numeroCellulare);
	}
	public void setNumeroCellulare(List<String> numeroCellulare) {
		this.numeroCellulare = SerialClone.clone(numeroCellulare);
	}
}

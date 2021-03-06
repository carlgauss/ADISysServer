package business.entity;

import java.util.List;

import org.joda.time.LocalDate;

import util.SerialClone;
import util.xml.adapter.XMLDateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlType (propOrder={"id", "nome", "cognome", "data", "numeroCellulare"})
public class Paziente implements Person {
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
	@Override
    @XmlElement
	public String getNome() {
		return nome;
	}
	@Override
    public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
    @XmlElement
	public String getCognome() {
		return cognome;
	}
	@Override
    public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	@XmlJavaTypeAdapter(XMLDateAdapter.class)
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

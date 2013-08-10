package business.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Infermiere implements Entity {
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 8182360441844160117L;
	
	@XmlElement
	private String id;
	
	@XmlElement
	private String nome;
	
	@XmlElement
	private String cognome;
	
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
}

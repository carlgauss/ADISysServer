package business.entity;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType (propOrder={"id", "nome", "cognome"})
public class Infermiere implements Entity {
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 8182360441844160117L;
	
	
	private String id;
	
	
	private String nome;
	
	
	private String cognome;
	
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
}

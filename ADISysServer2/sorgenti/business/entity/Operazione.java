package business.entity;

import util.SerialClone;

import javax.xml.bind.annotation.*;

@XmlRootElement
public class Operazione implements Entity {
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 3829368754663286748L;
	@XmlElement
	private String id;
	@XmlElement
	private String nome;
	@XmlElement(nillable = true)
	private String nota;
	@XmlElement(required = false)
	private ValoreRilevato valoreRilevato;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	public ValoreRilevato getValoreRilevato() {
		return SerialClone.clone(valoreRilevato);
	}
	public void setValoreRilevato(ValoreRilevato valoreRilevato) {
		this.valoreRilevato = SerialClone.clone(valoreRilevato);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}

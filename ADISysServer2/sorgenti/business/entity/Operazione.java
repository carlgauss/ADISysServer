package business.entity;

import util.SerialClone;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType (propOrder={"id", "nome", "nota", "valoreRilevato"})
public class Operazione implements Entity {
	/**
	 * 
	 */
	@XmlTransient
	private static final long serialVersionUID = 3829368754663286748L;
	
	private String id;
	
	private String nome;
	
	private String nota;
	private ValoreRilevato valoreRilevato;
	
	@XmlElement
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@XmlElement(nillable = true)
	public String getNota() {
		return nota;
	}
	public void setNota(String nota) {
		this.nota = nota;
	}
	@XmlElement(required = false)
	public ValoreRilevato getValoreRilevato() {
		return SerialClone.clone(valoreRilevato);
	}
	public void setValoreRilevato(ValoreRilevato valoreRilevato) {
		this.valoreRilevato = SerialClone.clone(valoreRilevato);
	}
	@XmlElement
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}

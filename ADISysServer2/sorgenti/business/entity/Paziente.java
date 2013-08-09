package business.entity;

import java.util.List;

import org.joda.time.LocalDate;

import util.SerialClone;

public class Paziente implements Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5047761740352062543L;
	private String id;
	private String nome;
	private String cognome;
	private LocalDate date;
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

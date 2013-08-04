package business.entity;

import org.joda.time.LocalDate;

public class Paziente {
	private String id;
	private String nome;
	private String cognome;
	private LocalDate date;
	private String[] numeroCellulare;
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
	public String[] getNumeroCellulare() {
		return numeroCellulare;
	}
	public void setNumeroCellulare(String[] numeroCellulare) {
		this.numeroCellulare = numeroCellulare;
	}
}

package business.entity;

import org.joda.time.LocalTime;

import util.SerialClone;

public class ValoreRilevato {
	private String misura;
	private LocalTime tempoOperazione;
	
	public String getMisura() {
		return misura;
	}
	public void setMisura(String misura) {
		this.misura = misura;
	}
	public LocalTime getTempoOperazione() {
		return tempoOperazione;
	}
	public void setTempoOperazione(LocalTime tempoOperazione) {
		this.tempoOperazione = tempoOperazione;
	}
	
}

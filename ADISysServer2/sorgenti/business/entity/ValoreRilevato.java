package business.entity;

import util.SerialClone;

public class ValoreRilevato {
	private String misura;
	private String tempoOperazione;
	
	public String getMisura() {
		return misura;
	}
	public void setMisura(String misura) {
		this.misura = misura;
	}
	public String getTempoOperazione() {
		return tempoOperazione;
	}
	public void setTempoOperazione(String tempoOperazione) {
		this.tempoOperazione = tempoOperazione;
	}
	
	//ESEMPIO
	private ValoreRilevato cloneObj() {
		return SerialClone.clone(this);
	}
}

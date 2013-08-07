package business.transfer;

import business.entity.Operazione;

public class OperazioneTO extends Operazione {
	private String idIntervento;

	public String getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(String idIntervento) {
		this.idIntervento = idIntervento;
	}
}

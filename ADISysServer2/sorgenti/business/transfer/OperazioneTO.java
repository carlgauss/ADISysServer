package business.transfer;

import business.entity.Operazione;
import business.entity.ValoreRilevato;

public class OperazioneTO extends Operazione {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1252928395615720975L;
	private String idIntervento;
	
	public OperazioneTO() {
		
	}
	
	public OperazioneTO(Operazione operazione) {
		String id = operazione.getId();
		setId(id);
		
		String nome = operazione.getNome();
		setNome(nome);
		
		String nota = operazione.getNota();
		setNota(nota);
		
		ValoreRilevato valoreRilevato = operazione.getValoreRilevato();
		setValoreRilevato(valoreRilevato);
	}

	public String getIdIntervento() {
		return idIntervento;
	}

	public void setIdIntervento(String idIntervento) {
		this.idIntervento = idIntervento;
	}
}

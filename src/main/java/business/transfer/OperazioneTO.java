package business.transfer;

import business.entity.Operazione;
import business.entity.Patologia;
import business.entity.ValoreRilevato;

import java.util.List;

public class OperazioneTO extends Operazione {
    /**
     *
     */
    private static final long serialVersionUID = -1252928395615720975L;
    private String idIntervento;
    private String idPaziente;

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

        List<Patologia> patologiaList = operazione.getPatologia();
        setPatologia(patologiaList);
    }

    public String getIdIntervento() {
        return idIntervento;
    }

    public void setIdIntervento(String idIntervento) {
        this.idIntervento = idIntervento;
    }

    public String getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }
}

package business.transfer;

import business.entity.Infermiere;
import business.entity.Intervento;

import java.util.List;

public class PianificazioneElement {
    private Infermiere infermiere;
    private List<Intervento> pianificazione;

    public List<Intervento> getPianificazione() {
        return pianificazione;
    }

    public void setPianificazione(List<Intervento> pianificazione) {
        this.pianificazione = pianificazione;
    }

    public Infermiere getInfermiere() {
        return infermiere;
    }

    public void setInfermiere(Infermiere infermiere) {
        this.infermiere = infermiere;
    }
}

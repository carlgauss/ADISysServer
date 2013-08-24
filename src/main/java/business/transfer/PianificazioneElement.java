package business.transfer;

import business.entity.Infermiere;
import business.entity.Intervento;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.List;

public class PianificazioneElement {
    private Infermiere infermiere;
    private List<Intervento> pianificazione;
    private BooleanProperty esporta = new SimpleBooleanProperty(false);

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

    public boolean isEsporta() {
        return esporta.get();
    }

    public void setEsporta(boolean esporta) {
        this.esporta.setValue(esporta);
    }

    public BooleanProperty getEsportaProperty() {
        return esporta;
    }
}

package business.entity;

import org.joda.time.LocalTime;

public class ValoreRilevato implements Entity {
    /**
     *
     */
    private static final long serialVersionUID = 2796107324385873233L;
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

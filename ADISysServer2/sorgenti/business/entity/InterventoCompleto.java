package business.entity;

import java.util.List;

public class InterventoCompleto extends Intervento {
    private static final long serialVersionUID = 1593975017900475176L;

    private List<GPS> gps;
    private List<Accelerometro> accelerometro;

    public InterventoCompleto() {
        super();
    }

    public InterventoCompleto(Intervento intervento) {
        super();
        setId(intervento.getId());
        setCitta(intervento.getCitta());
        setCap(intervento.getCap());
        setIndirizzo(intervento.getIndirizzo());
        setData(intervento.getData());
        setOra(intervento.getOra());
        setOperazione(intervento.getOperazione());
        setPaziente(intervento.getPaziente());
        setInfermiere(intervento.getInfermiere());
    }

    public List<GPS> getGps() {
        return gps;
    }

    public void setGps(List<GPS> gps) {
        this.gps = gps;
    }

    public List<Accelerometro> getAccelerometro() {
        return accelerometro;
    }

    public void setAccelerometro(List<Accelerometro> accelerometro) {
        this.accelerometro = accelerometro;
    }
}

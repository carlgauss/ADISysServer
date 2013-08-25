package business.entity;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "intervento")
@XmlType(propOrder = {"id", "citta", "cap", "indirizzo", "data", "ora", "operazione", "paziente", "infermiere", "gps", "accelerometro"})
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

    @XmlElements({
            @XmlElement(name = "gps", type = GPS.class)
    })
    @XmlElementWrapper(name = "listaGPS")
    public List<GPS> getGps() {
        return gps;
    }

    public void setGps(List<GPS> gps) {
        this.gps = gps;
    }

    @XmlElements({
            @XmlElement(name = "accelerometro", type = Accelerometro.class)
    })
    @XmlElementWrapper(name = "listaAccelerometro")
    public List<Accelerometro> getAccelerometro() {
        return accelerometro;
    }

    public void setAccelerometro(List<Accelerometro> accelerometro) {
        this.accelerometro = accelerometro;
    }
}

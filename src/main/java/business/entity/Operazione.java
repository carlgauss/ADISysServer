package business.entity;

import utility.SerialClone;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"id", "nome", "nota", "valoreRilevato", "patologia"})
public class Operazione implements IndipendentEntity {
    /**
     *
     */
    @XmlTransient
    private static final long serialVersionUID = 3829368754663286748L;

    private String id;

    private String nome;

    private String nota;
    private ValoreRilevato valoreRilevato;

    private List<Patologia> patologia;

    @XmlElement
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    @XmlElement(nillable = true)
    public String getNota() {
        return nota;
    }

    private static final transient String NEW_LINE_REGEX = "\\r?\\n";

    public void setNota(String nota) {
        if (nota != null) {
            String notaTrimmed = "";
            nota = nota.trim();
            String[] notaLines = nota.split(NEW_LINE_REGEX);
            for (int i = 0; i < notaLines.length; i++) {
                notaTrimmed += notaLines[i].trim();
                if (i < notaLines.length - 1) {
                    notaTrimmed += "\n";
                }
            }
            this.nota = notaTrimmed;
        } else {
            this.nota = "";
        }

    }

    @XmlElement(required = false)
    public ValoreRilevato getValoreRilevato() {
        return SerialClone.clone(valoreRilevato);
    }

    public void setValoreRilevato(ValoreRilevato valoreRilevato) {
        this.valoreRilevato = SerialClone.clone(valoreRilevato);
    }

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElements({
            @XmlElement(name = "patologia")
    })
    @XmlElementWrapper(name = "listaPatologie")
    public List<Patologia> getPatologia() {
        return SerialClone.clone(patologia);
    }

    public void setPatologia(List<Patologia> patologia) {
        this.patologia = SerialClone.clone(patologia);
    }

    private static final String SEPARATOR = " ";

    public String toString() {
        return nome;
    }
}

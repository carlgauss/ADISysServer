package business.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"codice", "nome", "gravita"})
public class Patologia implements Entity {

    private String codice;
    private String nome;
    private int gravita;

    @XmlElement
    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        codice = codice.trim();
        this.codice = codice;
    }

    @XmlElement
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        nome = nome.trim();
        this.nome = nome;
    }

    @XmlElement
    public int getGravita() {
        return gravita;
    }

    public void setGravita(int gravita) {
        this.gravita = gravita;
    }

    @Override
    public boolean equals(Object o) {
        Patologia patologia = (Patologia) o;
        return  (o != null) &&
                codice.equals(patologia.codice);
    }

    @Override
    public int hashCode() {
        return codice.hashCode();
    }
}

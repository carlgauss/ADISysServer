package business.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "nome", "cognome"})
public class Infermiere implements Person {
    /**
     *
     */
    @XmlTransient
    private static final long serialVersionUID = 8182360441844160117L;

    private String id;

    private String nome;

    private String cognome;

    @XmlElement
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        cognome = cognome.trim();
        this.cognome = cognome;
    }
}

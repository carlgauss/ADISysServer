package business.entity;

import javax.xml.bind.annotation.XmlElement;


public abstract class Person implements Entity {
    @XmlElement
    public abstract String getNome();

    public abstract void setNome(String nome);

    @XmlElement
    public abstract String getCognome();

    public abstract void setCognome(String cognome);

    public String toString() {
        return getNome() + " " + getCognome();
    }
}

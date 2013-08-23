package business.entity;

import business.applicationservice.exception.InvalidInfermiereFieldException;

import javax.xml.bind.annotation.XmlElement;


public abstract class Person implements Entity {
    @XmlElement
    public abstract String getNome();

    public abstract void setNome(String nome) throws InvalidInfermiereFieldException, Exception;

    @XmlElement
    public abstract String getCognome();

    public abstract void setCognome(String cognome) throws InvalidInfermiereFieldException, Exception;

    public String toString() {
        return getNome() + " " + getCognome();
    }
}

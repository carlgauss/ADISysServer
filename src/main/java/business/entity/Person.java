package business.entity;

import business.applicationservice.exception.InvalidInfermiereFieldException;
import business.applicationservice.exception.InvalidPazienteFieldException;

import javax.xml.bind.annotation.XmlElement;


public abstract class Person implements Entity {
    @XmlElement
    public abstract String getNome();

    public abstract void setNome(String nome) throws InvalidInfermiereFieldException, InvalidPazienteFieldException;

    @XmlElement
    public abstract String getCognome();

    public abstract void setCognome(String cognome) throws InvalidInfermiereFieldException, InvalidPazienteFieldException;

    public String toString() {
        return getNome() + " " + getCognome();
    }
}

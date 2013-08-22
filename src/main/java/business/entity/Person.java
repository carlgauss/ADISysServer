package business.entity;

import business.applicationservice.exception.InvalidInfermiereFieldException;

import javax.xml.bind.annotation.XmlElement;


public interface Person extends Entity {
    @XmlElement
    String getNome();

    void setNome(String nome) throws InvalidInfermiereFieldException;

    @XmlElement
    String getCognome();

    void setCognome(String cognome) throws InvalidInfermiereFieldException;
}

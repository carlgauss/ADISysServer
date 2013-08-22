package business.entity;

import business.applicationservice.exception.InvalidInfermiereFieldException;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 20/08/13
 * Time: 00:58
 * To change this template use File | Settings | File Templates.
 */
public interface Person extends Entity {
    @XmlElement
    String getNome();

    void setNome(String nome) throws InvalidInfermiereFieldException;

    @XmlElement
    String getCognome();

    void setCognome(String cognome) throws InvalidInfermiereFieldException;
}

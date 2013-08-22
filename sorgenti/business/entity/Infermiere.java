package business.entity;

import business.applicationservice.exception.InvalidInfermiereFieldException;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType (propOrder={"id", "nome", "cognome"})
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
	public void setNome(String nome) throws InvalidInfermiereFieldException {
        nome = nome.trim();
        int length = nome.length();
        boolean isValid = (length >= 3) && (length <= 30);
        if (!isValid) {
            throw new InvalidInfermiereFieldException("invalidNurseName");
        }
        this.nome = nome;
	}
	@XmlElement
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) throws InvalidInfermiereFieldException {
        cognome = cognome.trim();
        int length = cognome.length();
        boolean isValid = (length >= 3) && (length <= 30);
        if (!isValid) {
            throw new InvalidInfermiereFieldException("invalidNurseSurname");
        }
		this.cognome = cognome;
	}
}

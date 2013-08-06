package integration.dao;

import java.util.List;

import business.entity.Intervento;

public class DAOIntervento extends HQSQLDAO<Intervento> {
	
	private static final String INSERT_QUERY = "INSERT INTO Intervento(Citta, CAP, Indirizzo, Data, Ora, PazienteID, InfermiereID) VALUES ('?', '?', '?', '?', '?', ?, ?)";
	private static final String UPDATE_QUERY = "UPDATE Intervento SET Citta = '?', CAP = '?', Indirizzo = '?', Data = '?', Ora = '?', PazienteID = ?, InfermiereID = ? WHERE ID = ?";
	//DELETE_QUERY not implemented
	private static final String READ_QUERY = "SELECT ID, Citta, CAP, Indirizzo, Data, Ora, PazienteID, InfermiereID FROM Intervento where ID = ?";
	private static final String GET_ALL_QUERY = "SELECT ID, Citta, CAP, Indirizzo, Data, Ora, PazienteID, InfermiereID FROM Intervento";
	
	private static final String ID_INTERVENTO_ATTRIBUTE_NAME = "ID";
	private static final String CITTA_INTERVENTO_ATTRIBUTE_NAME = "Citta";
	private static final String CAP_INTERVENTO_ATTRIBUTE_NAME = "CAP";
	private static final String INDIRIZZO_INTERVENTO_ATTRIBUTE_NAME = "Indirizzo";
	private static final String DATA_INTERVENTO_ATTRIBUTE_NAME = "Data";
	private static final String ORA_INTERVENTO_ATTRIBUTE_NAME = "Ora";
	private static final String ID_INFERMIERE_ATTRIBUTE_NAME = "InfermiereID";
	private static final String ID_PAZIENTE_ATTRIBUTE_NAME = "PazienteID";
	

	@Override
	public void create(Intervento entity) {

		
	}

	@Override
	public void update(Intervento entity) {

	}

	@Override
	public void delete(Intervento entity) {
		//Not implemented yet
	}

	@Override
	public Intervento read(String ID) {

		return null;
	}

	@Override
	public List<Intervento> getAll() {

		return null;
	}

}

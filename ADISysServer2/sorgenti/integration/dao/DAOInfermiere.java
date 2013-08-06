package integration.dao;

import java.util.List;

import business.entity.Infermiere;

import static util.QueryStringReplacer.*;

public class DAOInfermiere extends HQSQLDAO<Infermiere> {
	
	private static final String INSERT_QUERY = "INSERT INTO Infermiere(Nome, Cognome) VALUES (?, ?)";
	private static final String UPDATE_QUERY = "UPDATE Infermiere SET Nome = ?, Cognome = ? WHERE ID = ?";
	//DELETE_QUERY not implemented
	private static final String READ_QUERY = "SELECT ID, Nome, Cognome FROM Infermiere where ID = ?";
	private static final String GET_ALL_QUERY = "SELECT ID, Nome, Cognome FROM Infermieri";

	@Override
	public void create(Infermiere entity) {
		String insertQuery = INSERT_QUERY;
		
		String nomeInfermiere = entity.getNome();
		insertQuery = queryReplaceFirst(insertQuery, nomeInfermiere);
		
		String cognomeInfermiere = entity.getNome();
		insertQuery = queryReplaceFirst(insertQuery, cognomeInfermiere);
		
		connector.executeUpdateQuery(insertQuery);
	}

	@Override
	public void update(Infermiere entity) {
		String updateQuery = INSERT_QUERY;
		
		String nomeInfermiere = entity.getNome();
		updateQuery = queryReplaceFirst(updateQuery, nomeInfermiere);
		
		String cognomeInfermiere = entity.getNome();
		updateQuery = queryReplaceFirst(updateQuery, cognomeInfermiere);
		
		connector.executeUpdateQuery(updateQuery);
	}

	@Override
	public void delete(Infermiere entity) {
		//Not implemented yet
	}

	@Override
	public Infermiere read(String ID) {

		return null;
	}

	@Override
	public List<Infermiere> getAll() {

		return null;
	}

}

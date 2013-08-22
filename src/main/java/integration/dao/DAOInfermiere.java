package integration.dao;

import business.applicationservice.exception.InvalidInfermiereFieldException;
import business.entity.Infermiere;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static util.QueryStringReplacer.queryReplaceFirst;

public class DAOInfermiere extends HQSQLDAO<Infermiere> {

    private static final String INSERT_QUERY = "INSERT INTO Infermiere(Nome, Cognome) VALUES ('?', '?')";
    private static final String UPDATE_QUERY = "UPDATE Infermiere SET Nome = '?', Cognome = '?' WHERE ID = ?";
    //DELETE_QUERY not implemented
    private static final String READ_QUERY = "SELECT ID, Nome, Cognome FROM Infermiere where ID = ?";
    private static final String GET_ALL_QUERY = "SELECT ID, Nome, Cognome FROM Infermiere";

    private static final String ID_INFERMIERE_ATTRIBUTE_NAME = "ID";
    private static final String NOME_INFERMIERE_ATTRIBUTE_NAME = "Nome";
    private static final String COGNOME_INFERMIERE_ATTRIBUTE_NAME = "Cognome";

    @Override
    public void create(Infermiere entity) {
        String insertQuery = INSERT_QUERY;

        String nomeInfermiere = entity.getNome();
        insertQuery = queryReplaceFirst(insertQuery, nomeInfermiere);

        String cognomeInfermiere = entity.getCognome();
        insertQuery = queryReplaceFirst(insertQuery, cognomeInfermiere);

        connector.executeUpdateQuery(insertQuery);
    }

    @Override
    public void update(Infermiere entity) {
        String updateQuery = UPDATE_QUERY;

        String nomeInfermiere = entity.getNome();
        updateQuery = queryReplaceFirst(updateQuery, nomeInfermiere);

        String cognomeInfermiere = entity.getCognome();
        updateQuery = queryReplaceFirst(updateQuery, cognomeInfermiere);

        String idInfermiere = entity.getId();
        updateQuery = queryReplaceFirst(updateQuery, idInfermiere);

        connector.executeUpdateQuery(updateQuery);
    }

    @Override
    public void delete(Infermiere entity) {
        //Not implemented yet
    }

    @Override
    public Infermiere read(String ID) {
        String readQuery = READ_QUERY;

        readQuery = queryReplaceFirst(readQuery, ID);

        ResultSet readQueryResultSet = connector.executeReadQuery(readQuery);

        List<Infermiere> elencoInfermieri = createElencoInfermieriBy(readQueryResultSet);

        return elencoInfermieri.get(FIRST);
    }

    @Override
    public List<Infermiere> getAll() {
        String getAllQuery = GET_ALL_QUERY;

        ResultSet getAllQueryResultSet = connector.executeReadQuery(getAllQuery);

        List<Infermiere> elencoInfermieri = createElencoInfermieriBy(getAllQueryResultSet);

        return elencoInfermieri;
    }

    private List<Infermiere> createElencoInfermieriBy(ResultSet resultSet) {
        List<Infermiere> result = new LinkedList<Infermiere>();

        try {
            while (resultSet.next()) {
                Infermiere element = new Infermiere();

                String id = resultSet.getString(ID_INFERMIERE_ATTRIBUTE_NAME);
                element.setId(id);

                String nome = resultSet.getString(NOME_INFERMIERE_ATTRIBUTE_NAME);
                element.setNome(nome.trim());

                String cognome = resultSet.getString(COGNOME_INFERMIERE_ATTRIBUTE_NAME);
                element.setCognome(cognome.trim());

                result.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidInfermiereFieldException e) {
            e.printStackTrace();
        }

        return result;
    }
}

package integration.dao;

import business.entity.Patologia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static integration.QueryStringReplacer.queryReplaceFirst;

public class DAOPatologia extends HQSQLDAO<Patologia> {

    private static final String INSERT_QUERY = "INSERT INTO Patologia(Codice, Nome, Gravita) VALUES ('?', '?', ?)";
    private static final String UPDATE_QUERY = "UPDATE Patologia SET Nome = '?', Gravita = ? WHERE Codice = '?'";
    //DELETE_QUERY not implemented
    private static final String READ_QUERY = "SELECT Codice, Nome, Gravita FROM Patologia WHERE Codice = '?'";
    private static final String GET_ALL_QUERY = "SELECT Codice, Nome, Gravita FROM Patologia";

    private static final String CODICE_PATOLOGIA_ATTRIBUTE_NAME = "Codice";
    private static final String NOME_PATOLOGIA_ATTRIBUTE_NAME = "Nome";
    private static final String GRAVITA_PATOLOGIA_ATTRIBUTE_NAME = "Gravita";


    @Override
    public void create(Patologia patologia) {
        String insertQuery = INSERT_QUERY;

        String codicePatologia = patologia.getCodice();
        insertQuery = queryReplaceFirst(insertQuery, codicePatologia);

        String nomePatologia = patologia.getNome();
        insertQuery = queryReplaceFirst(insertQuery, nomePatologia);

        String gravitaPatologia = String.valueOf(patologia.getGravita());
        insertQuery = queryReplaceFirst(insertQuery, gravitaPatologia);

        connector.executeUpdateQuery(insertQuery);
    }

    @Override
    public void update(Patologia patologia) {
        String updateQuery = UPDATE_QUERY;

        String nomePatologia = patologia.getNome();
        updateQuery = queryReplaceFirst(updateQuery, nomePatologia);

        String gravitaPatologia = String.valueOf(patologia.getGravita());
        updateQuery = queryReplaceFirst(updateQuery, gravitaPatologia);

        String codicePatologia = patologia.getCodice();
        updateQuery = queryReplaceFirst(updateQuery, codicePatologia);

        connector.executeUpdateQuery(updateQuery);
    }

    @Override
    public void delete(String codice) {
        // Not implemented yet
    }

    @Override
    public Patologia read(String codice) {
        String readQuery = READ_QUERY;

        readQuery = queryReplaceFirst(readQuery, codice);

        ResultSet readQueryResultSet = connector.executeReadQuery(readQuery);

        List<Patologia> elencoPatologie = createElencoPatologieBy(readQueryResultSet);

        Patologia patologia = null;

        if(!elencoPatologie.isEmpty()) {
            patologia = elencoPatologie.get(FIRST);
        }

        return patologia;
    }

    @Override
    public List<Patologia> getAll() {
        String readQuery = GET_ALL_QUERY;

        ResultSet readQueryResultSet = connector.executeReadQuery(readQuery);

        return createElencoPatologieBy(readQueryResultSet);
    }

    private List<Patologia> createElencoPatologieBy(ResultSet resultSet) {
        List<Patologia> result = new LinkedList<Patologia>();

        try {
            while (resultSet.next()) {
                Patologia element = new Patologia();

                String codice = resultSet.getString(CODICE_PATOLOGIA_ATTRIBUTE_NAME);
                element.setCodice(codice.trim());

                String nome = resultSet.getString(NOME_PATOLOGIA_ATTRIBUTE_NAME);
                element.setNome(nome.trim());

                int gravita = resultSet.getInt(GRAVITA_PATOLOGIA_ATTRIBUTE_NAME);
                element.setGravita(gravita);

                result.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}

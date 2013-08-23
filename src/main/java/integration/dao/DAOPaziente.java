package integration.dao;

import business.applicationservice.exception.InvalidPazienteFieldException;
import business.entity.Paziente;
import org.joda.time.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static utility.QueryStringReplacer.queryReplaceFirst;

public class DAOPaziente extends HQSQLDAO<Paziente> {

    private static final String INSERT_QUERY = "INSERT INTO Paziente(Nome, Cognome, Data) VALUES ('?', '?', '?')";
    private static final String INSERT_NUM_CELL_QUERY = "INSERT INTO Cellulare(PazienteID, Numero) VALUES (?, '?')";

    private static final String UPDATE_QUERY = "UPDATE Paziente SET Nome = '?', Cognome = '?', Data = '?' WHERE ID = ?";
    private static final String DELETE_NUM_CELL_QUERY = "DELETE FROM Cellulare WHERE PazienteID = ?";

    //DELETE_QUERY not implemented

    private static final String READ_QUERY = "SELECT ID, Nome, Cognome, Data FROM Paziente where ID = ?";
    private static final String GET_ALL_QUERY = "SELECT ID, Nome, Cognome, Data FROM Paziente";

    private static final String GET_NUM_CELL_QUERY = "SELECT Numero, PazienteID FROM Cellulare WHERE PazienteID = ?";

    private static final String ID_PAZIENTE_ATTRIBUTE_NAME = "ID";
    private static final String NOME_PAZIENTE_ATTRIBUTE_NAME = "Nome";
    private static final String COGNOME_PAZIENTE_ATTRIBUTE_NAME = "Cognome";
    private static final String DATA_PAZIENTE_ATTRIBUTE_NAME = "Data";

    private static final String NUM_CELL_ATTRIBUTE_NAME = "Numero";

    @Override
    public void create(Paziente entity) {
        String insertQuery = INSERT_QUERY;

        String nomePaziente = entity.getNome();
        insertQuery = queryReplaceFirst(insertQuery, nomePaziente);

        String cognomePaziente = entity.getCognome();
        insertQuery = queryReplaceFirst(insertQuery, cognomePaziente);

        LocalDate dataPaziente = entity.getData();
        String dataPazienteString = dataPaziente.toString();
        insertQuery = queryReplaceFirst(insertQuery, dataPazienteString);

        ResultSet insertedRow = connector.executeUpdateQuery(insertQuery);

        List<String> numCellList = entity.getNumeroCellulare();

        String insertedPazienteID = getIDPazienteBy(insertedRow);
        insertNumCell(insertedPazienteID, numCellList);
    }

    @Override
    public void update(Paziente entity) {
        String updateQuery = UPDATE_QUERY;

        String nomePaziente = entity.getNome();
        updateQuery = queryReplaceFirst(updateQuery, nomePaziente);

        String cognomePaziente = entity.getCognome();
        updateQuery = queryReplaceFirst(updateQuery, cognomePaziente);

        LocalDate dataPaziente = entity.getData();
        String dataPazienteString = dataPaziente.toString();
        updateQuery = queryReplaceFirst(updateQuery, dataPazienteString);

        String idPaziente = entity.getId();
        updateQuery = queryReplaceFirst(updateQuery, idPaziente);

        connector.executeUpdateQuery(updateQuery);

        deleteNumCell(idPaziente);

        List<String> updatedNumCellList = entity.getNumeroCellulare();
        insertNumCell(idPaziente, updatedNumCellList);
    }

    @Override
    public void delete(Paziente entity) {
        //not implemented yet
    }

    @Override
    public Paziente read(String ID) {
        String readQuery = READ_QUERY;

        readQuery = queryReplaceFirst(readQuery, ID);

        ResultSet readQueryResultSet = connector.executeReadQuery(readQuery);

        List<Paziente> elencoPazienti = createElencoPazientiBy(readQueryResultSet);

        return elencoPazienti.get(FIRST);
    }

    @Override
    public List<Paziente> getAll() {
        String getAllQuery = GET_ALL_QUERY;

        ResultSet getAllQueryResultSet = connector.executeReadQuery(getAllQuery);

        List<Paziente> elencoPazienti = createElencoPazientiBy(getAllQueryResultSet);

        return elencoPazienti;
    }

    private List<Paziente> createElencoPazientiBy(ResultSet resultSet) {
        List<Paziente> result = new LinkedList<Paziente>();

        try {
            while (resultSet.next()) {
                Paziente element = new Paziente();

                String id = resultSet.getString(ID_PAZIENTE_ATTRIBUTE_NAME);
                element.setId(id.trim());

                String nome = resultSet.getString(NOME_PAZIENTE_ATTRIBUTE_NAME);
                element.setNome(nome.trim());

                String cognome = resultSet.getString(COGNOME_PAZIENTE_ATTRIBUTE_NAME);
                element.setCognome(cognome.trim());

                String dataString = resultSet.getString(DATA_PAZIENTE_ATTRIBUTE_NAME);
                LocalDate data = LocalDate.parse(dataString);
                element.setData(data);

                List<String> numCellList = getNumCell(id);
                element.setNumeroCellulare(numCellList);

                result.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<String> getNumCell(String id) throws SQLException {
        List<String> numCellList = new LinkedList<String>();

        String getNumCellQuery = GET_NUM_CELL_QUERY;

        getNumCellQuery = queryReplaceFirst(getNumCellQuery, id);

        ResultSet numCellResultSet = connector.executeReadQuery(getNumCellQuery);

        while (numCellResultSet.next()) {
            String numCell = numCellResultSet.getNString(NUM_CELL_ATTRIBUTE_NAME);
            numCellList.add(numCell.trim());
        }

        return numCellList;
    }

    private void insertNumCell(String id, List<String> numCellList) {
        String insertNumCellQuery = INSERT_NUM_CELL_QUERY;
        insertNumCellQuery = queryReplaceFirst(insertNumCellQuery, id);

        String currentInsertNumCellQuery;

        for (String numCell : numCellList) {
            currentInsertNumCellQuery = queryReplaceFirst(insertNumCellQuery, numCell);

            connector.executeUpdateQuery(currentInsertNumCellQuery);
        }
    }

    private void deleteNumCell(String id) {
        String deleteNumCellQuery = DELETE_NUM_CELL_QUERY;
        deleteNumCellQuery = queryReplaceFirst(deleteNumCellQuery, id);

        connector.executeUpdateQuery(deleteNumCellQuery);
    }

    private String getIDPazienteBy(ResultSet resultSet) {
        String id = null;
        try {
            resultSet.next();
            id = resultSet.getString(ID_PAZIENTE_ATTRIBUTE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}

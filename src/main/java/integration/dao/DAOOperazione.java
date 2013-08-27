package integration.dao;

import business.entity.Operazione;
import business.transfer.OperazioneTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static integration.QueryStringReplacer.queryReplaceFirst;

public class DAOOperazione extends HQSQLDAO<Operazione> {

    private static final String INSERT_QUERY = "INSERT INTO Operazione(Nome, Nota, InterventoID) VALUES ('?', '?', ?)";
    //UPDATE_QUERY not implemented
    //DELETE_QUERY not implemented
    //READ_QUERY not implemented
    //GET_ALL_QUERY not implemented
    private static final String DELETE_BY_INTERVENTO_QUERY = "DELETE FROM Operazione WHERE InterventoID = ?";
    private static final String GET_BY_INTERVENTO_QUERY = "SELECT ID, Nome, Nota FROM Operazione WHERE InterventoID = ?";

    private static final String ID_OPERAZIONE_ATTRIBUTE_NAME = "ID";
    private static final String NOME_OPERAZIONE_ATTRIBUTE_NAME = "Nome";
    private static final String NOTA_OPERAZIONE_ATTRIBUTE_NAME = "Nota";

    @Override
    public void create(Operazione entity) {
        String insertQuery = INSERT_QUERY;

        OperazioneTO operazioneTO = (OperazioneTO) entity;

        String nomeOperazione = operazioneTO.getNome();
        insertQuery = queryReplaceFirst(insertQuery, nomeOperazione);

        String notaOperazione = operazioneTO.getNota();
        if (notaOperazione == null) {
            notaOperazione = "";
        }
        insertQuery = queryReplaceFirst(insertQuery, notaOperazione);

        String idIntervento = operazioneTO.getIdIntervento();
        insertQuery = queryReplaceFirst(insertQuery, idIntervento);

        connector.executeUpdateQuery(insertQuery);
    }

    @Override
    public void update(Operazione entity) {
        // Not implemented yet

    }

    @Override
    public void delete(String ID) {
        // Not implemented yet
    }

    @Override
    public Operazione read(String ID) {
        // Not implemented yet
        return null;
    }

    @Override
    public List<Operazione> getAll() {
        // Not implemented yet
        return null;
    }

    public void insertListaOperazioneByIntervento(List<Operazione> operazioneList, String idIntervento) {
        for (Operazione operazione : operazioneList) {
            OperazioneTO operazioneTO = new OperazioneTO(operazione);
            operazioneTO.setIdIntervento(idIntervento);
            create(operazioneTO);
        }
    }

    public void deleteByIdIntervento(String idIntervento) {
        String deleteByInterventoQuery = DELETE_BY_INTERVENTO_QUERY;

        deleteByInterventoQuery = queryReplaceFirst(deleteByInterventoQuery, idIntervento);

        connector.executeUpdateQuery(deleteByInterventoQuery);
    }

    public List<Operazione> getByIdIntervento(String idIntervento) {
        String getByInterventoQuery = GET_BY_INTERVENTO_QUERY;

        getByInterventoQuery = queryReplaceFirst(getByInterventoQuery, idIntervento);

        ResultSet operazioneResultSet = connector.executeReadQuery(getByInterventoQuery);

        return createElencoOperazioniBy(operazioneResultSet);
    }

    private List<Operazione> createElencoOperazioniBy(ResultSet resultSet) {
        List<Operazione> result = new LinkedList<Operazione>();

        try {
            while (resultSet.next()) {
                Operazione element = new Operazione();

                String id = resultSet.getString(ID_OPERAZIONE_ATTRIBUTE_NAME);
                element.setId(id);

                String nome = resultSet.getString(NOME_OPERAZIONE_ATTRIBUTE_NAME);
                element.setNome(nome.trim());

                String nota = resultSet.getString(NOTA_OPERAZIONE_ATTRIBUTE_NAME);
                element.setNota(nota.trim());

                result.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}

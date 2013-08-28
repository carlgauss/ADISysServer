package integration.dao;

import business.entity.Intervento;
import business.entity.Operazione;
import business.entity.Patologia;
import business.transfer.OperazioneTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    private static final String INSERT_CURA = "INSERT INTO Cura(SofferenzaPazienteID, SofferenzaPatologiaCodice, OperazioneID) VALUES (?, '?', ?)";
    private static final String SELECT_CURA = "SELECT SofferenzaPazienteID, SofferenzaPatologiaCodice, OperazioneID FROM Cura WHERE OperazioneID = ?";

    private static final String SOFFERENZA_PATOLOGIA_CODICE_CURA_ATTRIBUTE_NAME = "SofferenzaPatologiaCodice";

    private DAO<Patologia> daoPatologia = DAOFactory.getDAOEntity("DAOPatologia");

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

        ResultSet idList = connector.executeUpdateQuery(insertQuery);

        String idOperazione = null;

        try {
            while (idList.next()) {
                idOperazione = idList.getString(ID_OPERAZIONE_ATTRIBUTE_NAME);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        insertCura(idOperazione, operazioneTO.getIdPaziente(), operazioneTO.getPatologia());
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

    public void insertListaOperazioneByIntervento(List<Operazione> operazioneList, Intervento intervento) {
        for (Operazione operazione : operazioneList) {
            OperazioneTO operazioneTO = new OperazioneTO(operazione);
            operazioneTO.setIdIntervento(intervento.getId());
            operazioneTO.setIdPaziente(intervento.getPaziente().getId());
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

                List<Patologia> patologia = getPatologia(id);
                element.setPatologia(patologia);

                result.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void insertCura(String operazioneID, String pazienteID, List<Patologia> patologiaList) {
        for (Patologia patologia : patologiaList) {
            String insertCura = INSERT_CURA;

            insertCura = queryReplaceFirst(insertCura, pazienteID);
            insertCura = queryReplaceFirst(insertCura, patologia.getCodice());
            insertCura = queryReplaceFirst(insertCura, operazioneID);

            connector.executeUpdateQuery(insertCura);
        }
    }

    private List<Patologia> getPatologia(String operazioneID) {
        List<Patologia> patologiaList = new ArrayList<>();

        String selectCura = SELECT_CURA;
        selectCura = queryReplaceFirst(selectCura, operazioneID);

        ResultSet resultSet = connector.executeReadQuery(selectCura);

        try {
            while (resultSet.next()) {
                String codicePatologia = resultSet.getString(SOFFERENZA_PATOLOGIA_CODICE_CURA_ATTRIBUTE_NAME);
                Patologia patologia = daoPatologia.read(codicePatologia);
                patologiaList.add(patologia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patologiaList;
    }
}

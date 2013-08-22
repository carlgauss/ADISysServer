package integration.dao;

import business.entity.Infermiere;
import business.entity.Intervento;
import business.entity.Operazione;
import business.entity.Paziente;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import static util.QueryStringReplacer.queryReplaceFirst;

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

    private static final String DAO_PAZIENTE = "DAOPaziente";
    private static final String DAO_INFERMIERE = "DAOInfermiere";

    private DAOOperazione daoOperazione = DAOFactory.buildInstance();

    @Override
    public void create(Intervento entity) {
        String insertQuery = INSERT_QUERY;

        String cittaIntervento = entity.getCitta();
        insertQuery = queryReplaceFirst(insertQuery, cittaIntervento);

        String capIntervento = entity.getCap();
        insertQuery = queryReplaceFirst(insertQuery, capIntervento);

        String indirizzoIntervento = entity.getIndirizzo();
        insertQuery = queryReplaceFirst(insertQuery, indirizzoIntervento);

        LocalDate dataIntervento = entity.getData();
        String dataInterventoString = dataIntervento.toString();
        insertQuery = queryReplaceFirst(insertQuery, dataInterventoString);

        LocalTime oraIntervento = entity.getOra();
        String oraInterventoString = oraIntervento.toString();
        insertQuery = queryReplaceFirst(insertQuery, oraInterventoString);

        Paziente pazienteIntervento = entity.getPaziente();
        String idPaziente = pazienteIntervento.getId();
        insertQuery = queryReplaceFirst(insertQuery, idPaziente);

        Infermiere infermiereIntervento = entity.getInfermiere();
        String idInfermiere = infermiereIntervento.getId();
        insertQuery = queryReplaceFirst(insertQuery, idInfermiere);

        ResultSet insertedOperazioniResultSet = connector.executeUpdateQuery(insertQuery);
        String idIntervento = getIDInterventoBy(insertedOperazioniResultSet);

        List<Operazione> operazioneList = entity.getOperazione();

        daoOperazione.insertListaOperazioneByIntervento(operazioneList, idIntervento);
    }

    @Override
    public void update(Intervento entity) {
        String updateQuery = UPDATE_QUERY;

        String cittaIntervento = entity.getCitta();
        updateQuery = queryReplaceFirst(updateQuery, cittaIntervento);

        String capIntervento = entity.getCap();
        updateQuery = queryReplaceFirst(updateQuery, capIntervento);

        String indirizzoIntervento = entity.getIndirizzo();
        updateQuery = queryReplaceFirst(updateQuery, indirizzoIntervento);

        LocalDate dataIntervento = entity.getData();
        String dataInterventoString = dataIntervento.toString();
        updateQuery = queryReplaceFirst(updateQuery, dataInterventoString);

        LocalTime oraIntervento = entity.getOra();
        String oraInterventoString = oraIntervento.toString();
        updateQuery = queryReplaceFirst(updateQuery, oraInterventoString);

        Paziente pazienteIntervento = entity.getPaziente();
        String idPaziente = pazienteIntervento.getId();
        updateQuery = queryReplaceFirst(updateQuery, idPaziente);

        Infermiere infermiereIntervento = entity.getInfermiere();
        String idInfermiere = infermiereIntervento.getId();
        updateQuery = queryReplaceFirst(updateQuery, idInfermiere);

        String idIntervento = entity.getId();
        updateQuery = queryReplaceFirst(updateQuery, idIntervento);

        connector.executeUpdateQuery(updateQuery);

        daoOperazione.deleteByIdIntervento(idIntervento);
        List<Operazione> operazioneList = entity.getOperazione();

        daoOperazione.insertListaOperazioneByIntervento(operazioneList, idIntervento);
    }

    @Override
    public void delete(Intervento entity) {
        //Not implemented yet
    }

    @Override
    public Intervento read(String ID) {
        String readQuery = READ_QUERY;

        readQuery = queryReplaceFirst(readQuery, ID);

        ResultSet readQueryResultSet = connector.executeReadQuery(readQuery);

        List<Intervento> elencoInterventi = createElencoInterventiBy(readQueryResultSet);

        return elencoInterventi.get(FIRST);
    }

    @Override
    public List<Intervento> getAll() {
        String readQuery = GET_ALL_QUERY;

        ResultSet readQueryResultSet = connector.executeReadQuery(readQuery);

        List<Intervento> elencoInterventi = createElencoInterventiBy(readQueryResultSet);

        return elencoInterventi;
    }

    private List<Intervento> createElencoInterventiBy(ResultSet resultSet) {
        List<Intervento> result = new LinkedList<Intervento>();

        DAO<Paziente> daoPaziente = DAOFactory.buildInstance(DAO_PAZIENTE);
        DAO<Infermiere> daoInfermiere = DAOFactory.buildInstance(DAO_INFERMIERE);

        try {
            while (resultSet.next()) {
                Intervento element = new Intervento();

                String id = resultSet.getString(ID_INTERVENTO_ATTRIBUTE_NAME);
                element.setId(id);

                String citta = resultSet.getString(CITTA_INTERVENTO_ATTRIBUTE_NAME);
                element.setCitta(citta.trim());

                String cap = resultSet.getString(CAP_INTERVENTO_ATTRIBUTE_NAME);
                element.setCap(cap.trim());

                String indirizzo = resultSet.getString(INDIRIZZO_INTERVENTO_ATTRIBUTE_NAME);
                element.setIndirizzo(indirizzo.trim());

                String dataString = resultSet.getString(DATA_INTERVENTO_ATTRIBUTE_NAME);
                LocalDate data = LocalDate.parse(dataString);
                element.setData(data);

                String oraString = resultSet.getString(ORA_INTERVENTO_ATTRIBUTE_NAME);
                LocalTime ora = LocalTime.parse(oraString);
                element.setOra(ora);

                String idPaziente = resultSet.getString(ID_PAZIENTE_ATTRIBUTE_NAME);
                Paziente paziente = daoPaziente.read(idPaziente);
                element.setPaziente(paziente);

                String idInfermiere = resultSet.getString(ID_INFERMIERE_ATTRIBUTE_NAME);
                Infermiere infermiere = daoInfermiere.read(idInfermiere);
                element.setInfermiere(infermiere);

                List<Operazione> operazioneList = daoOperazione.getByIdIntervento(id);
                element.setOperazione(operazioneList);

                result.add(element);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private String getIDInterventoBy(ResultSet resultSet) {
        String id = null;
        try {
            resultSet.next();
            id = resultSet.getString(ID_INTERVENTO_ATTRIBUTE_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
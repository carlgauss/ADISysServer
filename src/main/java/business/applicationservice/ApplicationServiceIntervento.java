package business.applicationservice;

import business.applicationservice.checker.Checker;
import business.applicationservice.checker.CheckerFactory;
import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidInterventoFieldException;
import business.entity.Infermiere;
import business.entity.Intervento;
import business.entity.Operazione;
import business.entity.Paziente;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import presentation.controller.ApplicationService;
import utility.DateConverter;
import utility.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ApplicationServiceIntervento implements ApplicationService, CRUG<Intervento> {

    private DAO<Intervento> daoIntervento = DAOFactory.getDAOEntity("DAOIntervento");
    private Checker checker = CheckerFactory.buildInstance(Intervento.class);

    public void create(Parameter parameter) throws CommonException {
        Intervento intervento = populate(parameter);

        daoIntervento.create(intervento);
    }

    public void update(Parameter parameter) throws CommonException {
        Intervento intervento = populate(parameter);

        String id = (String) parameter.getValue("id");
        intervento.setId(id);

        Intervento interventoBeforeEdit = daoIntervento.read(id);
        interventoBeforeEdit.setEditable(InterventoDurationEditChecker.checkInterventoEditable(interventoBeforeEdit));

        if (interventoBeforeEdit.isEditable()) {
            daoIntervento.update(intervento);
        } else {
            throw new InvalidInterventoFieldException("inconsistentInterventionDateTime");
        }
    }

    public Intervento read(Parameter parameter) {
        String id = (String) parameter.getValue("id");

        Intervento intervento = daoIntervento.read(id);

        intervento.setEditable(InterventoDurationEditChecker.checkInterventoEditable(intervento));

        return intervento;
    }

    public List<Intervento> getAll(Parameter parameter) {
        List<Intervento> interventoList = daoIntervento.getAll();

        for (Intervento intervento : interventoList) {
            intervento.setEditable(InterventoDurationEditChecker.checkInterventoEditable(intervento));
        }

        return interventoList;
    }

    public void delete(Parameter parameter) throws CommonException {
        String id = (String) parameter.getValue("id");

        Intervento intervento = daoIntervento.read(id);
        intervento.setEditable(InterventoDurationEditChecker.checkInterventoEditable(intervento));

        if (intervento.isEditable()) {
            daoIntervento.delete(id);
        } else {
            throw new InvalidInterventoFieldException("inconsistentUndoIntervention");
        }
    }

    private Intervento populate(Parameter parameter) throws CommonException {
        String citta = (String) parameter.getValue("citta");
        String cap = (String) parameter.getValue("cap");
        String indirizzo = (String) parameter.getValue("indirizzo");
        String dataString = (String) parameter.getValue("data");
        String oraString = (String) parameter.getValue("ora");
        Object infermiereObject = parameter.getValue("infermiere");
        Object pazienteObject = parameter.getValue("paziente");
        List<Operazione> operazione = (List<Operazione>) parameter.getValue("operazione");

        dataString = dataString.trim();
        oraString = oraString.trim();

        List<Object> interventoValues = new ArrayList<>();
        interventoValues.add(citta);
        interventoValues.add(cap);
        interventoValues.add(indirizzo);
        interventoValues.add(dataString);
        interventoValues.add(oraString);
        interventoValues.add(infermiereObject);
        interventoValues.add(pazienteObject);
        interventoValues.add(operazione);

        checker.check(interventoValues);

        Intervento intervento = new Intervento();

        LocalDate data = LocalDate.parse(dataString, DateConverter.NORMAL_DATE_FORMAT);
        LocalTime ora = LocalTime.parse(oraString, DateConverter.EUROPEAN_TIME_FORMAT);

        Infermiere infermiere = (Infermiere) infermiereObject;
        Paziente paziente = (Paziente) pazienteObject;

        intervento.setCitta(citta);
        intervento.setCap(cap);
        intervento.setIndirizzo(indirizzo);
        intervento.setData(data);
        intervento.setOra(ora);
        intervento.setInfermiere(infermiere);
        intervento.setPaziente(paziente);
        intervento.setOperazione(operazione);

        return intervento;
    }

    public List<Intervento> queryIntervento(Parameter parameter) {
        List<Intervento> result = new ArrayList<>();

        List<Intervento> interventoList = (List<Intervento>) parameter.getValue("intervento");

        String infermiereSearch = (String) parameter.getValue("infermiere");
        String dataSearch = (String) parameter.getValue("data");

        for (Intervento intervento : interventoList) {
            Infermiere infermiere = intervento.getInfermiere();
            String infermiereName = infermiere.getNome() + " " + infermiere.getCognome();
            String dataString = intervento.getData().toString(DateConverter.NORMAL_DATE_FORMAT);

            boolean containsInfermiere = infermiereName.toLowerCase().contains(infermiereSearch.toLowerCase());
            boolean containsData = dataString.contains(dataSearch);

            if (containsInfermiere && containsData) {
                result.add(intervento);
            }
        }

        return result;
    }
}

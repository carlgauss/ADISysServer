package business.applicationservice;

import business.applicationservice.exception.EmptyPianificazioneException;
import business.entity.Infermiere;
import business.entity.Intervento;
import business.transfer.PianificazioneElement;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import integration.xml.DAOPianificazione;
import integration.xml.DAOPianificazioneFactory;
import org.xml.sax.SAXException;
import presentation.controller.ApplicationService;
import business.transfer.Parameter;

import java.util.*;

public class ApplicationServicePianificazione implements ApplicationService {

    public void export(Parameter parameter) throws SAXException, EmptyPianificazioneException {
        List<Intervento> pianificazione = (List<Intervento>) parameter.getValue("pianificazione");
        Infermiere infermiere = (Infermiere) parameter.getValue("infermiere");

        if (pianificazione.isEmpty()) {
            throw new EmptyPianificazioneException();
        }

        DAOPianificazione daoPianificazione = DAOPianificazioneFactory.getPianificazione(infermiere);

        daoPianificazione.export(pianificazione);
    }

    public List<PianificazioneElement> showPianificazione(Parameter parameter) {
        DAO<Intervento> daoIntervento = DAOFactory.getDAOEntity("DAOIntervento");

        PianificazioneEsportazioneMap pianificazioneMap = new PianificazioneEsportazioneMap();

        List<Intervento> interventoList = daoIntervento.getAll();

        for (Intervento intervento : interventoList) {
            Infermiere infermiere = intervento.getInfermiere();

            if (InterventoDurationEditChecker.checkDaily(intervento)) {
                pianificazioneMap.addIntervento(infermiere, intervento);
            }
        }

        return pianificazioneMap.getList();
    }


}

class PianificazioneEsportazioneMap extends HashMap<String, List<Intervento>> {
    public void addIntervento(Infermiere infermiere, Intervento intervento) {
        if (containsKey(infermiere.getId())) {
            get(infermiere.getId()).add(intervento);
        } else {
            List<Intervento> interventoList = new LinkedList<>();
            interventoList.add(intervento);
            put(infermiere.getId(), interventoList);
            infermiereHashMap.put(infermiere.getId(), infermiere);
        }
    }

    public List<PianificazioneElement> getList() {
        List<PianificazioneElement> list = new ArrayList<>();

        for (Map.Entry<String, List<Intervento>> elem : entrySet()) {
            PianificazioneElement pianificazioneElement = new PianificazioneElement();

            Infermiere infermiere = infermiereHashMap.get(elem.getKey());
            pianificazioneElement.setInfermiere(infermiere);
            pianificazioneElement.setPianificazione(elem.getValue());

            list.add(pianificazioneElement);
        }

        return list;
    }

    private Map<String, Infermiere> infermiereHashMap = new HashMap<>();
}

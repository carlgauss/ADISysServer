package business.applicationservice;

import business.entity.Intervento;
import integration.xml.DAOPianificazione;
import integration.xml.DAOPianificazioneFactory;
import org.xml.sax.SAXException;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.util.List;

public class ApplicationServicePianificazione implements ApplicationService {

    public void export(Parameter parameter) throws SAXException {
        List<Intervento> pianificazione = (List<Intervento>) parameter.getValue("pianificazione");

        DAOPianificazione daoPianificazione = DAOPianificazioneFactory.buildInstance();

        daoPianificazione.export(pianificazione);
    }

}

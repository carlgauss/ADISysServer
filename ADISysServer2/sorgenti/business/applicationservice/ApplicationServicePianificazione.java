package business.applicationservice;

import integration.xml.DAOPianificazione;
import integration.xml.DAOPianificazioneFactory;

import java.util.List;

import org.xml.sax.SAXException;

import business.entity.Intervento;
import presentation.controller.ApplicationService;
import util.Parameter;

public class ApplicationServicePianificazione implements ApplicationService {
	
	public void export(Parameter parameter) throws SAXException {
		List<Intervento> pianificazione = (List<Intervento>) parameter.getValue("pianificazione");
		
		DAOPianificazione daoPianificazione = DAOPianificazioneFactory.buildInstance();
		
		daoPianificazione.export(pianificazione);
	}

}

package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.List;

import business.entity.Intervento;
import presentation.controller.ApplicationService;
import util.Parameter;

public class ApplicationServiceIntervento implements ApplicationService, CRUG<Intervento> {
	
	private DAO<Intervento> daoIntervento = DAOFactory.buildInstance("DAOPIntervento");

    public void create(Parameter parameter) {
    	Intervento intervento = (Intervento) parameter.getValue("intervento");
    	
    	daoIntervento.create(intervento);
    }

    public void update(Parameter parameter) {	
    	Intervento intervento = (Intervento) parameter.getValue("intervento");
    	
    	daoIntervento.update(intervento);
    }

    public Intervento read(Parameter parameter) {
    	String id = (String) parameter.getValue("id");
    	
    	return daoIntervento.read(id);
    }

    public List<Intervento> getAll(Parameter parameter) {
    	return daoIntervento.getAll();
    }

}

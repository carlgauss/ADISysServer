package business.applicationservice;

import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.List;

import business.entity.Paziente;
import presentation.controller.ApplicationService;
import util.Parameter;

public class ApplicationServicePaziente implements ApplicationService, CRUG<Paziente> {
	
	private DAO<Paziente> daoInfermiere = DAOFactory.buildInstance("DAOPaziente");

    public void create(Parameter parameter) {
    	//return null;
    }

    public void update(Parameter parameter) {	
    	//return null;
    }

    public Paziente read(Parameter parameter) {
	return null;
    }

    public List<Paziente> getAll(Parameter parameter) {
	return null;
    }

}

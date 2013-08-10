package business.applicationservice;

import java.util.List;

import integration.dao.DAO;
import integration.dao.DAOFactory;
import business.entity.Infermiere;
import presentation.controller.ApplicationService;
import util.Parameter;
//TODO tutti i metodi
public class ApplicationServiceInfermiere implements ApplicationService, CRUG<Infermiere> {

	private DAO<Infermiere> daoInfermiere = DAOFactory.buildInstance("DAOInfermiere");
	
    public void create(Parameter parameter) {
    	Infermiere infermiere = new Infermiere();
    	
    	String nome = (String) parameter.getValue("nome");
    	infermiere.setNome(nome);
    	
    	String cognome = (String) parameter.getValue("cognome");
    	infermiere.setCognome(cognome);
    	
    	daoInfermiere.create(infermiere);
    }

    public void update(Parameter parameter) {	
    	Infermiere infermiere = new Infermiere();
    	
    	String id = (String) parameter.getValue("id");
    	infermiere.setId(id);
    	
    	String nome = (String) parameter.getValue("nome");
    	infermiere.setNome(nome);
    	
    	String cognome = (String) parameter.getValue("cognome");
    	infermiere.setCognome(cognome);
    	
    	daoInfermiere.update(infermiere);
    }

    public Infermiere read(Parameter parameter) {
    	String id = (String) parameter.getValue("id");
    	
    	return daoInfermiere.read(id);
    }

    public List<Infermiere> getAll(Parameter parameter) {
    	return daoInfermiere.getAll();
    }

}

package business.applicationservice;

import business.applicationservice.exception.InvalidInfermiereFieldException;
import business.entity.Infermiere;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import presentation.controller.ApplicationService;
import util.Parameter;

import java.util.List;

//TODO tutti i metodi
public class ApplicationServiceInfermiere implements ApplicationService, CRUG<Infermiere> {

    private DAO<Infermiere> daoInfermiere = DAOFactory.buildInstance("DAOInfermiere");

    public void create(Parameter parameter) throws InvalidInfermiereFieldException {
        Infermiere infermiere = populate(parameter);

        daoInfermiere.create(infermiere);
    }

    public void update(Parameter parameter) throws InvalidInfermiereFieldException {
        Infermiere infermiere = populate(parameter);

        String id = (String) parameter.getValue("id");
        infermiere.setId(id);

        daoInfermiere.update(infermiere);
    }

    public Infermiere read(Parameter parameter) {
        String id = (String) parameter.getValue("id");

        return daoInfermiere.read(id);
    }

    public List<Infermiere> getAll(Parameter parameter) {
        return daoInfermiere.getAll();
    }


    private Infermiere populate(Parameter parameter) throws InvalidInfermiereFieldException {
        Infermiere infermiere = new Infermiere();

        String nome = (String) parameter.getValue("nome");
        infermiere.setNome(nome);

        String cognome = (String) parameter.getValue("cognome");
        infermiere.setCognome(cognome);

        return infermiere;
    }
}

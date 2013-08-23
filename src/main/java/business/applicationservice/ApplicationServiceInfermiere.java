package business.applicationservice;

import business.applicationservice.checker.Checker;
import business.applicationservice.checker.CheckerFactory;
import business.applicationservice.exception.InvalidInfermiereFieldException;
import business.entity.Infermiere;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.util.List;

//TODO tutti i metodi
public class ApplicationServiceInfermiere implements ApplicationService, CRUG<Infermiere> {

    private DAO<Infermiere> daoInfermiere = DAOFactory.getDAOEntity("DAOInfermiere");

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
        Checker checker = CheckerFactory.buildInstance(Infermiere.class);

        String nome = (String) parameter.getValue("nome");
        String cognome = (String) parameter.getValue("cognome");

        Infermiere infermiere = new Infermiere();


        infermiere.setNome(nome);


        infermiere.setCognome(cognome);

        nome = infermiere.getNome();
        cognome = infermiere.getCognome();

        return infermiere;
    }
}

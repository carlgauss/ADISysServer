package business.applicationservice;

import business.entity.Paziente;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.util.List;

public class ApplicationServicePaziente implements ApplicationService, CRUG<Paziente> {

    private DAO<Paziente> daoPaziente = DAOFactory.getDAOEntity("DAOPaziente");

    public void create(Parameter parameter) {
        Paziente paziente = (Paziente) parameter.getValue("paziente");

        daoPaziente.create(paziente);
    }

    public void update(Parameter parameter) {
        Paziente paziente = (Paziente) parameter.getValue("paziente");

        daoPaziente.update(paziente);
    }

    public Paziente read(Parameter parameter) {
        String id = (String) parameter.getValue("id");

        return daoPaziente.read(id);
    }

    public List<Paziente> getAll(Parameter parameter) {
        return daoPaziente.getAll();
    }

}

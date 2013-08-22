package business.applicationservice;

import business.applicationservice.exception.InvalidPazienteFieldException;
import business.entity.Paziente;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import org.joda.time.LocalDate;
import presentation.controller.ApplicationService;
import utility.DateConverter;
import utility.Parameter;

import java.util.List;

public class ApplicationServicePaziente implements ApplicationService, CRUG<Paziente> {

    private DAO<Paziente> daoPaziente = DAOFactory.getDAOEntity("DAOPaziente");

    public void create(Parameter parameter) throws InvalidPazienteFieldException {
        Paziente paziente = populate(parameter);

        daoPaziente.create(paziente);
    }

    public void update(Parameter parameter) throws InvalidPazienteFieldException {
        Paziente paziente = populate(parameter);

        String id = (String) parameter.getValue("id");
        paziente.setId(id);

        daoPaziente.update(paziente);
    }

    public Paziente read(Parameter parameter) {
        String id = (String) parameter.getValue("id");

        return daoPaziente.read(id);
    }

    public List<Paziente> getAll(Parameter parameter) {
        return daoPaziente.getAll();
    }

    private Paziente populate(Parameter parameter) throws InvalidPazienteFieldException {
        Paziente paziente = new Paziente();

        String nome = (String) parameter.getValue("nome");
        paziente.setNome(nome);

        String cognome = (String) parameter.getValue("cognome");
        paziente.setCognome(cognome);

        String dataString = (String) parameter.getValue("data");

        try {
            LocalDate data = LocalDate.parse(dataString, DateConverter.NORMAL_DATE_FORMAT);
        } catch (IllegalArgumentException e) {
            throw new InvalidPazienteFieldException("formatDateError");
        }

        List<String> numero = (List<String>) parameter.getValue("numero");
        paziente.setNumeroCellulare(numero);

        return paziente;
    }

}

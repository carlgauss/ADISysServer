package business.applicationservice;

import business.applicationservice.checker.Checker;
import business.applicationservice.checker.CheckerFactory;
import business.applicationservice.exception.CommonException;
import business.entity.Paziente;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import org.joda.time.LocalDate;
import presentation.controller.ApplicationService;
import utility.DateConverter;
import utility.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ApplicationServicePaziente implements ApplicationService, CRUG<Paziente> {

    private DAO<Paziente> daoPaziente = DAOFactory.getDAOEntity("DAOPaziente");
    Checker checker = CheckerFactory.buildInstance(Paziente.class);

    public void create(Parameter parameter) throws CommonException {
        Paziente paziente = populate(parameter);

        daoPaziente.create(paziente);
    }

    public void update(Parameter parameter) throws CommonException {
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

    private Paziente populate(Parameter parameter) throws CommonException {
        String nome = (String) parameter.getValue("nome");
        String cognome = (String) parameter.getValue("cognome");
        String dataString = (String) parameter.getValue("data");
        List<String> numero = (List<String>) parameter.getValue("numero");

        dataString = dataString.trim();

        List<Object> values = new ArrayList<>();
        values.add(nome);
        values.add(cognome);
        values.add(dataString);
        values.add(numero);

        checker.check(values);

        Paziente paziente = new Paziente();

        paziente.setNome(nome);
        paziente.setCognome(cognome);

        LocalDate data = LocalDate.parse(dataString, DateConverter.NORMAL_DATE_FORMAT);
        paziente.setData(data);

        paziente.setNumeroCellulare(numero);

        return paziente;
    }

}

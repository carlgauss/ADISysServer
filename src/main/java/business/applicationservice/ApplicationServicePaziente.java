package business.applicationservice;

import business.applicationservice.exception.InvalidPazienteFieldException;
import business.entity.Paziente;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import org.joda.time.LocalDate;
import presentation.controller.ApplicationService;
import utility.DateConverter;
import utility.Parameter;

import java.util.LinkedList;
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
            paziente.setData(data);
        } catch (IllegalArgumentException e) {
            throw new InvalidPazienteFieldException("formatDateError");
        }

        List<String> numero = (List<String>) parameter.getValue("numero");

        List<String> numeroCellulareFiltered = new LinkedList<>();

        for (String singoloNumero : numero) {
            String numeroTrimmed = singoloNumero.trim();

            boolean isNumeroValid = (3 <= numeroTrimmed.length()) && (numeroTrimmed.length() <= 20);

            if (isNumeroValid) {
                numeroCellulareFiltered.add(numeroTrimmed);
            }
        }

        paziente.setNumeroCellulare(numeroCellulareFiltered);

        nome = paziente.getNome();
        boolean isValid = (3 <= nome.length()) && (nome.length() <= 30);

        if (!isValid) {
            throw new InvalidPazienteFieldException("invalidPatientName");
        }

        cognome = paziente.getCognome();
        isValid = (3 <= cognome.length()) && (cognome.length() <= 30);

        if (!isValid) {
            throw new InvalidPazienteFieldException("invalidPatientSurname");
        }


        return paziente;
    }

}

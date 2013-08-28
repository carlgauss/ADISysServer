package business.applicationservice;

import business.applicationservice.checker.Checker;
import business.applicationservice.checker.CheckerFactory;
import business.applicationservice.exception.CommonException;
import business.applicationservice.exception.InvalidPatologiaFieldException;
import business.entity.Patologia;
import business.transfer.PatologiaTO;
import integration.dao.DAO;
import integration.dao.DAOFactory;
import integration.dao.DAOPatologia;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.util.*;

public class ApplicationServicePatologia implements ApplicationService, CRUG<Patologia> {
    private DAO<Patologia> daoPatologia = DAOFactory.getDAOEntity("DAOPatologia");
    private Checker checker = CheckerFactory.buildInstance(Patologia.class);

    public void create(Parameter parameter) throws CommonException {
        String codice = (String) parameter.getValue("codice");

        Patologia patologiaDuplicate = daoPatologia.read(codice);

        if (patologiaDuplicate == null) {
            Patologia patologia = populate(parameter);

            daoPatologia.create(patologia);
        } else {
            throw new InvalidPatologiaFieldException("duplicateDiseaseCode");
        }
    }

    public void update(Parameter parameter) throws CommonException {
        Patologia patologia = populate(parameter);

        daoPatologia.update(patologia);
    }

    public Patologia read(Parameter parameter) {
        String id = (String) parameter.getValue("id");

        return daoPatologia.read(id);
    }

    public List<Patologia> getAll(Parameter parameter) {
        return daoPatologia.getAll();
    }


    private Patologia populate(Parameter parameter) throws CommonException {
        String codice = (String) parameter.getValue("codice");
        String nome = (String) parameter.getValue("nome");
        int gravita = (int) parameter.getValue("gravita");

        List<Object> patologiaValues = new ArrayList<>();
        patologiaValues.add(codice);
        patologiaValues.add(nome);
        patologiaValues.add(gravita);

        checker.check(patologiaValues);

        Patologia patologia = new Patologia();

        patologia.setCodice(codice);
        patologia.setNome(nome);
        patologia.setGravita(gravita);

        return patologia;
    }

    private static final int CODICE_LENGTH = 6;
    private static final String CODICE_REGEX = "([0-9]){" + CODICE_LENGTH + "}";

    public void checkCodice(Parameter parameter) throws CommonException {
        String codice = (String) parameter.getValue("codice");
        Set<String> codiceToInsert = (Set<String>) parameter.getValue("patologieDaInserire");

        codice = codice.trim();

        if (!codice.matches(CODICE_REGEX)) {
            throw new InvalidPatologiaFieldException("invalidDiseaseCode");
        }

        if (codiceToInsert.contains(codice)) {
            throw new InvalidPatologiaFieldException("duplicateDiseaseCode");
        }

        Patologia patologiaDuplicate = daoPatologia.read(codice);

        if (patologiaDuplicate != null) {
            throw new InvalidPatologiaFieldException("duplicateDiseaseCode");
        }
    }

    public void updateAll(Parameter parameter) throws CommonException {
        Collection<Patologia> patologiaList = (Collection<Patologia>) parameter.getValue("listaPatologia");
        for (Patologia patologia : patologiaList) {
            List<Object> attibutes = new LinkedList<>();
            attibutes.add(patologia.getCodice());
            attibutes.add(patologia.getNome());
            attibutes.add(patologia.getGravita());

            checker.check(attibutes);
        }

        for (Patologia patologia : patologiaList) {
            PatologiaTO patologiaTO = (PatologiaTO) patologia;
            if(patologiaTO.isToInsert()) {
                daoPatologia.create(patologia);
            } else {
                daoPatologia.update(patologia);
            }
        }
    }
}

package business.applicationservice;

import business.applicationservice.checker.Checker;
import business.applicationservice.checker.CheckerFactory;
import business.applicationservice.exception.CommonInvalidFieldException;
import business.entity.Operazione;
import presentation.controller.ApplicationService;
import utility.Parameter;

import java.util.LinkedList;
import java.util.List;

public class ApplicationServiceOperazione implements ApplicationService {

    public void checkOperazione(Parameter parameter) throws CommonInvalidFieldException {
        String nome = (String) parameter.getValue("nome");
        List<Object> operazioneValues = new LinkedList<>();
        operazioneValues.add(nome);

        Checker operazioneChecker = CheckerFactory.buildInstance(Operazione.class);
        operazioneChecker.check(operazioneValues);
    }
}

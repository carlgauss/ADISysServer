package business.applicationservice;

import business.applicationservice.checker.Checker;
import business.applicationservice.checker.CheckerFactory;
import business.applicationservice.exception.CommonException;
import business.entity.Operazione;
import business.entity.Patologia;
import presentation.controller.ApplicationService;
import business.transfer.Parameter;

import java.util.LinkedList;
import java.util.List;

public class ApplicationServiceOperazione implements ApplicationService {

    public void checkOperazione(Parameter parameter) throws CommonException {
        String nome = (String) parameter.getValue("nome");
        List<Patologia> patologia = (List<Patologia>) parameter.getValue("patologia");

        List<Object> operazioneValues = new LinkedList<>();

        operazioneValues.add(nome);
        operazioneValues.add(patologia);

        Checker operazioneChecker = CheckerFactory.getChecker(Operazione.class);
        operazioneChecker.check(operazioneValues);
    }
}

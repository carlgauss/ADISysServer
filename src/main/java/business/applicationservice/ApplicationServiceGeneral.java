package business.applicationservice;

import business.applicationservice.exception.FatalMissingFileException;
import presentation.controller.ApplicationService;
import business.transfer.Parameter;

import java.io.File;

public class ApplicationServiceGeneral implements ApplicationService {
    private static final String ENG_LANGUE_STRING = "language/english.properties";
    private static final File ENG_LANGUE = new File(ENG_LANGUE_STRING);

    private static final String ITA_LANGUE_STRING = "language/italiano.properties";
    private static final File ITA_LANGUE = new File(ITA_LANGUE_STRING);

    private static final String XSD_JOURNALING_STRING = "schema/XMLJournalingSchema.xsd";
    private static final File XSD_JOURNALING = new File(XSD_JOURNALING_STRING);

    private static final String XSD_PIANIFICAZIONE_STRING = "schema/XMLPianificazioneSchema.xsd";
    private static final File XSD_PIANIFICAZIONE = new File(XSD_PIANIFICAZIONE_STRING);

    private static final String BLANK = "";
    private static final String INDENT = "\n";

    public void check(Parameter parameter) throws FatalMissingFileException {
        boolean isValid =
                ENG_LANGUE.exists() &&
                ITA_LANGUE.exists() &&
                XSD_JOURNALING.exists() &&
                XSD_PIANIFICAZIONE.exists();

        if(!isValid) {
            System.runFinalization();

            String msg = "Fatal: unable to read important file:";
            msg += ENG_LANGUE.exists() ? BLANK : INDENT + ENG_LANGUE_STRING;
            msg += ITA_LANGUE.exists() ? BLANK : INDENT + ITA_LANGUE_STRING;
            msg += XSD_JOURNALING.exists() ? BLANK : INDENT + XSD_JOURNALING_STRING;
            msg += XSD_PIANIFICAZIONE.exists() ? BLANK : INDENT + XSD_PIANIFICAZIONE_STRING;

            throw new FatalMissingFileException(msg);
        }
    }
}

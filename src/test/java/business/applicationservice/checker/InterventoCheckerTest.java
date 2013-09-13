package business.applicationservice.checker;
import business.applicationservice.exception.InvalidInterventoFieldException;
import business.entity.Infermiere;
import business.entity.Operazione;
import business.entity.Patologia;
import business.entity.Paziente;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
public class InterventoCheckerTest {

    private static final int MIN_CITTA_VALUE = 2;
    private static final int MAX_CITTA_VALUE = 30;
    private static final int MIN_CAP_VALUE = 3;
    private static final int MAX_CAP_VALUE = 12;
    private static final int MIN_INDIRIZZO_VALUE = 3;
    private static final int MAX_INDIRIZZO_VALUE = 50;

    private static final int MIN_OPERAZIONE_SIZE = 1;

    InterventoChecker interventoChecker;
    List<Object> listOfValues;
    Paziente paziente;
    Infermiere infermiere;
    Operazione operazione;

    Patologia patologia;


    @Before
    public void setUp() throws Exception {
        interventoChecker = new InterventoChecker();
        listOfValues = new ArrayList<>();
        patologia = new Patologia();
    }

    @After
    public void tearDown() throws Exception {
        interventoChecker = null;
        listOfValues = null;
        patologia = null;
    }

    @Test
    public void testCheckAllValidFields() throws Exception {
        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);

        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);

    }

    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckEmptyCitta() throws Exception {
        String citta = StringGenerator.getAStringOfExactlyLength(0);
        listOfValues.add(citta);
        interventoChecker.check(listOfValues);
    }

    @Test
    public void testCheckCittaMinValue() throws Exception {
        String citta = StringGenerator.getAStringOfExactlyLength(MIN_CITTA_VALUE);
        listOfValues.add(citta);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);


    }

    @Test
    public void testCheckCittaMaxValue() throws Exception {
        String citta = StringGenerator.getAStringOfExactlyLength(MAX_CITTA_VALUE);
        listOfValues.add(citta);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);


        interventoChecker.check(listOfValues);


    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckCittaUnderOneMinValue() throws Exception {
        String citta = StringGenerator.getAStringOfExactlyLength(MIN_CITTA_VALUE - 1);
        listOfValues.add(citta);
        interventoChecker.check(listOfValues);
    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckCittaOverOneMaxValue() throws Exception {
        String citta = StringGenerator.getAStringOfExactlyLength(MAX_CITTA_VALUE + 1);
        listOfValues.add(citta);
        interventoChecker.check(listOfValues);
    }


    @Test
    public void testCheckCittaOverOneMinValue() throws Exception {
        String citta = StringGenerator.getAStringOfExactlyLength(MIN_CITTA_VALUE + 1);
        listOfValues.add(citta);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);
    }

    @Test
    public void testCheckCittaUnderOneMaxValue() throws Exception {
        String citta = StringGenerator.getAStringOfExactlyLength(MAX_CITTA_VALUE - 1);
        listOfValues.add(citta);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);
    }



    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckEmptyCAP() throws Exception {
        listOfValues.add("Nome di Citta'");
        String CAP = StringGenerator.getAStringOfExactlyLength(0);
        listOfValues.add(CAP);
        interventoChecker.check(listOfValues);
    }

    @Test
    public void testCheckCAPMinValue() throws Exception {
        String CAP = StringGenerator.getAStringOfExactlyLength(MIN_CAP_VALUE);
        listOfValues.add("Nome di citta'");
        listOfValues.add(CAP);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);



        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);


    }

    @Test
    public void testCheckCAPMaxValue() throws Exception {
        String CAP = StringGenerator.getAStringOfExactlyLength(MAX_CAP_VALUE);
        listOfValues.add("Nome di citta'");
        listOfValues.add(CAP);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);



        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);


        interventoChecker.check(listOfValues);


    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckCAPUnderOneMinValue() throws Exception {
        String CAP = StringGenerator.getAStringOfExactlyLength(MIN_CAP_VALUE - 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add(CAP);
        interventoChecker.check(listOfValues);
    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckCAPOverOneMaxValue() throws Exception {
        String CAP = StringGenerator.getAStringOfExactlyLength(MAX_CAP_VALUE + 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add(CAP);
        interventoChecker.check(listOfValues);
    }


    @Test
    public void testCheckCAPOverOneMinValue() throws Exception {
        String CAP = StringGenerator.getAStringOfExactlyLength(MIN_CAP_VALUE + 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add(CAP);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);



        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);
    }

    @Test
    public void testCheckCAPUnderOneMaxValue() throws Exception {
        String CAP = StringGenerator.getAStringOfExactlyLength(MAX_CAP_VALUE - 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add(CAP);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);



        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);
    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckEmptyIndirizzo() throws Exception {
        listOfValues.add("Nome di Citta'");
        listOfValues.add("CAP");
        String Indirizzo = StringGenerator.getAStringOfExactlyLength(0);
        listOfValues.add(Indirizzo);
        interventoChecker.check(listOfValues);
    }

    @Test
    public void testCheckIndirizzoMinValue() throws Exception {
        String Indirizzo = StringGenerator.getAStringOfExactlyLength(MIN_INDIRIZZO_VALUE);
        listOfValues.add("Nome di citta'");
        listOfValues.add("CAP");
        listOfValues.add(Indirizzo);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);


    }

    @Test
    public void testCheckIndirizzoMaxValue() throws Exception {
        String Indirizzo = StringGenerator.getAStringOfExactlyLength(MAX_INDIRIZZO_VALUE);
        listOfValues.add("Nome di citta'");
        listOfValues.add("CAP");
        listOfValues.add(Indirizzo);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);


        interventoChecker.check(listOfValues);


    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckIndirizzoUnderOneMinValue() throws Exception {
        String Indirizzo = StringGenerator.getAStringOfExactlyLength(MIN_INDIRIZZO_VALUE - 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add("CAP");
        listOfValues.add(Indirizzo);
        interventoChecker.check(listOfValues);
    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckIndirizzoOverOneMaxValue() throws Exception {
        String Indirizzo = StringGenerator.getAStringOfExactlyLength(MAX_INDIRIZZO_VALUE + 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add("CAP");
        listOfValues.add(Indirizzo);
        interventoChecker.check(listOfValues);
    }


    @Test
    public void testCheckIndirizzoOverOneMinValue() throws Exception {
        String Indirizzo = StringGenerator.getAStringOfExactlyLength(MIN_INDIRIZZO_VALUE + 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add("CAP");
        listOfValues.add(Indirizzo);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);
    }

    @Test
    public void testCheckIndirizzoUnderOneMaxValue() throws Exception {
        String Indirizzo = StringGenerator.getAStringOfExactlyLength(MAX_INDIRIZZO_VALUE - 1);
        listOfValues.add("Nome di citta'");
        listOfValues.add("CAP");
        listOfValues.add(Indirizzo);


        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);


        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);
    }



    @Test   (expected = InvalidInterventoFieldException.class)
    public void testCheckInvalidDayData() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("00/5/1980");

        interventoChecker.check(listOfValues);
    }

    @Test   (expected = InvalidInterventoFieldException.class)
    public void testCheckInvalidMonthData() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("14/0/1980");

        interventoChecker.check(listOfValues);
    }

    @Test   (expected = InvalidInterventoFieldException.class)
    public void testCheckInvalidMonthDataTooBig() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("14/22/1980");

        interventoChecker.check(listOfValues);
    }

    @Test   (expected = InvalidInterventoFieldException.class)
    public void testCheckInvalidYearDataTooBig() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("14/22/1000000000");

        interventoChecker.check(listOfValues);
    }

    @Test   (expected = InvalidInterventoFieldException.class)
    public void testCheckInvalidFormatData() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("10 - 5 - 1980");

        interventoChecker.check(listOfValues);
    }

    @Test   (expected = InvalidInterventoFieldException.class)
    public void testCheckInvalidFormatData2() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("10-5-1980");

        interventoChecker.check(listOfValues);

    }

    @Test   (expected = InvalidInterventoFieldException.class)
    public void testCheckInvalidFormatData3() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("10.5.2568");

        interventoChecker.check(listOfValues);
    }


    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckInfermiereNull() throws Exception {
        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(null);

        interventoChecker.check(listOfValues);

    }

    @Test (expected = InvalidInterventoFieldException.class)
    public void testCheckPazienteNull() throws Exception {

        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(null);

        interventoChecker.check(listOfValues);

    }


    @Test  (expected = InvalidInterventoFieldException.class)
    public void testCheckListOperationsEmpty() throws Exception {
        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaList = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaList.add(patologia);

        paziente.setPatologia(patologiaList);

        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/2980");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaList);
       // operazioneList.add(operazione);  In this way there's no operations in the list
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);

    }


    @Test  (expected = InvalidInterventoFieldException.class)
    public void testCheckInconsistentPatologiaFieldBetweenPazienteAndIntervento() throws Exception {
        paziente = new Paziente();
        patologia = new Patologia();

        List<Patologia> patologiaListPaziente = new ArrayList<>();
        patologia.setCodice("123456");
        patologiaListPaziente.add(patologia);

        List<Patologia> patologiaListIntervento = new ArrayList<>();
        patologia.setCodice("654321");
        patologiaListIntervento.add(patologia);

        paziente.setPatologia(patologiaListPaziente);

        listOfValues.add("Citta'");
        listOfValues.add("CAP");
        listOfValues.add("Indirizzo");
        listOfValues.add("23/5/1500");
        listOfValues.add("09:00:00");
        listOfValues.add(new Infermiere());
        listOfValues.add(paziente);

        List<Operazione>  operazioneList = new ArrayList<>();
        operazione = new Operazione();
        operazione.setPatologia(patologiaListIntervento);
        operazioneList.add(operazione);
        listOfValues.add(operazioneList);
        interventoChecker.check(listOfValues);

    }


}

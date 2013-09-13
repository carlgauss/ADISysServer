package integration.dao;

import business.entity.*;
import integration.connector.HQSQLConnector;
import mockit.Mockit;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static integration.dao.DAOInfermiereTest.fillInfermieri;
import static integration.dao.DAOInfermiereTest.infermieri;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DAOInterventoTest {

    private static DAO<Intervento> daoIntervento;
    private static DAO<Paziente> daoPaziente;
    private static DAO<Infermiere> daoInfermiere;
    private static DAO<Patologia> daoPatologia;

    private Intervento intervento;
    private Paziente paziente;
    private Patologia patologia;
    private List<Patologia> patologiaList;

    private static String msg;

    private static List<List<Operazione>> listOpList;
    private static List<Paziente> listP;
    private static List<Infermiere> listI;

    private static final Object[][][] ARRAY_OPERAZIONI = new Object[][][]{
            {},
            {
                    {"Defribrillazione", null, new int[]{0, 2}},
            },
            {
                    {"Defribrillazione", "", new int[]{0, 2}},
                    {"Eutanasia", "Uso di morfina (pericoloso)", new int[]{2, 0}},
            },
            {
                    {"Tracheotomia", null, new int[]{0, 2}},
                    {"Rimozione dell'appendice", "", new int[]{2, 0}},
                    {"Anestesia", "Totale", new int[]{0, 2}},
                    {"Fai SQL Injection", "' SELECT FROM", new int[]{2, 0}},
            },
    };

    private static final Object[][] ARRAY_INTERVENTI = new Object[][]{
            {"Bari", "70123", "Via Nicolai 287", LocalDate.now(), new LocalTime(00, 00, 00), 0, 4, 0},
            {"Bari", "70123", "Via Nicolai 287", LocalDate.now(), new LocalTime(18, 00, 00), 1, 3, 1},
            {"Bari", "70123", "Via Nicolai 287", LocalDate.now(), new LocalTime(23, 59, 59), 2, 2, 2},
            {"Bari", "70123", "Via Nicolai 287", LocalDate.now(), new LocalTime(00, 00, 01), 3, 3, 1},
            {"Bari", "70123", "Via Nicolai 287", LocalDate.now().plusYears(2), new LocalTime(12, 00, 00), 0, 4, 4},
    };

    private static final int SIZE = ARRAY_INTERVENTI.length;

    public static List<Intervento> interventi;



    public static List<List<Operazione>> fillOperazioni() {
        List<List<Operazione>> operazioni = new LinkedList<>();
        for (Object[][] e : ARRAY_OPERAZIONI) {
            List<Operazione> tempL = new LinkedList<>();
            for (Object[] f : e) {
                Operazione temp = new Operazione();
                temp.setNome((String) f[0]);
                temp.setNota((String) f[1]);

                List<Patologia> patologiaList = new ArrayList<>(2);
                int[] list = (int[]) f[2];
                for(int i : list) {
                    patologiaList.add(DAOPatologiaTest.patologie[i]);
                }
                temp.setPatologia(patologiaList);

                tempL.add(temp);
            }

            operazioni.add(tempL);
        }

        return operazioni;
    }


    @Before
    public void setUp() throws Exception {
        HQSQLConnectorStub conn = new HQSQLConnectorStub();
        intervento = new Intervento();
        paziente = new Paziente();
        patologiaList = new ArrayList<>();
        conn.deleteAll();
        try {
            conn.close();
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        conn = null;

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);

        daoIntervento = DAOFactory.getDAOEntity("DAOIntervento");
        daoPaziente = DAOFactory.getDAOEntity("DAOPaziente");
        daoInfermiere = DAOFactory.getDAOEntity("DAOInfermiere");
        daoPatologia = DAOFactory.getDAOEntity("DAOPatologia");


    }

    private void preTest() {
        fillInfermieri();
        DAOPazienteTest.fillPazienti();


        //Filling pazienti, patologie and operazioni
        for (Paziente e : DAOPazienteTest.pazienti) {
            daoPaziente.create(e);
        }

        for (Infermiere e : infermieri) {
            daoInfermiere.create(e);
        }

        listOpList = fillOperazioni();
        listP = daoPaziente.getAll();
        listI = daoInfermiere.getAll();

        List<Paziente> listP2 = daoPaziente.getAll();

        List<Infermiere> listI2 = daoInfermiere.getAll();

        //Filling interventi

        interventi = new LinkedList<>();

        for (int i = 0; i < SIZE; i++) {
            Intervento intrv = new Intervento();

            intrv.setCitta((String) ARRAY_INTERVENTI[i][0]);
            intrv.setCap((String) ARRAY_INTERVENTI[i][1]);
            intrv.setIndirizzo((String) ARRAY_INTERVENTI[i][2]);
            intrv.setData((LocalDate) ARRAY_INTERVENTI[i][3]);
            intrv.setOra((LocalTime) ARRAY_INTERVENTI[i][4]);
            intrv.setOperazione(listOpList.get((int) ARRAY_INTERVENTI[i][5]));
            intrv.setPaziente(listP.get((int) ARRAY_INTERVENTI[i][6]));
            intrv.setInfermiere(listI.get((int) ARRAY_INTERVENTI[i][7]));

            interventi.add(intrv);
        }
    }

    @After
    public void tearDown() throws Exception {
        daoIntervento = null;
        daoPaziente = null;
        daoInfermiere = null;

        intervento = null;
        paziente = null;
        patologiaList = null;
        System.gc();
    }

    @Test
    public void testUnique() {
        preTest();
        for (Intervento e : interventi) {
            daoIntervento.create(e);
        }

        List<Intervento> list = daoIntervento.getAll();

        int i = 0;
        for (Intervento e : list) {
            e.setCitta(e.getCitta() + "k");
            List<Operazione> temp = e.getOperazione();
            for (Operazione f : temp) {
                f.setNota(f.getNota() + "k");

                List<Patologia> patologiaList = f.getPatologia();
                patologiaList.remove(0);
                f.setPatologia(patologiaList);
            }
            e.setOperazione(temp);

            e.setPaziente(listP.get((((int) ARRAY_INTERVENTI[i][6]) + 1) % 5));
            e.setInfermiere(listI.get((((int) ARRAY_INTERVENTI[i][7]) + 1) % 5));

            i++;

            daoIntervento.update(e);
        }

        for (Intervento e : list) {
            Intervento gotInt = daoIntervento.read(e.getId());
        }
    }

    public static void fillAll() {

        HQSQLConnectorStub conn = new HQSQLConnectorStub();
        conn.deleteAll();
        try {
            conn.close();
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        conn = null;

        fillInfermieri();
        DAOPazienteTest.fillPazienti();

        daoIntervento = DAOFactory.getDAOEntity("DAOIntervento");
        daoPaziente = DAOFactory.getDAOEntity("DAOPaziente");
        daoInfermiere = DAOFactory.getDAOEntity("DAOInfermiere");

        //Filling pazienti, patologie and operazioni
        for (Paziente e : DAOPazienteTest.pazienti) {
            daoPaziente.create(e);
        }

        for (Infermiere e : infermieri) {
            daoInfermiere.create(e);
        }

        listOpList = fillOperazioni();
        listP = daoPaziente.getAll();
        listI = daoInfermiere.getAll();

        List<Paziente> listP2 = daoPaziente.getAll();

        List<Infermiere> listI2 = daoInfermiere.getAll();

        //Filling interventi

        interventi = new LinkedList<>();

        for (int i = 0; i < SIZE; i++) {
            Intervento intrv = new Intervento();

            intrv.setCitta((String) ARRAY_INTERVENTI[i][0]);
            intrv.setCap((String) ARRAY_INTERVENTI[i][1]);
            intrv.setIndirizzo((String) ARRAY_INTERVENTI[i][2]);
            intrv.setData((LocalDate) ARRAY_INTERVENTI[i][3]);
            intrv.setOra((LocalTime) ARRAY_INTERVENTI[i][4]);
            intrv.setOperazione(listOpList.get((int) ARRAY_INTERVENTI[i][5]));
            intrv.setPaziente(listP.get((int) ARRAY_INTERVENTI[i][6]));
            intrv.setInfermiere(listI.get((int) ARRAY_INTERVENTI[i][7]));

            interventi.add(intrv);
        }

        for (Intervento e : interventi) {
            daoIntervento.create(e);
        }

        interventi = daoIntervento.getAll();
    }


    @Test
    public void testCreateInterventoWithNoOperazioni() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(0));

    }


    @Test
    public void testCreateInterventoWithOneOperazione() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));

    }


    @Test
    public void testCreateInterventoWithTwoOperazioniforTheSamePatologia() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento1 = new Operazione();
        operazioneIntervento1.setNome("Fai qualcosa");
        operazioneIntervento1.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento1);

        Operazione operazioneIntervento2 = new Operazione();
        operazioneIntervento2.setNome("Fai ancora qualcosa");
        operazioneIntervento2.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento2);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(2));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento1.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento1.getPatologia()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(1).getNome(), equalTo(operazioneIntervento2.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(1).getPatologia(), equalTo(operazioneIntervento2.getPatologia()));


    }


    @Test  (expected = NullPointerException.class)
    public void testCreateInterventoWithOperazioneForDifferentPazientePatologia() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");

         //New Patologia list, different from the one of the patient
        List<Patologia> differentPatologiaList = new ArrayList<>();
        Patologia differentPatologia = new Patologia();
        differentPatologia.setCodice("987654");
        differentPatologia.setNome("differente patologia");
        differentPatologia.setGravita(4);
        differentPatologiaList.add(differentPatologia);


        operazioneIntervento.setPatologia(differentPatologiaList);
        operazioniIntervento.add(operazioneIntervento);


        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia().get(0).getCodice(), equalTo("987654"));

    }



    @Test
    public void testCreateCittaInterventoWithDateInThePast() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(1200, 1, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        String newCittaIntervento = "nuova citta";
        tuttiInterventi.get(0).setCitta(newCittaIntervento);
        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(newCittaIntervento));
    }



    @Test
    public void testUpdateCittaIntervento() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        String newCittaIntervento = "nuova citta";
        tuttiInterventi.get(0).setCitta(newCittaIntervento);
        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(newCittaIntervento));
    }

    @Test
    public void testUpdateCAPIntervento() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        String newCapIntervento = "nuovo CAP";
        tuttiInterventi.get(0).setCap(newCapIntervento);
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(newCapIntervento));
    }

    @Test
    public void testUpdateIndirizzoIntervento() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        String newIndirizzoIntervento = "nuova citta";
        tuttiInterventi.get(0).setIndirizzo(newIndirizzoIntervento);
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(newIndirizzoIntervento));
    }

    @Test
    public void testUpdateOraIntervento() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        LocalTime newOraIntervento = new LocalTime(15, 0, 0);
        tuttiInterventi.get(0).setOra(newOraIntervento);
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(newOraIntervento));
    }


    @Test
    public void testUpdateInfermiereIntervento() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        Infermiere newInfermiereIntervento = new Infermiere();
        newInfermiereIntervento.setNome("John");
        newInfermiereIntervento.setCognome("Locke");

        tuttiInterventi.get(0).setInfermiere(newInfermiereIntervento);
        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(newInfermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(newInfermiereIntervento.getCognome()));
    }


    @Test
    public void testUpdatePazienteIntervento() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        Paziente newPazienteIntervento = new Paziente();
        newPazienteIntervento.setNome("Jamie");
        newPazienteIntervento.setCognome("Lewis");
        newPazienteIntervento.setData(new LocalDate(1, 1, 1));

        tuttiInterventi.get(0).setPaziente(newPazienteIntervento);
        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(newPazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(newPazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(newPazienteIntervento.getData()));
    }

    @Test
    public void testUpdatePazienteInterventoAddingOnePatologia() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        Paziente newPazienteIntervento = new Paziente();
        newPazienteIntervento.setNome("Jamie");
        newPazienteIntervento.setCognome("Lewis");
        newPazienteIntervento.setData(new LocalDate(1, 1, 1));

        Patologia newPatologia = new Patologia();
        newPatologia.setCodice("666");
        newPatologia.setNome("morte certa");
        newPatologia.setGravita(1);
        patologiaList.add(newPatologia);
        newPazienteIntervento.setPatologia(patologiaList);

        tuttiInterventi.get(0).setPaziente(newPazienteIntervento);
        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(newPazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(newPazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(newPazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia().size(), equalTo(2));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia().get(1).getNome(), equalTo(newPatologia.getNome()));
    }





    @Test
    public void testUpdateDataIntervento() throws Exception {
        String cittaIntervento = "citta";
        String capIntervento = "CAP";
        String indirizzoIntervento = "indirizzo";
        LocalDate dataIntervento = new LocalDate(3100, 5, 30);
        LocalTime oraIntervento = new LocalTime(9, 0, 0);
        Infermiere infermiereIntervento = giveMeACompleteInfermiere();
        Paziente pazienteIntervento = giveMeACompletePaziente();
        List<Operazione> operazioniIntervento = new ArrayList<>();

        //Operazione matters
        Operazione operazioneIntervento = new Operazione();
        operazioneIntervento.setNome("Fai qualcosa");
        operazioneIntervento.setPatologia(patologiaList);
        operazioniIntervento.add(operazioneIntervento);

        intervento.setCitta(cittaIntervento);
        intervento.setCap(capIntervento);
        intervento.setIndirizzo(indirizzoIntervento);
        intervento.setData(dataIntervento);
        intervento.setOra(oraIntervento);
        intervento.setPaziente(pazienteIntervento);
        intervento.setInfermiere(infermiereIntervento);
        intervento.setOperazione(operazioniIntervento);

        daoIntervento.create(intervento);

        //Assertions
        List<Intervento> tuttiInterventi = daoIntervento.getAll();

        assertThat(tuttiInterventi.size(), equalTo(1));

        assertThat(tuttiInterventi.get(0).getCitta(), equalTo(cittaIntervento));
        assertThat(tuttiInterventi.get(0).getCap(), equalTo(capIntervento));
        assertThat(tuttiInterventi.get(0).getIndirizzo(), equalTo(indirizzoIntervento));
        assertThat(tuttiInterventi.get(0).getData(), equalTo(dataIntervento));
        assertThat(tuttiInterventi.get(0).getOra(), equalTo(oraIntervento));

        assertThat(tuttiInterventi.get(0).getPaziente().getNome(), equalTo(pazienteIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getCognome(), equalTo(pazienteIntervento.getCognome()));
        assertThat(tuttiInterventi.get(0).getPaziente().getData(), equalTo(pazienteIntervento.getData()));
        assertThat(tuttiInterventi.get(0).getPaziente().getNumeroCellulare(), equalTo(pazienteIntervento.getNumeroCellulare()));
        assertThat(tuttiInterventi.get(0).getPaziente().getPatologia(), equalTo(pazienteIntervento.getPatologia()));

        assertThat(tuttiInterventi.get(0).getInfermiere().getNome(), equalTo(infermiereIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getInfermiere().getCognome(), equalTo(infermiereIntervento.getCognome()));

        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(operazioniIntervento.size()));
        assertThat(tuttiInterventi.get(0).getOperazione().size(), equalTo(1));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getNome(), equalTo(operazioneIntervento.getNome()));
        assertThat(tuttiInterventi.get(0).getOperazione().get(0).getPatologia(), equalTo(operazioneIntervento.getPatologia()));


        //Updating
        LocalDate newDataIntervento = new LocalDate(5490, 4, 29);
        tuttiInterventi.get(0).setData(newDataIntervento);
        assertThat(tuttiInterventi.get(0).getData(), equalTo(newDataIntervento));
    }



    private Paziente giveMeACompletePaziente(){
        String nomePaziente = "Il nome";
        String cognomePaziente = "Il cognome";
        LocalDate dataNascitaPaziente = new LocalDate(1700, 12, 19);
        String cellularePaziente = "123456789";

        String codicePatologia = "112233";
        String nomePatologia = "Tumore";
        int gravitaPatologia = 4;

        paziente.setNome(nomePaziente);
        paziente.setCognome(cognomePaziente);
        paziente.setData(dataNascitaPaziente);

        List<String> cellList = new ArrayList<>();
        cellList.add(cellularePaziente);
        paziente.setNumeroCellulare(cellList);

        Patologia patologia = new Patologia();
        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);
        patologiaList.add(patologia);

        daoPatologia.create(patologia);

        paziente.setPatologia(patologiaList);


        daoPaziente.create(paziente);
        return daoPaziente.getAll().get(0);
    }

    private Infermiere giveMeACompleteInfermiere(){
        Infermiere infermiere = new Infermiere();
        String nomeInfermiere = "nome infermiere";
        String cognomeInfermiere = "cognome infermiere";

        infermiere.setNome(nomeInfermiere);
        infermiere.setCognome(cognomeInfermiere);

        daoInfermiere.create(infermiere);
        return daoInfermiere.getAll().get(0);
    }
}

package integration.dao.test;

import static org.junit.Assert.*;

import integration.connector.HQSQLConnector;
import integration.dao.DAO;
import integration.dao.DAOFactory;

import java.util.LinkedList;
import java.util.List;

import mockit.Mockit;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import business.entity.Infermiere;
import business.entity.Intervento;
import business.entity.Operazione;
import business.entity.Paziente;
import business.transfer.OperazioneTO;

import static integration.dao.test.DAOInfermiereTest.*;
import static integration.dao.test.DAOPazienteTest.*;

public class DAOInterventoTest {

    private static DAO<Intervento> dao;
    private static DAO<Paziente> daoP;
    private static DAO<Infermiere> daoI;

    private static String msg;

    private static List<List<Operazione>> listOpList;
    private static List<Paziente> listP;
    private static List<Infermiere> listI;

    private static final String[][][] ARRAY_OPERAZIONI = new String[][][]{
            {},
            {
                    {"Defribrillazione", null},
            },
            {
                    {"Defribrillazione", ""},
                    {"Eutanasia", "Uso di morfina (pericoloso)"},
            },
            {
                    {"Tracheotomia", null},
                    {"Rimozione dell'appendice", ""},
                    {"Anestesia", "Totale"},
                    {"Fai SQL Injection", "' SELECT FROM"},
            },
    };

    private static final Object[][] ARRAY_INTERVENTI = new Object[][]{
            {"Bari", "12481", "via aaa 123", LocalDate.now(), new LocalTime(12, 00, 00), 0, 4, 0},
            {"Bari", "12481", "via aaa 123", LocalDate.now(), new LocalTime(0, 00, 00), 1, 3, 1},
            {"Bari", "12481", "via aaa 123", LocalDate.now(), new LocalTime(23, 59, 59), 2, 2, 2},
            {"Bari", "12481", "via aaa 123", LocalDate.now(), new LocalTime(00, 00, 01), 3, 3, 1},
            {"Bari", "12481", "via aaa 123", LocalDate.now(), new LocalTime(12, 00, 00), 0, 4, 4},
    };

    private static final int SIZE = ARRAY_INTERVENTI.length;

    public static List<Intervento> interventi;

    public static String printOperazioni(List<Operazione> op) {
        String msg2 = "";
        for (Operazione e : op) {
            msg2 += "\t";
            msg2 += e.getId() + " ";
            msg2 += e.getNome() + ", ";
            msg2 += e.getNota() + "\n";
        }
        return msg2;
    }

    public static List<List<Operazione>> fillOperazioni() {
        List<List<Operazione>> operazioni = new LinkedList<>();
        for (String[][] e : ARRAY_OPERAZIONI) {
            List<Operazione> tempL = new LinkedList<>();
            for (String[] f : e) {
                Operazione temp = new Operazione();
                temp.setNome(f[0]);
                temp.setNota(f[1]);

                tempL.add(temp);
            }

            operazioni.add(tempL);
        }

        return operazioni;
    }


    @Before
    public void setUp() throws Exception {
        HQSQLConnectorStub conn = new HQSQLConnectorStub();
        conn.deleteAll();
        try {
            conn.close();
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        conn = null;

        fillInfermieri();
        fillPazienti();

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);

        dao = DAOFactory.buildInstance("DAOIntervento");
        daoP = DAOFactory.buildInstance("DAOPaziente");
        daoI = DAOFactory.buildInstance("DAOInfermiere");

        //Filling pazienti, infermieri and operazioni
        for (Paziente e : pazienti) {
            daoP.create(e);
        }

        for (Infermiere e : infermieri) {
            daoI.create(e);
        }

        listOpList = fillOperazioni();
        listP = daoP.getAll();
        listI = daoI.getAll();

        System.out.println("---printing pazienti using get all---");
        List<Paziente> listP2 = daoP.getAll();
        for (Paziente e : listP2) {
            msg = e.getId() + " " + e.getNome() + " " + e.getCognome() + " " + e.getData();
            System.out.println(msg);
        }

        System.out.println("---printing infermieri using get all---");
        List<Infermiere> listI2 = daoI.getAll();
        for (Infermiere e : listI2) {
            msg = e.getId() + " " + e.getNome() + " " + e.getCognome();
            System.out.println(msg);
        }

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
        dao = null;
        daoP = null;
        daoI = null;
        System.gc();
    }

    @Test
    public void testUnique() {
        System.out.println("---create test---");
        for (Intervento e : interventi) {
            dao.create(e);
            System.out.println("intervento created");
        }

        System.out.println("---printing using get all---");
        List<Intervento> list = dao.getAll();
        for (Intervento e : list) {
            msg = e.getId() + " " + e.getCitta() + " " + e.getCap() + " " + e.getIndirizzo() + " " + e.getData() + " " + e.getOra()
                    + " Paziente " + e.getPaziente().getId() + " Infermiere " + e.getInfermiere().getId() + "\n";
            msg += printOperazioni(e.getOperazione());
            System.out.print(msg);
        }

        System.out.println("---updating all queries (adding k to the cities and operation note, and mixing both paziente and infermiere)---");
        int i = 0;
        for (Intervento e : list) {
            e.setCitta(e.getCitta() + "k");
            List<Operazione> temp = e.getOperazione();
            for (Operazione f : temp) {
                f.setNota(f.getNota() + "k");
            }
            e.setOperazione(temp);

            e.setPaziente(listP.get((((int) ARRAY_INTERVENTI[i][6]) + 1) % 5));
            e.setInfermiere(listI.get((((int) ARRAY_INTERVENTI[i][7]) + 1) % 5));

            i++;

            dao.update(e);
            System.out.println("intervento updated");
        }

        System.out.println("---printing using read---");
        for (Intervento e : list) {
            Intervento gotInt = dao.read(e.getId());
            msg = gotInt.getId() + " " + gotInt.getCitta() + " " + gotInt.getCap() + " " + gotInt.getIndirizzo() + " " + gotInt.getData() + " " + gotInt.getOra()
                    + " Paziente " + gotInt.getPaziente().getId() + " Infermiere " + gotInt.getInfermiere().getId() + "\n";
            msg += printOperazioni(gotInt.getOperazione());
            System.out.print(msg);
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
        fillPazienti();

        dao = DAOFactory.buildInstance("DAOIntervento");
        daoP = DAOFactory.buildInstance("DAOPaziente");
        daoI = DAOFactory.buildInstance("DAOInfermiere");

        //Filling pazienti, infermieri and operazioni
        for (Paziente e : pazienti) {
            daoP.create(e);
        }

        for (Infermiere e : infermieri) {
            daoI.create(e);
        }

        listOpList = fillOperazioni();
        listP = daoP.getAll();
        listI = daoI.getAll();

        List<Paziente> listP2 = daoP.getAll();

        List<Infermiere> listI2 = daoI.getAll();

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
            dao.create(e);
        }

        interventi = dao.getAll();
    }
}

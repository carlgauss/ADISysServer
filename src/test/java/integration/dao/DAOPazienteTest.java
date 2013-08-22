package integration.dao;

import business.entity.Paziente;
import integration.connector.HQSQLConnector;
import mockit.Mockit;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class DAOPazienteTest {
    private static DAO<Paziente> dao;

    private static final Object[][] ARRAY_PAZIENTI = new Object[][]{
            {"aaa", "bbb", new LocalDate(1991, 12, 12), new String[]{"124", "1412"}},
            {"Bbb", "Ccc", new LocalDate(1995, 2, 12), new String[]{}},
            {"bbb", "Zzz", new LocalDate(1991, 12, 2), new String[]{"124"}},
            {"OoO", "q Qq", new LocalDate(1991, 12, 22), new String[]{"124546745", "1412", "3134"}},
            {"l LL", "KKk", new LocalDate(2002, 12, 12), new String[]{"124", "1412", "1214", "32424"}}
    };

    public static Paziente[] pazienti;

    public static void fillPazienti() {
        pazienti = new Paziente[ARRAY_PAZIENTI.length];
        for (int i = 0; i < pazienti.length; i++) {
            pazienti[i] = new Paziente();
            pazienti[i].setNome(ARRAY_PAZIENTI[i][0].toString());
            pazienti[i].setCognome(ARRAY_PAZIENTI[i][1].toString());
            pazienti[i].setData((LocalDate) ARRAY_PAZIENTI[i][2]);
            String[] cell = (String[]) ARRAY_PAZIENTI[i][3];
            List<String> cellList = new LinkedList<>();
            for (String e : cell) {
                cellList.add(e);
            }
            pazienti[i].setNumeroCellulare(cellList);
        }
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

        fillPazienti();

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        dao = DAOFactory.buildInstance("DAOPaziente");
    }

    @After
    public void tearDown() throws Exception {
        dao = null;
        System.gc();
    }

    @Test
    public void testUnique() {
        String msg;

        System.out.println("---create test---");
        for (Paziente e : pazienti) {
            dao.create(e);
            System.out.println("paziente created");
        }

        System.out.println("---printing using get all---");
        List<Paziente> list = dao.getAll();
        for (Paziente e : list) {
            msg = e.getId() + " " + e.getNome() + " " + e.getCognome() + " " + e.getData() + "\n -> Cell numbers:";
            for (String f : e.getNumeroCellulare()) {
                msg += " " + f;
            }
            System.out.println(msg);
        }

        System.out.println("---updating all queries (adding k to the names and adding a cell phone number)---");
        for (Paziente e : list) {
            e.setNome(e.getNome() + "k");
            e.setCognome(e.getCognome() + "k");
            List<String> newCellList = e.getNumeroCellulare();
            newCellList.add("123456789");
            e.setNumeroCellulare(newCellList);
            dao.update(e);
            System.out.println("paziente updated");
        }

        System.out.println("---printing using read---");
        for (Paziente e : list) {
            Paziente gotPaz = dao.read(e.getId());
            msg = gotPaz.getId() + " " + gotPaz.getNome() + " " + gotPaz.getCognome() + " " + gotPaz.getData() + "\n -> Cell numbers:";
            for (String f : gotPaz.getNumeroCellulare()) {
                msg += " " + f;
            }
            System.out.println(msg);
        }
    }

}

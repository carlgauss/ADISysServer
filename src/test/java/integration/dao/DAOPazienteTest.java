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
            {"Rocco", "Coriandoli", new LocalDate(1965, 2, 22), new String[]{"0805241452", "368125510"}},
            {"Nicola", "Amenicoli", new LocalDate(1988, 7, 17), new String[]{}},
            {"Renzo", "Ottato", new LocalDate(1979, 12, 1), new String[]{"3256971266"}},
            {"Eleonora", "Drachi Malfieri", new LocalDate(1954, 1, 31), new String[]{"0809541289", "0805436699", "3018230953"}},
            {"Maria Caterina", "Colò", new LocalDate(1999, 8, 19), new String[]{"253735968", "021236490", "0804356715", "3206270012"}}
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
        dao = DAOFactory.getDAOEntity("DAOPaziente");
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

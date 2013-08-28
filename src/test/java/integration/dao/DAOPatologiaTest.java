package integration.dao;

import business.entity.Patologia;
import integration.connector.HQSQLConnector;
import mockit.Mockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DAOPatologiaTest {

    private static DAO<Patologia> dao;

    private static final String[][] PATOLOGIE_STRING = new String[][]{
            {"100212", "   Verruche", "1"},
            {"123234   ", "Occhio di pernice", "2"},
            {"002120", "Raffreddore", "1"},
            {"  912740  ", "  Gastroenterite  ", "5"},
            {"131452", "Carpale", "3"},
            {"402194", "Carie ai denti", "4"}
    };

    public static Patologia[] patologie;

    public static void fillPatologie() {
        patologie = new Patologia[PATOLOGIE_STRING.length];
        for (int i = 0; i < patologie.length; i++) {
            patologie[i] = new Patologia();
            patologie[i].setCodice(PATOLOGIE_STRING[i][0]);
            patologie[i].setNome(PATOLOGIE_STRING[i][1]);
            patologie[i].setGravita(Integer.parseInt(PATOLOGIE_STRING[i][2]));
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

        fillPatologie();

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        dao = DAOFactory.getDAOEntity("DAOPatologia");
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
        for (Patologia e : patologie) {
            dao.create(e);
            System.out.println("patologia created");
        }

        System.out.println("---printing using get all---");
        List<Patologia> list = dao.getAll();
        for (Patologia e : list) {
            msg = e.getCodice() + " " + e.getNome() + " " + e.getGravita();
            System.out.println(msg);
        }

        System.out.println("---updating all queries (adding k to the names)---");
        for (Patologia e : list) {
            e.setNome(e.getNome() + "MOD");
            e.setGravita((e.getGravita() + 1) % 5 + 1);
            dao.update(e);
            System.out.println("patologia updated");
        }

        System.out.println("---printing using read---");
        for (Patologia e : list) {
            Patologia gotPat = dao.read(e.getCodice());
            msg = gotPat.getCodice() + " " + gotPat.getNome() + " " + gotPat.getGravita();
            System.out.println(msg);
        }
    }
}

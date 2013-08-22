package integration.dao;

import business.applicationservice.exception.InvalidInfermiereFieldException;
import business.entity.Infermiere;
import integration.connector.HQSQLConnector;
import mockit.Mockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DAOInfermiereTest {
    private static DAO<Infermiere> dao;

    private static final String[][] INFERMIERI_STRING = new String[][]{
            {"aaa", "bbb"},
            {"Bbb", "Ccc"},
            {"bbb", "Zzz"},
            {"OoO", "q Qq"},
            {"l LL", "KKk"}
    };

    public static Infermiere[] infermieri;

    public static void fillInfermieri() {
        infermieri = new Infermiere[INFERMIERI_STRING.length];
        for (int i = 0; i < infermieri.length; i++) {
            infermieri[i] = new Infermiere();
            try {
                infermieri[i].setNome(INFERMIERI_STRING[i][0]);
            } catch (InvalidInfermiereFieldException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                infermieri[i].setCognome(INFERMIERI_STRING[i][1]);
            } catch (InvalidInfermiereFieldException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
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

        fillInfermieri();

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        dao = DAOFactory.buildInstance("DAOInfermiere");
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
        for (Infermiere e : infermieri) {
            dao.create(e);
            System.out.println("infermiere created");
        }

        System.out.println("---printing using get all---");
        List<Infermiere> list = dao.getAll();
        for (Infermiere e : list) {
            msg = e.getId() + " " + e.getNome() + " " + e.getCognome();
            System.out.println(msg);
        }

        System.out.println("---updating all queries (adding k to the names)---");
        for (Infermiere e : list) {
            try {
                e.setNome(e.getNome() + "k");
            } catch (InvalidInfermiereFieldException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            try {
                e.setCognome(e.getCognome() + "k");
            } catch (InvalidInfermiereFieldException e1) {
                e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            dao.update(e);
            System.out.println("infermiere updated");
        }

        System.out.println("---printing using read---");
        for (Infermiere e : list) {
            Infermiere gotInf = dao.read(e.getId());
            msg = gotInf.getId() + " " + gotInf.getNome() + " " + gotInf.getCognome();
            System.out.println(msg);
        }
    }

}
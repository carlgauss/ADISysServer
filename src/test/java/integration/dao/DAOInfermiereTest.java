package integration.dao;

import business.entity.Infermiere;
import integration.connector.HQSQLConnector;
import mockit.Mockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DAOInfermiereTest {
    private static DAO<Infermiere> daoInfermiere;
    private Infermiere infermiere;

    private static final String[][] INFERMIERI_STRING = new String[][]{
            {"Francesco", "Callistoli"},
            {"Franco", "Scalati"},
            {"Nicola", "Opizio"},
            {"Lorenzo", "De Girolamo"},
            {"Francesca Maria", "Tripolese"}
    };

    public static Infermiere[] infermieri;

    public static void fillInfermieri() {
        infermieri = new Infermiere[INFERMIERI_STRING.length];
        for (int i = 0; i < infermieri.length; i++) {
            infermieri[i] = new Infermiere();
            infermieri[i].setNome(INFERMIERI_STRING[i][0]);
            infermieri[i].setCognome(INFERMIERI_STRING[i][1]);
        }
    }

    @Before
    public void setUp() throws Exception {
        HQSQLConnectorStub conn = new HQSQLConnectorStub();
        infermiere = new Infermiere();
        conn.deleteAll();
        try {
            conn.close();
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        conn = null;



        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        daoInfermiere = DAOFactory.getDAOEntity("DAOInfermiere");
    }

    @After
    public void tearDown() throws Exception {
        daoInfermiere = null;
        infermiere  = null;
        System.gc();
    }

    @Test
    public void testUnique() {
        fillInfermieri();
        for (Infermiere e : infermieri) {
            daoInfermiere.create(e);
        }

        List<Infermiere> list = daoInfermiere.getAll();

        for (Infermiere e : list) {
            e.setNome(e.getNome() + "k");
            e.setCognome(e.getCognome() + "k");
            daoInfermiere.update(e);
        }

        for (Infermiere e : list) {
            Infermiere gotInf = daoInfermiere.read(e.getId());
        }
    }


    @Test
    public void testCreateInfermiere() throws Exception {
        String nomeInfermiere = "nome infermiere";
        String cognomeInfermiere = "cognome infermiere";

        infermiere.setNome(nomeInfermiere);
        infermiere.setCognome(cognomeInfermiere);

        daoInfermiere.create(infermiere);

        List<Infermiere> tuttiInfermieri = daoInfermiere.getAll();

        //Assertions
        assertThat(tuttiInfermieri.size(), equalTo(1));
        assertThat(tuttiInfermieri.get(0).getNome(), equalTo(nomeInfermiere));
        assertThat(tuttiInfermieri.get(0).getCognome(), equalTo(cognomeInfermiere));
    }

    @Test  (expected = NullPointerException.class)
    public void testCreateInfermiereWithoutNome() throws Exception {
        String cognomeInfermiere = "cognome infermiere";
        infermiere.setCognome(cognomeInfermiere);

        daoInfermiere.create(infermiere);

        List<Infermiere> tuttiInfermieri = daoInfermiere.getAll();

        //Assertions
        assertThat(tuttiInfermieri.size(), equalTo(1));
        assertThat(tuttiInfermieri.get(0).getCognome(), equalTo(cognomeInfermiere));
    }

    @Test (expected = NullPointerException.class)
    public void testCreateInfermiereWithoutCognome() throws Exception {
        String nomeInfermiere = "nome infermiere";
        infermiere.setNome(nomeInfermiere);

        daoInfermiere.create(infermiere);

        List<Infermiere> tuttiInfermieri = daoInfermiere.getAll();

        //Assertions
        assertThat(tuttiInfermieri.size(), equalTo(1));
        assertThat(tuttiInfermieri.get(0).getNome(), equalTo(nomeInfermiere));
    }


    @Test
    public void testCreateInfermiereUpdateNome() throws Exception {
        String nomeInfermiere = "nome infermiere";
        String cognomeInfermiere = "cognome infermiere";

        infermiere.setNome(nomeInfermiere);
        infermiere.setCognome(cognomeInfermiere);

        daoInfermiere.create(infermiere);

        List<Infermiere> tuttiInfermieri = daoInfermiere.getAll();

        //Assertions
        assertThat(tuttiInfermieri.size(), equalTo(1));
        assertThat(tuttiInfermieri.get(0).getNome(), equalTo(nomeInfermiere));
        assertThat(tuttiInfermieri.get(0).getCognome(), equalTo(cognomeInfermiere));

        //Updating
        String newNomeInfermiere = "Nuovo nome";
        tuttiInfermieri.get(0).setNome(newNomeInfermiere);
        assertThat(tuttiInfermieri.get(0).getNome(), equalTo(newNomeInfermiere));
    }

    @Test
    public void testCreateInfermiereUpdateCognome() throws Exception {
        String nomeInfermiere = "nome infermiere";
        String cognomeInfermiere = "cognome infermiere";

        infermiere.setNome(nomeInfermiere);
        infermiere.setCognome(cognomeInfermiere);

        daoInfermiere.create(infermiere);

        List<Infermiere> tuttiInfermieri = daoInfermiere.getAll();

        //Assertions
        assertThat(tuttiInfermieri.size(), equalTo(1));
        assertThat(tuttiInfermieri.get(0).getNome(), equalTo(nomeInfermiere));
        assertThat(tuttiInfermieri.get(0).getCognome(), equalTo(cognomeInfermiere));

        //Updating
        String newCognomeInfermiere = "Nuovo cognome";
        tuttiInfermieri.get(0).setCognome(newCognomeInfermiere);
        assertThat(tuttiInfermieri.get(0).getCognome(), equalTo(newCognomeInfermiere));
    }


}

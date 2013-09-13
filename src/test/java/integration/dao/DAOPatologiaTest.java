package integration.dao;

import business.entity.Patologia;
import integration.connector.HQSQLConnector;
import mockit.Mockit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DAOPatologiaTest {

    private static DAO<Patologia> daoPatologia;
    private Patologia patologia;


    @Before
    public void setUp() throws Exception {
        HQSQLConnectorStub conn = new HQSQLConnectorStub();
        patologia = new Patologia();
        conn.deleteAll();
        try {
            conn.close();
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        conn = null;
        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        daoPatologia = DAOFactory.getDAOEntity("DAOPatologia");
    }

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

    public static void createPatologieDB() {
        daoPatologia = DAOFactory.getDAOEntity("DAOPatologia");
        for (Patologia e : patologie) {
            daoPatologia.create(e);
        }

    }



    @After
    public void tearDown() throws Exception {
        daoPatologia = null;
        patologia = null;
        System.gc();
    }

    @Test
    public void testUnique() {
        fillPatologie();
        for (Patologia e : patologie) {
            daoPatologia.create(e);
        }

        List<Patologia> list = daoPatologia.getAll();

        for (Patologia e : list) {
            e.setNome(e.getNome() + "MOD");
            e.setGravita((e.getGravita() + 1) % 5 + 1);
            daoPatologia.update(e);
        }

        for (Patologia e : list) {
            Patologia gotPat = daoPatologia.read(e.getCodice());
        }
    }


    @Test
    public void testCreatePatologia() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }

    @Test (expected = NullPointerException.class)
    public void testCreatePatologiaWithoutCodice() throws Exception {
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);


    }

    @Test (expected = NullPointerException.class)
    public void testCreatePatologiaWithoutNome() throws Exception {
        String codicePatologia = "654321";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);
    }

    @Test
    public void testCreatePatologiaWithoutGravita() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(0));

    }


    @Test
    public void testCreatePatologiaWithStrangeCodiceTooBig() throws Exception {
        String codicePatologia = "654321999000999";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

    }

    @Test
    public void testCreatePatologiaWithCodiceTooSmallLessThanSixCharacters() throws Exception {
        String codicePatologia = "1";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }

    @Test
    public void testCreatePatologiaWithCodiceZero() throws Exception {
        String codicePatologia = "0";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }

    @Test
    public void testCreatePatologiaWithNegativeCodice() throws Exception {
        String codicePatologia = "-654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);
    }


    @Test
    public void testCreatePatologiaWithGravitaGreaterThanSix() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 6;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }

    @Test
    public void testCreatePatologiaWithGravitaLesserThanOne() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 0;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }

    @Test
    public void testCreatePatologiaWithNegativeGravita() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = -60;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }


    @Test
    public void testUpdateCodicePatologia() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

        //Updating
        String newCodicePatologia = "102030";
        tuttePatologie.get(0).setCodice(newCodicePatologia);
        assertThat(tuttePatologie.get(0).getCodice(), equalTo(newCodicePatologia));

    }

    @Test
    public void testUpdateNomePatologia() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

        //Updating
        String newNomePatologia = "Nuovo nome";
        tuttePatologie.get(0).setNome(newNomePatologia);
        assertThat(tuttePatologie.get(0).getNome(), equalTo(newNomePatologia));

    }

    @Test
    public void testUpdateGravitaPatologia() throws Exception {
        String codicePatologia = "654321";
        String nomePatologia = "Broncopolmonite";
        int gravitaPatologia = 4;

        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);

        daoPatologia.create(patologia);

        //Assertions
        List<Patologia> tuttePatologie = daoPatologia.getAll();

        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getCodice(), equalTo(codicePatologia));
        assertThat(tuttePatologie.get(0).getNome(), equalTo(nomePatologia));
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

        //Updating
        int newGravitaPatologia = 1;
        tuttePatologie.get(0).setGravita(newGravitaPatologia);
        assertThat(tuttePatologie.get(0).getGravita(), equalTo(newGravitaPatologia));

    }
}

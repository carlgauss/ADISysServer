package integration.dao;

import business.entity.Patologia;
import business.entity.Paziente;
import integration.connector.HQSQLConnector;
import mockit.Mockit;
import org.joda.time.LocalDate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DAOPazienteTest {
    private static DAO<Paziente> daoPaziente;
    private static DAO<Patologia> daoPatologia;
    private Paziente paziente;


    private static final Object[][] ARRAY_PAZIENTI = new Object[][]{
            {"Rocco", "Coriandoli", new LocalDate(1965, 2, 22), new String[]{"0805241452", "368125510"}, new int[]{0, 2}},
            {"Nicola", "Amenicoli", new LocalDate(1988, 7, 17), new String[]{}, new int[]{0, 2, 3, 4, 5}},
            {"Renzo", "Ottato", new LocalDate(1979, 12, 1), new String[]{"3256971266"}, new int[]{4, 0, 3, 2}},
            {"Eleonora", "Drachi Malfieri", new LocalDate(1954, 1, 31), new String[]{"0809541289", "0805436699", "3018230953"}, new int[]{5, 0, 2}},
            {"Maria Caterina", "Colò", new LocalDate(1999, 8, 19), new String[]{"253735968", "021236490", "0804356715", "3206270012"}, new int[]{0, 3, 2}}
    };

    public static Paziente[] pazienti;

    @Before
    public void setUp() throws Exception {
        HQSQLConnectorStub connectorStub = new HQSQLConnectorStub();
        connectorStub.deleteAll();
        try {
            connectorStub.close();
        } catch (Throwable e1) {
            e1.printStackTrace();
        }
        connectorStub = null;

        Mockit.setUpMock(HQSQLConnector.class, HQSQLConnectorStub.class);
        //fillPazienti();

        daoPaziente = DAOFactory.getDAOEntity("DAOPaziente");
        daoPatologia = DAOFactory.getDAOEntity("DAOPatologia");
        paziente = new Paziente();
    }

    public static void fillPazienti() {

        DAOPatologiaTest.fillPatologie();
        DAOPatologiaTest.createPatologieDB();

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

            List<Patologia> patologiaList = new ArrayList<>();
            int[] elems = (int[]) ARRAY_PAZIENTI[i][4];
            for (int j : elems) {
                patologiaList.add(DAOPatologiaTest.patologie[j]);
            }
            pazienti[i].setPatologia(patologiaList);
        }
    }


    @After
    public void tearDown() throws Exception {
        daoPaziente = null;
        paziente = null;
        System.gc();
    }

    @Test
    public void testUnique() {
        fillPazienti();

        for (Paziente e : pazienti) {
            daoPaziente.create(e);
        }

        List<Paziente> list = daoPaziente.getAll();

        for (Paziente e : list) {
            e.setNome(e.getNome() + "k");
            e.setCognome(e.getCognome() + "k");

            List<String> newCellList = e.getNumeroCellulare();
            newCellList.add("123456789");
            e.setNumeroCellulare(newCellList);

            List<Patologia> newPatList = e.getPatologia();
            newPatList.add(DAOPatologiaTest.patologie[1]);
            e.setPatologia(newPatList);

            daoPaziente.update(e);
        }
    }


    @Test (expected = NullPointerException.class)
    public void testCreatePazienteWithoutCellphone() throws Exception {
        paziente.setNome("Nome Paziente");
        paziente.setCognome("Cognome Paziente");
        paziente.setData(new LocalDate(1200, 12, 19));
        daoPaziente.create(paziente);
    }

    @Test (expected = NullPointerException.class)
    public void testCreatePazienteWithoutPatologia() throws Exception {
        paziente.setNome("Nome Paziente");
        paziente.setCognome("Cognome Paziente");
        paziente.setData(new LocalDate(1200, 12, 19));

        List<String> cellList = new ArrayList<>();
        cellList.add("123456789");
        paziente.setNumeroCellulare(cellList);
        daoPaziente.create(paziente);
    }

    @Test
    public void testCreatePaziente() throws Exception {

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

        List<Patologia> patologiaList = new ArrayList<>();
        Patologia patologia = new Patologia();
        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);
        patologiaList.add(patologia);

        daoPatologia.create(patologia);

        paziente.setPatologia(patologiaList);


        daoPaziente.create(paziente);

        List<Paziente> listAllPazienti = daoPaziente.getAll();
        assertThat(listAllPazienti.isEmpty(), equalTo(false));
        assertThat(listAllPazienti.size(), equalTo(1));
        for (Paziente p : listAllPazienti) {
            assertThat(p.getNome(), equalTo(nomePaziente));
            assertThat(p.getCognome(), equalTo(cognomePaziente));
            assertThat(p.getData(), equalTo(dataNascitaPaziente));
        }

        List<Patologia> tuttePatologie = daoPatologia.getAll();
        assertThat(tuttePatologie.isEmpty(), equalTo(false));
        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }

    @Test
    public void testCreatePazienteWithTwoCellphones() throws Exception {

        String nomePaziente = "Il nome";
        String cognomePaziente = "Il cognome";
        LocalDate dataNascitaPaziente = new LocalDate(1700, 12, 19);
        String cellularePaziente1 = "123456789";
        String cellularePaziente2 = "222345678";

        String codicePatologia = "112233";
        String nomePatologia = "Tumore";
        int gravitaPatologia = 4;

        paziente.setNome(nomePaziente);
        paziente.setCognome(cognomePaziente);
        paziente.setData(dataNascitaPaziente);

        List<String> cellList = new ArrayList<>();
        cellList.add(cellularePaziente1);
        cellList.add(cellularePaziente2);
        paziente.setNumeroCellulare(cellList);

        List<Patologia> patologiaList = new ArrayList<>();
        Patologia patologia = new Patologia();
        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);
        patologiaList.add(patologia);

        daoPatologia.create(patologia);

        paziente.setPatologia(patologiaList);


        daoPaziente.create(paziente);

        List<Paziente> listAllPazienti = daoPaziente.getAll();
        assertThat(listAllPazienti.isEmpty(), equalTo(false));
        assertThat(listAllPazienti.size(), equalTo(1));
        for (Paziente p : listAllPazienti) {
            assertThat(p.getNome(), equalTo(nomePaziente));
            assertThat(p.getCognome(), equalTo(cognomePaziente));
            assertThat(p.getData(), equalTo(dataNascitaPaziente));
            assertThat(p.getNumeroCellulare().size(), equalTo(2));
        }

        List<Patologia> tuttePatologie = daoPatologia.getAll();
        assertThat(tuttePatologie.isEmpty(), equalTo(false));
        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));

    }

    @Test
    public void testCreatePazienteWithNoGravitaPatologia() throws Exception {
        String nomePaziente = "Il nome";
        String cognomePaziente = "Il cognome";
        LocalDate dataNascitaPaziente = new LocalDate(1700, 12, 19);
        String cellularePaziente = "123456789";

        String codicePatologia = "112233";
        String nomePatologia = "Tumore";
        int noSettedGravitaPatologia = 0;

        paziente.setNome(nomePaziente);
        paziente.setCognome(cognomePaziente);
        paziente.setData(dataNascitaPaziente);

        List<String> cellList = new ArrayList<>();
        cellList.add(cellularePaziente);
        paziente.setNumeroCellulare(cellList);

        List<Patologia> patologiaList = new ArrayList<>();
        Patologia patologia = new Patologia();
        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        // patologia.setGravita(gravitaPatologia);
        patologiaList.add(patologia);
        DAO<Patologia> daoPatologia = DAOFactory.getDAOEntity("DAOPatologia");
        daoPatologia.create(patologia);

        paziente.setPatologia(patologiaList);


        daoPaziente.create(paziente);

        List<Paziente> listAllPazienti = daoPaziente.getAll();
        assertThat(listAllPazienti.isEmpty(), equalTo(false));
        assertThat(listAllPazienti.size(), equalTo(1));
        for (Paziente p : listAllPazienti) {
            assertThat(p.getNome(), equalTo(nomePaziente));
            assertThat(p.getCognome(), equalTo(cognomePaziente));
            assertThat(p.getData(), equalTo(dataNascitaPaziente));
        }

        List<Patologia> tuttePatologie = daoPatologia.getAll();
        assertThat(tuttePatologie.isEmpty(), equalTo(false));
        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getGravita(), equalTo(noSettedGravitaPatologia));

    }

    @Test
    public void testCreatePazienteWithTwoPatologie() throws Exception {

        String nomePaziente = "Jack";
        String cognomePaziente = "Shephard";
        LocalDate dataNascitaPaziente = new LocalDate(1970, 12, 19);
        String cellularePaziente = "555667788";
        List<String> cellList = new ArrayList<>();
        cellList.add(cellularePaziente);

        paziente.setNome(nomePaziente);
        paziente.setCognome(cognomePaziente);
        paziente.setData(dataNascitaPaziente);
        paziente.setNumeroCellulare(cellList);


        String codicePatologia1 = "112233";
        String nomePatologia1 = "Tumore";
        int gravitaPatologia1 = 5;
        String codicePatologia2 = "111777";
        String nomePatologia2 = "Cancro";
        int gravitaPatologia2 = 5;


        List<Patologia> patologiaList = new ArrayList<>();

        Patologia patologia1 = new Patologia();
        patologia1.setCodice(codicePatologia1);
        patologia1.setNome(nomePatologia1);
        patologia1.setGravita(gravitaPatologia1);
        daoPatologia.create(patologia1);

        Patologia patologia2 = new Patologia();
        patologia2.setCodice(codicePatologia2);
        patologia2.setNome(nomePatologia2);
        patologia2.setGravita(gravitaPatologia2);
        daoPatologia.create(patologia2);

        patologiaList.add(patologia1);
        patologiaList.add(patologia2);

        paziente.setPatologia(patologiaList);
        daoPaziente.create(paziente);

        List<Paziente> listAllPazienti = daoPaziente.getAll();
        assertThat(listAllPazienti.isEmpty(), equalTo(false));
        assertThat(listAllPazienti.size(), equalTo(1));
        for (Paziente p : listAllPazienti) {
            assertThat(p.getNome(), equalTo(nomePaziente));
            assertThat(p.getCognome(), equalTo(cognomePaziente));
            assertThat(p.getData(), equalTo(dataNascitaPaziente));
        }

        List<Patologia> tuttePatologie = daoPatologia.getAll();
        assertThat(tuttePatologie.isEmpty(), equalTo(false));
        assertThat(tuttePatologie.size(), equalTo(2));


        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia1));
        assertThat(tuttePatologie.get(1).getGravita(), equalTo(gravitaPatologia2));



    }


    @Test
    public void testUpdateSoloNomePaziente() throws Exception {

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

        List<Patologia> patologiaList = new ArrayList<>();
        Patologia patologia = new Patologia();
        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);
        patologiaList.add(patologia);

        daoPatologia.create(patologia);
        paziente.setPatologia(patologiaList);
        daoPaziente.create(paziente);

        List<Paziente> listAllPazienti = daoPaziente.getAll();
        assertThat(listAllPazienti.isEmpty(), equalTo(false));
        assertThat(listAllPazienti.size(), equalTo(1));
        for (Paziente p : listAllPazienti) {
            assertThat(p.getNome(), equalTo(nomePaziente));
            assertThat(p.getCognome(), equalTo(cognomePaziente));
            assertThat(p.getData(), equalTo(dataNascitaPaziente));
        }

        List<Patologia> tuttePatologie = daoPatologia.getAll();
        assertThat(tuttePatologie.isEmpty(), equalTo(false));
        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));



        //Updating
        String nomeDaCambiare = "Nome Cambiato";
        listAllPazienti.get(0).setNome(nomeDaCambiare);

        assertThat(listAllPazienti.get(0).getNome(), equalTo(nomeDaCambiare));
    }

    @Test
    public void testUpdateSoloCognomePaziente() throws Exception {

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

        List<Patologia> patologiaList = new ArrayList<>();
        Patologia patologia = new Patologia();
        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);
        patologiaList.add(patologia);

        daoPatologia.create(patologia);
        paziente.setPatologia(patologiaList);
        daoPaziente.create(paziente);

        List<Paziente> listAllPazienti = daoPaziente.getAll();
        assertThat(listAllPazienti.isEmpty(), equalTo(false));
        assertThat(listAllPazienti.size(), equalTo(1));
        for (Paziente p : listAllPazienti) {
            assertThat(p.getNome(), equalTo(nomePaziente));
            assertThat(p.getCognome(), equalTo(cognomePaziente));
            assertThat(p.getData(), equalTo(dataNascitaPaziente));
        }

        List<Patologia> tuttePatologie = daoPatologia.getAll();
        assertThat(tuttePatologie.isEmpty(), equalTo(false));
        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));



        //Updating
        String cognomeDaCambiare = "Cognome Cambiato";
        listAllPazienti.get(0).setCognome(cognomeDaCambiare);

        assertThat(listAllPazienti.get(0).getCognome(), equalTo(cognomeDaCambiare));
    }

    @Test
    public void testUpdateSoloCellularePaziente() throws Exception {

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

        List<Patologia> patologiaList = new ArrayList<>();
        Patologia patologia = new Patologia();
        patologia.setCodice(codicePatologia);
        patologia.setNome(nomePatologia);
        patologia.setGravita(gravitaPatologia);
        patologiaList.add(patologia);

        daoPatologia.create(patologia);
        paziente.setPatologia(patologiaList);
        daoPaziente.create(paziente);

        List<Paziente> listAllPazienti = daoPaziente.getAll();
        assertThat(listAllPazienti.isEmpty(), equalTo(false));
        assertThat(listAllPazienti.size(), equalTo(1));
        for (Paziente p : listAllPazienti) {
            assertThat(p.getNome(), equalTo(nomePaziente));
            assertThat(p.getCognome(), equalTo(cognomePaziente));
            assertThat(p.getData(), equalTo(dataNascitaPaziente));
        }

        List<Patologia> tuttePatologie = daoPatologia.getAll();
        assertThat(tuttePatologie.isEmpty(), equalTo(false));
        assertThat(tuttePatologie.size(), equalTo(1));

        assertThat(tuttePatologie.get(0).getGravita(), equalTo(gravitaPatologia));



        //Updating
        cellList.remove(0);
        cellList.add(0, "999000888");
        listAllPazienti.get(0).setNumeroCellulare(cellList);

        assertThat(listAllPazienti.get(0).getNumeroCellulare().get(0), equalTo(cellList.get(0)));
    }

}

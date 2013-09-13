package business.applicationservice;
import business.entity.Accelerometro;
import business.entity.GPS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class ApplicationServiceVerificaTest {

    Accelerometro accelerometro;
    GPS gps;

    @Before
    public void setUp() throws Exception {
        accelerometro = new Accelerometro();
        gps = new GPS();
    }

    @After
    public void tearDown() throws Exception {
        accelerometro = null;
        gps = null;
    }

    @Test
    public void testDeriveGPS() throws Exception {

    }

    @Test
    public void testDeriveAccelerometro() throws Exception {

    }
}

package business.applicationservice;
import business.entity.Accelerometro;
import business.entity.GPS;
import business.transfer.Parameter;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
public class ApplicationServiceVerificaTest {

    ApplicationServiceVerifica  applicationServiceVerifica;

    Parameter parameter;


    @Before
    public void setUp() throws Exception {
        applicationServiceVerifica = new ApplicationServiceVerifica();
        parameter = new Parameter();
    }

    @After
    public void tearDown() throws Exception {
        applicationServiceVerifica = null;
        parameter = null;
    }


    @Test
    public void testDeriveGPSYellowSizeListGPSisOne() throws Exception {
        List<GPS> listaDiGPS = new ArrayList<>();
        GPS gps1 = new GPS();

        gps1.setLatitudine(80.0);
        gps1.setLongitudine(40.0);


        listaDiGPS.add(gps1);

        parameter.setValue("gps", listaDiGPS);

        Integer resultOfGPSAnalysis = applicationServiceVerifica.deriveGPS(parameter);

        assertThat(resultOfGPSAnalysis, equalTo(0));
    }

    @Test
    public void testDeriveGPSYellowSizeListGPSisZero() throws Exception {
        List<GPS> listaDiGPS = new ArrayList<>();

        parameter.setValue("gps", listaDiGPS);

        Integer resultOfGPSAnalysis = applicationServiceVerifica.deriveGPS(parameter);

        assertThat(resultOfGPSAnalysis, equalTo(0));
    }

    @Test (expected = NullPointerException.class)
    public void testDeriveGPSYellowSizeListGPSisNull() throws Exception {
        List<GPS> listaDiGPS = null;

        parameter.setValue("gps", listaDiGPS);

        Integer resultOfGPSAnalysis = applicationServiceVerifica.deriveGPS(parameter);

        assertThat(resultOfGPSAnalysis, equalTo(0));
    }

    @Test
    public void testDeriveGPSForGreenLightJustLittleUnder30Meters() throws Exception {
        List<GPS> listaDiGPS = new ArrayList<>();
        GPS gps1 = new GPS();
        GPS gps2 = new GPS();

        gps1.setLatitudine(80.0);
        gps1.setLongitudine(40.0);
        gps2.setLatitudine(80.00043419);
        gps2.setLongitudine(40.0);

        listaDiGPS.add(gps1);
        listaDiGPS.add(gps2);

        parameter.setValue("gps", listaDiGPS);

        Integer resultOfGPSAnalysis = applicationServiceVerifica.deriveGPS(parameter);

        assertThat(resultOfGPSAnalysis, equalTo(1));
    }

    @Test
    public void testDeriveGPSForRedLightJustLittleAbove30Meters() throws Exception {
        List<GPS> listaDiGPS = new ArrayList<>();
        GPS gps1 = new GPS();
        GPS gps2 = new GPS();

        gps1.setLatitudine(80.0);
        gps1.setLongitudine(40.0);
        gps2.setLatitudine(80.0004342);
        gps2.setLongitudine(40.0);

        listaDiGPS.add(gps1);
        listaDiGPS.add(gps2);

        parameter.setValue("gps", listaDiGPS);

        Integer resultOfGPSAnalysis = applicationServiceVerifica.deriveGPS(parameter);

        assertThat(resultOfGPSAnalysis, equalTo(-1));

    }


    @Test
    public void testDeriveGPSForGreenLightWhenDeviceIsStill() throws Exception {
        List<GPS> listaDiSegnaliGPS = new ArrayList<>();
        GPS gps1 = new GPS();
        GPS gps2 = new GPS();

        gps1.setLatitudine(80.0);
        gps1.setLongitudine(40.0);
        gps2.setLatitudine(80.0);
        gps2.setLongitudine(40.0);

        listaDiSegnaliGPS.add(gps1);
        listaDiSegnaliGPS.add(gps2);

        parameter.setValue("gps", listaDiSegnaliGPS);

        Integer resultOfGPSAnalysis = applicationServiceVerifica.deriveGPS(parameter);

        assertThat(resultOfGPSAnalysis, equalTo(1));

    }






    @Test
    public void testDeriveAccelerometroWhenListIsEmpty() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = new ArrayList<>();

        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(0));

    }

    @Test   (expected = NullPointerException.class)
    public void testDeriveAccelerometroWhenListIsNull() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = null;


        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(0));

    }

    @Test
    public void testDeriveAccelerometroForGREENWithNoMovements() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = new ArrayList<>();
        Accelerometro acc1 = new Accelerometro();
        Accelerometro acc2 = new Accelerometro();

        acc1.setData(new LocalDate(2100, 1, 1));
        acc1.setOra(new LocalTime(9, 0, 0));
        acc1.setX(9.0);
        acc1.setY(9.0);
        acc1.setZ(9.0);

        acc2.setData(new LocalDate(2100, 1, 1));
        acc2.setOra(new LocalTime(9, 0, 5));
        acc2.setX(9.0);
        acc2.setY(9.0);
        acc2.setZ(9.0);


        listaDiSegnaliAccelerometri.add(acc1);
        listaDiSegnaliAccelerometri.add(acc2);

        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(1));

    }

    @Test
    public void testDeriveAccelerometroForGREENWithMovements() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = new ArrayList<>();
        Accelerometro acc1 = new Accelerometro();
        Accelerometro acc2 = new Accelerometro();

        acc1.setData(new LocalDate(2100, 1, 1));
        acc1.setOra(new LocalTime(9, 0, 0));
        acc1.setX(9.0);
        acc1.setY(9.0);
        acc1.setZ(9.0);

        acc2.setData(new LocalDate(2100, 1, 1));
        acc2.setOra(new LocalTime(9, 0, 5));
        acc2.setX(27.0);
        acc2.setY(27.0);
        acc2.setZ(27.0);


        listaDiSegnaliAccelerometri.add(acc1);
        listaDiSegnaliAccelerometri.add(acc2);

        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(1));

    }

    @Test
    public void testDeriveAccelerometroForREDWithNoMovementOfTheDevice() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = new ArrayList<>();
        Accelerometro acc1 = new Accelerometro();
        Accelerometro acc2 = new Accelerometro();

        acc1.setData(new LocalDate(2100, 1, 1));
        acc1.setOra(new LocalTime(9, 0, 0));
        acc1.setX(9.0);
        acc1.setY(9.0);
        acc1.setZ(9.0);

        acc2.setData(new LocalDate(2100, 1, 1));
        acc2.setOra(new LocalTime(9, 30, 6));
        acc2.setX(9.0);
        acc2.setY(9.0);
        acc2.setZ(9.0);


        listaDiSegnaliAccelerometri.add(acc1);
        listaDiSegnaliAccelerometri.add(acc2);

        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(-1));

    }

    @Test
    public void testDeriveAccelerometroForREDWithMovementOfTheDevice() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = new ArrayList<>();
        Accelerometro acc1 = new Accelerometro();
        Accelerometro acc2 = new Accelerometro();

        acc1.setData(new LocalDate(2100, 1, 1));
        acc1.setOra(new LocalTime(9, 0, 0));
        acc1.setX(9.0);
        acc1.setY(9.0);
        acc1.setZ(9.0);

        acc2.setData(new LocalDate(2100, 1, 1));
        acc2.setOra(new LocalTime(9, 30, 6));
        acc2.setX(18.0);
        acc2.setY(18.0);
        acc2.setZ(18.0);


        listaDiSegnaliAccelerometri.add(acc1);
        listaDiSegnaliAccelerometri.add(acc2);

        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(-1));

    }

    @Test
    public void testDeriveAccelerometroForREDWithExact30MinutesWithMovements() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = new ArrayList<>();
        Accelerometro acc1 = new Accelerometro();
        Accelerometro acc2 = new Accelerometro();

        acc1.setData(new LocalDate(2100, 1, 1));
        acc1.setOra(new LocalTime(9, 0, 0));
        acc1.setX(9.0);
        acc1.setY(9.0);
        acc1.setZ(9.0);

        acc2.setData(new LocalDate(2100, 1, 1));
        acc2.setOra(new LocalTime(9, 30, 0));
        acc2.setX(18.0);
        acc2.setY(18.0);
        acc2.setZ(18.0);


        listaDiSegnaliAccelerometri.add(acc1);
        listaDiSegnaliAccelerometri.add(acc2);

        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(1));

    }

    @Test
    public void testDeriveAccelerometroForREDWithExact30MinutesWithNoMovements() throws Exception {

        List<Accelerometro> listaDiSegnaliAccelerometri = new ArrayList<>();
        Accelerometro acc1 = new Accelerometro();
        Accelerometro acc2 = new Accelerometro();

        acc1.setData(new LocalDate(2100, 1, 1));
        acc1.setOra(new LocalTime(9, 0, 0));
        acc1.setX(9.0);
        acc1.setY(9.0);
        acc1.setZ(9.0);

        acc2.setData(new LocalDate(2100, 1, 1));
        acc2.setOra(new LocalTime(9, 30, 0));
        acc2.setX(9.0);
        acc2.setY(9.0);
        acc2.setZ(9.0);


        listaDiSegnaliAccelerometri.add(acc1);
        listaDiSegnaliAccelerometri.add(acc2);

        parameter.setValue("accelerometro", listaDiSegnaliAccelerometri);

        Integer resultOfAccelerometerAnalysis = applicationServiceVerifica.deriveAccelerometro(parameter);

        assertThat(resultOfAccelerometerAnalysis, equalTo(1));

    }
}

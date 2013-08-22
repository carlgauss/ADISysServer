package util.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.Parameter;
import util.UnavaliableKeyException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ParameterTest {

    private String[] keys = {"nome", "cognome", "età"};
    private Object[] values = {"Carlo", "Ranieri", 38};
    private Parameter param = null;

    @Before
    public void setUp() throws Exception {
        param = new Parameter();
    }

    @After
    public void tearDown() throws Exception {
        param = null;
    }

    @Test
    public void testSetValue() {
        for (int i = 0; i < keys.length; i++) {
            param.setValue(keys[i], values[i]);
        }

        for (int i = 0; i < keys.length; i++) {
            Object value = param.getValue(keys[i]);
            String msg = "Expected" + values[i].toString() + "==" + value.toString();

            assertEquals(msg, value, values[i]);
        }
    }

    @Test
    public void testGetValue() {
        for (int i = 0; i < keys.length; i++) {
            param.setValue(keys[i], values[i]);
        }

        for (int i = 0; i < keys.length; i++) {
            Object value = param.getValue(keys[i]);
            String msg = "Expected" + values[i].toString() + "==" + value.toString();

            assertEquals(msg, value, values[i]);
        }

        try {
            param.getValue("città");
            fail("Not thrown UnavaliableKeyException");
        } catch (UnavaliableKeyException e) {
            //Success
        }
    }


}

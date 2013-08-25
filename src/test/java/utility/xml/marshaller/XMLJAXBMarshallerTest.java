package utility.xml.marshaller;

import business.entity.Journaling;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XMLJAXBMarshallerTest {
    XMLJAXBMarshaller unmarshaller;

    @Before
    public void setUp() throws Exception {
        unmarshaller = new XMLJAXBMarshaller("journaling/Journaling Nicola Opizio 2013-08-25.xml", Journaling.class);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testMarshal() throws Exception {
        //OK
    }

    @Test
    public void testUnmarshal() throws Exception {
        Journaling journaling = (Journaling) unmarshaller.unmarshal();

        System.out.println();
    }
}

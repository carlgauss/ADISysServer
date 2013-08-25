package integration.xml.marshaller;

public interface XMLMarshaller {
    public void marshal(Object entity);

    public Object unmarshal();
}

package util.xml.marshaller;

public interface XMLMarshaller {
	public void marshal(Object entity);
	public Object unmarshal();
}

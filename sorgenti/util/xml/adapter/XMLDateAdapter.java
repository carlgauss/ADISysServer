package util.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.LocalDate;

public class XMLDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		return LocalDate.parse(v);
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		return v.toString();
	}

}

package utility.xml.adapter;

import org.joda.time.LocalTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;


public class XMLTimeAdapter extends XmlAdapter<String, LocalTime> {

    @Override
    public LocalTime unmarshal(String v) throws Exception {
        return LocalTime.parse(v);
    }

    @Override
    public String marshal(LocalTime v) throws Exception {
        return v.toString();
    }

}

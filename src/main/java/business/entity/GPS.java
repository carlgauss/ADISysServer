package business.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "gps")
public class GPS implements Entity {
    private static final long serialVersionUID = -1552393217862969096L;

    private double latitudine;
    private double longitudine;

    public double getLongitudine() {
        return longitudine;
    }

    @XmlElement
    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    @XmlElement
    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public String toString() {
        return "(" + latitudine + ", " + longitudine + ")";
    }
}

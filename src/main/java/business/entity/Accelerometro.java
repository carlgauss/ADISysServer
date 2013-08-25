package business.entity;

import integration.xml.adapter.XMLDateAdapter;
import integration.xml.adapter.XMLTimeAdapter;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlType(propOrder = {"x", "y", "z", "data", "ora"})
public class Accelerometro implements Entity {
    private static final long serialVersionUID = -5064182844412366320L;
    private double x;
    private double y;
    private double z;

    private LocalDate data;
    private LocalTime ora;

    @XmlElement
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @XmlElement
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @XmlElement
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @XmlJavaTypeAdapter(XMLDateAdapter.class)
    @XmlElement
    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @XmlJavaTypeAdapter(XMLTimeAdapter.class)
    @XmlElement
    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}

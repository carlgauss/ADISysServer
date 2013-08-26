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

    private static final double EARTH_RADIUS = 3958.75;

    public double distance(GPS gps) {
        double latitudeDistance = Math.toRadians(gps.latitudine - latitudine);
        double longitudeDistance = Math.toRadians(gps.longitudine - longitudine);

        double sineLatitudeDistance = Math.sin(latitudeDistance / 2);
        double sineLongitudeDistance = Math.sin(longitudeDistance / 2);

        double angle = Math.pow(sineLatitudeDistance, 2) +
                Math.pow(sineLongitudeDistance, 2) * Math.cos(latitudine) * Math.cos(gps.latitudine);

        double coefficient = 2 * Math.atan2(Math.sqrt(angle), Math.sqrt(1 - angle));

        double distance = EARTH_RADIUS * coefficient;

        return distance;
    }
}

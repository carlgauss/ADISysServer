package business.entity;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 14/08/13
 * Time: 19:25
 * To change this template use File | Settings | File Templates.
 */
public class GPS implements Entity {
    private static final long serialVersionUID = -1552393217862969096L;

    private double latitudine;
    private double longitudine;

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public String toString() {
        return "(" + latitudine + ", " + longitudine + ")";
    }
}

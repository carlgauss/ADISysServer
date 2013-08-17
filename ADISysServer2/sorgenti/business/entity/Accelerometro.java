package business.entity;

import org.joda.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: michelesummo
 * Date: 15/08/13
 * Time: 01:14
 * To change this template use File | Settings | File Templates.
 */
public class Accelerometro implements Entity {
    private static final long serialVersionUID = -5064182844412366320L;
    private double x;
    private double y;
    private double z;
    private LocalDateTime data;

    public Accelerometro() {
        data = LocalDateTime.now();
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}

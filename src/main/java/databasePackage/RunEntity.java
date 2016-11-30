package databasePackage;

import javax.persistence.*;

/**
 * Created by Hebo on 30.11.2016.
 */
@Entity
@Table(name = "run", schema = "firma_transportowa", catalog = "")
public class RunEntity {
    private int idRun;
    private String name;
    private double price;
    private double distance;
    private String warnings;
    private CarsEntity carsByCarsId;

    @Id
    @Column(name = "id_run")
    public int getIdRun() {
        return idRun;
    }

    public void setIdRun(int idRun) {
        this.idRun = idRun;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "distance")
    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Basic
    @Column(name = "warnings")
    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunEntity runEntity = (RunEntity) o;

        if (idRun != runEntity.idRun) return false;
        if (Double.compare(runEntity.price, price) != 0) return false;
        if (Double.compare(runEntity.distance, distance) != 0) return false;
        if (name != null ? !name.equals(runEntity.name) : runEntity.name != null) return false;
        if (warnings != null ? !warnings.equals(runEntity.warnings) : runEntity.warnings != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idRun;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (warnings != null ? warnings.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cars_id", referencedColumnName = "id_cars")
    public CarsEntity getCarsByCarsId() {
        return carsByCarsId;
    }

    public void setCarsByCarsId(CarsEntity carsByCarsId) {
        this.carsByCarsId = carsByCarsId;
    }
}

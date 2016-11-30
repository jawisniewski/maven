package databasePackage;

import javax.persistence.*;

/**
 * Created by Hebo on 30.11.2016.
 */
@Entity
@Table(name = "cars", schema = "firma_transportowa", catalog = "")
public class CarsEntity {
    private int idCars;
    private String name;
    private int course;
    private String year;
    private String warnings;

    @Id
    @Column(name = "id_cars")
    public int getIdCars() {
        return idCars;
    }

    public void setIdCars(int idCars) {
        this.idCars = idCars;
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
    @Column(name = "Course")
    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    @Basic
    @Column(name = "year")
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

        CarsEntity that = (CarsEntity) o;

        if (idCars != that.idCars) return false;
        if (course != that.course) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (warnings != null ? !warnings.equals(that.warnings) : that.warnings != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCars;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + course;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (warnings != null ? warnings.hashCode() : 0);
        return result;
    }
}

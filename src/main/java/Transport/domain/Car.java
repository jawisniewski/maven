package Transport.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Collection;


@Entity
@NamedQueries({
        @NamedQuery(name="car.all", query = "select b from Car b"),
        @NamedQuery(name = "car.byName",query = "select b from Car b where b.name=:name "),
        @NamedQuery(name = "car.byYear",query = "select a from Car a where a.year>:year")
})
public class Car {

    private Long idCar;
    private String name;
    private String warnings;
    private double course;
    private int year;
    private Collection<Run> runs;


    @Id
    @Column(name = "idCar")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return idCar;
    }

    public void setId(Long idCar) {
        this.idCar = idCar;
    }
    //@Column(unique = true,nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setCourse(double course) {
        this.course = course;
    }


    public double getCourse() {
        return course;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
//    @ManyToOne
//    @JoinColumn(name = "run_idRun")
//    public Run getRuns() {
//        return runs;
//    }
//
//    public void setRuns(Run runs) {
//        this.runs = runs;
//    }
//

    @OneToMany(mappedBy = "cars",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    public Collection<Run> getRuns() {
        return runs;
    }

    public void setRuns(Collection<Run> runs) {
        this.runs = runs;
    }

}

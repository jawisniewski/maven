package Transport.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@Entity
@NamedQueries({
        @NamedQuery(name="car.all", query = "select b from Car b"),
        @NamedQuery(name = "car.byTitle",query = "select b from Car b where b.tittle=:tittle "),
        @NamedQuery(name = "car.byRun",query = "select a from Car a where a.types=:types")
})
public class Car {

    private Long idCar;
    private String tittle;
    private String country;
    private int yearB;
    private Run runs;


    @Id
    @Column(name = "IDCar")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return idCar;
    }

    public void setId(Long idCar) {
        this.idCar = idCar;
    }
    @Column(unique = true,nullable = false)
    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYearB() {
        return yearB;
    }

    public void setYearB(int yearB) {
        this.yearB = yearB;
    }
    @ManyToOne
    @JoinColumn(name = "Run")
    public Run getTypes() {
        return runs;
    }

    public void setTypes(Run run) {
        this.runs = run;
    }
}

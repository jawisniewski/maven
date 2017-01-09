package Transport.domain;

import java.util.*;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "run.all", query = "Select t from Run t"),
		@NamedQuery(name = "run.byDescript",query = "select t from Run t where t.descript=:descript"),
		@NamedQuery(name = "run.byCar",query = "select a from Run a where a.cars=:cars")
})
public class Run {

	private Long idRun;
	private String name;
	private String descript;
	private double price;
	private double distance;
	private Car cars;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="idRun")
	public Long getId() {
		return idRun;
	}
	public void setId(Long idRun) {
		this.idRun = idRun;
	}
//
//	@Column(unique = true,nullable = false)
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}


	@ManyToOne
	@JoinColumn(name = "car_IdCar")
	public Car getCars() {
		return cars;
	}

	public void setCars(Car cars) {
		this.cars = cars;
	}
}

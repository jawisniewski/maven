package Transport.domain;

import java.util.*;

import javax.persistence.*;

@Entity
@NamedQueries({
		@NamedQuery(name = "run.all", query = "Select t from Run t"),
		@NamedQuery(name = "run.byDescript",query = "select t from Run t where t.descript=:descript")

})
public class Run {

	private Long idRun;
	private String descript;
	private int yearT;
	private String shortcut;
	private Collection<Car> cars;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="idRun")
	public Long getId() {
		return idRun;
	}
	public void setId(Long idRun) {
		this.idRun = idRun;
	}

	@Column(unique = true,nullable = false)
	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getYearT() {
		return yearT;
	}

	public void setYearT(int yearT) {
		this.yearT = yearT;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setShortcut(String shortcut) {
		this.shortcut = shortcut;
	}
	@OneToMany(mappedBy = "types",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	public Collection<Car> getCars() {
		return cars;
	}

	public void setCars(Collection<Car> cars) {
		this.cars = cars;
	}
}

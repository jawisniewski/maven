package Transport.service;

import java.util.ArrayList;
import java.util.List;

import Transport.domain.Car;
import Transport.domain.Run;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TransMangerHibernateImpl implements TransManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Long addRun(Run run) {
		run.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(run);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Run> getAllTypes() {
		return sessionFactory
				.getCurrentSession()
				.getNamedQuery("run.all")
				.list();
	}

	@Override
	public Run findByIdType(Long id) {
		return (Run)sessionFactory
				.getCurrentSession()
				.get(Run.class,id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Run findByDescriptType(String descript) {
		return (Run)sessionFactory
				.getCurrentSession()
				.getNamedQuery("run.byDescript")
				.setString("descript",descript)
				.uniqueResult();
	}

	@Override
	public void deleteRun(Run runs) {
		runs = (Run) sessionFactory.getCurrentSession()
				.get(Run.class,runs.getId());
		if(runs.getCars()!=null)
			for (Car car :runs.getCars()){
					car.setTypes(null);
					sessionFactory.getCurrentSession().update(car);

			}
		sessionFactory.getCurrentSession().delete(runs);
	}

	@Override
	public Long addCar(Car car) {
		car.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Car> getAllCar() {
		return sessionFactory
				.getCurrentSession()
				.getNamedQuery("car.all")
				.list();
	}

	@Override
	public Car findByIdCar(Long id) {
		return (Car) sessionFactory
				.getCurrentSession()
				.get(Car.class,id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Car findByTittleCar(String tittle) {
		return (Car) sessionFactory
				.getCurrentSession()
				.getNamedQuery("car.byTitle")
				.setString("tittle",tittle)
				.uniqueResult();
	}

	@Override
	public void deleteCar(Car car) {
		car = (Car) sessionFactory
				.getCurrentSession()
				.get(Car.class, car.getId());
		sessionFactory.getCurrentSession().delete(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Car> getAllRunCar(Long idType) {
		Run runs = (Run) sessionFactory.getCurrentSession()
				.get(Run.class, idType);
		List<Car> allRunCar =new ArrayList<>();
		allRunCar =sessionFactory.getCurrentSession()
				.getNamedQuery("car.byRun")
				.setLong("types",idType)
				.list();

		return allRunCar;
	}

	@Override
	public void addRunToCar(Long idType, Long idBook) {
		Car car = (Car) sessionFactory.getCurrentSession().get(
				Car.class, idBook);
		Run types = (Run) sessionFactory.getCurrentSession()
				.get(Run.class, idType);
		car.setTypes(types);
	}

	@Override
	@SuppressWarnings("unchecked")
//	tymczasowo na testy
	public void clearAll() {
		sessionFactory
				.getCurrentSession()
				.createQuery("delete from Run")
				.executeUpdate();
		sessionFactory
				.getCurrentSession()
				.createQuery("delete from Cars")
				.executeUpdate();
	}


}

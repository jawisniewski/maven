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
	public List<Run> getAllRun() {
		return sessionFactory
				.getCurrentSession()
				.getNamedQuery("run.all")
				.list();
	}

	@Override
	public Run findByIdRun(Long id) {
		return (Run)sessionFactory
				.getCurrentSession()
				.get(Run.class,id);
	}

	@Override
	public Run findByDescriptRun(String descript) {
		return (Run)sessionFactory
				.getCurrentSession()
				.getNamedQuery("run.byDescript")
				.setString("descript",descript)
				.uniqueResult();
	}

	@Override
	public void deleteRun(Run runs) {
//		runs = (Run) sessionFactory.getCurrentSession()
//				.get(Run.class,runs.getId());
//		if(runs.getCars()!=null)
//			for (Car car :runs.getCars()){
//					car.setName(null);
//					sessionFactory.getCurrentSession().update(car);
//
//			}
//		sessionFactory.getCurrentSession().delete(runs);
//

		runs = (Run) sessionFactory
				.getCurrentSession()
				.get(Run.class, runs.getId());
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
	public Car findByNameCar(String name) {
		return (Car) sessionFactory
				.getCurrentSession()
				.getNamedQuery("car.byName")
				.setString("name",name)
				.uniqueResult();
	}

	@Override
	public void deleteCar(Car car) {
		car = (Car) sessionFactory.getCurrentSession()
				.get(Car.class,car.getId());
		if(car.getRuns()!=null)
			for (Run run :car.getRuns()){
				run.setCars(null);
				sessionFactory.getCurrentSession().update(run);

			}
		sessionFactory.getCurrentSession().delete(car);


	}

	@Override
	public List<Run> getAllRunCar(Long idCars) {
//		Run runs = (Run) sessionFactory.getCurrentSession()
//				.get(Run.class, idCars);
		List<Run> allRunCar ;
		allRunCar =sessionFactory.getCurrentSession()
				.getNamedQuery("run.byCar")
				.setLong("cars",idCars)
				.list();

		return allRunCar;
	}

	@Override
	public void addRunToCar(Long idRun, Long idCar) {
		Car car = (Car) sessionFactory.getCurrentSession().get(
				Car.class, idCar);
		Run run = (Run) sessionFactory.getCurrentSession()
				.get(Run.class, idRun);
		run.setCars(car);
	}

	@Override
	public void clearAll() {
		sessionFactory
				.getCurrentSession()
				.createQuery("delete from Run")
				.executeUpdate();
		sessionFactory
				.getCurrentSession()
				.createQuery("delete from Car")
				.executeUpdate();
	}


}

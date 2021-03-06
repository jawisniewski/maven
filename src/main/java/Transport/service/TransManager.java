package Transport.service;

import java.util.List;

import Transport.domain.Car;
import Transport.domain.Run;

public interface TransManager {

	Long addRun(Run run);
	List<Run> getAllRun();
	Run findByIdRun(Long id);
	Run findByDescriptRun(String descript);
	void deleteRun(Run runs);
	void editRun(Run run);

	Long addCar(Car car);
	List<Car> getAllCar();
	Car findByIdCar(Long id);
	List<Car> findByNameCar(String name);
	List<Car> findCarByYear(int year);
	void deleteCar(Car car);
	void editCar(Car car);

	List<Run> getAllRunCar(Long idCar);
	void addRunToCar(Long idType,Long idBook);

	void clearAll();

}

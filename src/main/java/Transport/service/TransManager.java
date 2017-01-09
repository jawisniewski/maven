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


	Long addCar(Car car);
	List<Car> getAllCar();
	Car findByIdCar(Long id);
	Car findByNameCar(String name);
	void deleteCar(Car car);

	List<Run> getAllRunCar(Long idCar);
	void addRunToCar(Long idType,Long idBook);
	void clearAll();

}

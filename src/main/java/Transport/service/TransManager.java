package Transport.service;

import java.util.List;

import Transport.domain.Car;
import Transport.domain.Run;

public interface TransManager {

	Long addRun(Run runs);
	List<Run> getAllTypes();
	Run findByIdType(Long id);
	Run findByDescriptType(String descript);
	void deleteRun(Run runs);


	Long addCar(Car car);
	List<Car> getAllCar();
	Car findByIdCar(Long id);
	Car findByTittleCar(String tittle);
	void deleteCar(Car car);

	List<Car> getAllRunCar(Long idType);
	void addRunToCar(Long idType,Long idBook);
	void clearAll();

}

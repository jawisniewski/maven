package Transport.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import Transport.domain.Car;
import Transport.domain.Run;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class TransManagerTest {

    String randomString="";
    Run run1=new Run();
    Run run2 = new Run();
    Run run3 = new Run();
    Car car1 =new Car();

    Car car2 =new Car();
    @Autowired
    TransManager transManager;

    @Before
    public void setUp() throws Exception {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        randomString= sb.toString();

        run1.setDescript(randomString);
        run1.setName("pol-eng");
        run1.setDistance(4000.00);
        run1.setPrice(4000.00);

        run2.setDescript(randomString);
        run2.setName("pol-fra");
        run2.setDistance(3000.00);
        run2.setPrice(3000.00);

        run3.setDescript(randomString);
        run3.setName("pol-ger");
        run3.setDistance(2000.00);
        run3.setPrice(2000.00);

        car1.setName("MAN TGA");
        car1.setWarnings(randomString);
        car1.setYear(2015);
        car1.setCourse(20000);


        car2.setName("DAF XF");
        car2.setWarnings(randomString);
        car2.setYear(2010);
        car2.setCourse(200000);
    }

    @After
    @Rollback
    public void tearDown() throws Exception {

        //transManager.clearAll();
    }

    @Test
    public void addRunCheck() {

    //    Long id= transManager.addRun(run1);
        transManager.addRun(run1);
        assertEquals(randomString,
                transManager
                        .findByDescriptRun(run1.getDescript())
                        .getDescript()
        );

   }
//
    @Test
    public void findByNameCar() {

    long      id=  transManager.addCar(car1);

        transManager.addCar(car2);

//        run1.setCars(allCars);
//        assertEquals(car2.getName(),
//                transManager
//                        .findByNameCar(car2.getName())
//                        .getName()
//
//        );

        assertNotNull(car2.getName(),transManager.findByNameCar(car2.getName()));
        assertEquals(car1.getName(),transManager.findByNameCar("MAN").get(0).getName());

        assertEquals(car1.getName(),transManager.findByNameCar("TGA").get(0).getName());
    }


//
    @Test
    public void addCarCheck() {
//
  //     Long id= transManager.addRun(run1);
//        Collection<Car> allCars =new ArrayList<>();
//        allCars.add(car1);
      // allCars.add(car2);


      //  Long idCar1= transManager.addCar(car1);
        transManager.addCar(car2);
//        run1.setCars(allCars);
        assertEquals(car2.getId(),
                transManager
                        .findByIdCar(car2.getId())
                        .getId()

        );


    }
//
    @Test
    public void getAllRunCheck() throws FileNotFoundException {


        transManager.addRun(run1);

        transManager.addRun(run2);
        assertTrue(2<=transManager.getAllRun().size());


    }

    @Test
    public void getAllCarCheck() {

        transManager.addCar(car1);
        transManager.addCar(car2);
        assertTrue(2<=transManager.getAllCar().size());

    }
    @Test
    public void findByIdRun() throws Exception {

        Long id= transManager.addRun(run1);

         transManager.addRun(run2);
        assertEquals(run1.getName(), transManager.findByIdRun(id).getName());

    }

    @Test
    public void deleteRun() throws Exception {

        Long idRun1= transManager.addRun(run1);

        Long idRun2= transManager.addRun(run2);
        transManager.deleteRun(run1);
        try {
            transManager.findByIdRun(idRun1);
        }catch (NullPointerException e){
            assertTrue(true);
        }
        assertEquals(idRun2, transManager.findByIdRun(idRun2).getId());


    }

    @Test
    public void findByIdCar() throws Exception {
        Long id= transManager.addCar(car1);
        assertEquals(car1.getName(), transManager.findByIdCar(id).getName());

    }

    @Test
    public void deleteCar() throws Exception {

        Long idCar= transManager.addCar(car1);

        Long idCar2= transManager.addCar(car2);
        transManager.deleteCar(car2);
        try {
            transManager.findByIdCar(idCar2);
        }catch (NullPointerException e){
            assertTrue(true);
        }
        assertEquals(idCar, transManager.findByIdCar(idCar).getId());

    }
    @Test
    public void addRunToCar() throws Exception {

        Long id= transManager.addRun(run1);
        Long id2 = transManager.addRun(run2);
        Long idCar2= transManager.addCar(car2);

        Long idCar1= transManager.addCar(car1);
        transManager.addRunToCar(id,idCar1);
        transManager.addRunToCar(id2,idCar1);
      //  transManager.addRunToCar(id,idCar2);
      //  assertEquals();
//        //sprawdza i wyswietla wszystkie x nalezace do y
       assertEquals(id, transManager.getAllRunCar(idCar1).get(0).getId());

        assertEquals(id2, transManager.getAllRunCar(idCar1).get(1).getId());
        assertEquals(0,transManager.getAllRunCar(idCar2).size());
//        assertTrue(transManager.getAllRunCar(idType).get(0).getId()!=idRuby);
    }

    @Test
    public void findCarByYear() throws Exception{
        transManager.addCar(car1);
        transManager.addCar(car2);
       assertTrue( 1 <=transManager.findCarByYear(2013).size());
     //  assertEquals("MAN TGA",transManager.findCarByYear(2013).get(0).getName());
    }

    @Test
    public void editCar() throws  Exception{
        transManager.addCar(car1);
        transManager.addCar(car2);

      Car  editCar = transManager.findByIdCar(car1.getId());

        editCar.setName("Iveco");
        transManager.editCar(editCar);
        assertEquals(editCar.getName(),transManager.findByIdCar(editCar.getId()).getName());

    }
    public void editRun() throws  Exception{
        transManager.addRun(run1);
        transManager.addRun(run2);

        Run editRun = transManager.findByIdRun(run1.getId());

        editRun.setName("eng-pol");
        transManager.editRun(editRun);
        assertEquals(editRun.getName(),transManager.findByIdRun(editRun.getId()).getName());
        assertEquals(run2.getName(),transManager.findByIdRun(run1.getId()).getName());

    }
}

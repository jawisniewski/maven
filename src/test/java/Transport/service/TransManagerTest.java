package Transport.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import Transport.domain.Car;
import Transport.domain.Run;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
    }

//    @After
//    public void tearDown() throws Exception {
//        transManager.clearAll();
//    }

    @Test
    public void addRunCheck() {
        Run run1=new Run();
        run1.setDescript(randomString);
        run1.setShortcut("bardzooo strasznyy");
        run1.setYearT(43321);
        transManager.addRun(run1);
        assertEquals(randomString,
                transManager
                        .findByDescriptType(run1.getDescript())
                        .getDescript()
        );

    }

    @Test
    public void addCarCheck() {
        Run run1=new Run();
        run1.setDescript(randomString);
        run1.setShortcut("bardzooo strasznyy2");
        run1.setYearT(43321);
        Long idType= transManager.addRun(run1);
        Car car1 =new Car();
        car1.setCountry("polska");
        car1.setTittle(randomString);
        car1.setYearB(1234);
        car1.setTypes(run1);

        Collection<Car> allCars =new ArrayList<>();
        allCars.add(car1);
        run1.setCars(allCars);

        Long idCar1= transManager.addCar(car1);

        assertEquals(randomString,
                transManager
                        .findByTittleCar(car1.getTittle())
                        .getTittle()

        );
        assertEquals(idType, transManager.findByIdCar(idCar1).getTypes().getId());


    }

    @Test
    public void getAllRunCheck() throws FileNotFoundException {
        Run sifi=new Run();
        sifi.setDescript(randomString);
        sifi.setYearT(1234);
        sifi.setShortcut("sifi");
        transManager.addRun(sifi);
        assertTrue(transManager.getAllTypes().size()>=1);


    }

    @Test
    public void getAllCarCheck() {
        Car java=new Car();
        java.setTittle(randomString);
        java.setCountry("polska");
        java.setYearB(1413);
        transManager.addCar(java);
        assertTrue(transManager.getAllCar().size()>=1);

    }
    @Test
    public void findByIdRun() throws Exception {
        Run komedia=new Run();
        komedia.setDescript(randomString);
        komedia.setShortcut("parodia");
        komedia.setYearT(123123);
        Long id= transManager.addRun(komedia);
        assertEquals(randomString, transManager.findByIdType(id).getDescript());
    }

    @Test
    public void deleteRun() throws Exception {
        Run poezja=new Run();
        poezja.setDescript(randomString);
        Long idPoezja= transManager.addRun(poezja);
        Run horror=new Run();
        setUp();
        horror.setDescript(randomString);
        Long idHorror= transManager.addRun(horror);
        transManager.deleteRun(poezja);
        try {
            transManager.findByIdType(idPoezja);
        }catch (NullPointerException e){
            assertTrue(true);
        }
        assertEquals(idHorror, transManager.findByIdType(idHorror).getId());


    }

    @Test
    public void findByIdCar() throws Exception {
        Car cs=new Car();
        cs.setTittle(randomString);
        cs.setCountry("MS");
        cs.setYearB(1134);
        Long id= transManager.addCar(cs);
        assertEquals(randomString, transManager.findByIdCar(id).getTittle());

    }

    @Test
    public void deleteCar() throws Exception {
        Car aglebra=new Car();
        aglebra.setTittle(randomString);
        Long idAlgebra= transManager.addCar(aglebra);
        Car dyskretna=new Car();
        setUp();
        dyskretna.setTittle(randomString);
        Long idDyskretna= transManager.addCar(dyskretna);
        transManager.deleteCar(aglebra);
        try {
            transManager.findByIdCar(idAlgebra);
        }catch (NullPointerException e){
            assertTrue(true);
        }
        assertEquals(idDyskretna, transManager.findByIdCar(idDyskretna).getId());

    }
    @Test
    public void addRunToCar() throws Exception {
        Run masakra=new Run();
        masakra.setDescript(randomString);
        masakra.setShortcut("masakiera");
        masakra.setYearT(1233);
        Long idType= transManager.addRun(masakra);
        Car car2 =new Car();
        setUp();
        car2.setCountry("polska2");
        car2.setTittle(randomString);
        car2.setYearB(12342);
        Long idBook2= transManager.addCar(car2);
        Car ruby=new Car();
        setUp();
        ruby.setTittle(randomString);
        ruby.setYearB(23);
        ruby.setCountry("sdasd");
        Long idRuby= transManager.addCar(ruby);
        transManager.addRunToCar(idType,idBook2);
        assertEquals(idType, transManager.findByIdCar(idBook2).getTypes().getId());
        //sprawdza i wyswietla wszystkie x nalezace do y
        assertEquals(idBook2, transManager.getAllRunCar(idType).get(0).getId());
        assertTrue(transManager.getAllRunCar(idType).get(0).getId()!=idRuby);
    }

}

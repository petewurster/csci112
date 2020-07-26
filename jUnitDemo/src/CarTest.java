/**
 * CarTest.java
 * 2020-07-26 pWurster
 *
 * This program demonstrates unit testing via assertions using the
 * jUnit library.  JUnit's .jar files are included in the "lib"
 * folder of this project for ease of demonstration.
 *
 */



import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;


public class CarTest {

    private Car car;

    @Before
    public void setup() {
        car = new Car();
    }



    @After
    public void nullify() {
        car = null;
    }



    @Test
    /**
     * full tank is 15 gallons of fuel;
     * 5 gallons of fuel are spent every time the car drives 100 miles
     *
     * invoke drive() 3 times to travel 300 miles: fuel should be 0 and odometer should read 300
     * then invoke drive() an additional time to ensure that car cannot travel without fuel
     */
    public void fuelOdometerTest() {

        for (int i = 0; i < 3; i++) car.drive();

        assertEquals(0, car.getFuel());
        assertEquals(300, car.getOdometer());

        car.drive();
        assertEquals(300, car.getOdometer());

    }





    @Test
    /**
     * invoke accelerate() and ensure that car speeds up;
     * invoke brake() twice to ensure car slows down but not below 0
     */
    public void speedTest() {

        car.accelerate();
        assertEquals(10, car.getSpeed());

        car.brake(); car.brake();
        assertEquals(0, car.getSpeed());
    }





    @Test
    /**
     * use 5 gallons of fuel to drive() 100 miles;
     * ensure values have changed
     * test the toString() method to ensure that its output matches the regex
     * (really this is just to demonstrate usage of a boolean assertion)
     */
    public void spellingTest() {
        String pattern = "Gallons remaining: \\d?\\d, Miles: \\d+";

        car.drive();
        assertEquals(10, car.getFuel());
        assertEquals(100, car.getOdometer());

        assertTrue(car.toString().matches(pattern));
    }




}
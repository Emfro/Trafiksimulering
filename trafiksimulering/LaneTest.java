import static org.junit.Assert.*;

import org.junit.Test;


public class LaneTest {

	@Test
	public void test() {
		
		Lane tester = new Lane(10);
		Car testCar = new Car(1,1,null);
				
/**
 * 		Tests lastFree
 */
		assertEquals(true, tester.lastFree());
/**
 * 		Adds car to last spot
 */
		tester.putLast(testCar);
/**
 * 		Tests if last spot is free again and tests putLast
 */
		assertEquals(false, tester.lastFree());
		
		testCar.setCurrentPosition(tester.getCarSpot(5));
		
		tester.getCarSpot(5).setCurrentCar(testCar);
		
		tester.step();
	/**
	 * tests if	the car has moved a step forward
	 */
		assertEquals(tester.getCarSpot(4).getCurrentCar(),testCar);
		
		
		
	}

}

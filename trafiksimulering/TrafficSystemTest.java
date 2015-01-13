import static org.junit.Assert.*;

import org.junit.Test;


public class TrafficSystemTest {
	LaneTest testet = new LaneTest();
	@Test
	public void test() {
		/**
		 * runs Lane test
		 */
		testet.test();
		
		TrafficSystem tester = new TrafficSystem(1,1,1,1,1,1);
		
		tester.step();
		tester.step();
	
		int time = tester.getTime();
		
		/**
		 * tests if time updates as it should
		 */
		assertEquals(2, time);
		
		tester.step();
		tester.step();
		tester.step();
		tester.step();
		
		int avg = tester.AvarageTime();
		int lowest = tester.getLowTime();
		int longest = tester.getLongTime();
		time = tester.getTime();
		int cars = tester.getCars();
		
		/**
		 * Tests if data is updated as it should during steps.
		 * First total cars
		 * Second time update
		 * Third Longest time in the system
		 * Forth lowest time in the system
		 * Fifth average time in system
		 */
		assertEquals(cars, 2);
		assertEquals(time,6);
		assertEquals(longest,2);
		assertEquals(lowest, 2);
		assertEquals(avg,2);
		
		
	}

}

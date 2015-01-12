import static org.junit.Assert.*;

import org.junit.Test;


public class TrafficSystemTest {
	
	@Test
	public void test() {
		TrafficSystem tester = new TrafficSystem(1,1,1,1,1,1);
		
		tester.step();
		tester.step();
	
		int time = tester.getTime();
		
		
		assertEquals(2, time);
		
		tester.step();
		tester.step();
		
		int cars = tester.getCars();
		
		fail("lol");
	}

}

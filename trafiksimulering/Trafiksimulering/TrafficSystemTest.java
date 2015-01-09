import static org.junit.Assert.*;

import org.junit.Test;


public class TrafficSystemTest {
	
	@Test
	public void test() {
		TrafficSystem tester = new TrafficSystem(2,10,10,5,5,1);
		
		tester.step();
		tester.step();
	
		int time = tester.getTime();
		
		
		assertEquals(2, time);
		
		
		fail("");
	}

}

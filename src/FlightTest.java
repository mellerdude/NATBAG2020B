import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FlightTest {
	public void testFilghts() {
		MyDate dateLondon=new MyDate(20,5,2020,10,10);
		MyDate dateNewYork=new MyDate(20,5,2020,00,45);
		Flight london=new Flight("elal", "ly315",dateLondon , 0,"london" , 3);
		Flight newYork=new Flight("elal", "ly001",dateNewYork , 0,"newYork" ,3);
		StringBuffer str=new StringBuffer();

	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}

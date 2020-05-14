package tests;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.*;
import org.junit.jupiter.api.Test;
import core.Flight;
import core.MyDate;
class FlightTest {
	
	@Test
	public void testFilghts() {
		MyDate dateLondon=new MyDate(20,5,2020,10,10);
		MyDate dateNewYork=new MyDate(20,5,2020,0,45);
		Flight london=new Flight("elal", "ly315",dateLondon,"london" , 3);
		Flight newYork=new Flight("elal", "ly001",dateNewYork,"newYork" ,3);
		List<Flight> destination = new ArrayList<Flight>();
		destination.add(london);
		destination.add(newYork);
		StringBuffer str=new StringBuffer("The list of the flights is: \n");
		str.append("Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/1/2020, time: 00:45, City=newYork\n");
		str.append("Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/1/2020, time: 10:10, City=london\n");
		
		assertEquals(str.toString(), Flight.showFlightByDate(destination,false));
	}
	

}

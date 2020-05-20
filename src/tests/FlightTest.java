package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import core.Flight;
import core.MyDate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
	public void testArrivalFilghts() {
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		MyDate dateNewYork = new MyDate(20, 5, 2020, 0, 45);
		Flight london = new Flight("elal", "ly315", dateLondon, "london", "England", 3);
		Flight newYork = new Flight("elal", "ly001", dateNewYork, "newYork", "USA", 3);
		List<Flight> Arrivals = new ArrayList<Flight>();
		Arrivals.add(london);
		Arrivals.add(newYork);
		StringBuffer str = new StringBuffer("The list of arrivals is:\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/1/2020, time: 00:45, Origin=newYork, Country=USA\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/1/2020, time: 10:10, Origin=london, Country=England\n");

		assertEquals(str.toString(), Flight.showFlightByDate(Arrivals, true));
	}

	@Test
	public void testDepartureFilghts() {
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		MyDate dateNewYork = new MyDate(20, 5, 2020, 0, 45);
		Flight london = new Flight("elal", "ly315", dateLondon, "london", "England", 3);
		Flight newYork = new Flight("elal", "ly001", dateNewYork, "newYork", "USA", 3);
		List<Flight> Departures = new ArrayList<Flight>();
		Departures.add(london);
		Departures.add(newYork);
		StringBuffer str = new StringBuffer("The list of departures is:\n");
		str.append(
				"Departure Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/1/2020, time: 00:45, Destination=newYork, Country=USA\n");
		str.append(
				"Departure Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/1/2020, time: 10:10, Destination=london, Country=England\n");

		assertEquals(str.toString(), Flight.showFlightByDate(Departures, false));
	}

	@Test
	public void testFileSaveAndWrite() throws FileNotFoundException {
		File f = new File("Test.txt");
		PrintWriter pw = new PrintWriter(f);
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		Flight london = new Flight("elal", "ly315", dateLondon, "london", "England", 3);
		List<Flight> Departures = new ArrayList<Flight>();
		Departures.add(london);
		london.save(pw);
		pw.close();
		StringBuffer str = new StringBuffer("The list of the departures is:\n"
				+ "Departure Flight:company=elal, Terminal=3, FlightCode=ly315, FlightDate=20/1/2020,"
				+ " time: 10:10, Destination=london, Country=England\n");
		assertEquals(str.toString(), Flight.showFlightsFromFile(f, 1,false));
	}
	//test if arrange by Country
	@Test
	public void testByCountryFlight() {
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		MyDate dateNewYork = new MyDate(20, 5, 2020, 0, 45);
		Flight newYork = new Flight("elal", "ly001", dateNewYork, "newYork", "USA", 3);
		Flight london = new Flight("elal", "ly315", dateLondon, "london", "England", 3);
		List<Flight> Arrivals = new ArrayList<Flight>();
		Arrivals.add(newYork);
		Arrivals.add(london);
		StringBuffer str = new StringBuffer("The list of the flights organized by country is: \n");
		str.append("The list of arrivals is:");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/1/2020, time: 10:10, Origin=london, Country=England\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/1/2020, time: 00:45, Origin=newYork, Country=USA\n");
		assertEquals(str.toString(), Flight.showFlightByCountry(Arrivals, true));
	}
	//test if arrange by City
	@Test
	public void testByCityFlight() {
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		MyDate dateNewYork = new MyDate(20, 5, 2020, 0, 45);
		Flight newYork = new Flight("elal", "ly001", dateNewYork, "newYork", "USA", 3);
		Flight london = new Flight("elal", "ly315", dateLondon, "london", "England", 3);
		List<Flight> Arrivals = new ArrayList<Flight>();
		Arrivals.add(newYork);
		Arrivals.add(london);
		StringBuffer str = new StringBuffer("The list of the flights organized by city is: \n");
		str.append("The list of arrivals is:");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/1/2020, time: 10:10, Origin=london, Country=England\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/1/2020, time: 00:45, Origin=newYork, Country=USA\n");
		assertEquals(str.toString(), Flight.showFlightByCity(Arrivals, true));
	}

}

package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


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
				"Arrival Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/5/2020, time: 00:45, Origin=newYork, Country=USA\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/5/2020, time: 10:10, Origin=london, Country=England\n");
		Arrivals = Flight.showFlightByDate(Arrivals);
		assertEquals(str.toString(), Flight.showWantedFlight(Arrivals, "Date", true));
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
				"Departure Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/5/2020, time: 00:45, Destination=newYork, Country=USA\n");
		str.append(
				"Departure Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/5/2020, time: 10:10, Destination=london, Country=England\n");
		Departures = Flight.showFlightByDate(Departures);
		assertEquals(str.toString(), Flight.showWantedFlight(Departures, "Date", false));
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
				+ "Departure Flight:company=elal, Terminal=3, FlightCode=ly315, FlightDate=20/5/2020,"
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
				"Arrival Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/5/2020, time: 10:10, Origin=london, Country=England\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/5/2020, time: 00:45, Origin=newYork, Country=USA\n");
		Arrivals = Flight.showFlightByCountry(Arrivals);
		assertEquals(str.toString(), Flight.showWantedFlight(Arrivals, "Country", true));
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
				"Arrival Flight:company=elal, terminal=3, flightCode=ly315, FlightDate=20/5/2020, time: 10:10, Origin=london, Country=England\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/5/2020, time: 00:45, Origin=newYork, Country=USA\n");
		Arrivals = Flight.showFlightByCity(Arrivals);
		assertEquals(str.toString(), Flight.showWantedFlight(Arrivals, "City", true));
	}
	@Test
	public void testArrivalFlightsFromDateToDate() {
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		MyDate dateNewYork = new MyDate(20, 5, 2020, 0, 45);
		MyDate dateTelAviv = new MyDate(4, 9, 2019, 12, 50);
		MyDate dateMoscow = new MyDate(5, 3, 2020, 9, 13);

		MyDate startDate = new MyDate(1, 8, 2018, 12, 50);
		MyDate endDate = new MyDate(4, 4, 2020, 12, 50);

		Flight london = new Flight("elal", "ly315", dateLondon, "london", "England", 3);
		Flight newYork = new Flight("elal", "ly001", dateNewYork, "newYork", "USA", 3);
		Flight telAviv = new Flight("elal", "ly021", dateTelAviv, "telAviv", "Israel", 3);
		Flight moscow = new Flight("aeroflot", "978", dateMoscow, "moscow", "Russia", 2);

		List<Flight> flights = new ArrayList<Flight>();
		flights.add(london);
		flights.add(newYork);
		flights.add(telAviv);
		flights.add(moscow);

		StringBuffer str = new StringBuffer("The list of arrivals is:\n");
		str.append(
				"Arrival Flight:company=elal, terminal=3, flightCode=ly021, FlightDate=4/9/2019, time: 12:50, Origin=telAviv, Country=Israel\n");
		str.append(
				"Arrival Flight:company=aeroflot, terminal=2, flightCode=978, FlightDate=5/3/2020, time: 09:13, Origin=moscow, Country=Russia\n");
		assertEquals(str.toString(), Flight.showFlightsFromDateToDate(flights, startDate, endDate));
	}
	@Test
	public void testDepartureFlightsFromDateToDate() {
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		MyDate dateNewYork = new MyDate(20, 5, 2020, 0, 45);
		MyDate dateTelAviv = new MyDate(4, 9, 2019, 12, 50);
		MyDate dateMoscow = new MyDate(5, 3, 2020, 9, 13);
		MyDate startDate = new MyDate(1, 8, 2018, 12, 50);
		MyDate endDate = new MyDate(4, 4, 2020, 12, 50);
		Flight london = new Flight("elal", "ly315", dateLondon, "london", "England", 3);
		Flight newYork = new Flight("elal", "ly001", dateNewYork, "newYork", "USA", 3);
		Flight telAviv = new Flight("elal", "ly021", dateTelAviv, "telAviv", "Israel", 3);
		Flight moscow = new Flight("aeroflot", "978", dateMoscow, "moscow", "Russia", 2);
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(london);
		flights.add(newYork);
		flights.add(telAviv);
		flights.add(moscow);
		StringBuffer str = new StringBuffer("The list of departures is:\n");
		str.append(
				"Departure Flight:company=elal, terminal=3, flightCode=ly021, FlightDate=4/9/2019, time: 12:50, Destination=telAviv, Country=Israel\n");
		str.append(
				"Departure Flight:company=aeroflot, terminal=2, flightCode=978, FlightDate=5/3/2020, time: 09:13, Destination=moscow, Country=Russia\n");
		assertEquals(str.toString(), Flight.showFlightsFromDateToDate(flights, startDate, endDate));
	}

}

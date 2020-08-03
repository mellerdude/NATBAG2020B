import java.util.ArrayList;
import java.util.List;

import core.Flight;
import core.MyDate;


public class BrowserMain {


	public static void main(String[] args) {
		MyDate dateLondon = new MyDate(20, 5, 2020, 10, 10);
		MyDate dateNewYork = new MyDate(20, 5, 2020, 0, 45);
		MyDate dateTelAviv = new MyDate(4, 9, 2019, 12, 50);
		MyDate dateSpain = new MyDate(5, 3, 2020, 9, 13);
		MyDate dateFrance = new MyDate(5, 7, 2020, 9, 13);
		// MyDate startDate = new MyDate(1, 8, 2018, 12, 50);
		// MyDate endDate = new MyDate(4, 4, 2020, 12, 50);
		Flight london = new Flight("elal", "Heathro", "ly315", dateLondon, "london", "England", 3);
		Flight newYork = new Flight("elal", "JFK", "ly001", dateNewYork, "newYork", "USA", 3);
		Flight telAviv = new Flight("elal", "Ben Gurion", "ly021", dateTelAviv, "telAviv", "Israel", 3);
		Flight Barcelona = new Flight("Iberia", "El Prat", "ib5743", dateSpain, "Barcelona", "Spain", 2);
		Flight france = new Flight("elal", "PDG", "ib5743", dateFrance, "Paris", "France", 2);

		String airline = "";
		String country = "";
		String city = "";
		String airport = "";
		int day1 = 1;
		int month1 = 1;
		int year1 = 2000;
		int hour1 = 0;
		int minute1 = 0;
		int day2 = 31;
		int month2 = 12;
		int year2 = 2020;
		int hour2 = 23;
		int minute2 = 59;
		boolean sunday = false;
		boolean monday = false;
		boolean tuesday = false;
		boolean wednesday = false;
		boolean thursday = false;
		boolean friday = false;
		boolean saturday = false;
		List<Flight> flights = new ArrayList<Flight>();
		flights.add(london);
		flights.add(newYork);
		flights.add(telAviv);
		flights.add(Barcelona);
		flights.add(france);
		boolean isHtml = (args.length > 0 && args[0].equalsIgnoreCase("html"));
		boolean isArrival = args.length > 1 && args[1].equalsIgnoreCase("arrivals");
		if (args.length > 1) {
			country = args[2];
			city = args[3];
			airport = args[4];
			airline = args[5];
			day1 = Integer.parseInt(args[6]);
			month1 = Integer.parseInt(args[7]);
			year1 = Integer.parseInt(args[8]);
			day2 = Integer.parseInt(args[9]);
			month2 = Integer.parseInt(args[10]);
			year2 = Integer.parseInt(args[11]);

		}

		if (isHtml = true) {
			if (isArrival) {
				System.out.println("<h2>The list of arrivals  flights is:</h2>");
				System.out.println("<style>" + "table, th, td {" + "  border: 2px solid black;"
						+ "border-collapse: collapse;" + "text-align: center;" + "}" + "</style>");
				System.out.println("<table style=\"width:50%\">");
				System.out.println("<tbody>\r\n\n" + "<tr>\r\n" + "<td><strong>Airline</strong></td>"
						+ "<td><strong>Airport</strong></td>\r\n" + "<td><strong>Terminal</strong></td>"
						+ "<td><strong>FlightCode</strong></td>" + "<td><strong>FlightDate</strong></td>"
						+ "<td><strong>Origin</strong></td>" + "<td><strong>Country</strong></td>");
				boolean isEqual = false;
				flights = flights.get(0).showFlightsFromDateToDate(flights,
						new MyDate(day1, month1, year1, hour1, minute1),
						new MyDate(day2, month2, year2, hour2, minute2));
				flights = flights.get(0).showFlightByDate(flights);
				for (int i = 0; i < flights.size(); i++) {
					isEqual = airline.equalsIgnoreCase(flights.get(i).getCompany())
							&& airport.equalsIgnoreCase(flights.get(i).getAirport())
							&& city.equalsIgnoreCase(flights.get(i).getCity())
							&& country.equalsIgnoreCase(flights.get(i).getCountry());
					if (isEqual) {
						System.out
								.println("</tr>\r\n" + "<tr>\r\n" + "<td>" + flights.get(i).getCompany() + "</td>\r\n");
						System.out.println("<td>" + flights.get(i).getAirport() + "</td>\r\n");
						System.out.println("<td>" + flights.get(i).getTerminal() + "</td>\r\n");
						System.out.println("<td>" + flights.get(i).getFlightCode() + "</td>\r\n");
						System.out.println("<td>" + flights.get(i).getFlightDate() + "</td>\r\n");
						System.out.println("<td>" + flights.get(i).getCity() + "</td>\r\n");
						System.out.println("<td>" + flights.get(i).getCountry() + "</td>\r\n");
					}
				}
			} else {
				System.out.println("<h2>The list of departures  flights is:</h2>");
				System.out.println("<style>" + "table, th, td {" + "  border: 2px solid black;"
						+ "border-collapse: collapse;" + "text-align: center;" + "}" + "</style>");
				System.out.println("<table style=\"width:50%\">");
				System.out.println("<tbody>\r\n" + "<tr>\r\n" + "<td><strong>Airline</strong></td>"
						+ "<td><strong>Airport</strong></td>\r\n" + "<td><strong>Terminal</strong></td>"
						+ "<td><strong>FlightCode</strong></td>" + "<td><strong>FlightDate</strong></td>"
						+ "<td><strong>Destination</strong></td>" + "<td><strong>Country</strong></td>");
			}
		} else {
			Flight.showFlightByFilters(flights, new MyDate(day1, month1, year1, hour1, minute1),
					new MyDate(day2, month2, year2, hour2, minute2), city, country, airline, airport, isArrival);
		}
	}
}

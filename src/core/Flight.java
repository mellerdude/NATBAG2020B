package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.UnaryOperator;

public class Flight {
	private String company;
	private String airport;
	private String flightCode;
	private MyDate FlightDate;
	private String city;
	private String country;
	private int terminal;

	public Flight(String company, String airport, String flightCode, MyDate FlightDate, String d, String coun,
			int terminal) {
		this.company = company;
		this.airport = airport;
		this.flightCode = flightCode;
		this.FlightDate = new MyDate(FlightDate);
		this.terminal = terminal;
		this.city = d;
		this.country = coun;
	}

	public Flight() {
		this("", "", "", new MyDate(), "", "", 0);

	}

	public String showString(boolean isArrival) {
		if (isArrival == true) {
			return "Arrival Flight:company=" + company + ", airport=" + airport + ", terminal=" + terminal
					+ ", flightCode=" + flightCode + ", FlightDate=" + FlightDate + ", Origin=" + city + ", Country="
					+ country + "\n";
		} else {
			return "Departure Flight:company=" + company + ", airport=" + airport + ", terminal=" + terminal
					+ ", flightCode=" + flightCode + ", FlightDate=" + FlightDate + ", Destination=" + city
					+ ", Country=" + country + "\n";
		}
	}

	public static String showArrivalFlights(List<Flight> flight) {
		StringBuffer sb = new StringBuffer("The list of arrivals is:\n");
		for (int i = 0; i < flight.size(); i++) {
			sb.append(flight.get(i).showString(true));
		}
		return sb.toString();
	}

	public static String showDepartureFlights(List<Flight> flight) {
		StringBuffer sb = new StringBuffer("The list of departures is:\n");
		for (int i = 0; i < flight.size(); i++) {
			sb.append(flight.get(i).showString(false));
		}
		return sb.toString();
	}

	public int getTerminal() {
		return terminal;
	}

	public MyDate getFlightDate() {
		return FlightDate;
	}

	public void setFlightDate(MyDate flightDate) {
		FlightDate = flightDate;
	}

	public String getCompany() {
		return company;
	}

	public String getAirport() {
		return airport;
	}

	public String getFlightCode() {
		return flightCode;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}
	//save in file
	public void save(PrintWriter pw) throws FileNotFoundException {
//		Flight:company=elal, airport=JFK, terminal=3, flightCode=ly001, FlightDate=20/1/2020, time: 00:45, City=newYork\n
		pw.println(company);
		pw.println(airport);
		pw.println(terminal);
		pw.println(flightCode);
		pw.println(FlightDate);
		pw.println(city);
		pw.println(country);
	}

	// show the final list
	public static String showFlightByFilters(List<Flight> flight, MyDate startDate, MyDate endDate, String city,
			String country, String company, String airport, boolean isArrival, boolean monday, boolean tuesday,
			boolean wednesday, boolean thursday, boolean friday, boolean saturday, boolean sunday) {
		List<Flight> tempFlight = new ArrayList<Flight>();
		// sort by date & take the wanted dates
		boolean noDate = startDate.getDay() == 0;
		removeDaysOfTheWeek(flight, monday, tuesday, wednesday, thursday, friday, saturday, sunday);
		if (!noDate) {
			flight = showFlightsFromDateToDate(flight, startDate, endDate);
		}
		// sort by city & take the wanted city
		if (!city.equals("")) {
			flight = showFlightByCity(flight);
			for (int i = 0; i < flight.size(); i++) {
				if (city.equalsIgnoreCase(flight.get(i).getCity())) {
					tempFlight.add(flight.get(i));
				}
			}
			// tempFlight = showFlightByCountry(tempFlight);
			flight = new ArrayList<Flight>(tempFlight);
			tempFlight.clear();
		}
		// sort by country & take the wanted country
		if (!country.equals("")) {
			flight = showFlightByCountry(flight);
			for (int i = 0; i < flight.size(); i++) {
				if (country.equalsIgnoreCase(flight.get(i).getCountry())) {
					tempFlight.add(flight.get(i));
				}
			}
			flight = new ArrayList<Flight>(tempFlight);
			tempFlight.clear();
		}
		// sort by company & take the wanted company
		if (!company.equals("")) {
			flight = showFlightByCompany(flight);
			for (int i = 0; i < flight.size(); i++) {
				if (company.equalsIgnoreCase(flight.get(i).getCompany())) {
					tempFlight.add(flight.get(i));
				}
			}
			flight = new ArrayList<Flight>(tempFlight);
			tempFlight.clear();
		}
		// sort by airport & take the wanted airport
		if (!airport.equals("")) {
			flight = showFlightByAirport(flight);
			for (int i = 0; i < flight.size(); i++) {
				if (airport.equalsIgnoreCase(flight.get(i).getAirport())) {
					tempFlight.add(flight.get(i));
				}
			}
			flight = new ArrayList<Flight>(tempFlight);
			tempFlight.clear();
		}

		String name = "";
		if (monday == false || tuesday == false || wednesday == false || thursday == false || friday == false
				|| saturday == false || sunday == false)
			name += "Day of the week";
		if (!city.equals("")) {
			name += ",City";
		}
		if (!country.equals("")) {
			name += ",Country";
		}
		if (!noDate) {
			name += ",Specific Dates";
		}
		if (!company.equals("")) {
			name += ",Company";
		}
		if (!airport.equals("")) {
			name += ",airport";
		}

		return showWantedFlight(flight, name, isArrival);

	}

	public static void removeDaysOfTheWeek(List<Flight> flight, boolean monday, boolean tuesday, boolean wednesday,
			boolean thursday, boolean friday, boolean saturday, boolean sunday) {
		for (int i = 0; i < flight.size(); i++) {
			if (monday == false && flight.get(i).getFlightDate().getDayOfTheWeek() == 1) {
				flight.remove(i);
			}
			if (tuesday == false && flight.get(i).getFlightDate().getDayOfTheWeek() == 2) {
				flight.remove(i);
			}
			if (wednesday == false && flight.get(i).getFlightDate().getDayOfTheWeek() == 3) {
				flight.remove(i);
			}
			if (thursday == false && flight.get(i).getFlightDate().getDayOfTheWeek() == 4) {
				flight.remove(i);
			}
			if (friday == false && flight.get(i).getFlightDate().getDayOfTheWeek() == 5) {
				flight.remove(i);
			}
			if (saturday == false && flight.get(i).getFlightDate().getDayOfTheWeek() == 6) {
				flight.remove(i);
			}
			if (sunday == false && flight.get(i).getFlightDate().getDayOfTheWeek() == 7) {
				flight.remove(i);
			}
		}
	}
	//filter by city

	public static List<Flight> showFlightByCity(List<Flight> flight) {
		Comparator<Flight> compareByCity = new Comparator<Flight>() {

			// return -1 if o1<o2, 1 if o1>o2, 0 if o1==o2
			// return -1 if c1<c2, 1 if c1>c2, 0 if c1==c2
			public int compare(Flight o1, Flight o2) {
				int city1Length = o1.getCity().length();
				int city2Lenght = o2.getCity().length();
				int smallLenght = Math.min(city1Length, city2Lenght);
				char c1, c2;
				int diff;
				for (int i = 0; i < smallLenght; i++) {
					c1 = o1.getCity().charAt(i);
					c2 = o2.getCity().charAt(i);
					diff = c1 - c2;
					if (diff < 0)
						return -1; // c1<c2
					if (diff > 0)
						return 1; // c1>c2
				}
				return 0; // o1 == o2
			}
		};
		Collections.sort(flight, compareByCity);
		return flight;

	}
	//filter by country
	public static List<Flight> showFlightByCountry(List<Flight> flight) {
		Comparator<Flight> compareByCountry = new Comparator<Flight>() {
			// return -1 if o1<o2, 1 if o1>o2, 0 if o1==o2
			// return -1 if c1<c2, 1 if c1>c2, 0 if c1==c2
			public int compare(Flight o1, Flight o2) {
				int country1Length = o1.getCountry().length();
				int country2Length = o2.getCountry().length();
				int smallLenght = Math.min(country1Length, country2Length);
				char c1, c2;
				int diff;
				for (int i = 0; i < smallLenght; i++) {
					c1 = o1.getCountry().charAt(i);
					c2 = o2.getCountry().charAt(i);
					diff = c1 - c2;
					if (diff < 0)
						return -1; // c1<c2
					if (diff > 0)
						return 1; // c1>c2
				}
				return 0; // o1 == o2
			}
		};
		Collections.sort(flight, compareByCountry);

		return flight;
	}
	//filter by airport
	public static List<Flight> showFlightByAirport(List<Flight> flight) {
		Comparator<Flight> compareByAirport = new Comparator<Flight>() {
			// return -1 if o1<o2, 1 if o1>o2, 0 if o1==o2
			// return -1 if c1<c2, 1 if c1>c2, 0 if c1==c2
			public int compare(Flight o1, Flight o2) {
				int airport1Length = o1.getAirport().length();
				int airport2Length = o2.getAirport().length();
				int smallLenght = Math.min(airport1Length, airport2Length);
				char c1, c2;
				int diff;
				for (int i = 0; i < smallLenght; i++) {
					c1 = o1.getAirport().charAt(i);
					c2 = o2.getAirport().charAt(i);
					diff = c1 - c2;
					if (diff < 0)
						return -1; // c1<c2
					if (diff > 0)
						return 1; // c1>c2
				}
				return 0; // o1 == o2
			}
		};
		Collections.sort(flight, compareByAirport);

		return flight;
	}
	//filter by company
	public static List<Flight> showFlightByCompany(List<Flight> flight) {
		Comparator<Flight> compareByCompany = new Comparator<Flight>() {
			// return -1 if o1<o2, 1 if o1>o2, 0 if o1==o2
			// return -1 if c1<c2, 1 if c1>c2, 0 if c1==c2
			public int compare(Flight o1, Flight o2) {
				int companyt1Length = o1.getCompany().length();
				int company2Length = o2.getCompany().length();
				int smallLenght = Math.min(companyt1Length, company2Length);
				char c1, c2;
				int diff;
				for (int i = 0; i < smallLenght; i++) {
					c1 = o1.getAirport().charAt(i);
					c2 = o2.getAirport().charAt(i);
					diff = c1 - c2;
					if (diff < 0)
						return -1; // c1<c2
					if (diff > 0)
						return 1; // c1>c2
				}
				return 0; // o1 == o2
			}
		};
		Collections.sort(flight, compareByCompany);

		return flight;
	}

	// arranging flight by date
	public static List<Flight> showFlightByDate(List<Flight> flight) {
		Comparator<Flight> compareByTime = new Comparator<Flight>() {

			// return -1 if o1<o2, 1 if o1>o2, 0 if o1==o2
			public int compare(Flight o1, Flight o2) {
				if (o1.getFlightDate().getYear() < o2.getFlightDate().getYear())
					return -1;
				else if (o1.getFlightDate().getYear() == o2.getFlightDate().getYear())
					if (o1.getFlightDate().getMonth() < o2.getFlightDate().getMonth())
						return -1;
					else if (o1.getFlightDate().getMonth() == o2.getFlightDate().getMonth())
						if (o1.getFlightDate().getDay() < o2.getFlightDate().getDay())
							return -1;
						else if (o1.getFlightDate().getDay() == o2.getFlightDate().getDay())
							if (o1.getFlightDate().getHour() < o2.getFlightDate().getHour())
								return -1;
							else if (o1.getFlightDate().getHour() == o2.getFlightDate().getHour())
								if (o1.getFlightDate().getMinute() < o2.getFlightDate().getMinute())
									return -1;
								else if (o1.getFlightDate().getMinute() == o2.getFlightDate().getMinute())
									return 0;
				return 1;
			}
		};
		Collections.sort(flight, compareByTime);
		return flight;
	}

	// view flights by specific dates
	public static List<Flight> showFlightsFromDateToDate(List<Flight> flight, MyDate startDate, MyDate endDate) {
		ArrayList<Flight> tempList = new ArrayList<Flight>();
		for (int i = 0; i < flight.size(); i++) {
			if ((flight.get(i).getFlightDate().getYear() == startDate.getYear())
					&& (flight.get(i).getFlightDate().getYear() == endDate.getYear())) {
				if ((flight.get(i).getFlightDate().getMonth() == startDate.getMonth())
						&& (flight.get(i).getFlightDate().getMonth() == endDate.getMonth())) {
					if ((flight.get(i).getFlightDate().getDay() >= startDate.getDay())
							&& (flight.get(i).getFlightDate().getDay() <= endDate.getDay())) {
						if ((flight.get(i).getFlightDate().getDay() >= startDate.getDay())
								&& (flight.get(i).getFlightDate().getDay() <= endDate.getDay())) {
							tempList.add(flight.get(i));
						}
					}
				} else if ((flight.get(i).getFlightDate().getMonth() >= startDate.getMonth())
						&& (flight.get(i).getFlightDate().getMonth() <= endDate.getMonth())) {
					tempList.add(flight.get(i));
				}
			} else if ((flight.get(i).getFlightDate().getYear() >= startDate.getYear())
					&& (flight.get(i).getFlightDate().getYear() < endDate.getYear())) {

				tempList.add(flight.get(i));

			} else if ((flight.get(i).getFlightDate().getYear() >= startDate.getYear())
					&& (flight.get(i).getFlightDate().getYear() == endDate.getYear())) {
				if (flight.get(i).getFlightDate().getMonth() < endDate.getMonth()) {
					tempList.add(flight.get(i));

				} else if (flight.get(i).getFlightDate().getMonth() == endDate.getMonth()) {
					if (flight.get(i).getFlightDate().getDay() <= endDate.getDay()) {
						tempList.add(flight.get(i));
					}
				}
			}

		}

		return showFlightByDate(tempList);
	}

	public static String showFlightsFromFile(File f, int numOfFlights, boolean isArrival) throws FileNotFoundException {
		Scanner in = new Scanner(f);
		StringBuffer sb = new StringBuffer("The list of the ");
		if (isArrival)
			sb.append("arrivals is:\n");
		else
			sb.append("departures is:\n");
		for (int i = 0; i < numOfFlights; i++) {
			if (isArrival)
				sb.append("Arrival Flight:company=" + in.next());
			else
				sb.append("Departure Flight:company=" + in.next());
			sb.append(", airport=" + in.next());
			sb.append(", Terminal=" + in.next());
			sb.append(", FlightCode=" + in.next());
			sb.append(", FlightDate=" + in.next());
			if (isArrival)
				sb.append(", Origin=" + in.next());
			else
				sb.append(", Destination=" + in.next());
			sb.append(", Country=" + in.next() + "\n");
		}
		in.close();
		return sb.toString();
	}

	// showing final flights(after filter)
	public static String showWantedFlight(List<Flight> flight, String info, boolean isArrival) {
		StringBuilder sb;
		if (isArrival) {
			sb = new StringBuilder("The list of the arrivals flights organized by " + info + " is: \n");
		} else {
			sb = new StringBuilder("The list of the departures flights organized by " + info + " is: \n");
		}
		for (int i = 0; i < flight.size(); i++) {
			sb.append(((Flight) flight.get(i)).showString(isArrival));
		}

		return sb.toString();
	}
}


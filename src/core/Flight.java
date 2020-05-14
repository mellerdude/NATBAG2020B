package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Flight {
	private String company;
	private String flightCode;
	private MyDate FlightDate;
	private String city;
	private int terminal;

	public Flight(String company, String flightCode, MyDate FlightDate, String d, int terminal) {
		this.company = company;
		this.flightCode = flightCode;
		this.FlightDate = new MyDate(FlightDate);
		this.terminal = terminal;

		this.city = d;
	}

	public String showString(boolean isArrival) {
		if(isArrival = true) {
		return "Arrival Flight:company=" + company + ", terminal=" + terminal + ", flightCode=" + flightCode + ", FlightDate="
				+ FlightDate +", Origin=" + city + "\n";
		}
		else {
		return "Departure Flight:company=" + company + ", terminal=" + terminal + ", flightCode=" + flightCode + ", FlightDate="
				+ FlightDate +", destination=" + city + "\n";
		}
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

	public String getFlightCode() {
		return flightCode;
	}

	public String getCity() {
		return city;
	}

	public void save(PrintWriter pw) throws FileNotFoundException {
//		Flight:company=elal, terminal=3, flightCode=ly001, FlightDate=20/1/2020, time: 00:45, City=newYork\n
		pw.println(company);
		pw.println(terminal);
		pw.println(flightCode);
		pw.println(FlightDate);
		pw.println(city);
	}

	public static String showFlightByDate(List flight, boolean isArrival) {
		StringBuilder sb = new StringBuilder("The list of the flights is: \n");
		Comparator<Flight> compareByTime = new Comparator<Flight>() {
			@Override
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
		if(isArrival) {
			sb.append("The list of arrivals is:");
		}
		for (int i = 0; i < flight.size(); i++) {
			sb.append(((Flight) flight.get(i)).showString(isArrival));
		}

		return sb.toString();
	}

	public static String showFlightsFromFile(File f, int numOfFlights) throws FileNotFoundException {
		Scanner in = new Scanner(f);
		StringBuffer sb = new StringBuffer("The list of the flights is: \n");
		for (int i = 0; i < numOfFlights; i++) {
			sb.append("Flight:company=" + in.nextLine());
			sb.append(" terminal=" + in.nextLine());
			sb.append(" flightCode=" + in.nextLine());
			sb.append(" FlightDate=" + in.nextLine());
			sb.append(" city=" + in.nextLine() + "\n");
		}
		in.close();
		return sb.toString();
	}
}

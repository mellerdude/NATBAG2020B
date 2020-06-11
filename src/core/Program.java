package core;

import core.Flight;
import core.MyDate;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

	/*
	 * READ and WRITE to file Number of passsengers = delete Show flight by country
	 * Show flight by dates Show flight from date -/-/- to -/-/- Show flight by city
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);

		int selection = 0;
		int numOfFlights = 0;
		List<Flight> departure = new ArrayList<Flight>();
		List<Flight> arrival = new ArrayList<Flight>();
		//hardcoded
		Flight london = new Flight("elal", "Heathro", "ly315", new MyDate(3, 8, 2020, 10, 10), "london", "England", 3);
		Flight newYork = new Flight("elal", "JFK", "ly001", new MyDate(8, 3, 2020, 0, 45), "newYork", "USA", 3);
		arrival.add(london);
		arrival.add(newYork);

		File f = new File("Arrivals.txt");
		File g = new File("Departures.txt");

		do {
			System.out.println("Welcome to our Flight system! please enter your selection \n"
					+ "1-Add arrival flight\n2-Add departure flight\n3-show arrival flight\n"
					+ "4-show departure flight\n5-show all arrivals from file\n" + "6-show all departures from file");
			selection = in.nextInt();
			switch (selection) {
			case 1:
				enterFlight(in, true, arrival, f);
				numOfFlights++;
				break;
			case 2:
				enterFlight(in, false, departure, g);
				numOfFlights++;
				break;
			case 3:
				showFlightMenu(in, arrival, true);
				break;
			case 4:
				showFlightMenu(in, departure, false);
				break;
			case 5:
				System.out.println(Flight.showFlightsFromFile(f, numOfFlights, true));
				break;
			case 6:
				System.out.println(Flight.showFlightsFromFile(g, numOfFlights, false));
				break;
			default:
				break;
			}

		} while (selection != -1);
	}

	private static void showFlightMenu(Scanner in, List<Flight> flight, boolean isArrival) {
		int select;
		MyDate startDate = new MyDate();
		MyDate endDate = new MyDate();
		String city = "", country = "", airport = "", company = "";
		do {
			System.out.println("You Selected Show Flights.\nChoose how you would like to filter the search:  \n"
					+ "1-Show by Date\n2-Show by City\n3-Show by Country\n" + "4-Show specific Dates\n"
					+ "5-Show Airport\n" + "6-Show Company\n" + "7-end");
			select = in.nextInt();
			switch (select) {
			case 1:
				System.out.println("Flights By Date:");
				flight = Flight.showFlightByDate(flight);
				break;
			case 2:
				System.out.println("Flights By City");
				System.out.println("Enter selected city");
				city = in.next();
				break;
			case 3:
				System.out.println("Flights By Country");
				System.out.println("Enter selected Country");
				country = in.next();
				break;
			case 4:
				System.out.println("Flights By Specific Dates: \nenter start date");
				startDate = new MyDate(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
				System.out.println("enter end date");
				endDate = new MyDate(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
				break;
			case 5:
				System.out.println("Flights By Airport");
				System.out.println("Enter selected Airport");
				airport = in.next();
				break;
			case 6:
				System.out.println("Flights By Company");
				System.out.println("Enter selected Company");
				company = in.next();
				break;
			case 7:
				System.out.println("Goodbay");
				break;
			}
		} while (select != 7);
		System.out.println(
				Flight.showFlightByFilters(flight, startDate, endDate, city, country, company, airport, isArrival));
	}
	
	public static void enterFlight(Scanner in, boolean isDeparture, List<Flight> flight, File f)
			throws FileNotFoundException {
		String company;
		String airport;
		String flightCode;
		MyDate FlightDate;
		int day, month, year, terminal;
		String city, country;
		System.out.println("Let's enter your flight\n" + "What is the company your travelling with?");
		company = in.next();
		System.out.println("what is the airport?");
		airport = in.next();
		System.out.println("what is the flight code?");
		flightCode = in.next();
		System.out.println("What is the flight date? d/m/y?");
		day = in.nextInt();
		month = in.nextInt();
		year = in.nextInt();
		System.out.println("What is the flight time? h/m");
		FlightDate = new MyDate(day, month, year, in.nextInt(), in.nextInt());
		if (isDeparture) {
			System.out.println("What is the departure?");
			in.nextLine();
			city = in.nextLine();
		} else {
			System.out.println("What is the destination?");
			in.nextLine();
			city = in.nextLine();
		}
		System.out.println("What is the country?");
		country = in.nextLine();
		System.out.println("What is the number of the terminal?");
		terminal = in.nextInt();
		Flight tempF = new Flight(company, airport, flightCode, FlightDate, city, country, terminal);
		flight.add(tempF);
		PrintWriter pw = new PrintWriter(f);
		for (int i = 0; i < flight.size(); i++) {
			((Flight) flight.get(i)).save(pw);
		}
		pw.close();
	}

}

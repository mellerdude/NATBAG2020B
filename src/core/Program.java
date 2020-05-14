package core;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Program {

	/*
	 * READ and WRITE to file
	 * Number of passsengers = delete
	 * Show flight by country
	 * Show flight by dates
	 * Show flight from date -/-/- to -/-/-
	 * Show flight by city
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);

		int selection = 0;
		int numOfFlights=0;
		List<Flight> departure = new ArrayList<Flight>();
		List<Flight> arrival = new ArrayList<Flight>();
		File f = new File("Arrivals.txt");
		File g = new File("Departures.txt");

		do {
			System.out.println("Welcome to our Flight system! please enter your selection \n"
					+ "1-Add arrival flight\n2-Add departure flight\n3-show arrival flight\n"
					+ "4-show departure flight\n5-show all arrivals from file\n"
					+ "6-show all departures from file");
			selection = in.nextInt();
			switch (selection) {
			case 1:
				enterFlight(in, true, arrival,f);
				numOfFlights++;
				break;
			case 2:
				enterFlight(in, false, departure,g);
				numOfFlights++;
				break;
			case 3:
				System.out.println(Flight.showFlightByDate(arrival,true));
				break;
			case 4:
				System.out.println(Flight.showFlightByDate(departure,false));
				break;
			case 5:
				System.out.println(Flight.showFlightsFromFile(f,numOfFlights));
				break;
			case 6:
				System.out.println(Flight.showFlightsFromFile(g, numOfFlights));
				break;
			default:
				break;
			}

		} while (selection != -1);
	}

	public static void enterFlight(Scanner in, boolean isDeparture, List flight, File f) throws FileNotFoundException {
		String company;
		String flightCode;
		MyDate FlightDate;
		int day,month,year,terminal;
		String city;
		System.out.println("Let's enter your flight\n" + "What is the company your travelling with?");
		company = in.next();
		System.out.println("what is the flight code?");
		flightCode = in.next();
		System.out.println("What is the flight date? d/m/y?");
		day=in.nextInt();
		month=in.nextInt();
		year=in.nextInt();
		System.out.println("What is the flight time? h/m");
		FlightDate = new MyDate(day, month,year , in.nextInt(), in.nextInt());
		if (isDeparture) {
			System.out.println("What is the departure?");
			in.nextLine();
			city = in.nextLine();
		} else {
			System.out.println("What is the destination?");
			in.nextLine();
			city = in.nextLine();
		}
		System.out.println("What is the number of the terminal?");
		terminal=in.nextInt();
		Flight tempF = new Flight(company, flightCode, FlightDate, city,terminal);
		flight.add(tempF);
		PrintWriter pw = new PrintWriter(f);
		for (int i = 0; i < flight.size(); i++) {
			((Flight) flight.get(i)).save(pw);
		}
		pw.close();
	}



}

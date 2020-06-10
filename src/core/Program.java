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
				showFlightMenu(in,arrival,true);
				break;
			case 4:
				showFlightMenu(in,departure,false);
				break;
			case 5:
				System.out.println(Flight.showFlightsFromFile(f,numOfFlights,true));
				break;
			case 6:
				System.out.println(Flight.showFlightsFromFile(g, numOfFlights,false));
				break;
			default:
				break;
			}

		} while (selection != -1);
	}
	
	
	private static void showFlightMenu(Scanner in,List<Flight> flight,boolean isArrival) {
		int select;
		System.out.println("You Selected Show Flights. please enter your selection \n"
				+ "1-Show by Date\n2-Show by City\n3-Show by Country\n"
				+ "4-Show specific Dates");
		select = in.nextInt(); 
		switch(select) {
		case 1:
			System.out.println("Flights By Date");
			flight = Flight.showFlightByDate(flight);
			System.out.println(Flight.showWantedFlight(flight,"Date",isArrival));
			break;
		case 2:
			System.out.println("Flights By City");
			flight = Flight.showFlightByCity(flight);
			System.out.println(Flight.showWantedFlight(flight,"City",isArrival));
			break;
		case 3:
			System.out.println("Flights By Country");
			flight = Flight.showFlightByCountry(flight);
			System.out.println(Flight.showWantedFlight(flight,"Country",isArrival));
			break;
		case 4:
			System.out.println("Flights By specific Dates: \nenter start date");
			MyDate startDate = new MyDate(in.nextInt(), in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
			System.out.println("enter end date");
			MyDate endDate = new MyDate(in.nextInt(), in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt());
			flight = Flight.showFlightsFromDateToDate(flight,startDate,endDate);
			System.out.println(Flight.showWantedFlight(flight,"specific Dates",isArrival));
			break;
			//show by company, show by Airport
		}
		
	}
	
	


	public static void enterFlight(Scanner in, boolean isDeparture, List<Flight> flight, File f) throws FileNotFoundException {
		String company;
		String flightCode;
		MyDate FlightDate;
		int day,month,year,terminal;
		String city,country;
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
		System.out.println("What is the country?");
		country = in.nextLine();
		System.out.println("What is the number of the terminal?");
		terminal=in.nextInt();
		Flight tempF = new Flight(company, flightCode, FlightDate, city,country,terminal);
		flight.add(tempF);
		PrintWriter pw = new PrintWriter(f);
		for (int i = 0; i < flight.size(); i++) {
			((Flight) flight.get(i)).save(pw);
		}
		pw.close();
	}



}

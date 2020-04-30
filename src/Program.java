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

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);

		int selection = 0;
		int numOfFlight = 6;
		// Flight[] allFlights = new Flight[numOfFlight];
		List<Flight> destination = new ArrayList<Flight>();
		List<Flight> departure = new ArrayList<Flight>();
		File f = new File("AllFlights.txt");
		PrintWriter pw = new PrintWriter(f);

		do {
			System.out.println("Welcome to our Flight system! please enter your selection \n"
					+ "1-Add arrival flight\n2-Add departure flight\n3-show arrival flight\n"
					+ "4-show departure flight");
			selection = in.nextInt();
			switch (selection) {
			case 1:
				enterFlight(in, true, departure);
				break;
			case 2:
				enterFlight(in, false, destination);
				break;
			case 3:
				showFlight(departure);
				break;
			case 4:
				showFlight(destination);
			default:
				break;
			}

		} while (selection != -1);
		// allFlights[i] = new
		// Flight(company,flightCode,FlightDate,numOfPassengers,Destination,Departure);
		// allFlights[i].save(pw,i);
		pw.close();
		// System.out.println(Arrays.toString(allFlights));
	}

	private static void enterFlight(Scanner in, boolean isDeparture, List flight) {
		String company;
		String flightCode;
		MyDate FlightDate;
		int numOfPassengers;
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
		System.out.println("What is the number of passengers");
		numOfPassengers = in.nextInt();
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
		flight.add(new Flight(company, flightCode, FlightDate, numOfPassengers, city,terminal));
		// allFlights[i].save(pw,i);
	}

	private static void showFlight(List flight) {
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
		System.out.println("The list of the flights is: \n"+flight);
		
	}

}

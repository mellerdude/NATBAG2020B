import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class Flight {
	private String company;
	private String flightCode;
	private MyDate FlightDate;
	private int numOfPassengers;
	private String city;
	private int terminal;
	//test commit user 2
	private int shmulik;

	public Flight(String company, String flightCode, MyDate FlightDate, int numOfPassengers, String d, int terminal) {
		this.company = company;
		this.flightCode = flightCode;
		this.FlightDate = new MyDate(FlightDate);
		this.terminal = terminal;

		setNumOfPassengers(numOfPassengers);
		this.city = d;
	}

	public String toString() {
		return "Flight:company=" + company + ", terminal=" + terminal + ", flightCode=" + flightCode + ", FlightDate="
				+ FlightDate + ", numOfPassengers=" + numOfPassengers + ", City=" + city + "\n";
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

	public void setNumOfPassengers(int n) {
		if (n > 0)
			this.numOfPassengers = n;
		else
			this.numOfPassengers = 0;
	}

	public void save(PrintWriter pw, int num) throws FileNotFoundException {

		pw.println(num);
		pw.println(company);
		pw.println(flightCode);
		pw.println(FlightDate);
		pw.println(numOfPassengers);
		pw.println(city);
	}
}

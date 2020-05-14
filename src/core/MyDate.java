package core;
import java.time.LocalDate;
import java.time.Period;

public class MyDate {
	private int day, month, year;
	private int hour, minute;
	private final static int[] DAYS_MONTHS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public MyDate(int day, int month, int year, int hour, int minute) {
		if (day >= 1 && day <= 31) {
			this.day = day;
		} else {
			this.day = 1;
		}
		if (month >= 1 && day <= 12) {
			this.month = month;
		} else {
			this.month = 1;
		}
		if (year >= 2000 && year <= 2020) {
			this.year = year;
		} else {
			this.year = 2020;
		}
		if (hour >= 0 && hour < 24) {
			this.hour = hour;
		} else {
			this.hour = 0;
		}
		if (minute >= 0 && minute < 60) {
			this.minute = minute;
		} else {
			this.minute = 0;
		}
	}

	public MyDate(MyDate other) {
		this.day = other.day;
		this.month = other.month;
		this.year = other.year;
		this.hour = other.hour;
		this.minute = other.minute;
	}

	public String toString() {
		String sb = new String();
		sb += day + "/" + month + "/" + year + ", time: ";
		if (hour < 10)
			sb += "0" + hour + ":";
		else sb += hour + ":";
		if (minute < 10)
			sb += "0" + minute;
		else sb += minute;
		return sb;
	}

	public int daysCount(MyDate d) {
		LocalDate enter = LocalDate.of(year, month, day);
		LocalDate out = LocalDate.of(d.year, d.month, d.day);
		Period period = Period.between(enter, out);
		int diff = Math.abs(period.getDays() + period.getMonths() * DAYS_MONTHS[month - 1] + period.getYears() * 365);
		return diff;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}
}

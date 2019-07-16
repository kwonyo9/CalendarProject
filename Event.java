package Proj;

import java.util.Calendar;
import java.util.GregorianCalendar;

//Testing the commit


public class Event implements Comparable<Event>{
	
	private String title;
	private int year;
	private int month;
	private int day;
	private String startTime;
	private String endTime;
	private Calendar start = new GregorianCalendar();
	private Calendar end = new GregorianCalendar();
	private String dayOfWeek;

	/**
	 * Creates an event in calendar
	 * @param title - title of event
	 * @param year - year of event
	 * @param month - month of event
	 * @param day - day of event
	 * @param startTime - start time of event
	 * @param endTime - end time of event 
	 */
	public Event(String title, int year, int month, int day, String startTime, String endTime) {
		
		this.title = title;
		this.year = year;
		this.month = month;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		EventModel model = new EventModel();
		dayOfWeek = model.getWeekDay(year, month, day);
		
	}
	
	public int compareTo(Event e) {
		if(e.start.before(this.start) && e.end.before(this.start)) {
			return 1;
		}else if(e.start.after(this.end) && e.end.after(this.end)) {
			return -1;
		}else {
			return 0;
		}
	}
}

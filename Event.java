package Proj;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event implements Comparable<Event>
{
	private String title;
	private int year;
	private int month;
	private int day;
	private String startTime;
	private String endTime;

	/**
	 * Creates an event in calendar
	 * @param title - title of event
	 * @param year - year of event
	 * @param month - month of event
	 * @param day - day of event
	 * @param startTime - start time of event
	 * @param endTime - end time of event 
	 */
	public Event(String title, int year, int month, int day, String startTime, String endTime)
	{
		this.title = title;
		this.year = year;
		this.month = month;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		EventModel model = new EventModel();
		String dayOfWeek = model.getWeekDay(year, month, day);
	}

	/**
	 * No args constructor that creates a empty event
	 * This is meant for filling the parameters later on
	 * when buttons are pressed in the MainView
	 */
	public Event()
	{
	}

	/**
	 * Sets a new title to the created event
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Sets a new year to the created event
	 * @param year
	 */
	public void setYear(int year)
	{
		this.year = year;
	}

	/**
	 * Sets a new month to the created event
	 * @param month
	 */
	public void setMonth(int month)
	{
		this.month = month;
	}

	/**
	 * Sets a new day to the created event
	 * @param day
	 */
	public void setDay(int day)
	{
		this.day = day;
	}

	/**
	 * Sets a new starting time to the created event
	 * @param startTime
	 */
	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	/**
	 * Sets a new ending time to the created event
	 * @param endTime
	 */
	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	@Override
	public String toString()
	{
		//The event (...) takes place on MM/DD/YYYY from HH:MM to HH:MM
		return "The event: " + title + " takes place on " + month + "/" + day +
				"/" + year + " from " + startTime + " to " + endTime;
	}

	/**
	 * Method that parses the date parameters
	 * of the events, and returns a LocalDate object
	 * that is used in the compareTo call between
	 * objects
	 * ==============================
	 * This method is also used in the storing of events
	 * @return
	 */
	public LocalDate eventDate()
	{
		LocalDate event = LocalDate.parse(year + "/" + month + "/" + day);
		return event;
	}

	/**
	 * Method that parses the time parameters
	 * of the event, and returns a LocalTime object
	 * that is used in the compareTo call between
	 * objects
	 * ==============================
	 * This method is also used in the storing of events
	 * @return
	 */
	public LocalTime eventStartingTime()
	{
		LocalTime eventTime = LocalTime.parse(startTime);
		return eventTime;
	}

	/**
	 * Method that parses the time parameters
	 * of the event, and returns a LocalTime object
	 * that is used in the compareTo call between
	 * objects
	 * ==============================
	 * This method is also used in the storing of events
	 * @return
	 */
	public LocalTime eventEndingTime()
	{
		LocalTime eventTime = LocalTime.parse(endTime);
		return eventTime;
	}

	@Override
	public int compareTo(Event that)
	{
		//If dates are the same, we check event times
		if (this.eventDate().compareTo(that.eventDate()) == 0)
		{
			//If starting times are the same we check ending times
			if (this.eventStartingTime().compareTo(that.eventStartingTime()) == 0)
			{
				//Deepest check involving ending times of the events
				return this.eventEndingTime().compareTo(that.eventEndingTime());
			}
			return this.eventStartingTime().compareTo(that.eventStartingTime());
		}
		return this.eventDate().compareTo(that.eventDate());
	}
}
package proj;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;


public class EventController
{
	// Attributes.
	private static EventController controller;
	private ArrayList<Event> events;
	private Calendar currentCalendar;

    /**
     * Constructor for objects of type
     * EventController
     * ================================
     * Contains a list of events
     * and a calendar that stores such events
     */
    private EventController()
    {
        events = new ArrayList<>();
        currentCalendar = Calendar.getInstance();
    }

    /**
     * Returns a EventController to use it in case
     * of an update
     *
     * @return Updated controller instance
     */
	public static EventController getInstance()
    {
		if(controller == null)
		{
			controller = new EventController();
		}
		return controller;
	}
	
	/**
     * Returns a calendar that is
     * in current use to the controller
     *
	 * @return The controller's current calendar
	 */
	public Calendar getCurrentCalendar()
    {
		return this.currentCalendar;
	}
	
	/**
	 * Updates the month of the calendar in use
	 *
	 * @param value New month value
	 */
	public void updateMonth(int value)
    {
		this.currentCalendar.add(Calendar.MONTH, value);
	}
	
	/**
	 * Updates the day of the calendar in use
	 * 
	 * @param value New day value
	 */
	public void updateDay(int value)
    {
		this.currentCalendar.add(Calendar.DAY_OF_MONTH, value);
	}
	
	/**
	 * Set new value for Calendar of day.
	 * 
	 * @param day New
	 */
	public void setCalendarDay(int day)
    {
		this.currentCalendar.set(Calendar.DAY_OF_MONTH, day);
	}
	
	/**
	 * Setting calendar to today date.
	 */
	public void setTodayCalendar()
    {
		this.currentCalendar = Calendar.getInstance();
	}
	
	/**
	 * Adds a new event to the underlying
     * list of events
     * A successful addition happens
     * when there is no time conflict
	 * 
	 * @param event Event to be added
	 * @return Boolean value for successful add
	 */
	public boolean addEvent(Event event)
    {
		for(Event temp: events)
		{
			if(temp.isTimeConflict(event))
			{
				return false;
			}
		}

		this.events.add(event);
		return true;
	}
	
	/**
     * Retrieves a list containing ALL
     * the events stored in the underlying
     * list of events that satisfy a specific
     * day
     *
	 * @return List containing events on the same day.
	 */
	public ArrayList<Event> getCalendarEventsByDay()
    {
		ArrayList<Event> dayListOfEvents = new ArrayList<>();

		for(int i = 0; i < events.size(); i++)
		{
			if( (events.get(i).getCalendar().get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) &&
				(events.get(i).getCalendar().get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) &&
				(events.get(i).getCalendar().get(Calendar.DAY_OF_MONTH) == currentCalendar.get(Calendar.DAY_OF_MONTH)))
			{
				dayListOfEvents.add(events.get(i));
			}
		}
		
		return dayListOfEvents;
	}
	
	/**
     * Retrieves a list containing ALL
     * the events stored in the underlying
     * list of events that satisfy a specific
     * month
     *
	 * @return List containing events on the same month.
	 */
	public ArrayList<Event> getCalendarEventsByMonth()
    {
		ArrayList<Event> monthListOfEvents = new ArrayList<>();
		
		for(int i = 0; i < events.size(); i++)
		{
			if( (events.get(i).getCalendar().get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) &&
				(events.get(i).getCalendar().get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)))
			{
				monthListOfEvents.add(events.get(i));
			}
		}
		return monthListOfEvents;
	}
	
	/**
     * Retrieves a list containing ALL
     * the events stored in the underlying
     * list of events that satisfy a specific
     * week
     *
	 * @return List containing events on the same week.
	 */
	public ArrayList<Event> getCalendarEventsByWeek()
    {
		ArrayList<Event> weekListOfEvents = new ArrayList<>();
		
		for(int i = 0; i < events.size(); i++)
		{
			if( (events.get(i).getCalendar().get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) &&
				(events.get(i).getCalendar().get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) &&
				(events.get(i).getCalendar().get(Calendar.WEEK_OF_MONTH) == currentCalendar.get(Calendar.WEEK_OF_MONTH)))
			{
				weekListOfEvents.add(events.get(i));
			}
		}
		return weekListOfEvents;
	}

	/**
	 * Retrieves a list containing ALL
	 * the events stored in the underlying
	 * list of events that satisfy a specific
	 * interval of dates
	 *
	 * @param startingDate Beginning date for look-up
	 * @param endingDate Ending date for look-up
	 * @return List containing events on the same time period.
	 */
	public ArrayList<Event> getCalendarEventsBetween(Calendar startingDate, Calendar endingDate)
	{
		ArrayList<Event> list = new ArrayList<>();

		for(int i = 0; i < events.size(); i++)
		{
			Calendar eventCalendar = events.get(i).getCalendar();

			if(isSame(startingDate, eventCalendar) || isSame(endingDate, eventCalendar) ||
				(eventCalendar.before(endingDate) && eventCalendar.after(startingDate)))
			{
				list.add(events.get(i));
			}
		}
		return list;
	}

	/**
	 * Function that checks if two
	 * calendars contain the same dates
	 *
	 * @param first 1st Calendar for check
	 * @param second 2nd Calendar for check
	 * @return Boolean value for similarity
	 */
	private boolean isSame(Calendar first, Calendar second)
	{
		return (first.get(Calendar.DAY_OF_MONTH) == second.get(Calendar.DAY_OF_MONTH))
				&& (first.get(Calendar.MONTH) == second.get(Calendar.MONTH))
				&& (first.get(Calendar.YEAR) == second.get(Calendar.YEAR));
	}

    /**
     * Function that reads the file in the parameter
     * and parses all attributes for processing
     * and eventually, will use those attributes for
     * the creation of recurrent events
     * @param file Passed-in file that contains the event's information
     * @return Boolean value to check if file was successfully read
     */
	public boolean executeFile(File file)
    {
		char[] days = {'S','M','T','W','H','F','A'};
		try
        {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;

			while((line = reader.readLine()) != null) //While line is not EOF
			{
			    //Gets the event's details and splits them for processing
				String[] tokens = line.split(";");

				String eventName = tokens[0];
				int year = Integer.parseInt(tokens[1]);
				int startMonth = Integer.parseInt(tokens[2]);
				int endMonth = Integer.parseInt(tokens[3]);
				String daysString = tokens[4];
				int startHour = Integer.parseInt(tokens[5]);
				int endHour = Integer.parseInt(tokens[6]);
				
				// Adding events to the calendar.
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, startMonth - 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);

				do
				{
					//If same with the day.
					char currentDay = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];

					if(daysString.contains(String.valueOf(currentDay)))
					{
						//Add the event into the calendar.
						Calendar eventCalendar = Calendar.getInstance();
						eventCalendar.set(Calendar.YEAR, year);
						eventCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
						eventCalendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
						addEvent(new Event(eventName, eventCalendar, startHour, endHour));
					}
					calendar.add(Calendar.DAY_OF_MONTH, 1);

				} while((calendar.get(Calendar.MONTH) != endMonth));
				
			}
			reader.close();
			return true;
		}
		catch(Exception e)
        {
			return false;
		}
	}
}
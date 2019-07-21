package Proj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class EventModel
{
	private ArrayList<Event> events;	//The list of events
	private Calendar calendar;			//Calendar
	private MainView view;				//GUI portion
	private ViewTypes viewType;			//enum part
	private Calendar agendaStart;
	private Calendar agendaEnd;

	/**
	 * Constructor for objects of type EventModel
	 * Conforms MVC model for the pattern
	 */
	public EventModel()
	{
		events = new ArrayList<>();
		calendar = new GregorianCalendar();
		viewType = ViewTypes.DAY;	//<Day> is the initial view to the user
	}
	
	/**
	 * Sets the selected view in the
	 * main window
	 * @param view Chosen layout in the event(s) panel
	 */
	public void setView(MainView view)
	{
		this.view = view;
	}

	/**
	 *
	 * @param viewType
	 */
	public void setViewType(ViewTypes viewType)
	{
		this.viewType = viewType;
	}

	/**
	 *
	 * @return
	 */
	public Calendar getCalendar()
	{
		return calendar;
	}
	
	/**
	 * Sets a specific day in the
	 * visual portion of the calendar
	 * @param day 
	 */
	public void setDay(int day)
	{
		calendar.set(Calendar.DAY_OF_MONTH, day);
		view.repaint();
	}

	/**
	 *
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public String getWeekDay(int year, int month, int day)
	{
		calendar.set(year, month - 1, day);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		switch (dayOfWeek)
		{
			case 1:
				return "S"; //Sunday
			case 2:
				return "M"; //Monday
			case 3:
				return "T"; //Tuesday
			case 4:
				return "W"; //Wednesday
			case 5:
				return "H"; //Thursday
			case 6:
				return "F"; //Friday
			case 7:
				return "A"; //Saturday
			default:
				return null;
		}
	}
}
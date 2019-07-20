package Proj;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class EventModel
{
	private ArrayList<Event> events;
	private Calendar calendar;
	private MainView view;
	private ViewTypes viewType;
	private Calendar agendaStart;
	private Calendar agendaEnd;
	
	public enum ViewTypes
	{
		DAY, WEEK, MONTH, AGENDA
	}

	/**
	 * MVC model of calendar program
	 */
	public EventModel()
	{
		events = new ArrayList<>();
		calendar = new GregorianCalendar();
		viewType = ViewTypes.DAY;
	}
	
	/**
	 * Set layout of events panel
	 * @param view - the layout of the events panel
	 */
	public void setView(MainView view)
	{
		this.view = view;
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
	 * Set day in calendar
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
		return Weekday(year, month, day);
	}

	/**
	 * Get day of the week
	 * @param year 
	 * @param month
	 * @param day
	 * @return
	 */
	private String Weekday(int year, int month, int day) {
		calendar.set(year, month - 1, day);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		   case 1:
               return "S";
           case 2:
               return "M";
           case 3:
               return "T";
           case 4:
               return "W";
           case 5:
               return "H";
           case 6:
               return "F";
           case 7:
               return "A";
           default:
               return null;
		}
	}
}
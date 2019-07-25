package proj;


import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;




public class EventController {

	// Attributes.
	private static EventController controller;
	private ArrayList<Event> events;
	private Calendar currentCalendar;
	
	public static EventController getInstance() {
		
		if(controller == null) {
			controller = new EventController();
		}
		
		return controller;
		
	}
	
	/**
	 * Constructor.
	 */
	private EventController() {
		
		events = new ArrayList<>();
		currentCalendar = Calendar.getInstance();
		
	}
	
	/**
	 * @return current calendar
	 */
	public Calendar getCurrentCalendar() {
		
		return this.currentCalendar;
		
	}
	
	/**
	 * Updating the month of the calendar.
	 * 
	 * @param value
	 */
	public void updateMonth(int value) {
		
		this.currentCalendar.add(Calendar.MONTH, value);
		
	}
	
	/**
	 * Updating the day of the calendar.
	 * 
	 * @param value
	 */
	public void updateDay(int value) {
		
		this.currentCalendar.add(Calendar.DAY_OF_MONTH, value);
		
	}
	
	/**
	 * Set new value for Calendar of day.
	 * 
	 * @param day
	 */
	public void setCalendarDay(int day) {
		
		this.currentCalendar.set(Calendar.DAY_OF_MONTH, day);
		
	}
	
	/**
	 * Setting calendar to today date.
	 */
	public void setTodayCalendar() {
		
		this.currentCalendar = Calendar.getInstance();
		
	}
	
	/**
	 * Adding a new event.
	 * 
	 * @param event
	 * @return condition
	 */
	public boolean addEvent(Event event) {
		
		for(Event temp: events) {
			
			if(temp.isTimeConflict(event)) {
				return false;
			}
			
		}
		this.events.add(event);
		return true;
		
	}
	
	/**
	 * @param currentCalendar
	 * @return list of events on the same day.
	 */
	public ArrayList<Event> getCalendarEventsByDay() {
		
		ArrayList<Event> list = new ArrayList<>();
		System.out.println(currentCalendar);
		for(int i = 0; i < events.size(); i++) {
			
			System.out.println("Event: " +events.get(i).getCalendar());
			if((events.get(i).getCalendar().get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) &&
					(events.get(i).getCalendar().get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) &&
					(events.get(i).getCalendar().get(Calendar.DAY_OF_MONTH) == currentCalendar.get(Calendar.DAY_OF_MONTH))) {
				
				list.add(events.get(i));
				
			}
			
		}
		
		return list;
		
	}
	
	/**
	 * @param currentCalendar
	 * @return list of events on the same month.
	 */
	public ArrayList<Event> getCalendarEventsByMonth() {
		
		ArrayList<Event> list = new ArrayList<>();
		
		for(int i = 0; i < events.size(); i++) {
			
			if((events.get(i).getCalendar().get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) &&
					(events.get(i).getCalendar().get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH))) {
				
				list.add(events.get(i));
				
			}
			
		}
		
		return list;
		
	}
	
	/**
	 * @param currentCalendar
	 * @return list of events on the same week.
	 */
	public ArrayList<Event> getCalendarEventsByWeek() {
		
		ArrayList<Event> list = new ArrayList<>();
		
		for(int i = 0; i < events.size(); i++) {
			
			if((events.get(i).getCalendar().get(Calendar.YEAR) == currentCalendar.get(Calendar.YEAR)) &&
					(events.get(i).getCalendar().get(Calendar.MONTH) == currentCalendar.get(Calendar.MONTH)) &&
					(events.get(i).getCalendar().get(Calendar.WEEK_OF_MONTH) == currentCalendar.get(Calendar.WEEK_OF_MONTH))) {
				
				list.add(events.get(i));
				
			}
			
		}
		
		return list;
		
	}
	
	public boolean executeFile(File file) {
		
		char[] days = {'S','M','T','W','H','F','A'};
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine()) != null) {
				
				String[] tokens = line.split(";");
				String eventName = tokens[0];
				int year = Integer.parseInt(tokens[1]);
				int startMonth = Integer.parseInt(tokens[2]);
				int endMonth = Integer.parseInt(tokens[3]);
				String daysString = tokens[4];
				int startHour = Integer.parseInt(tokens[5]);
				int endHour = Integer.parseInt(tokens[6]);
				
				// Adding events in the calendar.
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.MONTH, startMonth - 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				do {
					
					// If same with the day.
					char currentDay = days[calendar.get(Calendar.DAY_OF_WEEK) - 1];
					if(daysString.contains(String.valueOf(currentDay))) {
						
						// Add the event into the calendar.
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
			
		} catch(Exception e) {
			return false;
		}
		
	}
	
}

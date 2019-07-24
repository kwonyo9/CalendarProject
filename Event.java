package proj;

//model
import java.util.Calendar;


public class Event {

	// Attributes..
	private String eventName;
	private Calendar calendar;
	private int startingHours;
	private int endingHours;
	
	/**
	 * Constructor.
	 * 
	 * @param eventName
	 * @param calendar
	 * @param startingHours
	 * @param endingHours
	 */
	public Event(String eventName, Calendar calendar, int startingHours, int endingHours) {
		
		this.eventName = eventName;
		this.calendar = calendar;
		this.startingHours = startingHours;
		this.endingHours = endingHours;
	
	}
	
	/**
	 * If current event is conflict with the input event.
	 * 
	 * @param event
	 * @return true of false.
	 */
	public boolean isTimeConflict(Event event) {
		
		Calendar paramCalendar = event.getCalendar();
		if(paramCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
				&& (paramCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
				&& (paramCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))) {
			
			if((startingHours >= event.getStartingHours() && startingHours < event.getEndingHours()) ||
					(endingHours > event.getStartingHours() && endingHours <= event.getEndingHours()) ||
					(event.getStartingHours() >= startingHours && event.getStartingHours() < endingHours) ||
					(event.getEndingHours() > startingHours && event.getEndingHours() <= endingHours)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	public int getStartingHours() {
		return startingHours;
	}
	public void setStartingHours(int startingHours) {
		this.startingHours = startingHours;
	}
	public int getEndingHours() {
		return endingHours;
	}
	public void setEndingHours(int endingHours) {
		this.endingHours = endingHours;
	}
	
	@Override
	public String toString() {
		
		return "Event: "+eventName+" Time: ("+startingHours+"-"+endingHours+")";
		
	}
	
}

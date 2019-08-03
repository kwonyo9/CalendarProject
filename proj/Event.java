package proj;

import java.util.Calendar;


public class Event
{
    // Attributes..
    private String eventName;
    private Calendar calendar;
    private int startingHours;
    private int endingHours;

    /**
     * Constructor for objects of type Event.
     *
     * @param eventName Description of the event
     * @param calendar
     * @param startingHours Hours of initialization
     * @param endingHours Hours of finalization
     */
    public Event(String eventName, Calendar calendar, int startingHours, int endingHours)
    {
        this.eventName = eventName;
        this.calendar = calendar;
        this.startingHours = startingHours;
        this.endingHours = endingHours;
    }

    /**
     * Rigorous check to decide if two
     * different events conflict in time with each other
     *
     * @param event Another event to compare with
     * @return Boolean value for conflict
     */
    public boolean isTimeConflict(Event event)
    {
        Calendar thisCalendar = event.getCalendar();
        if(thisCalendar.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH) //Day Check
                && (thisCalendar.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))       //Month Check
                && (thisCalendar.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)))        //Year Check
        {
            if((startingHours >= event.getStartingHours() && startingHours < event.getEndingHours()) || //Check starting hours
                    (endingHours > event.getStartingHours() && endingHours <= event.getEndingHours()) ||    //Check ending hours
                    (event.getStartingHours() >= startingHours && event.getStartingHours() < endingHours) || //Check hour boundary
                    (event.getEndingHours() > startingHours && event.getEndingHours() <= endingHours))  //Check hour boundary
            {
                return true; //Events conflict with each other
            }
        }
        return false; //Events do not conflict with each other
    }

    /**
     * Returns the events purpose/description
     * @return Event's description
     */
    public String getEventName()
    {
        return eventName;
    }

    /**
     * Sets a different title for the event
     * @param eventName The event's new title
     */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    /**
     * Gets the used calendar for this event
     * @return Returns used calendar
     */
    public Calendar getCalendar()
    {
        return calendar;
    }

    /**
     * Sets a different calendar for the event
     * @param calendar The event's new calendar
     */
    public void setCalendar(Calendar calendar)
    {
        this.calendar = calendar;
    }

    /**
     * Returns the starting event hours
     * @return The event's starting hours
     */
    public int getStartingHours()
    {
        return startingHours;
    }

    /**
     * Sets new starting hours to the event
     * @param startingHours The event's new starting hours
     */
    public void setStartingHours(int startingHours)
    {
        this.startingHours = startingHours;
    }

    /**
     * Sets new starting hours to the event
     * @return The event's ending hours
     */
    public int getEndingHours()
    {
        return endingHours;
    }

    /**
     * Sets new ending hours to the event
     * @param The event's new ending hours
     */
    public void setEndingHours(int endingHours)
    {
        this.endingHours = endingHours;
    }

    @Override
    public String toString()
    {
        int currentMonth = calendar.get(Calendar.MONTH);
        currentMonth += 1;
        return "Event: " + eventName + "  " + calendar.get(Calendar.YEAR) +
                "/" + currentMonth + "/" + calendar.get(Calendar.DAY_OF_MONTH) + " " +
                "Time: ("+ startingHours + "-" + endingHours+ ")";
    }
}

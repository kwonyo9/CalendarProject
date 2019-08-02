package proj;

import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CalendarView extends JFrame implements ActionListener
{
	// Attributes...
	private static final long serialVersionUID = 1L;
	private int selectedDay;
	private EventController controller;

	// month arrays..
	private final String[] months = new String[] {  "January", "Feburary", "March",
													"April", "May", "June", "July",
													"August", "September", "October",
													"November", "December"};

	//Buttons required to build our User Interface
	private JPanel contentPane;
	private JButton[][] dayNumber;
	private JButton todayButton;
	private JButton previousMonthButton;
	private JButton nextMonthButton;
	private JTextArea eventDetails;
	private JButton dayViewButton;
	private JButton weekViewButton;
	private JButton monthViewButton;
	private JButton agendaViewButton;
	private JButton fileSelectButton;
	private JLabel monthYearTitle;
	private JButton createEventButton;
	private JButton previousDayButton;
	private JButton nextDayButton;

	/**
	 * Constructor for objects of type
	 * Calendar View
	 * ================================
	 * Contains a Panel containing all
	 * buttons to interact with the user
	 */
	public CalendarView()
	{
		setTitle("Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		initializeComponents();
		listeners();

		this.controller = EventController.getInstance();
		this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
		displayMonth(); //Month View is set by default
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton button = (JButton) e.getSource();
		resetDayColors();
		button.setForeground(Color.RED);
		this.selectedDay = Integer.parseInt(button.getText());
		controller.setCalendarDay(selectedDay);
	}

	/**
	 * Resets the standard day color to black.
	 */
	private void resetDayColors()
	{
		for(int i = 0; i < dayNumber.length; i++)
		{
			for(JButton button: dayNumber[i])
			{
				button.setForeground(Color.BLACK);
			}
		}
	}

	/**
	 * Displaying the month.
	 */
	public void displayMonth()
	{
		Calendar calendar = controller.getCurrentCalendar();

		// resetting..
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 7; j++)
			{
				dayNumber[i][j].setVisible(false);
			}
		}

		monthYearTitle.setText(months[calendar.get(Calendar.MONTH)]+ " " +calendar.get(Calendar.YEAR));
		int totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		int row = 0;
		int column = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int day = 1;

		for(int i = 0; i < totalDays; i++)
		{
			if(this.selectedDay == day)
			{
				dayNumber[row][column].setForeground(Color.RED);
			}

			dayNumber[row][column].setText(day + "");
			dayNumber[row][column].setVisible(true);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			//
			// Calendar Events..
			//
			day++;
			column++;
			if(column >= 7)
			{
				column = 0;
				row++;
			}
		}

		calendar.set(Calendar.DAY_OF_MONTH, this.selectedDay);
	}

	/**
	 * Assigns all listeners to all the
	 * buttons in the application for future use
	 */
	private void listeners()
	{
		//Button that travels to today's date
		todayButton.addActionListener(action ->
		{
			resetDayColors();
			controller.setTodayCalendar();
			this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
			displayMonth();
		});

		//Button that reverses the month by 1
		previousMonthButton.addActionListener(action ->
		{
			resetDayColors();
			controller.updateMonth(-1);
			displayMonth();

		});

		//Button that advances the month by 1
		nextMonthButton.addActionListener(action ->
		{
			resetDayColors();
			controller.updateMonth(1);
			displayMonth();
		});

		//Button that reverses the date by 1 day
		previousDayButton.addActionListener(action ->
		{
			System.out.println("Back...");
			resetDayColors();
			controller.updateDay(-1);
			this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
			displayMonth();
		});

		//Button that advances the date by 1 day
		nextDayButton.addActionListener(action ->
		{
			System.out.println("Forward...");
			resetDayColors();
			controller.updateDay(1);
			this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
			displayMonth();
		});

		//Button that prompts the CREATE function for events
		createEventButton.addActionListener(action ->
		{
			addNewEvent();
		});

		//Button that prompts the agenda view
		agendaViewButton.addActionListener(action ->
		{
			agendaDetails();
		});

		//Button that prompts the DAY view for events
		dayViewButton.addActionListener(action ->
		{
			showEventDetails(controller.getCalendarEventsByDay());
		});

		//Button that prompts the MONTH view for events
		monthViewButton.addActionListener(action ->
		{
			showEventDetails(controller.getCalendarEventsByMonth());
		});

		//Button that prompts the WEEK view for events
		weekViewButton.addActionListener(action ->
		{
			showEventDetails(controller.getCalendarEventsByWeek());
		});

		//Buttons that prompts the selection of a file for the events
		fileSelectButton.addActionListener(action ->
		{
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showOpenDialog(null);

			if(result == JFileChooser.APPROVE_OPTION)
			{
				File file = chooser.getSelectedFile();
				boolean filesStatus = controller.executeFile(file);
				if(filesStatus)
				{
					JOptionPane.showMessageDialog(null, "File uploaded successfully.");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "File failed to upload due to wrong format.");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No any txt file Selected.");
			}
		});
	}

	/**
	 * Shows the event details per event
	 * from the list of events
	 *
	 * @param list List that contains events
	 */
	private void showEventDetails(ArrayList<Event> list)
	{
		String events = "";
		for(Event event: list)
		{
			events += event + "\n";
		}
		eventDetails.setText(events);
	}

	/**
	 * Shows the event details per event
	 * from the list of events
	 *
	 * This function is to be used in the
	 * agenda functionality
	 */
	private void agendaDetails()
	{
		Calendar startingDate = Calendar.getInstance();
		Calendar endingDate = Calendar.getInstance();

		do
		{
			try
			{
				String[] startingDateArray = JOptionPane.showInputDialog("Enter Starting Date (MM/DD/YYYY)").split("/");
				int month = Integer.parseInt(startingDateArray[0]);
				if(month > 0 && month <= 12)
				{
					startingDate.set(Calendar.MONTH, month - 1);
					int day = Integer.parseInt(startingDateArray[1]);

					if(day > 0 && day <= 31)
					{
						startingDate.set(Calendar.DAY_OF_MONTH, day);
						int year = Integer.parseInt(startingDateArray[2]);

						if(year > 0)
						{
							startingDate.set(Calendar.YEAR, year);
							break;
						}
					}
				}

				JOptionPane.showMessageDialog(null, "Please enter correct date format (MMM/DD/YYYY)");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Please enter correct date format (MMM/DD/YYYY)");
			}
		}
		while(startingDate.after(Calendar.getInstance()));

		do
		{
			try
			{
				String[] endingDateArray = JOptionPane.showInputDialog("Enter Ending Date (MM/DD/YYYY)").split("/");
				int month = Integer.parseInt(endingDateArray[0]);
				if(month > 0 && month <= 12)
				{
					endingDate.set(Calendar.MONTH, month - 1);
					int day = Integer.parseInt(endingDateArray[1]);

					if(day > 0 && day <= 31)
					{
						endingDate.set(Calendar.DAY_OF_MONTH, day);
						int year = Integer.parseInt(endingDateArray[2]);

						if(year > 0)
						{
							endingDate.set(Calendar.YEAR, year);
							break;
						}
					}
				}

				JOptionPane.showMessageDialog(null, "Please enter correct format of date (MMM/DD/YYYY)");
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Please enter correct format of date (MMM/DD/YYYY)");
			}
		}
		while(endingDate.after(Calendar.getInstance()));

		showEventDetails(controller.getCalendarEventsBetween(startingDate, endingDate));
	}

	/**
	 * Function the prompts the user for
	 * details for a desired event to be
	 * created
	 * ===================================
	 * It is then analyzed for time conflict
	 * with other events and adds it to the
	 * controller's list of events
	 */
	private void addNewEvent()
	{
		String eventName = JOptionPane.showInputDialog("Enter Event Name: ");
		int hoursFromInt, hoursEndInt;

		//Ask user for the desired STARTING hours of the event
		do
		{
			try
			{
				hoursFromInt = Integer.parseInt(JOptionPane.showInputDialog("Enter Starting Hour (0-23) "));
				if(hoursFromInt < 0 || hoursFromInt > 23)
				{
					JOptionPane.showMessageDialog(null, "Please select correct value from 0-23 ");
				}
				else
				{
					break;
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Please select correct value from 0-23 ");
			}
		}
		while(true);

		//Ask user for the desired ENDING hours of the event
		do
		{
			try
			{
				hoursEndInt = Integer.parseInt(JOptionPane.showInputDialog("Enter Ending Hour (0-23) "));
				if (hoursEndInt < 0 || hoursEndInt > 23 || hoursEndInt < hoursFromInt)
				{
					JOptionPane.showMessageDialog(null, "Please select correct value from 0-23 and more than starting hours. ");
				}
				else
				{
					break;
				}
			}
			catch(Exception e)
			{
				JOptionPane.showMessageDialog(null, "Please select correct value from 0-23");
			}
		}
		while(true);

		Calendar eventCalendar = Calendar.getInstance();
		eventCalendar.set(Calendar.YEAR, this.controller.getCurrentCalendar().get(Calendar.YEAR));
		eventCalendar.set(Calendar.MONTH, this.controller.getCurrentCalendar().get(Calendar.MONTH));
		eventCalendar.set(Calendar.DAY_OF_MONTH, this.selectedDay);
		Event event = new Event(eventName, eventCalendar, hoursFromInt, hoursEndInt);
		boolean result = controller.addEvent(event);

		if(result)
		{
			JOptionPane.showMessageDialog(null, "Event " + "''" + eventName  + "''" +
																		" was added successfully.");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Event " + "''" + eventName  + "''" +
											" failed to be created due to time conflict with another event.");
		}
	}

	/**
	 * Initializes all buttons and adds
	 * them into the frame.
	 * ==================================
	 * These buttons will later be assigned
	 * an action
	 */
	private void initializeComponents()
	{
		//Button that travels to today's date
		todayButton = new JButton("Today");
		todayButton.setBackground(Color.WHITE);
		todayButton.setBounds(20, 16, 87, 47);
		contentPane.add(todayButton);

		//Button that reverses the date by 1 day
		previousDayButton = new JButton("<");
		previousDayButton.setBackground(Color.WHITE);
		previousDayButton.setBounds(119, 16, 45, 47);
		contentPane.add(previousDayButton);

		//Button that advances the date by 1 day
		nextDayButton = new JButton(">");
		nextDayButton.setBackground(Color.WHITE);
		nextDayButton.setBounds(176, 16, 45, 47);
		contentPane.add(nextDayButton);

		//Buttons that prompts the selection of a file for the events
		fileSelectButton = new JButton("From File");
		fileSelectButton.setBackground(Color.WHITE);
		fileSelectButton.setBounds(590, 12, 93, 37);
		contentPane.add(fileSelectButton);

		//Button that prompts the agenda view
		agendaViewButton = new JButton("Agenda");
		agendaViewButton.setBackground(Color.WHITE);
		agendaViewButton.setBounds(610, 61, 70, 47);
		contentPane.add(agendaViewButton);

		//Button that prompts the MONTH view for events
		monthViewButton = new JButton("Month");
		monthViewButton.setBackground(Color.WHITE);
		monthViewButton.setBounds(550, 61, 60, 47);
		contentPane.add(monthViewButton);

		//Button that prompts the WEEK view for events
		weekViewButton = new JButton("Week");
		weekViewButton.setBackground(Color.WHITE);
		weekViewButton.setBounds(490, 61, 60, 47);
		contentPane.add(weekViewButton);

		//Button that prompts the DAY view for events
		dayViewButton = new JButton("Day");
		dayViewButton.setBackground(Color.WHITE);
		dayViewButton.setBounds(430, 61, 60, 47);
		contentPane.add(dayViewButton);

		//Button that prompts the CREATE function for events
		createEventButton = new JButton("Create");
		createEventButton.setForeground(Color.RED);
		createEventButton.setBounds(20, 70, 398, 38);
		contentPane.add(createEventButton);

		//Button that reverses the month by 1
		previousMonthButton = new JButton("<");
		previousMonthButton.setBackground(Color.WHITE);
		previousMonthButton.setBounds(20, 113, 45, 38);
		contentPane.add(previousMonthButton);

		//Button that advances the month by 1
		nextMonthButton = new JButton(">");
		nextMonthButton.setBackground(Color.WHITE);
		nextMonthButton.setBounds(77, 113, 45, 38);
		contentPane.add(nextMonthButton);

		//JTextArea that will print all the details from the events
		eventDetails = new JTextArea();
		eventDetails.setBorder(new LineBorder(new Color(0, 0, 0)));
		eventDetails.setBounds(440, 120, 232, 363);
		JScrollPane pane = new JScrollPane(eventDetails);
		pane.setBounds(440, 120, 232, 363);
		contentPane.add(pane);

		monthYearTitle = new JLabel("October 2013");
		monthYearTitle.setHorizontalAlignment(SwingConstants.CENTER);
		monthYearTitle.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		monthYearTitle.setBounds(134, 110, 278, 37);
		contentPane.add(monthYearTitle);

		String[] weeks = {"SU","M","T","W","T","F","SA"};
		JLabel[] labels = new JLabel[7];
		int x = 30;
		for(int i = 0; i < labels.length; i++)
		{
			labels[i] = new JLabel(weeks[i]);
			labels[i].setBounds(x, 180, 50, 40);
			labels[i].setHorizontalAlignment(SwingConstants.CENTER);
			labels[i].setBackground(Color.lightGray);
			labels[i].setOpaque(true);
			x += 54;
			contentPane.add(labels[i]);
		}

		labels[0].setBackground(Color.RED);
		labels[6].setBackground(Color.RED);

		// creating days..
		dayNumber = new JButton[6][7];
		x = 30;
		int y = 225;
		int count = 1;
		for(int i = 0; i < dayNumber.length; i++)
		{
			for(int j = 0; j < dayNumber[0].length; j++)
			{
				dayNumber[i][j] = new JButton(count+ "");
				count++;
				dayNumber[i][j].setBounds(x, y, 50, 40);
				dayNumber[i][j].setBackground(Color.WHITE);
				dayNumber[i][j].addActionListener(this);
				x += 54;
				contentPane.add(dayNumber[i][j]);
			}
			x = 30;
			y += 44;
		}
	}
}
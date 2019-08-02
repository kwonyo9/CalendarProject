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



public class CalendarView extends JFrame implements ActionListener {

	// Attributes...
	private static final long serialVersionUID = 1L;
	private int selectedDay = 0;
	private EventController controller;
	
	// month arrays..
	private final String[] months = new String[] {"January", "Feburary", "March",
			"April", "May", "June", "July", "August", "September", "October", 
			"November", "December"};
		
	// UI
	private JPanel contentPane;
	private JButton[][] dayNumber;
	private JButton btnToday;
	private JButton btnLeft;
	private JButton btnRight;
	private JTextArea eventDetails;
	private JButton btnDay;
	private JButton btnWeek;
	private JButton btnMonth;
	private JButton btnAgenda;
	private JButton btnFromFile;
	private JLabel monthYearTitle;
	private JButton btnCreate;
	private JButton btnDayBack;
	private JButton btnDayForward;
	
	/**
	 * Constructor.
	 */
	public CalendarView() {
		
		setTitle("Calendar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 476);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		intializingComponents();
		listeners();
		
		this.controller = EventController.getInstance();
		this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
		displayMonth();
			
	}
	
	/**
	 * If any day button is pressed.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		JButton button = (JButton)e.getSource();
		resetDayColors();
		button.setForeground(Color.RED);
		this.selectedDay = Integer.parseInt(button.getText());
		controller.setCalendarDay(selectedDay);
		
	}

	/**
	 * Resetting the day color back to black.
	 */
	private void resetDayColors() {
		
		for(int i = 0; i < dayNumber.length; i++) {
			for(JButton button: dayNumber[i]) {
				
				button.setForeground(Color.BLACK);
				
			}
		}
		
	}
	
	/**
	 * Displaying the month.
	 * 
	 * @param calendar
	 */
	public void displayMonth() {
		
		Calendar calendar = controller.getCurrentCalendar();
		
		// resetting..
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				dayNumber[i][j].setVisible(false);
			}
		}
		
		monthYearTitle.setText(months[calendar.get(Calendar.MONTH)]+" "+calendar.get(Calendar.YEAR));
		int totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, 1);;
		int x = 0;
		int y = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int day = 1;
		for(int i = 0; i < totalDays; i++) {
			
			if(this.selectedDay == day) {
				
				dayNumber[x][y].setForeground(Color.RED);
				
			}
			
			dayNumber[x][y].setText(day+"");
			dayNumber[x][y].setVisible(true);
			calendar.set(Calendar.DAY_OF_MONTH, day);
			//
			// Calendar Events..
			//
			day++;
			y++;
			if(y >= 7) {
				y = 0;
				x++;
			}
			
		}
		calendar.set(Calendar.DAY_OF_MONTH, this.selectedDay);

	}
	
	/**
	 * Listeners for the whole class.
	 */
	private void listeners() {
		
		btnToday.addActionListener(action -> {
			
			resetDayColors();
			controller.setTodayCalendar();
			this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
			displayMonth();
			
		});

		btnLeft.addActionListener(action -> {
			
			resetDayColors();
			controller.updateMonth(-1);
			displayMonth();
			
		});

		btnRight.addActionListener(action -> {

			resetDayColors();
			controller.updateMonth(1);
			displayMonth();
			
		});

		btnDayBack.addActionListener(action -> {
			
			System.out.println("Back...");
			resetDayColors();
			controller.updateDay(-1);
			this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
			displayMonth();
			
		});
		
		btnDayForward.addActionListener(action -> {

			System.out.println("Forward...");
			resetDayColors();
			controller.updateDay(1);
			this.selectedDay = controller.getCurrentCalendar().get(Calendar.DAY_OF_MONTH);
			displayMonth();
			
		});
		
		btnCreate.addActionListener(action -> {
			
			addNewEvent();
			
		});
		
		btnDay.addActionListener(action -> {

			showEventDetails(controller.getCalendarEventsByDay());
			
		});
		
		btnMonth.addActionListener(action -> {

			showEventDetails(controller.getCalendarEventsByMonth());
			
		});

		btnWeek.addActionListener(action -> {
			
			showEventDetails(controller.getCalendarEventsByWeek());
			
		});
		
		btnAgenda.addActionListener(action -> {
			
			agendaDetails();
			
		});
		
		btnFromFile.addActionListener(action -> {
			
			JFileChooser chooser = new JFileChooser();
			int result = chooser.showOpenDialog(null);
			if(result == JFileChooser.APPROVE_OPTION) {
				
				File file = chooser.getSelectedFile();
				boolean filesStatus = controller.executeFile(file);
				if(filesStatus) {
					JOptionPane.showMessageDialog(null, "File uploaded successfully.");
				}else {
					JOptionPane.showMessageDialog(null, "File failed to upload due to wrong format.");
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "No any txt file Selected.");
			}
			
		});
		
	}
	
	/**
	 * Agenda Details.
	 */
	private void agendaDetails() {
		
		Calendar startingDate = Calendar.getInstance();
		Calendar endingDate = Calendar.getInstance();
		do {
			try 
			{
				
				String[] startingDateArray = JOptionPane.showInputDialog("Enter Starting Date (MM/DD/YYYY)").split("/");
				int month = Integer.parseInt(startingDateArray[0]);
				if(month > 0 && month <= 12) {
					
					startingDate.set(Calendar.MONTH, month - 1);
				
				int day = Integer.parseInt(startingDateArray[1]);
				if(day > 0 && day <= 31) {
						
					startingDate.set(Calendar.DAY_OF_MONTH, day);
					
				int year = Integer.parseInt(startingDateArray[2]);
				if(year > 0) {
							
					startingDate.set(Calendar.YEAR, year);
				break;
						
						}
						
					}
					
				}
				JOptionPane.showMessageDialog(null, "Please enter correct format of date (MMM/DD/YYYY)");
					
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter correct format of date (MMM/DD/YYYY)");
			}
		} 
		while(startingDate.after(Calendar.getInstance()));
		
		do {
			try 
			{
				
				String[] endingDateArray = JOptionPane.showInputDialog("Enter Ending Date (MM/DD/YYYY)").split("/");
				int month = Integer.parseInt(endingDateArray[0]);
				if(month > 0 && month <= 12) {
					
					endingDate.set(Calendar.MONTH, month - 1);
				
				int day = Integer.parseInt(endingDateArray[1]);
				if(day > 0 && day <= 31) {
						
					endingDate.set(Calendar.DAY_OF_MONTH, day);
					
				int year = Integer.parseInt(endingDateArray[2]);
				if(year > 0) {
							
					endingDate.set(Calendar.YEAR, year);
					break;
							
						}
						
					}
					
				}
				JOptionPane.showMessageDialog(null, "Please enter correct format of date (MMM/DD/YYYY)");
					
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please enter correct format of date (MMM/DD/YYYY)");
			}
		} 
		while(endingDate.after(Calendar.getInstance()));
		showEventDetails(controller.getCalendarEventsBetweenn(startingDate, endingDate));
		
	}
	
	
	/**
	 * Showing event details.
	 * 
	 * @param list
	 */
	private void showEventDetails(ArrayList<Event> list) {
		
		String events = "";
		for(Event event: list) {
			events += event + "\n";
		}
		eventDetails.setText(events);
		
	}
	
	/**
	 * Taking input and adding a new event.
	 */
	private void addNewEvent() {
		
		String eventName = JOptionPane.showInputDialog("Enter Event Name:");
		int hoursFromInt = 0, hoursEndInt = 0;


		// taking hours.
		do {
			try {
				hoursFromInt = Integer.parseInt(JOptionPane.showInputDialog("Enter Starting Hour (1-24)"));
				if(hoursFromInt < 1 || hoursFromInt > 24) {
					JOptionPane.showMessageDialog(null, "Please select correct value from 1-24");
				}else {
					break;
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please select correct value from 1-24");
			}
		} 
		while(eventName != null);
		do {
			try {
				hoursEndInt = Integer.parseInt(JOptionPane.showInputDialog("Enter Ending Hour (1-24)"));
				if(hoursEndInt < 1 || hoursEndInt > 24 || hoursEndInt < hoursFromInt) {
					JOptionPane.showMessageDialog(null, "Please select correct value from 1-24 and more than starting hours.");
				}else {
					break;
				}
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, "Please select correct value from 1-24");
			}
		} 
		while(hoursFromInt >= 0 && hoursEndInt >= 0);
		
		Calendar eventCalendar = Calendar.getInstance();
		eventCalendar.set(Calendar.YEAR, this.controller.getCurrentCalendar().get(Calendar.YEAR));
		eventCalendar.set(Calendar.MONTH, this.controller.getCurrentCalendar().get(Calendar.MONTH));
		eventCalendar.set(Calendar.DAY_OF_MONTH, this.selectedDay);
		Event event = new Event(eventName, eventCalendar, hoursFromInt, hoursEndInt);
		boolean result = controller.addEvent(event);
		if(result) {
			JOptionPane.showMessageDialog(null, "Event "+eventName+" added successfully.");
		}else {
			JOptionPane.showMessageDialog(null, "Event "+eventName+" faield to add due to conflicts with other event.");
		}
		
	}

	/**
	 * Initializing all components and adding
	 * them into the frame.
	 */
	private void intializingComponents() {
		
		btnToday = new JButton("Today");
		btnToday.setBackground(Color.WHITE);
		btnToday.setBounds(20, 16, 87, 47);
		contentPane.add(btnToday);

		btnDayBack = new JButton("<");
		btnDayBack.setBackground(Color.WHITE);
		btnDayBack.setBounds(119, 16, 45, 47);
		contentPane.add(btnDayBack);

		btnDayForward = new JButton(">");
		btnDayForward.setBackground(Color.WHITE);
		btnDayForward.setBounds(176, 16, 45, 47);
		contentPane.add(btnDayForward);
		
		btnFromFile = new JButton("From File");
		btnFromFile.setBackground(Color.WHITE);
		btnFromFile.setBounds(590, 12, 93, 37);
		contentPane.add(btnFromFile);
		
		btnAgenda = new JButton("Agenda");
		btnAgenda.setBackground(Color.WHITE);
		btnAgenda.setBounds(610, 61, 70, 47);
		contentPane.add(btnAgenda);
		
		btnMonth = new JButton("Month");
		btnMonth.setBackground(Color.WHITE);
		btnMonth.setBounds(550, 61, 60, 47);
		contentPane.add(btnMonth);
		
		btnWeek = new JButton("Week");
		btnWeek.setBackground(Color.WHITE);
		btnWeek.setBounds(490, 61, 60, 47);
		contentPane.add(btnWeek);
		
		btnDay = new JButton("Day");
		btnDay.setBackground(Color.WHITE);
		btnDay.setBounds(430, 61, 60, 47);
		contentPane.add(btnDay);
		
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
		
		btnCreate = new JButton("Create");
		btnCreate.setForeground(Color.RED);
		btnCreate.setBounds(20, 70, 398, 38);
		contentPane.add(btnCreate);

		btnLeft = new JButton("<");
		btnLeft.setBackground(Color.WHITE);
		btnLeft.setBounds(20, 113, 45, 38);
		contentPane.add(btnLeft);

		btnRight = new JButton(">");
		btnRight.setBackground(Color.WHITE);
		btnRight.setBounds(77, 113, 45, 38);
		contentPane.add(btnRight);

		
		String[] weeks = {"SU","M","T","W","T","F","SA"};
		JLabel[] labels = new JLabel[7];
		int x = 30;
		for(int i = 0; i < labels.length; i++) {
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
		for(int i = 0; i < dayNumber.length; i++) {
			
			for(int j = 0; j < dayNumber[0].length; j++) {
				
				dayNumber[i][j] = new JButton(count+"");
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

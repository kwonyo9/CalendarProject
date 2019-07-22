package Proj;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainButton extends JPanel
{
	/**
	 *
	 * @param model
	 */
	public MainButton(final EventModel model)
	{
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		//INITIALIZING ALL APPLICATION BUTTONS
		final JButton today = new JButton("TODAY");
		final JButton create = new JButton("CREATE");
		final JButton nextMonth = new JButton(">");
		final JButton previousMonth = new JButton("<");
		final JButton quit = new JButton("QUIT");
		final JButton load = new JButton("LOAD");
		final JButton day = new JButton("DAY");
		final JButton week = new JButton("WEEK");
		final JButton month = new JButton("MONTH");
		final JButton agenda = new JButton("AGENDA");

		//Functionality for TODAY button
		today.setFont(new Font("Arial", Font.PLAIN, 10));
		today.setPreferredSize(new Dimension(40,20));
		today.addActionListener(event ->
		{
			Calendar cal = new GregorianCalendar();
		});

		//Functionality for TODAY button
		create.setFont(new Font("Arial", Font.PLAIN, 8));
		create.setPreferredSize(new Dimension(40,20));
		create.addActionListener(event ->
		{
			//
		});

		//Functionality for PREVIOUS button
		previousMonth.setFont(new Font("Arial", Font.PLAIN, 10));
		previousMonth.setPreferredSize(new Dimension(40,20));
		previousMonth.addActionListener(event ->
		{
			//
		});

		//Functionality for NEXT button
		nextMonth.setFont(new Font("Arial", Font.PLAIN, 10));
		nextMonth.setPreferredSize(new Dimension(40,20));
		nextMonth.addActionListener(event ->
		{
			//
		});

		//Functionality for QUIT button
		quit.setFont(new Font("Arial", Font.PLAIN, 10));
		quit.setPreferredSize(new Dimension(40,20));
		quit.addActionListener(event -> System.exit(0));

		//Functionality for LOAD button
		load.setFont(new Font("Arial", Font.PLAIN, 10));
		load.setPreferredSize(new Dimension(40,20));
		load.addActionListener(event ->
		{
			System.out.println("Read event from file.");
			File file = new File("event.txt");
			String[] eventData = new String[6];
			try{
				Scanner sc = new Scanner(file);
				int i = 0;
				while(sc.hasNextLine()){
					eventData[i] = sc.nextLine();
					System.out.println(eventData[i]);
					i++;
				}
				sc.close();
			} catch (FileNotFoundException e){
				e.printStackTrace();
			}
			if(eventData[0]!= null){
				String title = eventData[0];
				int year = Integer.valueOf(eventData[1]);
				int months = Integer.valueOf(eventData[2]);
				int days = Integer.valueOf(eventData[3]);
				String startTime = eventData[4];
				String endTime = eventData[5];
				Event events = new Event(title, year, months, days, startTime, endTime);
				System.out.println(events.toString());
				System.out.println("Event has been created.");
			}
		});

		//Functionality for DAY button
		day.setFont(new Font("Arial", Font.PLAIN, 10));
		day.setPreferredSize(new Dimension(40,20));
		day.addActionListener(event ->
		{
			model.setViewType(ViewTypes.DAY);
		});

		//Functionality for WEEK button
		week.setFont(new Font("Arial", Font.PLAIN, 10));
		week.setPreferredSize(new Dimension(40,20));
		week.addActionListener(event ->
		{
			model.setViewType(ViewTypes.WEEK);
		});

		//Functionality for MONTH button
		month.setFont(new Font("Arial", Font.PLAIN, 10));
		month.setPreferredSize(new Dimension(40,20));
		month.addActionListener(event ->
		{
			model.setViewType(ViewTypes.MONTH);
		});

		//Functionality for AGENDA button
		agenda.setFont(new Font("Arial", Font.PLAIN, 8));
		agenda.setPreferredSize(new Dimension(40,20));
		agenda.addActionListener(event ->
		{
			model.setViewType(ViewTypes.AGENDA);
		});

		//Initializing JPanel that will contain the JButtons
		final JPanel upper = new JPanel();
		upper.setBackground(Color.WHITE);

		//TODO
		upper.add(today);
		//		upper.add(previousMonth);
		upper.add(previousMonth);
		upper.add(nextMonth);
		//	upper.add(Box.createRigidArea(new Dimension(350,0)));

		upper.add(create);
		//		upper.add(nextMonth);
		upper.add(load);
		//	upper.add(create);
		
		upper.add(day);
		upper.add(week);
		upper.add(month);
		upper.add(agenda);
		
		
		upper.add(quit);
		upper.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		add(upper, BorderLayout.NORTH);
		//	add(lower, BorderLayout.SOUTH);
	}
}
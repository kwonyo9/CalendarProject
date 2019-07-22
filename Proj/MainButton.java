package Proj;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

			//ArrayList that will contain the event data in the .txt file
			ArrayList<String> eventData = new ArrayList<>();

			try
			{
				//We start File reader and memory to read the file
				FileReader fileReader = new FileReader(file);
				BufferedReader bufferedReader = new BufferedReader(fileReader);

				String line = bufferedReader.readLine();
				while (line != null)
				{
					//Each event has 6 attributes, so we iterate 6 times
					for (int i = 0; i < 6; i++)
					{
						eventData.add(line); //Add line to the event data
						line = bufferedReader.readLine(); //Read next line
					}
					if (eventData.get(0) != null)
					{
						//Convert all data to suitable event format
						String title = eventData.get(0);
						int year = Integer.valueOf(eventData.get(1));
						int months = Integer.valueOf(eventData.get(2));
						int days = Integer.valueOf(eventData.get(3));
						String startTime = eventData.get(4);
						String endTime = eventData.get(5);
						model.addEvent(new Event(title, year, months, days, startTime, endTime));
						
						//We clear all data to continue our file read process
						eventData.clear();
						line = bufferedReader.readLine();
					}
				}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
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
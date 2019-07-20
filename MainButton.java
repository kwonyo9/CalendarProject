package Proj;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainButton extends JPanel
{
	/**
	 *
	 * @param model
	 */
	public MainButton(final EventModel model)
	{
		
		setBackground(Color.white);
		setLayout(new BorderLayout());

		final JPanel upper = new JPanel();
		//	JPanel lower = new JPanel();
		upper.setBackground(Color.WHITE);
		//	lower.setBackground(Color.white);

		final JButton today = new JButton("TODAY");
		final JButton create = new JButton("CREATE");
		final JButton previous = new JButton("<");
		final JButton next = new JButton(">");
		//		JButton previousMonth = new JButton("<<");
		//		JButton nextMonth = new JButton(">>");
		final JButton quit = new JButton("QUIT");
		final JButton load = new JButton("LOAD");
		final JButton day = new JButton("DAY");
		final JButton week = new JButton("WEEK");
		final JButton month = new JButton("MONTH");
		final JButton agenda = new JButton("AGENDA");
		

		today.setFont(new Font("Arial", Font.PLAIN, 10));
		today.setPreferredSize(new Dimension(40,20));
		today.addActionListener(event ->
		{
			Calendar cal = new GregorianCalendar();
		});
		
		create.setFont(new Font("Arial", Font.PLAIN, 8));
		create.setPreferredSize(new Dimension(40,20));
		create.addActionListener(e ->
		{
			//
		});
		
		previous.setFont(new Font("Arial", Font.PLAIN, 10));
		previous.setPreferredSize(new Dimension(40,20));
		previous.addActionListener(arg0 ->
		{
			//
		});

		next.setFont(new Font("Arial", Font.PLAIN, 10));
		next.setPreferredSize(new Dimension(40,20));
		next.addActionListener(arg0 ->
		{
			//
		});

		quit.setFont(new Font("Arial", Font.PLAIN, 10));
		quit.setPreferredSize(new Dimension(40,20));
		quit.addActionListener(arg0 ->
		{
			//
		});

		load.setFont(new Font("Arial", Font.PLAIN, 10));
		load.setPreferredSize(new Dimension(40,20));
		load.addActionListener(e ->
		{
			//
		});

		day.setFont(new Font("Arial", Font.PLAIN, 10));
		day.setPreferredSize(new Dimension(40,20));
		day.addActionListener(e ->
		{
			//
		});

		week.setFont(new Font("Arial", Font.PLAIN, 10));
		week.setPreferredSize(new Dimension(40,20));
		week.addActionListener(e ->
		{
			//
		});

		month.setFont(new Font("Arial", Font.PLAIN, 10));
		month.setPreferredSize(new Dimension(40,20));
		month.addActionListener(e ->
		{
			//
		});

		agenda.setFont(new Font("Arial", Font.PLAIN, 8));
		agenda.setPreferredSize(new Dimension(40,20));
		agenda.addActionListener(e ->
		{
			//
		});

		//TODO
		upper.add(today);
		//		upper.add(previousMonth);
		upper.add(previous);
		upper.add(next);	
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
package Proj;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

//Testing the commit/edition to another file

public class MainButton extends JPanel{

	
	public MainButton(final EventModel model){
		
		setBackground(Color.white);
		setLayout(new BorderLayout());

		final JPanel upper = new JPanel();
		//	JPanel lower = new JPanel();
		upper.setBackground(Color.white);
		//	lower.setBackground(Color.white);

		final JButton today = new JButton("TODAY");
		final JButton create = new JButton("CREATE");
		final JButton previous = new JButton("<");
		final JButton next = new JButton(">");
		//		JButton previousMonth = new JButton("<<");
		//		JButton nextMonth = new JButton(">>");
		final JButton quit = new JButton("QUIT");
		final JButton load = new JButton("LOAD EVENTS FROM FILE");
		final JButton day = new JButton("DAY");
		final JButton week = new JButton("WEEK");
		final JButton month = new JButton("MONTH");
		final JButton agenda = new JButton("AGENDA");
		
		final Calendar cal = Calendar.getInstance();

		today.setOpaque(true);
		today.setBorderPainted(false); // makes color bg show properly on Mac
		today.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar cal = new GregorianCalendar();
				
			}
		});
		
		create.setOpaque(true);
		create.setBorderPainted(false);
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		previous.setOpaque(true);
		previous.setBorderPainted(false);
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});

		next.setOpaque(true);
		next.setBorderPainted(false);
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});

		quit.setOpaque(true);
		quit.setBorderPainted(false);
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});

		load.setOpaque(true);
		load.setBorderPainted(false);
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				
			}
		});

		day.setOpaque(true);
		day.setBorderPainted(false);
		day.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});

		week.setOpaque(true);
		week.setBorderPainted(false);
		week.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		month.setOpaque(true);
		month.setBorderPainted(false);
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
			}
		});

		agenda.setOpaque(true);
		agenda.setBorderPainted(false);
		agenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

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

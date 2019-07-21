package Proj;


import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class MainButton extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainButton(final EventModel model){
		
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
		today.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Calendar cal = new GregorianCalendar();
			}
		});
		
		create.setFont(new Font("Arial", Font.PLAIN, 8));
		create.setPreferredSize(new Dimension(40,20));
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		previous.setFont(new Font("Arial", Font.PLAIN, 10));
		previous.setPreferredSize(new Dimension(40,20));
		previous.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				
			}
		});

		next.setFont(new Font("Arial", Font.PLAIN, 10));
		next.setPreferredSize(new Dimension(40,20));
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});

		quit.setFont(new Font("Arial", Font.PLAIN, 10));
		quit.setPreferredSize(new Dimension(40,20));
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});

		load.setFont(new Font("Arial", Font.PLAIN, 10));
		load.setPreferredSize(new Dimension(40,20));
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae){
				System.out.println("Read event from file.");
                File file = new File("input_event.txt");
                String[] eventData = new String[6];
                try {
                    Scanner sc = new Scanner(file);
                    int i = 0;
                    while (sc.hasNextLine()) {
                        eventData[i] = sc.nextLine();
                        System.out.println(eventData[i]);
                        i++;
                    }
                    sc.close();
                } catch (FileNotFoundException ea) {
                    ea.printStackTrace();
                }
                if (eventData[0] != null) {
                    String title = eventData[0];
                    int year = Integer.valueOf(eventData[1]);
                    int month = Integer.valueOf(eventData[2]);
                    int day = Integer.valueOf(eventData[3]);
                    String startTime = eventData[4];
                    String endTime = eventData[5];
                    Event event = new Event(title, year, month, day, startTime, endTime);
                    System.out.println("An event is created.");
                }
				
			}
		});

		day.setFont(new Font("Arial", Font.PLAIN, 10));
		day.setPreferredSize(new Dimension(40,20));
		day.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
			
		week.setFont(new Font("Arial", Font.PLAIN, 10));
		week.setPreferredSize(new Dimension(40,20));
		week.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		month.setFont(new Font("Arial", Font.PLAIN, 10));
		month.setPreferredSize(new Dimension(40,20));
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
			}
		});

		agenda.setFont(new Font("Arial", Font.PLAIN, 8));
		agenda.setPreferredSize(new Dimension(40,20));
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

package Proj;

import java.awt.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class MainView
{
	private EventModel model;
	private final Calendar cal;
	private final JScrollPane scrollPane;
	private final JLabel monthLabel = new JLabel();
	private final JPanel monthPanel;
	private final JPanel eventsPanel;

	/**
	 *
	 * @param model
	 */
	public MainView(final EventModel model)
	{
		this.model = model;
		this.cal = model.getCalendar();


		MainButton menu = new MainButton(model);
		menu.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));

		JFrame frame = new JFrame();
		JPanel month = new JPanel();

		JButton previousMonth = new JButton("<");
		JButton nextMonth = new JButton(">");


		previousMonth.setBackground(new Color(189,189,189));
		previousMonth.setForeground(Color.BLACK);

		//TODO
		previousMonth.addActionListener(event -> System.out.println());

		//TODO
		nextMonth.addActionListener(event -> System.out.println());

		monthPanel = new JPanel();
		monthPanel.setLayout(new GridLayout(0, 7, 0, 0));
		JPanel monthWrap = new JPanel();
		monthWrap.setLayout(new BorderLayout());
		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		month.add(previousMonth, BorderLayout.EAST);
		month.add(monthLabel, BorderLayout.CENTER);
		month.add(nextMonth, BorderLayout.WEST);
		monthWrap.add(month, BorderLayout.NORTH);
		monthWrap.add(monthPanel, BorderLayout.CENTER);
		monthWrap.setSize(new Dimension(400,400));
		monthWrap.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
		drawMonth(monthPanel);

		scrollPane = new JScrollPane();
		eventsPanel = new JPanel();
		//dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.PAGE_AXIS));
		eventsPanel.setLayout(new BorderLayout());
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane.getViewport().add(eventsPanel);
		scrollPane.setPreferredSize(new Dimension(500, 200));

		eventsPanel.add(menu,BorderLayout.BEFORE_FIRST_LINE);
		frame.add(monthWrap, BorderLayout.WEST);
		frame.add(scrollPane, BorderLayout.EAST);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void repaint()
	{
		monthPanel.removeAll();
		drawMonth(monthPanel);
		monthPanel.revalidate();
		monthPanel.repaint();
	}

	private void drawMonth(JPanel monthPanel) {
		//Gets the month and the year and sets it on the top of the month view
		monthLabel.setText(new SimpleDateFormat("MMMM yyyy").format(cal.getTime()));
		monthLabel.setFont(new Font("Tahoma", Font.BOLD, 24));

		//Add Week Labels at top of Month View
		for (int i = 0; i<7; i++) {
			JLabel day = new JLabel("" + DAYS_OF_WEEK.values()[i], SwingConstants.CENTER);
			day.setBorder(new CompoundBorder(day.getBorder(), new EmptyBorder(10, 10, 10, 10)));
			day.setFont(new Font("Tahoma", Font.BOLD, 12));
			if(i == 0 || i == 6) {
				day.setForeground(Color.RED);
			}
			monthPanel.add(day);
		}

		//Adds the days in the month to the JLabel day 
		int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Calendar getStart = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
		int startDay = getStart.get(Calendar.DAY_OF_WEEK);
		for (int i = 1; i<daysInMonth+startDay; i++) {
			if (i < startDay) {
				final JLabel day = new JLabel("");
				monthPanel.add(day);
			} else {
				int dayNumber = i-startDay+1;
				final JLabel day = new JLabel(dayNumber + "", SwingConstants.CENTER);
				day.addMouseListener(new MouseListener() {
					public void mouseClicked(MouseEvent e) {
						int num = Integer.parseInt(day.getText());
						model.setDay(num);
					}
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
				});
				if (dayNumber == cal.get(Calendar.DAY_OF_MONTH)) {
					day.setBorder(BorderFactory.createLineBorder(Color.black));
				}
				day.setFont(new Font("Tahoma", Font.BOLD, 16));
				//Adds the JLabel to the JPanel
				monthPanel.add(day);
			}
		}
	}
}

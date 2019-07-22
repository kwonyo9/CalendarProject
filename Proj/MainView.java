package Proj;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class MainView
{
	private EventModel model;
	private final Calendar calendar;
	private final JScrollPane scrollPane;
	private final JLabel monthLabel;
	private final JPanel monthPanel;
	private final JPanel eventsPanel;
	private final JTextArea textArea;

	/**
	 *
	 * @param model
	 */
	public MainView(final EventModel model)
	{
		this.model = model;
		this.calendar = model.getCalendar();

		monthLabel = new JLabel();

		textArea = new JTextArea();

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
		monthPanel.setBackground(Color.WHITE);
		JPanel monthWrap = new JPanel();

		month.add(previousMonth, BorderLayout.EAST);
		month.add(monthLabel, BorderLayout.CENTER);
		month.add(nextMonth, BorderLayout.WEST);
		monthWrap.add(month, BorderLayout.NORTH);
		monthWrap.add(monthPanel, BorderLayout.CENTER);
		monthWrap.setSize(new Dimension(300,300));
		drawMonth(monthPanel);

		scrollPane = new JScrollPane();
		eventsPanel = new JPanel();
		eventsPanel.add(textArea);
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

	private void drawMonth(JPanel monthPanel)
	{

		monthLabel.setText(new SimpleDateFormat("MMMM yyyy").format(calendar.getTime()));

		//Adds the days in the month to the JLabel day
		int daysInMonth = calendar.get(Calendar.DAY_OF_MONTH);
		Calendar getStart = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
		int startDay = getStart.get(Calendar.DAY_OF_WEEK);

		for (int i = 1; i < daysInMonth + startDay; i++)
		{
			int dayNumber = i - startDay + 1;
			final JLabel day = new JLabel(dayNumber + "");
			day.addMouseListener(new MouseListener(){

				public void mouseClicked(MouseEvent e)
				{
					int num = Integer.parseInt(day.getText());
					model.setDay(num);
					monthLabel.revalidate();
					monthLabel.repaint();
				}
				public void mousePressed(MouseEvent e) {}
				public void mouseReleased(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
			});

			if (dayNumber == calendar.get(Calendar.DAY_OF_MONTH))
			{
				day.setBorder(BorderFactory.createLineBorder(Color.RED));
			}

			Font d = new Font("Dialog", Font.PLAIN, 18);
			day.setFont(d);
			day.setBackground(Color.BLACK);


			//Adds the JLabel to the JPanel
			monthPanel.add(day);
		}

	}
}

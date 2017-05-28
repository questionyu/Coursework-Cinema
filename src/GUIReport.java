import javax.swing.*;
import java.awt.*;

/**
 * Title        GUIReport.java
 * Description  This class defines the UI of report.
 */
class GUIReport extends JPanel {
	/**
	 * Constructor function. Create a panel which shows the report of kiosk.
	 */
	GUIReport() {
		super(new BorderLayout());

		JPanel reportPanel = new JPanel();
		reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));

		//the total sale of each film
		JLabel label1 = new JLabel("The total sale of each film:", JLabel.CENTER);
		label1.setFont(Kiosk.getUIMainFont());
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.CYAN);
		panel1.add(label1);
		reportPanel.add(panel1);

		String[] columnNames1 = {"Film", "Amount", "Total Sale"};
		String[][] data1 = new String[KioskController.films.size()][];
		for (int i = 0; i < data1.length; i++) {
			Film film = KioskController.getFilm(i);
			double total = 0.0;
			int amount = 0;
			for (Ticket ticket : KioskController.tickets) {
				if (film.equals(ticket.getFilm())) {
					total += ticket.getFinalPrice();
					amount++;
				}
			}
			data1[i] = new String[]{film.getName(), "" + amount, "" + total};
		}

		JTable table1 = new JTable(data1, columnNames1);
		table1.setFont(new Font(null, Font.PLAIN, 15));
		JScrollPane scrollPane1 = new JScrollPane(table1);

		reportPanel.add(scrollPane1);

		//total number of tickets sold
		JLabel totalNumberOfTickets = new JLabel("Total number of tickets sold: " + KioskController.tickets.size(), JLabel.CENTER);
		totalNumberOfTickets.setFont(Kiosk.getUIMainFont());
		reportPanel.add(totalNumberOfTickets);

		//each type of ticket sold
		JLabel label3 = new JLabel("Each type of ticket sold", JLabel.CENTER);
		label3.setFont(Kiosk.getUIMainFont());
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.CYAN);
		panel3.add(label3);
		reportPanel.add(panel3);

		int[] amount = new int[]{0, 0, 0, 0};
		for (Ticket ticket : KioskController.tickets) {
			switch (ticket.getType()) {
				case Ticket.ADULT:
					amount[0]++;
					break;
				case Ticket.CHILD:
					amount[1]++;
					break;
				case Ticket.SENIOR:
					amount[2]++;
					break;
				case Ticket.STUDENT:
					amount[3]++;
					break;
			}
		}

		String[] columnNames3 = {"Adult", "Child", "Senior", "Student"};
		String[][] data3 = {{"" + amount[0], "" + amount[1], "" + amount[2], "" + amount[3]}};

		JTable table3 = new JTable(data3, columnNames3);
		table3.setFont(new Font(null, Font.PLAIN, 15));
		JScrollPane scrollPane3 = new JScrollPane(table3);

		reportPanel.add(scrollPane3);

		JPanel reportSouthPanel = new JPanel();
		reportSouthPanel.setLayout(new BoxLayout(reportSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(Kiosk.getUIMainFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(Kiosk.getUIMainFont());
		backButton.addActionListener(e -> Kiosk.showWelcome());

		JButton sendMailButton = new JButton("Send Mail");
		sendMailButton.setFont(Kiosk.getUIMainFont());
		sendMailButton.addActionListener(e -> KioskController.sendMail());

		reportSouthPanel.add(exitButton);
		reportSouthPanel.add(Box.createHorizontalStrut(25));
		reportSouthPanel.add(backButton);
		reportSouthPanel.add(Box.createHorizontalGlue());
		reportSouthPanel.add(sendMailButton);

		add(reportPanel, BorderLayout.CENTER);
		add(reportSouthPanel, BorderLayout.SOUTH);
	}
}

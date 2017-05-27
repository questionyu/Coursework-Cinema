import javax.swing.*;
import java.awt.*;

/**
 * Title        GUIReport.java
 * Description  This class defines the UI of report.
 */
class GUIReport extends JPanel {
	GUIReport() {
		super(new BorderLayout());

		JPanel reportPanel = new JPanel();
		reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));

		//the total sale of each film
		double[] totalOfEachFilm = new double[KioskController.films.size()];
		JLabel[] totalOfEachFilmLabel = new JLabel[KioskController.films.size()];
		for (int i = 0; i < totalOfEachFilm.length; i++) {
			totalOfEachFilm[i] = 0.0;
			Film film = KioskController.films.get(i);
			for (Ticket ticket : KioskController.tickets) {
				if (film.equals(ticket.getFilm()))
					totalOfEachFilm[i] += ticket.getFinalPrice();
			}
			totalOfEachFilmLabel[i] = new JLabel(film.getName() + ": " + totalOfEachFilm[i], JLabel.CENTER);
			totalOfEachFilmLabel[i].setFont(Kiosk.getButtonFont());
		}

		//total number of tickets sold
		JLabel totalNumberOfTickets = new JLabel("Total number of tickets sold: " + KioskController.tickets.size(), JLabel.CENTER);
		totalNumberOfTickets.setFont(Kiosk.getButtonFont());

		//each type of ticket sold
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
		JLabel eachType = new JLabel("Adult: " + amount[0] + " Child: " + amount[1] + " Senior: " + amount[2] + " Student: " + amount[3], JLabel.CENTER);
		eachType.setFont(Kiosk.getButtonFont());

		JLabel label1 = new JLabel("The total sale of each film:", JLabel.CENTER);
		label1.setFont(Kiosk.getButtonFont());
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.CYAN);
		panel1.add(label1);
		reportPanel.add(panel1);
		for (JLabel label : totalOfEachFilmLabel)
			reportPanel.add(label);

		reportPanel.add(totalNumberOfTickets);

		JLabel label3 = new JLabel("Each type of ticket sold", JLabel.CENTER);
		label3.setFont(Kiosk.getButtonFont());
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.CYAN);
		panel3.add(label3);
		reportPanel.add(panel3);
		reportPanel.add(eachType);

		JPanel reportSouthPanel = new JPanel();
		reportSouthPanel.setLayout(new BoxLayout(reportSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(Kiosk.getButtonFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(Kiosk.getButtonFont());
		backButton.addActionListener(e -> Kiosk.showWelcome());

		reportSouthPanel.add(exitButton);
		reportSouthPanel.add(Box.createHorizontalStrut(25));
		reportSouthPanel.add(backButton);
		reportSouthPanel.add(Box.createHorizontalGlue());

		add(reportPanel, BorderLayout.CENTER);
		add(reportSouthPanel, BorderLayout.SOUTH);
	}
}

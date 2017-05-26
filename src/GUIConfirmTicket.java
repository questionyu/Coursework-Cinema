import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Title        .java
 * Description  description...
 */
class GUIConfirmTicket extends JPanel {
	GUIConfirmTicket(Kiosk kiosk) {
		super(new BorderLayout());

		String[] columnNames = {
				"Film",
				"Time",
				"Screen",
				"Seat",
				"Ticket type",
				"Original Price",
				"Discount Price"
		};
		ArrayList<Ticket> orderTickets = CinemaController.orderTickets;
		String[][] data = new String[orderTickets.size()][];
		for (int i = 0; i < orderTickets.size(); i++) {
			Ticket ticket = orderTickets.get(i);
			data[i] = new String[]{
					ticket.getFilmName(),
					ticket.getTime(),
					"" + ticket.getScreen(),
					ticket.getSeat(),
					ticket.getType(),
					"" + ticket.getPrice(),
					"" + ticket.getFinalPrice()
			};
		}

		JTable table = new JTable(data, columnNames);
		table.setFont(new Font(null, Font.PLAIN, 15));
//		FitTableColumns(table);
		JScrollPane scrollPane = new JScrollPane(table);
		JLabel totalPriceLabel = new JLabel("Total Price: Original: " + CinemaController.orderTicketTotalPrice() + " After discount: " + CinemaController.orderTicketTotalFinalPrice(), JLabel.CENTER);

		JPanel ticketInformationPanel = new JPanel(new BorderLayout());
		ticketInformationPanel.add(scrollPane, BorderLayout.CENTER);
		ticketInformationPanel.add(totalPriceLabel, BorderLayout.SOUTH);

		JPanel confirmTicketSouthPanel = new JPanel();
		confirmTicketSouthPanel.setLayout(new BoxLayout(confirmTicketSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(kiosk.getButtonFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(kiosk.getButtonFont());
		backButton.addActionListener(e -> kiosk.showListSeat());

		JButton payButton = new JButton("Pay");
		payButton.setFont(kiosk.getButtonFont());
//		payButton.addActionListener();

		confirmTicketSouthPanel.add(exitButton);
		confirmTicketSouthPanel.add(Box.createHorizontalStrut(25));
		confirmTicketSouthPanel.add(backButton);
		confirmTicketSouthPanel.add(Box.createHorizontalGlue());
		confirmTicketSouthPanel.add(payButton);

		add(ticketInformationPanel, BorderLayout.CENTER);
		add(confirmTicketSouthPanel, BorderLayout.SOUTH);
	}
}

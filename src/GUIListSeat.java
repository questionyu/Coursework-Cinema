import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Title
 * Description
 */
class GUIListSeat extends JPanel implements ActionListener {
	private int ticketType = Ticket.ADULT;
	private Color currentColor = Ticket.ADULT_COLOR;

	GUIListSeat(Kiosk kiosk, Film film, String screening) {
		super(new BorderLayout());

		String[] screen = screening.split("/");

		JPanel selectSeatPanel = new JPanel(new BorderLayout());

		JPanel seatPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(0, 1));

		JLabel[] cols = new JLabel[5];
		cols[0] = new JLabel("A", JLabel.CENTER);
		cols[0].setFont(kiosk.getButtonFont());
		cols[1] = new JLabel("B", JLabel.CENTER);
		cols[1].setFont(kiosk.getButtonFont());
		cols[2] = new JLabel("C", JLabel.CENTER);
		cols[2].setFont(kiosk.getButtonFont());
		cols[3] = new JLabel("D", JLabel.CENTER);
		cols[3].setFont(kiosk.getButtonFont());
		cols[4] = new JLabel("E", JLabel.CENTER);
		cols[4].setFont(kiosk.getButtonFont());

		int[][] seats = null;
		switch (Integer.parseInt(screen[0])) {
			case 1:
				seats = new int[][]{{1, 1, 1, 1, 0, 1, 1, 1, 1},
						{1, 1, 1, 1, 0, 1, 1, 1, 1},
						{1, 1, 1, 1, 0, 1, 1, 1, 1},
						{1, 1, 1, 1, 0, 1, 1, 1, 1}};
				seatPanel.setLayout(new GridLayout(seats.length, seats[0].length));
				break;
			case 2:
				seats = new int[][]{{1, 1, 1, 1, 0, 1, 1, 1, 1},
						{0, 1, 1, 1, 0, 1, 1, 1, 0},
						{0, 1, 1, 1, 0, 1, 1, 1, 0},
						{0, 1, 1, 1, 0, 1, 1, 1, 0}};
				seatPanel.setLayout(new GridLayout(seats.length, seats[0].length));
				break;
			case 3:
				seats = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1},
						{1, 1, 0, 1, 1, 0, 1, 1},
						{1, 1, 0, 1, 1, 0, 1, 1},
						{1, 1, 0, 1, 1, 0, 1, 1},
						{1, 1, 0, 1, 1, 0, 1, 1}};
				seatPanel.setLayout(new GridLayout(seats.length, seats[0].length));
				break;
		}

		int col = seats.length;
		for (int[] seat : seats) {
			int num = 0;
			for (int aSeat : seat) {
				if (aSeat == 1) {
					num++;
				}
			}
			for (int aSeat : seat) {
				if (aSeat == 1) {
					Seat seatButton = new Seat("" + num, film, screening);
					seatButton.setFont(kiosk.getButtonFont());
					seatButton.setSeat(cols[col - 1].getText() + num);
					seatButton.addMouseListener(new mouseAdapter());
					seatPanel.add(seatButton);
					num--;
				} else {
					seatPanel.add(new JLabel());
				}
			}
			rightPanel.add(cols[col - 1]);
			col--;
		}

		JPanel seatAndNumber = new JPanel(new BorderLayout());

		JPanel screenLabelPanel = new JPanel();
		JLabel screenLabel = new JLabel("Screen");
		screenLabel.setFont(kiosk.getButtonFont());
		screenLabelPanel.setBackground(Color.CYAN);
		screenLabelPanel.add(screenLabel);

		seatAndNumber.add(seatPanel, BorderLayout.CENTER);
		seatAndNumber.add(rightPanel, BorderLayout.EAST);
		seatAndNumber.add(screenLabelPanel, BorderLayout.SOUTH);

		//Radio buttons. To select a ticket type.
		JRadioButton adult = new JRadioButton("Adult");
		adult.setFont(kiosk.getButtonFont());
		adult.setForeground(Ticket.ADULT_COLOR);
		adult.setActionCommand("Adult");
		adult.setSelected(true);

		JRadioButton child = new JRadioButton("Child");
		child.setFont(kiosk.getButtonFont());
		child.setForeground(Ticket.CHILD_COLOR);
		child.setActionCommand("Child");

		JRadioButton senior = new JRadioButton("Senior");
		senior.setFont(kiosk.getButtonFont());
		senior.setForeground(Ticket.SENIOR_COLOR);
		senior.setActionCommand("Senior");

		JRadioButton student = new JRadioButton("Student");
		student.setFont(kiosk.getButtonFont());
		student.setForeground(Ticket.STUDENT_COLOR);
		student.setActionCommand("Student");

		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(child);
		group.add(adult);
		group.add(senior);
		group.add(student);

		//Register a listener for the radio buttons.
		child.addActionListener(this);
		adult.addActionListener(this);
		senior.addActionListener(this);
		student.addActionListener(this);

		//Add radio buttons to a panel.
		JPanel radioPanel = new JPanel(new GridLayout(0, 1));
		radioPanel.add(adult);
		radioPanel.add(child);
		radioPanel.add(senior);
		radioPanel.add(student);

		selectSeatPanel.add(radioPanel, BorderLayout.WEST);
		selectSeatPanel.add(seatAndNumber, BorderLayout.CENTER);

		JPanel listSeatSouthPanel = new JPanel();
		listSeatSouthPanel.setLayout(new BoxLayout(listSeatSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(kiosk.getButtonFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(kiosk.getButtonFont());
		backButton.addActionListener(e -> kiosk.showListScreening());

		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(kiosk.getButtonFont());
		confirmButton.addActionListener(e -> kiosk.confirmTicket());

		listSeatSouthPanel.add(exitButton);
		listSeatSouthPanel.add(Box.createHorizontalStrut(25));
		listSeatSouthPanel.add(backButton);
		listSeatSouthPanel.add(Box.createHorizontalGlue());
		listSeatSouthPanel.add(confirmButton);

		add(selectSeatPanel, BorderLayout.CENTER);
		add(listSeatSouthPanel, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Adult":
				ticketType = Ticket.ADULT;
				currentColor = Ticket.ADULT_COLOR;
				break;
			case "Child":
				ticketType = Ticket.CHILD;
				currentColor = Ticket.CHILD_COLOR;
				break;
			case "Senior":
				ticketType = Ticket.SENIOR;
				currentColor = Ticket.SENIOR_COLOR;
				break;
			case "Student":
				ticketType = Ticket.STUDENT;
				currentColor = Ticket.STUDENT_COLOR;
				break;
		}
	}

	class mouseAdapter extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			Seat seatButton = (Seat) e.getSource();
			if (seatButton.isSelected()) {
				seatButton.setForeground(currentColor);
				seatButton.setTicketType(ticketType);
				System.out.println(seatButton.getSeat());
				KioskController.selectedSeats.add(seatButton);
			} else {
				seatButton.setForeground(Color.BLACK);
				KioskController.selectedSeats.removeIf(seat1 -> seat1.getSeat().equals(seatButton.getSeat()));
			}
		}
	}
}
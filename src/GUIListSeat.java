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
class GUIListSeat extends JPanel {
	Film film;
	private Kiosk kiosk;

	GUIListSeat(Kiosk kiosk, Film film, int screeningNo) {
		super(new BorderLayout());
		this.kiosk = kiosk;

		Font buttonFont = kiosk.getButtonFont();

		String screening = film.getScreenings().get(screeningNo);
		String[] screen = screening.split("/");
		JPanel selectSeatPanel = new Screen(kiosk, Integer.parseInt(screen[0]));

		JPanel listSeatSouthPanel = new JPanel();
		listSeatSouthPanel.setLayout(new BoxLayout(listSeatSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(buttonFont);
		exitButton.addActionListener(e -> System.exit(0));
		listSeatSouthPanel.add(exitButton);
		listSeatSouthPanel.add(Box.createHorizontalStrut(25));

		JButton backButton = new JButton("Back");
		backButton.setFont(kiosk.getButtonFont());
		backButton.addActionListener(e -> kiosk.showListScreening());
		listSeatSouthPanel.add(backButton);
		listSeatSouthPanel.add(Box.createHorizontalGlue());

		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(kiosk.getButtonFont());
		listSeatSouthPanel.add(confirmButton);

		add(selectSeatPanel, BorderLayout.CENTER);
		add(listSeatSouthPanel, BorderLayout.SOUTH);
	}
}

class Screen extends JPanel implements ActionListener {
	private int screenNum;
	private String movieName;
	private int[][] seats;
	private Color selectedColor = Ticket.ADULT_COLOR;

	Screen(Kiosk kiosk, int screenNum) {
		setLayout(new BorderLayout());

		JPanel seatPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(0, 1));

		JLabel A = new JLabel("A", JLabel.CENTER);
		A.setFont(kiosk.getButtonFont());
		JLabel B = new JLabel("B", JLabel.CENTER);
		B.setFont(kiosk.getButtonFont());
		JLabel C = new JLabel("C", JLabel.CENTER);
		C.setFont(kiosk.getButtonFont());
		JLabel D = new JLabel("D", JLabel.CENTER);
		D.setFont(kiosk.getButtonFont());
		JLabel E = new JLabel("E", JLabel.CENTER);
		E.setFont(kiosk.getButtonFont());

		switch (screenNum) {
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
				rightPanel.add(E);
				break;
		}
		rightPanel.add(D);
		rightPanel.add(C);
		rightPanel.add(B);
		rightPanel.add(A);

		for (int[] seat : seats) {
			int num = 0;
			for (int aSeat : seat) {
				if (aSeat == 1)
					num++;
			}
			for (int aSeat : seat) {
				if (aSeat == 1) {
					JToggleButton seatButton = new JToggleButton("" + num);
					seatButton.setFont(kiosk.getButtonFont());
					seatButton.addMouseListener(new mouseAdapter());
					seatPanel.add(seatButton);
					num--;
				} else {
					seatPanel.add(new JLabel());
				}
			}
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

		add(radioPanel, BorderLayout.WEST);
		add(seatAndNumber, BorderLayout.CENTER);
	}

	int getScreenNum() {
		return screenNum;
	}

	String getMovieName() {
		return movieName;
	}

	int[][] getSeats() {
		return seats;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "Adult":
				selectedColor = Ticket.ADULT_COLOR;
				break;
			case "Child":
				selectedColor = Ticket.CHILD_COLOR;
				break;
			case "Senior":
				selectedColor = Ticket.SENIOR_COLOR;
				break;
			case "Student":
				selectedColor = Ticket.STUDENT_COLOR;
				break;
		}
	}

	class mouseAdapter extends MouseAdapter {
		mouseAdapter() {
			super();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			JToggleButton toggleButton = (JToggleButton) e.getSource();
			if (toggleButton.isSelected())
				toggleButton.setForeground(selectedColor);
			else
				toggleButton.setForeground(Color.BLACK);
		}
	}
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Title
 * Description
 */
class GUIListSeat extends JPanel {
	Kiosk kiosk;
	Film film;

	GUIListSeat(Kiosk kiosk, Film film, int i) {
		super(new BorderLayout());
		this.kiosk = kiosk;

		Font buttonFont = kiosk.getButtonFont();

		String screening = film.getScreenings().get(i);
		String[] screen = screening.split("/");
		JPanel seatAndMorePanel = new Screen(kiosk, Integer.parseInt(screen[0]));

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

		add(seatAndMorePanel, BorderLayout.CENTER);
		add(listSeatSouthPanel, BorderLayout.SOUTH);
	}
}

class Screen extends JPanel {
	private int screenNum;
	private String movieName;
	private int[] seats;

	Screen(Kiosk kiosk, int screenNum) {
		setLayout(new BorderLayout());

		JPanel seatPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();
		JPanel downPanel = new JPanel();

		JLabel A = new JLabel("A");
		A.setFont(kiosk.getButtonFont());

		JLabel B = new JLabel("B");
		B.setFont(kiosk.getButtonFont());

		JLabel C = new JLabel("C");
		C.setFont(kiosk.getButtonFont());

		JLabel D = new JLabel("D");
		D.setFont(kiosk.getButtonFont());

		JLabel E = new JLabel("E");
		E.setFont(kiosk.getButtonFont());

		int rows;
		int cols = 0;
		switch (screenNum) {
			case 1:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1};
				rows = 4;
				cols = 9;
				seatPanel.setLayout(new GridLayout(rows, cols));
				downPanel.setLayout(new GridLayout(1, cols));
				leftPanel.setLayout(new GridLayout(rows, 1));
				rightPanel.setLayout(new GridLayout(rows, 1));
				break;
			case 2:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						0, 1, 1, 1, 0, 1, 1, 1, 0,
						0, 1, 1, 1, 0, 1, 1, 1, 0,
						0, 1, 1, 1, 0, 1, 1, 1, 0};
				rows = 4;
				cols = 9;
				seatPanel.setLayout(new GridLayout(rows, cols));
				downPanel.setLayout(new GridLayout(1, cols));
				leftPanel.setLayout(new GridLayout(rows, 1));
				rightPanel.setLayout(new GridLayout(rows, 1));
				break;
			case 3:
				seats = new int[]{1, 1, 1, 1, 1, 1, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1};
				rows = 5;
				cols = 8;
				seatPanel.setLayout(new GridLayout(rows, cols));
				downPanel.setLayout(new GridLayout(1, cols));
				leftPanel.setLayout(new GridLayout(rows, 1));
				rightPanel.setLayout(new GridLayout(rows, 1));
				leftPanel.add(E);
				rightPanel.add(E);
				break;
		}
		leftPanel.add(D);
		leftPanel.add(C);
		leftPanel.add(B);
		leftPanel.add(A);
		rightPanel.add(D);
		rightPanel.add(C);
		rightPanel.add(B);
		rightPanel.add(A);

		for (int i = cols; i > 0; i--) {
			JLabel numLabel = new JLabel("<html><center>" + i + "</center></html>");
			numLabel.setFont(kiosk.getButtonFont());
			downPanel.add(numLabel);
		}

		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == 1) {
				JButton seat = new JButton();
				seat.addMouseListener(new mouseAdapter());
				seatPanel.add(seat);
			} else {
				seatPanel.add(new JLabel());
			}
		}

		JPanel seatAndNumber = new JPanel(new BorderLayout());
		seatAndNumber.add(seatPanel, BorderLayout.CENTER);
		seatAndNumber.add(leftPanel, BorderLayout.WEST);
		seatAndNumber.add(rightPanel, BorderLayout.EAST);
		seatAndNumber.add(downPanel, BorderLayout.SOUTH);

		JPanel screenLabelPanel = new JPanel();
		JLabel screenLabel = new JLabel("Screen");
		screenLabel.setFont(kiosk.getButtonFont());
		screenLabelPanel.setBackground(Color.CYAN);
		screenLabelPanel.add(screenLabel);

		add(seatAndNumber, BorderLayout.CENTER);
		add(screenLabelPanel, BorderLayout.SOUTH);
	}

	int getScreenNum() {
		return screenNum;
	}

	String getMovieName() {
		return movieName;
	}

	int[] getSeats() {
		return seats;
	}

	class mouseAdapter extends MouseAdapter {
		mouseAdapter() {
			super();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Title        Screen.java
 * Description  This class defines three screens.
 */
class Screen extends JPanel {
	private int screenNum;
	private String movieName;
	private int[] seats;

	Screen(Kiosk kiosk, int screenNum) {
		super(new BorderLayout());

		JPanel seatPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		JPanel rightPanel = new JPanel();

		JLabel A = new JLabel("A");
		A.setAlignmentX(leftPanel.getAlignmentX());
		A.setFont(kiosk.getButtonFont());

		JLabel B = new JLabel("B");
		B.setAlignmentX(leftPanel.getAlignmentX());
		B.setFont(kiosk.getButtonFont());

		JLabel C = new JLabel("C");
		C.setAlignmentX(leftPanel.getAlignmentX());
		C.setFont(kiosk.getButtonFont());

		JLabel D = new JLabel("D");
		D.setAlignmentX(leftPanel.getAlignmentX());
		D.setFont(kiosk.getButtonFont());

		JLabel E = new JLabel("E");
		E.setAlignmentX(leftPanel.getAlignmentX());
		E.setFont(kiosk.getButtonFont());

		switch (screenNum) {
			case 1:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1};
				seatPanel.setLayout(new GridLayout(4, 9));
				leftPanel.setLayout(new GridLayout(4, 1));
				rightPanel.setLayout(new GridLayout(4, 1));
				leftPanel.add(A);
				leftPanel.add(B);
				leftPanel.add(C);
				leftPanel.add(D);
				rightPanel.add(A);
				rightPanel.add(B);
				rightPanel.add(C);
				rightPanel.add(D);
				break;
			case 2:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						0, 1, 1, 1, 0, 1, 1, 1, 0,
						0, 1, 1, 1, 0, 1, 1, 1, 0,
						0, 1, 1, 1, 0, 1, 1, 1, 0};
				seatPanel.setLayout(new GridLayout(4, 9));
				leftPanel.setLayout(new GridLayout(4, 1));
				rightPanel.setLayout(new GridLayout(4, 1));
				break;
			case 3:
				seats = new int[]{1, 1, 1, 1, 1, 1, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1};
				seatPanel.setLayout(new GridLayout(5, 8));
				leftPanel.setLayout(new GridLayout(5, 1));
				rightPanel.setLayout(new GridLayout(5, 1));
				break;
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

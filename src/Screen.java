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

	Screen(int screenNum) {
		switch (screenNum) {
			case 1:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1};
				setLayout(new GridLayout(4, 9));
				break;
			case 2:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						0, 1, 1, 1, 0, 1, 1, 1, 0,
						0, 1, 1, 1, 0, 1, 1, 1, 0,
						0, 1, 1, 1, 0, 1, 1, 1, 0};
				setLayout(new GridLayout(4, 9));
				break;
			case 3:
				seats = new int[]{1, 1, 1, 1, 1, 1, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1,
						1, 1, 0, 1, 1, 0, 1, 1};
				setLayout(new GridLayout(5, 8));
				break;
		}
		for (int i = 0; i < seats.length; i++) {
			if (seats[i] == 1) {
				JButton seat = new JButton();
				seat.addMouseListener(new mouseAdapter());
				add(seat);
			} else {
				add(new JLabel());
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
		public mouseAdapter() {
			super();
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}
	}
}

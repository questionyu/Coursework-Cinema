import javax.swing.*;
import java.awt.*;

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
		JPanel seatPanel = new Screen(kiosk, Integer.parseInt(screen[0]));

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

		add(seatPanel, BorderLayout.CENTER);
		add(listSeatSouthPanel, BorderLayout.SOUTH);
	}
}

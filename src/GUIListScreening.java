import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Title        GUIListScreening.java
 * Description  This class list all screenings of one movie.
 */
class GUIListScreening extends JPanel {
	Film film;

	GUIListScreening(Kiosk kiosk, Film film) {
		super(new BorderLayout());
		this.film = film;

		JPanel listScreeningPanel = new JPanel();
		listScreeningPanel.setLayout(new BoxLayout(listScreeningPanel, BoxLayout.Y_AXIS));
		ArrayList<String> screenings = film.getScreenings();
		for (int i = 0; i < screenings.size(); i++) {
			JLabel label = new JLabel(screenings.get(i));
			listScreeningPanel.add(label);
		}

		JPanel listScreeningSouthPanel = new JPanel();
		listScreeningSouthPanel.setLayout(new BoxLayout(listScreeningSouthPanel, BoxLayout.X_AXIS));

		Font buttonFont = new Font("Segoe UI", Font.PLAIN, 25);
		JButton exitButton = new JButton("Exit");
		exitButton.setFont(buttonFont);
		exitButton.addActionListener(e -> System.exit(0));
		JButton backButton = new JButton("Back");
		backButton.setFont(buttonFont);
		backButton.addActionListener(e -> kiosk.showListFilm());

		listScreeningSouthPanel.add(exitButton);
		listScreeningSouthPanel.add(Box.createHorizontalStrut(25));
		listScreeningSouthPanel.add(backButton);
		listScreeningSouthPanel.add(Box.createHorizontalGlue());

		add(listScreeningPanel, BorderLayout.CENTER);
		add(listScreeningSouthPanel, BorderLayout.SOUTH);
	}

}

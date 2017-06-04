import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Title        GUIListScreening.java
 * Description  This class list all screenings of one movie.
 */
class GUIListScreening extends JPanel {
	/**
	 * Constructor function.
	 *
	 * @param film The selected film.
	 */
	GUIListScreening(Film film) {
		super(new BorderLayout());

		JPanel listScreeningPanel = new JPanel();
		listScreeningPanel.setLayout(new BoxLayout(listScreeningPanel, BoxLayout.Y_AXIS));
		JPanel[] screen = new JPanel[3];
		JLabel[] screenLabel = new JLabel[screen.length];
		JPanel[] screenLabelPanel = new JPanel[screen.length];
		JPanel[] screenButtonPanel = new JPanel[screen.length];
		for (int i = 0; i < screen.length; i++) {
			screen[i] = new JPanel(new BorderLayout());
			screenLabelPanel[i] = new JPanel();
			screenLabelPanel[i].setBackground(Color.CYAN);
			screenLabel[i] = new JLabel("Screen " + (i + 1));
			screenLabel[i].setFont(Kiosk.getUIMainFont());
			screenLabel[i].setAlignmentX(screenLabelPanel[i].getAlignmentX());
			screenLabelPanel[i].add(screenLabel[i]);
			screen[i].add(screenLabelPanel[i], BorderLayout.NORTH);
			screenButtonPanel[i] = new JPanel();
			screen[i].add(screenButtonPanel[i], BorderLayout.CENTER);
			listScreeningPanel.add(screen[i]);
		}
		ArrayList<String> screenings = film.getTodayScreenings();
		for (String screening : screenings) {
			Date date = KioskController.convertDate(screening);
			SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
			JButton screeningButton = new JButton(ft.format(date));
			screeningButton.setFont(Kiosk.getUIMainFont());
			Date now = new Date();
			if (now.after(date))
				screeningButton.setEnabled(false);
			else
				screeningButton.addActionListener(e -> Kiosk.listSeat(film, screening));
			screenButtonPanel[Integer.parseInt(screening.split("/")[0]) - 1].add(screeningButton);
		}

		JPanel listScreeningSouthPanel = new JPanel();
		listScreeningSouthPanel.setLayout(new BoxLayout(listScreeningSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(Kiosk.getUIMainFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(Kiosk.getUIMainFont());
		backButton.addActionListener(e -> Kiosk.showListFilm());

		listScreeningSouthPanel.add(exitButton);
		listScreeningSouthPanel.add(Box.createHorizontalStrut(25));
		listScreeningSouthPanel.add(backButton);
		listScreeningSouthPanel.add(Box.createHorizontalGlue());

		ImageIcon image = film.getImage();
		image.setImage(image.getImage().getScaledInstance(330, 470, Image.SCALE_DEFAULT));

		add(new JLabel(image), BorderLayout.WEST);
		add(listScreeningPanel, BorderLayout.CENTER);
		add(listScreeningSouthPanel, BorderLayout.SOUTH);
	}
}

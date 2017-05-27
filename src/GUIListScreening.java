import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
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
	 * @param kiosk The instance of Kiosk.
	 * @param film  The selected film.
	 */
	GUIListScreening(Kiosk kiosk, Film film) {
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
			screenLabel[i].setFont(kiosk.getButtonFont());
			screenLabel[i].setAlignmentX(screenLabelPanel[i].getAlignmentX());
			screenLabelPanel[i].add(screenLabel[i]);
			screen[i].add(screenLabelPanel[i], BorderLayout.NORTH);
			screenButtonPanel[i] = new JPanel();
			screen[i].add(screenButtonPanel[i], BorderLayout.CENTER);
			listScreeningPanel.add(screen[i]);
		}
		ArrayList<String> screenings = film.getScreenings();
		for (String screening : screenings) {
			String[] time = screening.split("/");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			Date date = new Date();
			try {
				date = simpleDateFormat.parse(time[1]);
			} catch (ParseException e) {
				System.out.println("screening information wrong!" + screening);
			}
			Date now = new Date();
			if (!(now.getYear() == date.getYear() && now.getMonth() == date.getMonth() && now.getDay() == date.getDay()))
				continue;

			SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
			JButton screeningButton = new JButton(ft.format(date));
			screeningButton.setFont(kiosk.getButtonFont());
			if (now.after(date))
				screeningButton.setEnabled(false);
			else
				screeningButton.addActionListener(e -> kiosk.listSeat(film, screening));
			switch (time[0]) {
				case "1":
					screenButtonPanel[0].add(screeningButton);
					break;
				case "2":
					screenButtonPanel[1].add(screeningButton);
					break;
				case "3":
					screenButtonPanel[2].add(screeningButton);
					break;
			}
		}

		JPanel listScreeningSouthPanel = new JPanel();
		listScreeningSouthPanel.setLayout(new BoxLayout(listScreeningSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(kiosk.getButtonFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(kiosk.getButtonFont());
		backButton.addActionListener(e -> kiosk.showListFilm());

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

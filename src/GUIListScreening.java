import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Title        GUIListScreening.java
 * Description  This class list all screenings of one movie.
 */
class GUIListScreening extends JPanel {
	private Kiosk kiosk;
	private Film film;

	GUIListScreening(Kiosk kiosk, Film film) {
		super(new BorderLayout());
		this.kiosk = kiosk;
		this.film = film;
		Font buttonFont = kiosk.getButtonFont();

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
			screenLabel[i].setFont(buttonFont);
			screenLabel[i].setAlignmentX(screenLabelPanel[i].getAlignmentX());
			screenLabelPanel[i].add(screenLabel[i]);
			screen[i].add(screenLabelPanel[i], BorderLayout.NORTH);
			screenButtonPanel[i] = new JPanel();
			screen[i].add(screenButtonPanel[i], BorderLayout.CENTER);
			listScreeningPanel.add(screen[i]);
		}
		ArrayList<String> screenings = film.getScreenings();
		int amount = screenings.size();
		JButton[] numButton = new JButton[amount];
		for (int i = 0; i < amount; i++) {
			String[] time = screenings.get(i).split("/");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			Date date = new Date();
			try {
				date = simpleDateFormat.parse(time[1]);
			} catch (ParseException e) {
				System.out.println("screening information wrong!" + screenings.get(i));
			}
			SimpleDateFormat ft = new SimpleDateFormat("HH:mm");
			numButton[i] = new JButton(ft.format(date));
			numButton[i].setFont(buttonFont);
			numButton[i].addMouseListener(new mouseAdapter(i));
			switch (time[0]) {
				case "1":
					screenButtonPanel[0].add(numButton[i]);
					break;
				case "2":
					screenButtonPanel[1].add(numButton[i]);
					break;
				case "3":
					screenButtonPanel[2].add(numButton[i]);
					break;
			}
		}

		JPanel listScreeningSouthPanel = new JPanel();
		listScreeningSouthPanel.setLayout(new BoxLayout(listScreeningSouthPanel, BoxLayout.X_AXIS));

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

		ImageIcon image = film.getImage();
		image.setImage(image.getImage().getScaledInstance(330, 470, Image.SCALE_DEFAULT));
		add(new JLabel(image), BorderLayout.WEST);
		add(listScreeningPanel, BorderLayout.CENTER);
		add(listScreeningSouthPanel, BorderLayout.SOUTH);
	}

	class mouseAdapter extends MouseAdapter {
		int i;

		mouseAdapter(int i) {
			this.i = i;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			kiosk.listSeat(film, i);
		}
	}
}

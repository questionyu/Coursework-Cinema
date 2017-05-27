import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Title        GUIListFilm.java
 * Description  This class shows the films' posters.
 */
class GUIListFilm extends JPanel {
	/**
	 * Constructor function. Create a panel which show all films.
	 */
	GUIListFilm() {
		super(new BorderLayout());

		JPanel filmPosterPanel = new JPanel(new GridLayout(3, 2, 5, 5));

		ArrayList<Film> films = KioskController.getFilms();

		for (int i = 0; i < films.size(); i++) {
			JPanel filmPanel = new JPanel(new BorderLayout());

			String labelString = "<html>" + films.get(i).getName() + "<br>" + films.get(i).getLength() + "min</html>";

			ImageIcon image = films.get(i).getImage();
			image.setImage(image.getImage().getScaledInstance(104, 156, Image.SCALE_DEFAULT));

			JLabel label = new JLabel(labelString, image, SwingConstants.LEFT);
			label.setFont(Kiosk.getUIMainFont());

			JButton numButton = new JButton("BUY");
			numButton.setFont(Kiosk.getUIMainFont());
			numButton.addMouseListener(new mouseAdapter(i));

			filmPanel.add(label, BorderLayout.WEST);
			filmPanel.add(numButton, BorderLayout.EAST);

			filmPosterPanel.add(filmPanel);
		}

		JPanel listFilmSouthPanel = new JPanel();
		listFilmSouthPanel.setLayout(new BoxLayout(listFilmSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(Kiosk.getUIMainFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(Kiosk.getUIMainFont());
		backButton.addActionListener(e -> Kiosk.showWelcome());

		listFilmSouthPanel.add(exitButton);
		listFilmSouthPanel.add(Box.createHorizontalStrut(25));
		listFilmSouthPanel.add(backButton);
		listFilmSouthPanel.add(Box.createHorizontalGlue());

		add(filmPosterPanel, BorderLayout.CENTER);
		add(listFilmSouthPanel, BorderLayout.SOUTH);
	}

	/**
	 * This class extends MouseAdapter. This class's instance record the number of film of each button.
	 */
	class mouseAdapter extends MouseAdapter {
		/**
		 * The film no.
		 */
		private int i;

		/**
		 * Constructor function. Create a mouse adapter instance.
		 *
		 * @param i The film no.
		 */
		mouseAdapter(int i) {
			this.i = i;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			Kiosk.listScreening(i);
		}
	}
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Title        .java
 * Description  This class shows the films' posters.
 */
class GUIListFilm extends JPanel {
	private JButton[] numButton;

	/**
	 * Constructor.
	 */
	GUIListFilm() {
		super(new BorderLayout());

		JPanel filmPosterPanel = new JPanel(new GridLayout(3, 2, 5, 5));
		filmPosterPanel.setOpaque(false);

		ArrayList<Film> films = KioskController.getFilms();
		int showQuantity = films.size();

		numButton = new JButton[showQuantity];
		JPanel[] filmPanel = new JPanel[showQuantity];
		JLabel[] label = new JLabel[showQuantity];

		String[] labelString = new String[showQuantity];
		ImageIcon[] image = new ImageIcon[showQuantity];
		for (int i = 0; i < showQuantity; i++) {
			labelString[i] = "<html>" + films.get(i).getName() + "<br>" + films.get(i).getLength() + "min</html>";
			image[i] = films.get(i).getImage();
		}

		add(filmPosterPanel, BorderLayout.CENTER);
		for (int i = 0; i < showQuantity; i++) {
			filmPanel[i] = new JPanel(new BorderLayout());
			filmPanel[i].setOpaque(false);
			image[i].setImage(image[i].getImage().getScaledInstance(104, 156, Image.SCALE_DEFAULT));
			label[i] = new JLabel(labelString[i], image[i], SwingConstants.LEFT);
			label[i].setFont(Kiosk.getUIMainFont());
			numButton[i] = new JButton("BUY");
			numButton[i].setFont(Kiosk.getUIMainFont());
			numButton[i].addMouseListener(new mouseAdapter(i));
			filmPanel[i].add(label[i], BorderLayout.WEST);
			filmPanel[i].add(numButton[i], BorderLayout.EAST);
			filmPosterPanel.add(filmPanel[i]);
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

		add(listFilmSouthPanel, BorderLayout.SOUTH);
	}

	class mouseAdapter extends MouseAdapter {
		int i;

		mouseAdapter(int i) {
			this.i = i;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Kiosk.listScreening(i);
		}
	}
}

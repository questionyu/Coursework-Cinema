import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Title        .java
 * Description  This class shows the films' posters.
 */
class GUIListFilm extends JPanel implements ActionListener {
	private Kiosk kiosk;
	private JButton[] numButton;

	/**
	 * Constructor.
	 */
	GUIListFilm(Kiosk kiosk) {
		super(new BorderLayout());
		this.kiosk = kiosk;

		Font buttonFont = kiosk.getButtonFont();

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(buttonFont);
		exitButton.addActionListener(e -> System.exit(0));

		JPanel filmPosterPanel = new JPanel(new GridLayout(3, 2));
		filmPosterPanel.setOpaque(false);

		ArrayList<Film> films = kiosk.getFilms();
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
			label[i].setFont(buttonFont);
			numButton[i] = new JButton("BUY");
			numButton[i].setFont(buttonFont);
			numButton[i].addMouseListener(new buyActionListener(i));
			filmPanel[i].add(label[i], BorderLayout.WEST);
			filmPanel[i].add(numButton[i], BorderLayout.EAST);
			filmPosterPanel.add(filmPanel[i]);
		}

		JPanel listFilmSouthPanel = new JPanel();
		listFilmSouthPanel.setLayout(new BoxLayout(listFilmSouthPanel, BoxLayout.X_AXIS));
		listFilmSouthPanel.setOpaque(false);

		listFilmSouthPanel.add(exitButton);
		listFilmSouthPanel.add(Box.createHorizontalGlue());

		add(listFilmSouthPanel, BorderLayout.SOUTH);
	}

	/**
	 * @param e Action event.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object eventObject = e.getSource();
		for (JButton button : numButton) {
			if (eventObject.equals(button))
				System.out.println(button.getText());
		}
	}

	class buyActionListener extends MouseAdapter {
		int i;

		buyActionListener(int i) {
			this.i = i;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			kiosk.listScreening(i);
		}
	}
}

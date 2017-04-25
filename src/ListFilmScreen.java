import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Title        ListFilmScreen.java
 * Description  This class shows the films' posters.
 */
class ListFilmScreen extends JPanel implements ActionListener {
	private JButton[] numButton;

	/**
	 *
	 */
	ListFilmScreen(KioskInterface kioskInterface) {
		super(new BorderLayout());
		setOpaque(false);

		Font buttonFont = new Font("Segoe UI", Font.PLAIN, 25);

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(buttonFont);
		exitButton.addActionListener(e -> System.exit(0));

		JPanel filmPosterPanel = new JPanel(new GridLayout(3, 2));
		filmPosterPanel.setOpaque(false);

		ArrayList<Film> films = kioskInterface.getFilm();
		int showQuantity = films.size();

		numButton = new JButton[showQuantity];
		JPanel[] filmPanel = new JPanel[showQuantity];
		ImageIcon[] image = new ImageIcon[showQuantity];
		JLabel[] label = new JLabel[showQuantity];

		String[] labelString = new String[showQuantity];
		for (int i = 0; i < showQuantity; i++) {
			labelString[i] = "<html>" + films.get(i).getName() + "<br>" + films.get(i).getLength() + "min</html>";
		}

		//TODO replace 'new ImageIcon' with 'getFilm'
		//TODO add image url to xml file
//		kioskInterface.getFilm();
		image[0] = new ImageIcon("images/kong.jpg");
		image[1] = new ImageIcon("images/logan.jpg");
		image[2] = new ImageIcon("images/beauty-and-the-beasts.jpg");
		image[3] = new ImageIcon("images/moonlight.jpg");
		image[4] = new ImageIcon("images/la-la-land.jpg");

		add(filmPosterPanel, BorderLayout.CENTER);
		for (int i = 0; i < showQuantity; i++) {
			filmPanel[i] = new JPanel(new BorderLayout());
			filmPanel[i].setOpaque(false);
			image[i].setImage(image[i].getImage().getScaledInstance(104, 156, Image.SCALE_DEFAULT));
			label[i] = new JLabel(labelString[i], image[i], SwingConstants.LEFT);
			label[i].setFont(buttonFont);
			numButton[i] = new JButton("BUY");
			numButton[i].setFont(buttonFont);
			numButton[i].addActionListener(this);
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
}

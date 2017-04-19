import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title        ListFilmScreen.java
 * Description  This class shows the films' posters.
 */
class ListFilmScreen extends JPanel implements ActionListener {
	private static int showQuantity = 5;
	private JButton[] numButton = new JButton[showQuantity];
	private float testfloat;

	/**
	 *
	 */
	ListFilmScreen(KioskInterface kioskInterface) {
		super(new BorderLayout());
		setOpaque(false);

		Font buttonFont = new Font("Segoe UI", Font.PLAIN, 30);

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(buttonFont);
		exitButton.addActionListener(e -> System.exit(0));

		JPanel filmPosterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 60));
		filmPosterPanel.setOpaque(false);
		JPanel[] filmPanel = new JPanel[showQuantity];
		ImageIcon[] image = new ImageIcon[showQuantity];
		JLabel[] label = new JLabel[showQuantity];

		image[0] = new ImageIcon("images/kong.jpg");
		image[1] = new ImageIcon("images/logan.jpg");
		image[2] = new ImageIcon("images/beauty-and-the-beasts.jpg");
		image[3] = new ImageIcon("images/moonlight.jpg");
		image[4] = new ImageIcon("images/la-la-land.jpg");

		add(filmPosterPanel, BorderLayout.CENTER);
		for (int i = 0; i < showQuantity; i++) {
			filmPanel[i] = new JPanel(new BorderLayout());
			image[i].setImage(image[i].getImage().getScaledInstance(180, 270, Image.SCALE_DEFAULT));
			label[i] = new JLabel(image[i]);
			numButton[i] = new JButton("Buy");
			numButton[i].setFont(buttonFont);
			numButton[i].addActionListener(this);
			filmPanel[i].add(label[i], BorderLayout.CENTER);
			filmPanel[i].add(numButton[i], BorderLayout.SOUTH);
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

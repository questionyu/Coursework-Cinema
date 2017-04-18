import javax.swing.*;
import java.awt.*;

/**
 * Title        WelcomeScreen.java
 * Description  This class shows a welcome on the screen.
 */
class WelcomeScreen extends JPanel {
	WelcomeScreen(KioskInterface kioskInterface) {
		super(new BorderLayout());
		setOpaque(false);

		JLabel welcomeLabel = new JLabel("Welcome!", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 50));

		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		enterButton.addActionListener(e -> kioskInterface.listFilm());

		JPanel welcomeSouthPanel = new JPanel();
		welcomeSouthPanel.setOpaque(false);
		welcomeSouthPanel.add(enterButton);

		add(welcomeLabel, BorderLayout.CENTER);
		add(welcomeSouthPanel, BorderLayout.SOUTH);
	}
}

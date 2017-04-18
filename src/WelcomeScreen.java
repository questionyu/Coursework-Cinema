import javax.swing.*;
import java.awt.*;

/**
 * Title        WelcomeScreen.java
 * Description
 */
class WelcomeScreen extends JPanel {
	WelcomeScreen(KioskInterface kioskInterface) {
		super(new BorderLayout());
		setOpaque(false);

		JLabel welcomeLabel = new JLabel("Welcome!", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 50));

		JPanel welcomeSouthPanel = new JPanel();
		welcomeSouthPanel.setOpaque(false); // Make this panel transparent.

		JButton enter = new JButton("Enter");
		enter.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		enter.addActionListener(e -> kioskInterface.listFilm());

		welcomeSouthPanel.add(enter);

		add(welcomeLabel, BorderLayout.CENTER);
		add(welcomeSouthPanel, BorderLayout.SOUTH);
	}
}

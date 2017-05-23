import javax.swing.*;
import java.awt.*;

/**
 * Title        WelcomeScreen.java
 * Description  This class shows a welcome on the screen.
 */
class GUIWelcome extends JPanel {
	GUIWelcome(Kiosk kiosk) {
		super(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Welcome!", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 50));

		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		enterButton.addActionListener(e -> kiosk.listFilm());

		JPanel welcomeSouthPanel = new JPanel();
		welcomeSouthPanel.setOpaque(false);
		welcomeSouthPanel.add(enterButton);

		add(welcomeLabel, BorderLayout.CENTER);
		add(welcomeSouthPanel, BorderLayout.SOUTH);
	}
}

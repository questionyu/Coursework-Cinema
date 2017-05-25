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

		JButton manageButton = new JButton("Manage");
		manageButton.setFont(kiosk.getButtonFont());
		manageButton.addActionListener(e -> kiosk.manage());

		JButton enterButton = new JButton("Buy");
		enterButton.setFont(kiosk.getButtonFont());
		enterButton.addActionListener(e -> kiosk.listFilm());

		JPanel welcomeSouthPanel = new JPanel();
		welcomeSouthPanel.add(manageButton);
		welcomeSouthPanel.add(enterButton);

		add(welcomeLabel, BorderLayout.CENTER);
		add(welcomeSouthPanel, BorderLayout.SOUTH);
	}
}

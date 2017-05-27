import javax.swing.*;
import java.awt.*;

/**
 * Title        WelcomeScreen.java
 * Description  This class shows a welcome on the screen.
 */
class GUIWelcome extends JPanel {
	GUIWelcome() {
		super(new BorderLayout());

		JLabel welcomeLabel = new JLabel("Welcome!", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 50));

		JButton manageButton = new JButton("Report");
		manageButton.setFont(Kiosk.getUIMainFont());
		manageButton.addActionListener(e -> Kiosk.report());

		JButton enterButton = new JButton("Buy");
		enterButton.setFont(Kiosk.getUIMainFont());
		enterButton.addActionListener(e -> Kiosk.listFilm());

		JPanel welcomeSouthPanel = new JPanel();
		welcomeSouthPanel.add(manageButton);
		welcomeSouthPanel.add(enterButton);

		add(welcomeLabel, BorderLayout.CENTER);
		add(welcomeSouthPanel, BorderLayout.SOUTH);
	}
}

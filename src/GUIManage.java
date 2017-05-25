import javax.swing.*;
import java.awt.*;

/**
 * Title        GUIManage.java
 * Description  This class defines the UI of management.
 */
class GUIManage extends JPanel {
	GUIManage(Kiosk kiosk) {
		super(new BorderLayout());

		JPanel manageSouthPanel = new JPanel();
		manageSouthPanel.setLayout(new BoxLayout(manageSouthPanel, BoxLayout.X_AXIS));

		JButton exitButton = new JButton("Exit");
		exitButton.setFont(kiosk.getButtonFont());
		exitButton.addActionListener(e -> System.exit(0));

		JButton backButton = new JButton("Back");
		backButton.setFont(kiosk.getButtonFont());
		backButton.addActionListener(e -> kiosk.showWelcome());

		manageSouthPanel.add(exitButton);
		manageSouthPanel.add(Box.createHorizontalStrut(25));
		manageSouthPanel.add(backButton);
		manageSouthPanel.add(Box.createHorizontalGlue());

		add(manageSouthPanel, BorderLayout.SOUTH);
	}
}

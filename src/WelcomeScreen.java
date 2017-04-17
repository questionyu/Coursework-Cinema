/*
 * Created by Question on 17/4/2017
 * Copyright (c) 2017. All Rights Reserved.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Title
 * Description
 */
class WelcomeScreen extends JFrame {
	WelcomeScreen(KioskInterface kioskInterface) {
		super("Self-service Ticketing Kiosk");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(960, 540);
		setResizable(false);
		setLocationRelativeTo(null);

		setBackground(kioskInterface.getBackgroundColor());

		JLabel welcomeLabel = new JLabel("Welcome!", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 50));

		JPanel welcomeSouthPanel = new JPanel();
		welcomeSouthPanel.setOpaque(false); // Make this panel transparent.

		JButton enter = new JButton("Enter");
		enter.setFont(new Font("Segoe UI", Font.PLAIN, 30));
		enter.addActionListener((ActionEvent e) -> {
			dispose();
			kioskInterface.listFilm();
		});

		welcomeSouthPanel.add(enter);

		add(welcomeLabel, BorderLayout.CENTER);
		add(welcomeSouthPanel, BorderLayout.SOUTH);

		setVisible(true);
	}


}

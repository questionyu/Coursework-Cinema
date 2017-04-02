/*
 * Created by Question on 2/4/2017
 * Copyright (c) 2017. All Rights Reserved.
 */

import javax.swing.*;
import java.awt.*;

/**
 * Title        Kiosk.java
 * Description
 */
public class Kiosk extends JPanel {

	Kiosk() {
		super(new BorderLayout());
		setOpaque(false);

		JLabel helloLabel = new JLabel("Welcome!", JLabel.CENTER);
		helloLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		helloLabel.setForeground(Color.BLACK);

		JButton enter = new JButton("Enter");
		enter.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		enter.setForeground(Color.BLACK);
		enter.addActionListener(e -> {
		});
		JPanel helloSouthPanel = new JPanel();
		helloSouthPanel.setOpaque(false);
		helloSouthPanel.add(enter);

		add(helloLabel, BorderLayout.CENTER);
		add(helloSouthPanel, BorderLayout.SOUTH);
	}


}

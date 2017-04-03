/*
 * Created by Question on 1/4/2017
 * Copyright (c) 2017. All Rights Reserved.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title        CustomerGUI.java
 * Description
 */

public class CustomerGUI implements ActionListener {
	private static void createAndShowGUI() {
		// Set up the frame and display it.
		JFrame frame = new JFrame("Self-service Ticketing Kiosk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 540);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		CustomerGUI demo = new CustomerGUI();
		frame.setContentPane(demo.createCinemaKiosk());
		frame.setVisible(true);
	}

	/**
	 * Program starts here.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(CustomerGUI::createAndShowGUI);
	}

	/**
	 * This method create a JPanel and set some settings.
	 */
	private JPanel createCinemaKiosk() {
		JPanel totalGUI = new JPanel();
		totalGUI.setLayout(new BorderLayout());
		totalGUI.setBackground(new Color(90, 154, 212));
		JLabel helloLabel = new JLabel("Welcome!", JLabel.CENTER);
		helloLabel.setFont(new Font("Helvetica", Font.BOLD, 50));
		helloLabel.setForeground(Color.BLACK);

		JButton enter = new JButton("Enter");
		enter.setFont(new Font("Helvetica", Font.BOLD, 30));
		enter.setForeground(Color.BLACK);
		enter.addActionListener(e -> {
		});
		JPanel helloSouthPanel = new JPanel();
		helloSouthPanel.setOpaque(false);
		helloSouthPanel.add(enter);

		totalGUI.add(helloLabel, BorderLayout.CENTER);
		totalGUI.add(helloSouthPanel, BorderLayout.SOUTH);

		return totalGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

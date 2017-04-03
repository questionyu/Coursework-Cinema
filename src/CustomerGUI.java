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
 * Description  This class create GUI of Kiosk.
 */

public class CustomerGUI extends JFrame implements ActionListener {
	private JButton exitButton;
	private Color backgroundColor;

	/**
	 *
	 */
	private CustomerGUI() {
		// Set up the frame and display it.
		super("Self-service Ticketing Kiosk");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(960, 540);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		exitButton = new JButton("Exit");
		exitButton.setFont(new Font("Segoe UI", Font.BOLD, 30));
		exitButton.addActionListener(e -> System.exit(0));

		backgroundColor = new Color(90, 154, 212);
	}

	/**
	 * Program starts here.
	 *
	 * @param args The input parameters.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> (new CustomerGUI()).welcomeScreen());
	}

	/**
	 * This method create a JPanel and set some settings.
	 */
	private void welcomeScreen() {
		JPanel welcomePanel = new JPanel(new BorderLayout());
		welcomePanel.setBackground(backgroundColor);

		JLabel welcomeLabel = new JLabel("Welcome!", JLabel.CENTER);
		welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 50));

		JPanel welcomeSouthPanel = new JPanel();
		welcomeSouthPanel.setOpaque(false);

		JButton enter = new JButton("Enter");
		enter.setFont(new Font("Segoe UI", Font.BOLD, 30));
		enter.addActionListener(e -> listFilm());

		welcomeSouthPanel.add(enter);

		welcomePanel.add(welcomeLabel, BorderLayout.CENTER);
		welcomePanel.add(welcomeSouthPanel, BorderLayout.SOUTH);

		setContentPane(welcomePanel);
		validate();
		//setVisible(true);
	}

	private void listFilm() {
		JPanel listFilmPanel = new JPanel(new BorderLayout());
		listFilmPanel.setBackground(backgroundColor);

		JPanel listFilmSouthPanel = new JPanel();
		listFilmSouthPanel.setOpaque(false);
		listFilmSouthPanel.add(exitButton);

		listFilmPanel.add(listFilmSouthPanel, BorderLayout.SOUTH);

		setContentPane(listFilmPanel);
		validate();
	}

	/**
	 * @param e Action event.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

	}
}

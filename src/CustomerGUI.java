/*
 * Created by Question on 1/4/2017
 * Copyright (c) 2017. All Rights Reserved.
 */

import javax.swing.*;

/**
 * Title        CustomerGUI.java
 * Description
 */

public class CustomerGUI {
	/**
	 * This method create a JFrame and set some settings.
	 */
	private CustomerGUI() {

		// Set up the frame and display it.
		JFrame frame = new JFrame("Self-service Ticketing Kiosk");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(960, 540);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.setContentPane(new Kiosk());
		frame.setVisible(true);
	}

	/**
	 * Program starts here.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(CustomerGUI::new);
	}
}

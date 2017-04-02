import javax.swing.*;
import java.awt.*;

/**
 * Title
 * Description
 * Copyright    (c) 2017 Copyright Holder All Rights Reserved.
 *
 * @author Question
 * @date 01/04/2017
 */

public class CustomerGUI {
	private JFrame frame = new JFrame("Self-service Ticketing");

	private int width = (int) ((float) (Toolkit.getDefaultToolkit().getScreenSize().width) / 2);
	private int height = (int) ((float) (Toolkit.getDefaultToolkit().getScreenSize().width) / 2);

	/**
	 * Program starts here.
	 */
	public static void main(String[] args) {
		new CustomerGUI();
	}

	/**
	 * This method create a JFrame and set some settings.
	 */
	private CustomerGUI() {

		// Set up the frame and display it.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(90, 154, 212));
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Title        Kiosk.java
 * Description  This class controls the UI.
 */
class Kiosk extends JFrame {
	/**
	 * The card layout instance which manages all UI of kiosk.
	 */
	private static CardLayout kioskCardLayout;

	/**
	 * The panel which use the card layout.
	 */
	private static JPanel kioskPanel;

	/**
	 * The main font of UI.
	 */
	private static Font UIMainFont;

	/**
	 * Constructor function of Kiosk.
	 * This function creates a JFrame to contain a JPanel which uses CardLayout to display all things.
	 */
	private Kiosk() {
		super("Self-service Ticketing Kiosk");

		//For better looks.
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
			e.printStackTrace();
		}

		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(960, 540);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		kioskCardLayout = new CardLayout();
		kioskPanel = new JPanel(kioskCardLayout);

		this.setContentPane(kioskPanel);

		UIMainFont = new Font("Segoe UI", Font.PLAIN, 25);

		this.setVisible(true);
	}

	/**
	 * Program starts here.
	 *
	 * @param args The input parameters.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			if (!KioskController.loadFilms()) {
				System.out.println("Load films failed!");
				System.exit(1);
			}
			new Kiosk();
			Kiosk.welcome();
		});
	}

	/**
	 * This function creates a welcome panel and adds it to CardLayout.
	 */
	private static void welcome() {
		kioskPanel.add(new GUIWelcome(), "GUIWelcome");
		showWelcome();
	}

	/**
	 * This function shows the welcome panel.
	 */
	static void showWelcome() {
		kioskCardLayout.show(kioskPanel, "GUIWelcome");
	}

	/**
	 * This function creates a report panel and adds it to CardLayout.
	 */
	static void report() {
		kioskPanel.add(new GUIReport(), "GUIReport");
		showReport();
	}

	/**
	 * This function shows the report panel.
	 */
	static void showReport() {
		kioskCardLayout.show(kioskPanel, "GUIReport");
	}

	/**
	 * This function creates a listFilm panel and adds it to CardLayout.
	 */
	static void listFilm() {
		kioskPanel.add(new GUIListFilm(), "GUIListFilm");
		showListFilm();
	}

	/**
	 * This function shows the listFilm panel.
	 */
	static void showListFilm() {
		kioskCardLayout.show(kioskPanel, "GUIListFilm");
	}

	/**
	 * This function creates a listScreening panel and adds it to CardLayout.
	 *
	 * @param i The screening no of the film.
	 */
	static void listScreening(int i) {
		kioskPanel.add(new GUIListScreening(KioskController.getFilm(i)), "GUIListScreenings");
		showListScreening();
	}

	/**
	 * This function shows the listScreening panel.
	 */
	static void showListScreening() {
		kioskCardLayout.show(kioskPanel, "GUIListScreenings");
	}

	/**
	 * This function creates a listSeat panel and adds it to CardLayout.
	 *
	 * @param film      The selected film.
	 * @param screening The selected screening of the film.
	 */
	static void listSeat(Film film, String screening) {
		kioskPanel.add(new GUIListSeat(film, screening), "GUIListSeat");
		KioskController.selectedSeats = new ArrayList<>(); //Clear all selected seats.
		showListSeat();
	}

	/**
	 * This function shows the listSeat panel.
	 */
	static void showListSeat() {
		kioskCardLayout.show(kioskPanel, "GUIListSeat");
	}

	/**
	 * This function creates a confirmTicket panel and adds it to CardLayout.
	 */
	static void confirmTicket() {
		KioskController.orderTickets = new ArrayList<>();
		KioskController.addOrderTicket();
		kioskPanel.add(new GUIConfirmTicket(), "GUIConfirmTicket");
		showConfirmTicket();
	}

	/**
	 * This function shows the confirmTicket panel.
	 */
	static void showConfirmTicket() {
		kioskCardLayout.show(kioskPanel, "GUIConfirmTicket");
	}

	/**
	 * Getter function of main font of UI.
	 *
	 * @return The main font.
	 */
	static Font getUIMainFont() {
		return UIMainFont;
	}
}

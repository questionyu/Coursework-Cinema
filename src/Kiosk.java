import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Title        Kiosk.java
 * Description  This class controls the UI.
 */
class Kiosk extends JFrame {
	private static CardLayout kioskCardLayout;
	private static JPanel kioskPanel;
	private static Font UIMainFont;

	/**
	 * Constructor function of Kiosk.
	 * This function creates a JFrame to contain a JPanel which uses CardLayout to display all things.
	 */
	private Kiosk() {
		super("Self-service Ticketing Kiosk");

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
	 * This function creates a welcome panel and adds it to CardLayout and to shows it.
	 */
	private static void welcome() {
		kioskPanel.add(new GUIWelcome(), "GUIWelcome");
		showWelcome();
	}

	static void showWelcome() {
		kioskCardLayout.show(kioskPanel, "GUIWelcome");
	}

	static void report() {
		kioskPanel.add(new GUIReport(), "GUIReport");
		showReport();
	}

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
	 * This function shows listFilm panel to let users see all films.
	 */
	static void showListFilm() {
		kioskCardLayout.show(kioskPanel, "GUIListFilm");
	}

	static void listScreening(int i) {
		kioskPanel.add(new GUIListScreening(KioskController.getFilm(i)), "GUIListScreenings");
		showListScreening();
	}

	static void showListScreening() {
		kioskCardLayout.show(kioskPanel, "GUIListScreenings");
	}

	static void listSeat(Film film, String screening) {
		kioskPanel.add(new GUIListSeat(film, screening), "GUIListSeat");
		KioskController.selectedSeats = new ArrayList<>(); //Clear all selected seats.
		showListSeat();
	}

	static void showListSeat() {
		kioskCardLayout.show(kioskPanel, "GUIListSeat");
	}

	static void confirmTicket() {
		KioskController.orderTickets = new ArrayList<>();
		KioskController.addOrderTicket();
		kioskPanel.add(new GUIConfirmTicket(), "GUIConfirmTicket");
		showConfirmTicket();
	}

	static void showConfirmTicket() {
		kioskCardLayout.show(kioskPanel, "GUIConfirmTicket");
	}

	static Font getUIMainFont() {
		return UIMainFont;
	}
}

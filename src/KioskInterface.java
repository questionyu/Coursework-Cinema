import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Title        KioskInterface.java
 * Description  This class contains the kiosk interface's definition.
 */
class KioskInterface {
	private File filmXMLFile = new File("films.xml");

	private JFrame kioskFrame;
	private CardLayout kioskCardLayout;
	private JPanel kioskPanel;
	private Color backgroundColor = new Color(90, 154, 212);
	private int testint;

	private KioskInterface() {
		kioskFrame = new JFrame("Self-service Ticketing Kiosk");
		kioskFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		kioskFrame.setSize(960, 540);
		kioskFrame.setResizable(false);
		kioskFrame.setLocationRelativeTo(null);

		kioskCardLayout = new CardLayout();
		kioskPanel = new JPanel(kioskCardLayout);
		kioskPanel.setBackground(backgroundColor);
		kioskFrame.add(kioskPanel);

		kioskFrame.setVisible(true);
	}

	/**
	 * Program starts here.
	 *
	 * @param args The input parameters.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> (new KioskInterface()).welcome());
	}

	private void welcome() {
		kioskPanel.add(new WelcomeScreen(this), "WelcomeScreen");
		kioskCardLayout.show(kioskPanel, "WelcomeScreen");
	}

	void listFilm() {
		kioskPanel.add(new ListFilmScreen(this), "ListFilmScreen");
		kioskCardLayout.show(kioskPanel, "ListFilmScreen");
	}

	ArrayList<Film> getFilm() {
		ArrayList<Film> films = ReadXMLFile(filmXMLFile);
		return films;
	}

	private ArrayList<Film> ReadXMLFile(File file) {
		ArrayList<Film> list = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList filmList = doc.getElementsByTagName("film");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void CreateXMLFile(ArrayList<Object> list) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

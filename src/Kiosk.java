import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Title        Kiosk.java
 * Description  This class contains the kiosk interface's definition.
 */
class Kiosk {
	private Cinema cinema;
	private CardLayout kioskCardLayout;
	private JPanel kioskPanel;

	/**
	 * Constructor function of Kiosk.
	 * This function creates a JFrame to contain a JPanel which uses CardLayout to display all things.
	 */
	private Kiosk() {
		cinema = new Cinema(getFilmFromFile());
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
			e.printStackTrace();
		}
		JFrame kioskFrame = new JFrame("Self-service Ticketing Kiosk");
		kioskFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		kioskFrame.setSize(960, 540);
		kioskFrame.setResizable(false);
		kioskFrame.setLocationRelativeTo(null);

		kioskCardLayout = new CardLayout();
		kioskPanel = new JPanel(kioskCardLayout);
		kioskFrame.add(kioskPanel);

		kioskFrame.setVisible(true);
	}

	/**
	 * Program starts here.
	 *
	 * @param args The input parameters.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> (new Kiosk()).welcome());
	}

	/**
	 * This function creates a welcome panel and adds it to CardLayout and to shows it.
	 */
	private void welcome() {
		kioskPanel.add(new GUIWelcome(this), "GUIWelcome");
		kioskCardLayout.show(kioskPanel, "GUIWelcome");
	}

	/**
	 * This function creates a listFilm panel and adds it to CardLayout.
	 */
	void listFilm() {
		kioskPanel.add(new GUIListFilm(this), "GUIListFilm");
		showListFilm();
	}

	/**
	 * This function shows listFilm panel to let users see all films.
	 */
	void showListFilm() {
		kioskCardLayout.show(kioskPanel, "GUIListFilm");
	}

	void listScreening(int i) {
		kioskPanel.add(new GUIListScreening(this, cinema.getFilm(i)), "GUIListScreenings");
		showListScreening();
	}

	void showListScreening() {
		kioskCardLayout.show(kioskPanel, "GUIListScreenings");
	}

	void listSeat() {
		kioskPanel.add(new GUIListSeat(), "GUIListSeat");
		showListSeat();
	}

	void showListSeat() {
		kioskCardLayout.show(kioskPanel, "GUIListSeat");
	}

	/**
	 * This function read films form xml and save to an ArrayList, and return it.
	 *
	 * @return the ArrayList which contains all films.
	 */
	private ArrayList<Film> getFilmFromFile() {
		ArrayList<Film> films = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("films.xml"));
			NodeList filmList = doc.getElementsByTagName("film");
			for (int i = 0; i < filmList.getLength(); i++) {
				Node filmNode = filmList.item(i);
				if (filmNode.getNodeType() == Node.ELEMENT_NODE) {
					Element filmElement = (Element) filmNode;
					String name = filmElement.getElementsByTagName("name").item(0).getTextContent();
					String imageUrl = filmElement.getElementsByTagName("imageUrl").item(0).getTextContent();
					int length = Integer.parseInt(filmElement.getElementsByTagName("length").item(0).getTextContent());
					double price = Double.parseDouble(filmElement.getElementsByTagName("price").item(0).getTextContent());
					NodeList screeningsList = filmElement.getElementsByTagName("screenings");
					ArrayList<String> screenings = new ArrayList<>();
					for (int j = 0; j < screeningsList.getLength(); j++) {
						screenings.add(screeningsList.item(j).getTextContent());
					}
					films.add(new Film(name, imageUrl, length, price, screenings));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return films;
	}

	/**
	 * This function save ArrayList of films to xml file.
	 *
	 * @param films The ArrayList which contains all films which you want to save to file.
	 */
	private void writeFilmToFile(ArrayList<Film> films) {
		Document doc;
		Element cinema;
		Element film;
		Element name;
		Element imageUrl;
		Element length;
		Element price;
		Element screenings;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			doc = dbBuilder.newDocument();

			cinema = doc.createElement("cinema");
			for (Film film1 : films) {
				film = doc.createElement("film");

				name = doc.createElement("name");
				name.appendChild(doc.createTextNode(film1.getName()));
				film.appendChild(name);

				imageUrl = doc.createElement("imageUrl");
				imageUrl.appendChild(doc.createTextNode(film1.getImageUrl()));
				film.appendChild(imageUrl);

				length = doc.createElement("length");
				length.appendChild(doc.createTextNode("" + film1.getLength()));
				film.appendChild(length);

				price = doc.createElement("price");
				price.appendChild(doc.createTextNode("" + film1.getPrice()));
				film.appendChild(price);

				screenings = doc.createElement("screenings");
				screenings.appendChild(doc.createTextNode("" + film1.getScreenings()));
				film.appendChild(screenings);

				cinema.appendChild(film);
			}
			doc.appendChild(cinema);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("films.xml"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");//增加换行缩进，但此时缩进默认为0
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");//设置缩进为4
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Film> getFilms() {
		return cinema.getFilms();
	}
}

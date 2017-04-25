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
 * Title        KioskInterface.java
 * Description  This class contains the kiosk interface's definition.
 */
class KioskInterface {
	private CardLayout kioskCardLayout;
	private JPanel kioskPanel;

	private KioskInterface() {
		JFrame kioskFrame = new JFrame("Self-service Ticketing Kiosk");
		kioskFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		kioskFrame.setSize(960, 540);
		kioskFrame.setResizable(false);
		kioskFrame.setLocationRelativeTo(null);

		kioskCardLayout = new CardLayout();
		kioskPanel = new JPanel(kioskCardLayout);
		kioskPanel.setBackground(new Color(90, 154, 212));
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
		showListFilm();
	}

	void showListFilm() {
		kioskCardLayout.show(kioskPanel, "ListFilmScreen");
	}

	//TODO maybe getFilm and ReadXMLFile can merge
	ArrayList<Film> getFilm() {
		return ReadXMLFile(new File("films.xml"));
	}

	//TODO read film information from xml file
	private ArrayList<Film> ReadXMLFile(File file) {
		ArrayList<Film> films = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList filmList = doc.getElementsByTagName("film");
			for (int i = 0; i < filmList.getLength(); i++) {
				Node filmNode = filmList.item(i);
				if (filmNode.getNodeType() == Node.ELEMENT_NODE) {
					Element filmElement = (Element) filmNode;
					String name = filmElement.getElementsByTagName("name").item(0).getTextContent();
					int length = Integer.valueOf(filmElement.getElementsByTagName("length").item(0).getTextContent());
					double price = Double.valueOf(filmElement.getElementsByTagName("price").item(0).getTextContent());
					NodeList screeningsList = filmElement.getElementsByTagName("screenings");
					ArrayList<String> screenings = new ArrayList<>();
					for (int j = 0; j < screeningsList.getLength(); j++) {
						screenings.add(screeningsList.item(j).getTextContent());
					}
					films.add(new Film(name, length, price, screenings));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return films;
	}

	//TODO edit xml file for manager or adding movie
	private void CreateXMLFile(ArrayList<Film> films) {
		Document doc;
		Element cinema;
		Element film;
		Element name;
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
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");//设置缩进为2
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

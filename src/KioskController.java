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
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Title        KioskController.java
 * Description  This class controls the kiosk.
 */
class KioskController {
	/**
	 * All the kiosk's films.
	 */
	static ArrayList<Film> films;

	/**
	 * All sold tickets.
	 */
	static ArrayList<Ticket> tickets = new ArrayList<>();

	/**
	 * All ordered tickets. But not paid.
	 */
	static ArrayList<Ticket> orderTickets = new ArrayList<>();

	/**
	 * All selected seats. But not paid.
	 */
	static ArrayList<Seat> selectedSeats = new ArrayList<>();

	/**
	 * A private blank constructor. Prevent other class creating a instance of KioskController.
	 */
	private KioskController() {
	}

	/**
	 * This function loads films.
	 *
	 * @return True for loading successfully, false for failed.
	 */
	static boolean loadFilms() {
		films = getFilmFromFile();
		return films != null;
	}

	/**
	 * This function can save films to file.
	 *
	 * @return True for saving successfully, false for failed.
	 */
	static boolean saveFilms() {
		return writeFilmToFile(films);
	}

	/**
	 * This function checks if new ticket's number is duplicated.
	 *
	 * @param newTicket The ticket which would be checked.
	 * @return True for duplicated, false for not.
	 */
	static boolean checkDuplicated(Ticket newTicket) {
		for (Ticket ticket : tickets)
			if (ticket.getNum() == newTicket.getNum())
				return true;
		for (Ticket ticket : orderTickets)
			if (ticket.getNum() == newTicket.getNum())
				return true;
		return false;
	}

	/**
	 * This function converts selected seats to ordered tickets.
	 */
	static void addOrderTicket() {
		for (Seat seat : selectedSeats) {
			Ticket newOrderTicket;
			if (seat.getTicketType() == Ticket.STUDENT)
				newOrderTicket = new Ticket(seat.getFilm(), seat.getScreening(), seat.getSeat(), seat.getTicketType(), seat.getStudentID());
			else
				newOrderTicket = new Ticket(seat.getFilm(), seat.getScreening(), seat.getSeat(), seat.getTicketType());
			while (checkDuplicated(newOrderTicket)) {
				newOrderTicket.generateRandomNum();
			}
			orderTickets.add(newOrderTicket);
		}
	}

	/**
	 * This function calculates the original total price of tickets.
	 *
	 * @return The original total price of tickets.
	 */
	static double orderTicketTotalPrice() {
		double totalPrice = 0.0;
		for (Ticket ticket : orderTickets) {
			totalPrice += ticket.getPrice();
		}
		return totalPrice;
	}

	/**
	 * This function calculates the total price of tickets after discount.
	 *
	 * @return The total price of tickets after discount.
	 */
	static double orderTicketTotalFinalPrice() {
		double totalFinalPrice = 0.0;
		for (Ticket ticket : orderTickets) {
			totalFinalPrice += ticket.getFinalPrice();
		}
		return totalFinalPrice;
	}

	/**
	 * This function prints all tickets to txt files. And transfer ordered tickets to tickets.
	 */
	static void payAndPrint() {
		//Export to txt files.
		for (Ticket ticket : orderTickets) {
			try {
				FileWriter fileWriter = new FileWriter("tickets/" + ticket.getNum() + ".txt");

				fileWriter.write(ticket.toString());

				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		tickets.addAll(orderTickets);
		orderTickets = new ArrayList<>();
	}

	/**
	 * This function checks if the seat was sold.
	 *
	 * @param seat The seat which would be checked.
	 * @return True for sold, false for not.
	 */
	static boolean checkSold(Seat seat) {
		for (Ticket ticket : tickets) {
			if (ticket.getScreening().equals(seat.getScreening()))
				if (ticket.getSeat().equals(seat.getSeat()))
					return true;
		}
		return false;
	}

	/**
	 * Getter function of films.
	 *
	 * @return All films of kiosk.
	 */
	static ArrayList<Film> getFilms() {
		return films;
	}

	/**
	 * Getter function of film.
	 *
	 * @param i The film no.
	 * @return The selected film.
	 */
	static Film getFilm(int i) {
		return films.get(i);
	}

	/**
	 * This function pretends to send an email.
	 */
	static void sendMail() {
		JLabel sendMailSuccessful = new JLabel("Send mail successfully!", JLabel.CENTER);
		sendMailSuccessful.setFont(Kiosk.getUIMainFont());
		JOptionPane.showMessageDialog(null, sendMailSuccessful, "Send Mail", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * This function read films form xml and save to an ArrayList, and return it.
	 *
	 * @return the ArrayList which contains all films.
	 */
	static ArrayList<Film> getFilmFromFile() {
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

					NodeList screeningList = filmElement.getElementsByTagName("screening");

					ArrayList<String> screenings = new ArrayList<>();
					for (int j = 0; j < screeningList.getLength(); j++) {
						screenings.add(screeningList.item(j).getTextContent());
					}
					films.add(new Film(name, imageUrl, length, price, screenings));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return films;
	}

	/**
	 * This function saves ArrayList of films to xml file.
	 *
	 * @param films The ArrayList which contains all films which you want to save to file.
	 * @return True means save successfully, false for failed.
	 */
	static boolean writeFilmToFile(ArrayList<Film> films) {
		Document doc;
		Element kiosk;
		Element film;
		Element name;
		Element imageUrl;
		Element length;
		Element price;
		Element screening;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			doc = dbBuilder.newDocument();

			kiosk = doc.createElement("kiosk");
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

				for (String screening1 : film1.getScreenings()) {
					screening = doc.createElement("screening");
					screening.appendChild(doc.createTextNode(screening1));
					film.appendChild(screening);
				}

				kiosk.appendChild(film);
			}
			doc.appendChild(kiosk);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("films.xml"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

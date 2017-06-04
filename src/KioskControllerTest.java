import org.junit.Before;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Title        KioskControllerTest.java
 * Description  This class test the kiosk controller.
 */
public class KioskControllerTest {

	private Film film;
	private Ticket ticket, orderTicket, newTicket, newOrderTicket;
	private Seat seat;
	private ArrayList<Film> films;


	/**
	 * This is the data preparation of tests.
	 *
	 * @throws Exception Test exception
	 */
	@Before
	public void setup() throws Exception {
		films = new ArrayList<>();
		ArrayList<Ticket> tickets = new ArrayList<>();
		ArrayList<Ticket> orderTickets = new ArrayList<>();
		ArrayList<Seat> selectedSeats = new ArrayList<>();
		ArrayList<String> todayScreenings = new ArrayList<>();

		todayScreenings.add("2/2017-05-29-23-00");
		todayScreenings.add("2/2017-05-29-23-30");

		film = new Film("MOONLIGHT", "images/moonlight.jpg", 111, 16.0, todayScreenings);
		ticket = new Ticket(film, "2/2017-05-29-23-30", "C1", 0);
		orderTicket = new Ticket(film, "2/2017-05-29-23-30", "B1", 0);
		seat = new Seat("A1", film, "2/2017-05-29-23-30");

		tickets.add(ticket);
		orderTickets.add(orderTicket);
		selectedSeats.add(seat);
		films.add(film);

		KioskController.tickets = tickets;
		KioskController.orderTickets = orderTickets;
		KioskController.selectedSeats = selectedSeats;
		KioskController.films = films;

		newTicket = new Ticket(film, "2/2017-05-29-23-30", "A1", 0);
		newOrderTicket = new Ticket(film, "2/2017-05-29-23-30", "A1", 0);
	}

	/**
	 * This is the test of loadFilm().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void loadFilmsTest() throws Exception {

		assertTrue("loadFilms Failed", KioskController.loadFilms());

	}

	/**
	 * This the test of checkDuplicated().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void checkDuplicatedTest() throws Exception {

		assertFalse("checkDuplicated Failed", KioskController.checkDuplicated(newTicket));

	}

	/**
	 * This is the test of addOrderTicket().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void addOrderTicketTest() throws Exception {

		KioskController.addOrderTicket();
		System.out.print(KioskController.orderTickets.size());
		assertEquals("Failed", newOrderTicket.getFilm(), KioskController.orderTickets.get(1).getFilm());
		assertEquals("Failed", newOrderTicket.getScreening(), KioskController.orderTickets.get(1).getScreening());
		assertEquals("Failed", newOrderTicket.getSeat(), KioskController.orderTickets.get(1).getSeat());
		assertEquals("Failed", newOrderTicket.getType(), KioskController.orderTickets.get(1).getType());

	}

	/**
	 * This is the test of orderTicketPrice().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void orderTicketTotalPriceTest() throws Exception {

		assertEquals("orderTicketTotalPrice Failed", 16.0, KioskController.orderTicketTotalPrice(), 0.0);

	}

	/**
	 * This is the test of orderTicketFinalPrice().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void orderTicketTotalFinalPriceTest() throws Exception {

		assertEquals("orderTicketTotalFinalPrice Failed", 16.0, KioskController.orderTicketTotalFinalPrice(), 0.0);

	}

	/**
	 * This is the test of payAndPrint().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void payAndPrintTest() throws Exception {

		KioskController.payAndPrint();
		assertEquals("payAndPrint Failed", 2, KioskController.tickets.size());

	}

	/**
	 * This is the test of checkSold().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void checkSoldTest() throws Exception {

		assertFalse("checkSold Failed", KioskController.checkSold(seat));

	}

	/**
	 * This is the test of writeFilmFromFile().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void writeFilmFromFileTest() throws Exception {

		assertTrue("writeFilmFromFile Failed", KioskController.writeFilmToFile(films));

	}

	/**
	 * This is the test of getFilmFromFile().
	 *
	 * @throws Exception Test exception
	 */
	@org.junit.Test
	public void getFilmFromFileTest() throws Exception {

		assertEquals("getFilmFromFile Failed", 5, KioskController.getFilmFromFile().size());

	}

}
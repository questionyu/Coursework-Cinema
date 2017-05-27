import java.util.ArrayList;

/**
 * Title        .java
 * Description  This class defines a cinema.
 */
class KioskController {
	static ArrayList<Film> films = new ArrayList<>();

	static ArrayList<Ticket> tickets = new ArrayList<>();

	static ArrayList<Ticket> orderTickets = new ArrayList<>();

	static ArrayList<Seat> selectedSeats = new ArrayList<>();

	private KioskController() {
	}

	private static boolean checkDuplicated(Ticket newTicket) {
		for (Ticket ticket : tickets)
			if (ticket.getNum() == newTicket.getNum())
				return true;
		for (Ticket ticket : orderTickets)
			if (ticket.getNum() == newTicket.getNum())
				return true;
		return false;
	}

	static void addOrderTicket() {
		for (Seat seat : selectedSeats) {
			Ticket newOrderTicket = new Ticket(seat.getFilm(), seat.getScreening(), seat.getSeat(), seat.getTicketType());
			while (checkDuplicated(newOrderTicket)) {
				newOrderTicket.generateRandomNum();
			}
			orderTickets.add(newOrderTicket);
		}
	}

	static double orderTicketTotalPrice() {
		double totalPrice = 0.0;
		for (Ticket ticket : orderTickets) {
			totalPrice += ticket.getPrice();
		}
		return totalPrice;
	}

	static double orderTicketTotalFinalPrice() {
		double totalFinalPrice = 0.0;
		for (Ticket ticket : orderTickets) {
			totalFinalPrice += ticket.getFinalPrice();
		}
		return totalFinalPrice;
	}

	static void payAndPrint() {
		//Export to txt files.
		tickets.addAll(orderTickets);
		orderTickets = new ArrayList<>();
	}

	static boolean checkSold(Seat seat) {
		for (Ticket ticket : tickets) {
			if (ticket.getScreening().equals(seat.getScreening()))
				if (ticket.getSeat().equals(seat.getSeat()))
					return true;
		}
		return false;
	}

	static ArrayList<Film> getFilms() {
		return films;
	}

	static Film getFilm(int i) {
		return films.get(i);
	}

	static void report() {
		//For management UI.
	}
}

import java.util.ArrayList;

/**
 * Title        .java
 * Description  This class defines a cinema.
 */
class CinemaController {
	static ArrayList<Film> films = new ArrayList<>();

	static ArrayList<Ticket> tickets = new ArrayList<>();

	static ArrayList<Ticket> orderTickets = new ArrayList<>();

	static ArrayList<Seat> selectedSeats = new ArrayList<>();

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
			Ticket newOrderTicket = new Ticket(seat.getFilm(), seat.getScreeningNo(), seat.getActionCommand(), seat.getTicketType());
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

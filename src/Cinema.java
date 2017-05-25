import java.util.ArrayList;

/**
 * Title        Cinema.java
 * Description  This class defines a cinema.
 */
class Cinema {
	static ArrayList<Film> films = new ArrayList<>();

	static ArrayList<Ticket> tickets = new ArrayList<>();

	static boolean checkDuplicated(Ticket newTicket) {
		for (Ticket ticket : tickets)
			if (ticket.getTicketNum() == newTicket.getTicketNum())
				return true;
		return false;
	}

	static void check(Ticket newTicket) {
		while (checkDuplicated(newTicket)) {
			newTicket.generateRandomNum();
		}
	}

	static ArrayList<Film> getFilms() {
		return films;
	}

	static Film getFilm(int i) {
		return films.get(i);
	}
}

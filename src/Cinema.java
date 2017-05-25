import java.util.ArrayList;

/**
 * Title        Cinema.java
 * Description  This class defines a cinema.
 */
class Cinema {
	static ArrayList<Film> films = new ArrayList<>();

	static ArrayList<Ticket> tickets = new ArrayList<>();

	static ArrayList<Film> getFilms() {
		return films;
	}

	static Film getFilm(int i) {
		return films.get(i);
	}
}

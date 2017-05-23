import java.util.ArrayList;

/**
 * Title        Cinema.java
 * Description  This class defines a cinema.
 */
class Cinema {
	private ArrayList<Film> films = new ArrayList<>();

	Cinema(ArrayList<Film> films) {
		this.films = films;
	}

	ArrayList<Film> getFilms() {
		return films;
	}

	Film getFilm(int i) {
		return films.get(i);
	}
}

import java.util.ArrayList;

/**
 * Title        Film.java
 * Description  This class store the information of movie.
 */
class Film {
	private String name;
	private int length;
	private double price;
	private ArrayList<String> screenings;

	Film(String name, int length, double price, ArrayList<String> screenings) {
		this.name = name;
		this.length = length;
		this.price = price;
		this.screenings = screenings;
	}

	String getName() {
		return name;
	}

	int getLength() {
		return length;
	}

	double getPrice() {
		return price;
	}

	ArrayList<String> getScreenings() {
		return screenings;
	}
}

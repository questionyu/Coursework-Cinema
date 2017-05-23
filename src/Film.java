import javax.swing.*;
import java.util.ArrayList;

/**
 * Title        Film.java
 * Description  This class defines a film.
 */
class Film {
	private String name;
	private String imageUrl;
	private int length;
	private double price;
	private ArrayList<String> screenings;

	Film(String name, String imageUrl, int length, double price, ArrayList<String> screenings) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.length = length;
		this.price = price;
		this.screenings = screenings;
	}

	String getName() {
		return name;
	}

	String getImageUrl() {
		return imageUrl;
	}

	ImageIcon getImage() {
		return new ImageIcon(imageUrl, name);
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

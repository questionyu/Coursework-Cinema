import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Title        Film.java
 * Description  This class defines a film.
 */
class Film {
	/**
	 * The name of film.
	 */
	private String name;

	/**
	 * The image url of film.
	 */
	private String imageUrl;

	/**
	 * The length of the film.
	 */
	private int length;

	/**
	 * The price of the film.
	 */
	private double price;

	/**
	 * All screenings of film.
	 */
	private ArrayList<String> screenings;

	/**
	 * Constructor function. Create a film.
	 *
	 * @param name       The name of film.
	 * @param imageUrl   The image url of film.
	 * @param length     The length of the film.
	 * @param price      The price of the film.
	 * @param screenings All screenings of film.
	 */
	Film(String name, String imageUrl, int length, double price, ArrayList<String> screenings) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.length = length;
		this.price = price;
		this.screenings = screenings;
	}

	/**
	 * Getter function of name.
	 *
	 * @return The name of film.
	 */
	String getName() {
		return name;
	}

	/**
	 * Getter function of image url.
	 *
	 * @return The image url of film.
	 */
	String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Getter function of poster image of film.
	 *
	 * @return The image of film's poster.
	 */
	ImageIcon getImage() {
		return new ImageIcon(imageUrl, name);
	}

	/**
	 * Getter function of length of film.
	 *
	 * @return The length of film.
	 */
	int getLength() {
		return length;
	}

	/**
	 * Getter function of price of film.
	 *
	 * @return The price of film.
	 */
	double getPrice() {
		return price;
	}

	/**
	 * Getter function of all screenings.
	 *
	 * @return All screenings of film.
	 */
	ArrayList<String> getScreenings() {
		return screenings;
	}

	/**
	 * Getter function of today's screenings.
	 *
	 * @return Today's screenings of film.
	 */
	ArrayList<String> getTodayScreenings() {
		ArrayList<String> todayScreenings = new ArrayList<>();
		for (String screening : screenings) {
			Date date = KioskController.convertDate(screening);
			//This is the key. Only today's screening will be added to the ArrayList.
			Calendar dateCalendar = Calendar.getInstance();
			dateCalendar.setTime(date);
			Calendar now = Calendar.getInstance();
			if (now.get(Calendar.YEAR) == dateCalendar.get(Calendar.YEAR) && now.get(Calendar.DAY_OF_YEAR) == dateCalendar.get(Calendar.DAY_OF_YEAR))
				todayScreenings.add(screening);
		}
		return todayScreenings;
	}
}

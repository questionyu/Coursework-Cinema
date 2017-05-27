import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

	ArrayList<String> getTodayScreenings() {
		ArrayList<String> todayScreenings = new ArrayList<>();
		for (String screening : screenings) {
			String[] time = screening.split("/");
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
			Date date = new Date();
			try {
				date = simpleDateFormat.parse(time[1]);
			} catch (ParseException e) {
				System.out.println("screening information wrong!" + screening);
			}
			Date now = new Date();
			if (now.getYear() == date.getYear() && now.getMonth() == date.getMonth() && now.getDay() == date.getDay())
				todayScreenings.add(screening);
		}
		return todayScreenings;
	}
}

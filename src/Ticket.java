import java.awt.*;

/**
 * Title        Ticket.java
 * Description  This class defines a ticket.
 */
class Ticket {
	static final int ADULT = 0;
	static final int CHILD = 1;
	static final int SENIOR = 2;
	static final int STUDENT = 3;

	static final Color ADULT_COLOR = Color.YELLOW;
	static final Color CHILD_COLOR = Color.RED;
	static final Color SENIOR_COLOR = Color.BLUE;
	static final Color STUDENT_COLOR = Color.PINK;

	private static final double ADULT_DISCOUNT = 0.0;
	private static final double CHILD_DISCOUNT = 0.5;
	private static final double SENIOR_DISCOUNT = 0.2;
	private static final double STUDENT_DISCOUNT = 0.15;

	private static final int TICKET_NUMBER_LENGTH = 8;

	private Film film;
	private int screen;
	private String time;
	private String seat;
	private int num;
	private int type;
	private double discount;
	private double price;
	private double finalPrice;

	Ticket(Film film, int screeningNo, String seat, int type) {
		this.film = film;
		this.seat = seat;
		this.type = type;
		generateRandomNum();
		switch (type) {
			case ADULT:
				this.discount = ADULT_DISCOUNT;
				break;
			case CHILD:
				this.discount = CHILD_DISCOUNT;
				break;
			case SENIOR:
				this.discount = SENIOR_DISCOUNT;
				break;
			case STUDENT:
				this.discount = STUDENT_DISCOUNT;
				break;
		}
		price = film.getPrice();
		finalPrice = price * (1 - discount);
		String[] screenAndTime = film.getScreenings().get(screeningNo).split("/");
		screen = Integer.parseInt(screenAndTime[0]);
		time = screenAndTime[1];
	}

	void generateRandomNum() {
		StringBuilder ticketNo = new StringBuilder();
		for (int i = 0; i < TICKET_NUMBER_LENGTH; i++) {
			ticketNo.append((int) (1 + Math.random() * 4));
		}
		num = Integer.parseInt(ticketNo.toString());
	}

	/**
	 * This method returns the ticket number.
	 *
	 * @return Ticket number.
	 */
	int getNum() {
		return num;
	}

	/**
	 * This method returns the type of ticket.
	 *
	 * @return A string variable which save the type of ticket.
	 */
	String getType() {
		switch (type) {
			case 0:
				return "Adult";
			case 1:
				return "Child";
			case 2:
				return "Senior";
			case 3:
				return "Student";
		}
		return null;
	}

	double getPrice() {
		return price;
	}

	double getFinalPrice() {
		return finalPrice;
	}

	String getFilmName() {
		return film.getName();
	}

	int getScreen() {
		return screen;
	}

	String getTime() {
		return time;
	}

	String getSeat() {
		return seat;
	}

	@Override
	public String toString() {
		return "Ticket\n" +
				"Film: " + film.getName() + "\n" +
				"Time: " + time + "\n" +
				"Screen: " + screen + "\n" +
				"Seat: " + seat + "\n" +
				"Ticket type: " + this.getType() + "\n" +
				"Ticket ID: " + num + "\n";
	}
}

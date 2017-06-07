import java.awt.*;

/**
 * Title        Ticket.java
 * Description  This class defines a ticket.
 */
class Ticket {
	/**
	 * Adult type.
	 */
	static final int ADULT = 0;

	/**
	 * Child type.
	 */
	static final int CHILD = 1;

	/**
	 * Senior type.
	 */
	static final int SENIOR = 2;

	/**
	 * Student type.
	 */
	static final int STUDENT = 3;

	/**
	 * Adult's color for selected seat.
	 */
	static final Color ADULT_COLOR = Color.YELLOW;

	/**
	 * Child's color for selected seat.
	 */
	static final Color CHILD_COLOR = Color.RED;

	/**
	 * Senior's color for selected seat.
	 */
	static final Color SENIOR_COLOR = Color.BLUE;

	/**
	 * Student's color for selected seat.
	 */
	static final Color STUDENT_COLOR = Color.PINK;

	/**
	 * Adult's discount for price.
	 */
	private static final double ADULT_DISCOUNT = 0.0;

	/**
	 * Child's discount for price.
	 */
	private static final double CHILD_DISCOUNT = 0.5;

	/**
	 * Senior's discount for price.
	 */
	private static final double SENIOR_DISCOUNT = 0.2;

	/**
	 * Student's discount for price.
	 */
	private static final double STUDENT_DISCOUNT = 0.15;

	/**
	 * The length of ticket number.
	 */
	private static final int TICKET_NUMBER_LENGTH = 8;

	/**
	 * The film of ticket.
	 */
	private Film film;

	/**
	 * The screening of ticket.
	 */
	private String screening;

	/**
	 * The screen of ticket.
	 */
	private int screen;

	/**
	 * The time of ticket.
	 */
	private String time;

	/**
	 * The seat number of ticket.
	 */
	private String seat;

	/**
	 * The ticket number of ticket.
	 */
	private int num;

	/**
	 * The type of ticket.
	 */
	private int type;

	/**
	 * The discount for price of ticket.
	 */
	private double discount;

	/**
	 * The original price of ticket.
	 */
	private double price;

	/**
	 * The price after discount of ticket.
	 */
	private double finalPrice;

	/**
	 * This string stores the student ID. Only when the ticket is student is valid.
	 */
	private String studentID;

	/**
	 * Constructor function. Create a ticket.
	 *
	 * @param film      The film of ticket.
	 * @param screening The screening of ticket.
	 * @param seat      The seat number of ticket.
	 * @param type      The type of ticket.
	 */
	Ticket(Film film, String screening, String seat, int type) {
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
		this.screening = screening;
		String[] screenAndTime = screening.split("/");
		screen = Integer.parseInt(screenAndTime[0]);
		time = screenAndTime[1];
	}

	/**
	 * Constructor function. Create a ticket.
	 *
	 * @param film      The film of ticket.
	 * @param screening The screening of ticket.
	 * @param seat      The seat number of ticket.
	 * @param type      The type of ticket.
	 * @param studentID The student ID.
	 */
	Ticket(Film film, String screening, String seat, int type, String studentID) {
		this(film, screening, seat, type);
		this.studentID = studentID;
	}

	/**
	 * This function generates a random ticket number.
	 */
	void generateRandomNum() {
		StringBuilder ticketNo = new StringBuilder();
		for (int i = 0; i < TICKET_NUMBER_LENGTH; i++) {
			ticketNo.append((int) (1 + Math.random() * 4));
		}
		num = Integer.parseInt(ticketNo.toString());
	}

	/**
	 * Getter function of ticket number.
	 *
	 * @return Ticket number.
	 */
	int getNum() {
		return num;
	}

	/**
	 * Getter function of ticket type.
	 *
	 * @return A string variable which save the type of ticket.
	 */
	String getTypeString() {
		switch (type) {
			case ADULT:
				return "Adult";
			case CHILD:
				return "Child";
			case SENIOR:
				return "Senior";
			case STUDENT:
				return "Student";
		}
		return null;
	}

	/**
	 * Getter function of ticket type.
	 *
	 * @return An int which save the type of ticket.
	 */
	int getType() {
		return type;
	}

	/**
	 * Getter function of price.
	 *
	 * @return The price of ticket.
	 */
	double getPrice() {
		return price;
	}

	/**
	 * Getter function of after discount price.
	 *
	 * @return The price of after discount.
	 */
	double getFinalPrice() {
		return finalPrice;
	}

	/**
	 * Getter function of film of ticket.
	 *
	 * @return The film of ticket.
	 */
	Film getFilm() {
		return film;
	}

	/**
	 * Getter function of the name of film of ticket.
	 *
	 * @return The name of film of ticket.
	 */
	String getFilmName() {
		return film.getName();
	}

	/**
	 * Getter function of the screening of ticket.
	 *
	 * @return The screening of ticket.
	 */
	String getScreening() {
		return screening;
	}

	/**
	 * Getter function of the screen of ticket.
	 *
	 * @return The screen of ticket.
	 */
	int getScreen() {
		return screen;
	}

	/**
	 * Getter function of the time of ticket.
	 *
	 * @return The time of ticket.
	 */
	String getTime() {
		return time;
	}

	/**
	 * Getter function of seat number of ticket.
	 *
	 * @return The seat number of ticket.
	 */
	String getSeat() {
		return seat;
	}

	/**
	 * This function prints all ticket information.
	 *
	 * @return A string which contains all information of ticket.
	 */
	@Override
	public String toString() {
		return "Ticket\n" +
				"Film: " + film.getName() + "\n" +
				"Time: " + time + "\n" +
				"Screen: " + screen + "\n" +
				"Seat: " + seat + "\n" +
				"Ticket type: " + this.getTypeString() + "\n" +
				(type == STUDENT ? "Student ID: " + studentID + "\n" : "") +
				"Ticket ID: " + num + "\n";
	}
}

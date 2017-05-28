import javax.swing.*;

/**
 * Title        Seat.java
 * Description  This class defines a seat. It is a subclass of JToggleButton.
 */
class Seat extends JToggleButton {
	/**
	 * The film of this seat.
	 */
	private Film film;

	/**
	 * The screening of this seat.
	 */
	private String screening;

	/**
	 * The type of the seat/ticket.
	 */
	private int ticketType;

	/**
	 * This string stores the student ID. Only when the ticket is student is valid.
	 */
	private String studentID;

	/**
	 * Constructor function. Create a seat.
	 *
	 * @param text      The displayed number.
	 * @param film      The film of this seat.
	 * @param screening The screening of this seat.
	 */
	Seat(String text, Film film, String screening) {
		super(text);
		this.film = film;
		this.screening = screening;
	}

	/**
	 * Getter function of type of seat.
	 *
	 * @return The type of seat.
	 */
	int getTicketType() {
		return ticketType;
	}

	/**
	 * Setter function of type of seat.
	 *
	 * @param type The type of seat.
	 */
	void setTicketType(int type) {
		this.ticketType = type;
	}

	/**
	 * Getter function of film of seat.
	 *
	 * @return The film of seat.
	 */
	Film getFilm() {
		return film;
	}

	/**
	 * Getter function of screening of seat.
	 *
	 * @return The screening of seat.
	 */
	String getScreening() {
		return screening;
	}

	/**
	 * Getter function of seat number.
	 *
	 * @return The seat number of seat.
	 */
	String getSeat() {
		return this.getActionCommand();
	}

	/**
	 * Setter function of seat number.
	 *
	 * @param seat The seat number of seat.
	 */
	void setSeat(String seat) {
		this.setActionCommand(seat);
	}

	/**
	 * Getter function of student ID.
	 *
	 * @return The student ID.
	 */
	String getStudentID() {
		return studentID;
	}

	/**
	 * Setter function of student ID.
	 *
	 * @param studentID The student ID.
	 */
	void setStudentID(String studentID) {
		this.studentID = studentID;
	}
}

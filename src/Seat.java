import javax.swing.*;

/**
 * Title        Seat.java
 * Description
 */
class Seat extends JToggleButton {
	private Film film;
	private int screeningNo;
	private String seat;
	private int ticketType;

	Seat(String text, Film film, int screeningNo) {
		super(text);
		this.film = film;
		this.screeningNo = screeningNo;
	}

	int getTicketType() {
		return ticketType;
	}

	void setTicketType(int type) {
		this.ticketType = type;
	}

	Film getFilm() {
		return film;
	}

	int getScreeningNo() {
		return screeningNo;
	}

	String getSeat() {
		return seat;
	}
}

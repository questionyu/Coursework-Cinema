import javax.swing.*;

/**
 * Title        Seat.java
 * Description
 */
class Seat extends JToggleButton {
	private Film film;
	private String screening;
	private int ticketType;

	Seat(String text, Film film, String screening) {
		super(text);
		this.film = film;
		this.screening = screening;
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

	String getScreening() {
		return screening;
	}

	String getSeat() {
		return this.getActionCommand();
	}

	void setSeat(String seat) {
		this.setActionCommand(seat);
	}
}

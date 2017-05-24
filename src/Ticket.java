import java.awt.*;

/**
 * Title        Ticket.java
 * Description  This class defines a ticket.
 */
class Ticket {
	static final int ADULT_DISCOUNT = 1;
	static final int CHILD_DISCOUNT = 0;
	static final int SENIOR_DISCOUNT = 2;
	static final int STUDENT_DISCOUNT = 3;

	static final Color ADULT_COLOR = Color.YELLOW;
	static final Color CHILD_COLOR = Color.RED;
	static final Color SENIOR_COLOR = Color.BLUE;
	static final Color STUDENT_COLOR = Color.PINK;

	private int ticketNum;
	private int ticketType;
	private double discount;

	private String seatNum;
	private String movieName;

	Ticket(int ticketNum, int ticketDiscount) {
		this.ticketNum = ticketNum;
		this.ticketType = ticketDiscount;
		switch (ticketDiscount) {
			case CHILD_DISCOUNT:
				this.discount = 0.5;
				break;
			case ADULT_DISCOUNT:
				this.discount = 0;
				break;
			case SENIOR_DISCOUNT:
				this.discount = 0.2;
				break;
			case STUDENT_DISCOUNT:
				this.discount = 0.15;
				break;
		}
	}

	/**
	 * This method returns the ticket number.
	 *
	 * @return Ticket number.
	 */
	int getTicketNum() {
		return ticketNum;
	}

	/**
	 * This method returns the type of ticket.
	 *
	 * @return A string variable which save the type of ticket.
	 */
	String getTicketType() {
		switch (ticketType) {
			case 0:
				return "Child";
			case 1:
				return "Adult";
			case 2:
				return "Senior";
			case 3:
				return "Student";
			default:
				return "Unknown Type";
		}
	}
}

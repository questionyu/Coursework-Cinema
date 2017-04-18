import javax.swing.*;

/**
 * Title        Seat.java
 * Description
 */
class Seat extends JButton {
	private int column;
	private String row;
	private String seatNum;

	Seat(int column, String row) {
		this.column = column;
		this.row = row;
		seatNum = row + column;
	}

	String getSeatNum() {
		return seatNum;
	}
}

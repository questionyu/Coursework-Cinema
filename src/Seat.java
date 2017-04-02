/**
 * Title
 * Description
 * Copyright    (c) 2017 Copyright Holder All Rights Reserved.
 *
 * @author Question
 * @date 02/04/2017
 */
public class Seat {
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

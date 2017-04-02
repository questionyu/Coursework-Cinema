/*
 * Created by Question on 2/4/2017
 * Copyright (c) 2017. All Rights Reserved.
 */

/**
 * Title        Seat.java
 * Description
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

/*
 * Created by Question on 2/4/2017
 * Copyright (c) 2017. All Rights Reserved.
 */

/**
 * Title        Ticket.java
 * Description
 */

public class Ticket {
	public static final int ChildDiscount = 0;
	public static final int AdultDiscount = 1;
	public static final int SeniorDiscount = 2;
	public static final int StudentDiscount = 3;

	private int ticketNum;
	private int ticketType;
	private double discount;

	private String seatNum;
	private String movieName;

	Ticket(int ticketNum, int ticketDiscount) {
		this.ticketNum = ticketNum;
		this.ticketType = ticketDiscount;
		switch (ticketDiscount) {
			case ChildDiscount:
				this.discount = 0.5;
				break;
			case AdultDiscount:
				this.discount = 0;
				break;
			case SeniorDiscount:
				this.discount = 0.2;
				break;
			case StudentDiscount:
				this.discount = 0.15;
				break;
		}
	}

	/**
	 * This method get the ticket number.
	 *
	 * @return ticketNum Ticket number.
	 */
	int getTicketNum() {
		return ticketNum;
	}

	/**
	 * This method get the type of ticket.
	 *
	 * @return String A string variable which save the type of ticket.
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

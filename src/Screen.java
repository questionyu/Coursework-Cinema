/**
 * Title        Screen.java
 * Description  This class defines three screens.
 */
class Screen {
	private int screenNum;
	private String movieName;
	private int[] seats;

	Screen(int screenNum) {
		switch (screenNum) {
			case 1:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1};
				break;
			case 2:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1};
				break;
			case 3:
				seats = new int[]{1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1,
						1, 1, 1, 1, 0, 1, 1, 1, 1};
				break;
		}

	}

	int getScreenNum() {
		return screenNum;
	}

	String getMovieName() {
		return movieName;
	}

	int[] getSeats() {
		return seats;
	}
}

/**
 * Created by Question on 01/04/2017.
 */
public class Screen {
	private int screenNum;
	private String movieName;

	protected Screen(int screenNum) {
		this.screenNum = screenNum;
	}

	protected int getScreenNum() {
		return screenNum;
	}

	protected String getMovieName() {
		return movieName;
	}
}

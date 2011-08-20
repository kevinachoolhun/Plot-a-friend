package plotAFriend.PlotAFriendSaver.Model.Logging;

import java.util.Date;

import android.util.Log;

public class SuggestLogger {

	private static final SuggestLogger instance = new SuggestLogger();

	// Private constructor prevents instantiation from other classes
	private SuggestLogger() {
	}

	public static SuggestLogger getLogger() {
		return instance;
	}

	private int getBattery() {
		return 10;
	}

	public void l(String tag) {
		Date now = new Date();
		
		String message = "Battery:" + this.getBattery();
		message += "Date:" + now.getTime();
		
		Log.v(tag, message);
		
	}

}

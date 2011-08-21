package plotAFriend.PlotAFriendSaver.Model.Logging;

import java.util.Date;


import android.content.Context;
import android.util.Log;

public class SuggestLogger {

	private static final SuggestLogger instance = new SuggestLogger();

	// Private constructor prevents instantiation from other classes
	private SuggestLogger() {
	}

	public static SuggestLogger getLogger() {
		return instance;
	}

	private int getBattery(Context c) {
		return SuggestBatteryReader.getInstance().getBatteryLevel(c);
	}
	
	private String getConnectivity(Context c)
	{
		return SuggestConnectivityReader.getInstance().getConnectivity(c);
		
	}

	public void l(String location, Context c) {
		//tag = "SuggestLogs"
		Date now = new Date();
		
		String message = location;
		message += "Battery:" + this.getBattery(c);
		message += "Connectivity:" + this.getConnectivity(c);
		message += "Date:" + now.getTime();
		
		Log.v("SuggestLog", message);
		
	}

}

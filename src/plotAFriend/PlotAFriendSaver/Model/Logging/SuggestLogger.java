package plotAFriend.PlotAFriendSaver.Model.Logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class SuggestLogger {

	private static final SuggestLogger instance = new SuggestLogger();

	// Private constructor prevents instantiation from other classes
	private SuggestLogger() {
	}

	public static SuggestLogger getLogger() {
		return instance;
	}

	private double getBattery(Context c) {
		return SuggestBatteryReader.getInstance().getBatteryLevel(c);
	}

	private String getConnectivity(Context c) {
		return SuggestConnectivityReader.getInstance().getConnectivity(c);

	}

	public void l(String location, Context c) {
		// tag = "SuggestLogs"
		Date now = new Date();

		String message = location;
		message += " Battery:" + this.getBattery(c);
		message += " Connectivity:" + this.getConnectivity(c);
		message += " Date:" + now.toLocaleString() + ";";

		Log.v("SuggestLog", message);
		writeToFile(message, c);

	}

	public void writeToFile(String message, Context c) {
		FileWriter writer = null;
		try {
			String state = Environment.getExternalStorageState();
			if (!state.equals(Environment.MEDIA_MOUNTED)) {
				Toast.makeText(c, "CANNOT WRITE TO SDCARD", 5000);

			} else {

				File directoryPath = new File(
						Environment.getExternalStorageDirectory(),
						"/Android/data/plotAFriend.PlotAFriendSaver/files/");

				if (!directoryPath.exists()) {
					directoryPath.mkdirs();
				}

				File logFile = new File(directoryPath, "logfile.txt");

				if (!logFile.exists()) {
					logFile.createNewFile();
				}

				writer = new FileWriter(logFile, true);

				if (writer != null) {
					writer.append(message);
				}
				// writer.flush();
				writer.close();

			}
		} catch (IOException e) {
			Toast.makeText(c, "FILE NOT WRITTEN!! ", 5000);
		}

	}

}

package plotAFriend.PlotAFriendSaver.Model.Logging;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SuggestConnectivityReader {
	private static SuggestConnectivityReader instance = null;

	private SuggestConnectivityReader() {

	}

	public static SuggestConnectivityReader getInstance() {
		if (instance == null) {
			instance = new SuggestConnectivityReader();

		}
		return instance;
	}

	public String getConnectivity(Context c) {
		String strConnection = "";
		ConnectivityManager mgr = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = mgr.getActiveNetworkInfo();

		if (netInfo != null) {

			if (netInfo.getState() == NetworkInfo.State.CONNECTED

			&& netInfo.isAvailable()) {

				strConnection = "Type: " + netInfo.getTypeName() + " Subtype: "
						+ netInfo.getSubtypeName();

			}

		}

		return strConnection;

	}
}

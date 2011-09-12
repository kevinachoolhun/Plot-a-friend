package com.kevinachoolhun.algae.contextreader;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityMonitor {
	private static ConnectivityMonitor instance = null;

	private ConnectivityMonitor() {

	}

	public static ConnectivityMonitor getInstance() {
		if (instance == null) {
			instance = new ConnectivityMonitor();

		}
		return instance;
	}

	public ConnectivityType getConnectivity(Context c) {
		
		ConnectivityType type = ConnectivityType.NONE;
		
		ConnectivityManager mgr = (ConnectivityManager) c
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = mgr.getActiveNetworkInfo();

		if (netInfo != null) {

			if (netInfo.getState() == NetworkInfo.State.CONNECTED

			&& netInfo.isAvailable()) {

				if (netInfo.getTypeName().equals("WIFI"))
				{
					type = ConnectivityType.WIFI;
				
				}
				else if (netInfo.getTypeName().equals("MOBILE"))
				{
					type = ConnectivityType.MOBILE;
				
				}
			}

		}

		return type;

	}
}



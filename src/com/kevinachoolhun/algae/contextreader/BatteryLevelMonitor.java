package com.kevinachoolhun.algae.contextreader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BatteryLevelMonitor {

	private int batterylevel = 0;
	private static BatteryLevelMonitor instance = null;

	private BatteryLevelMonitor() {

	}

	public static BatteryLevelMonitor getInstance() {
		if (instance == null) {
			instance = new BatteryLevelMonitor();

		}
		return instance;
	}

	public double getBatteryLevel(Context c) {
		// c.registerReceiver(this.myBatteryReceiver, new
		// IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		Intent batteryIntent = c.getApplicationContext().registerReceiver(null,
				new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int rawlevel = batteryIntent.getIntExtra("level", -1);
		double scale = batteryIntent.getIntExtra("scale", -1);
		double level = -1;
		if (rawlevel >= 0 && scale > 0) {
			level = rawlevel * 100 / scale;
		}
		return level;

	}

	private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {

			if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
				batterylevel = intent.getIntExtra("level", 0);
				// str = "Battery Level: " + batterylevel;
			}

			// tv.setText(str);
		}
	};

}

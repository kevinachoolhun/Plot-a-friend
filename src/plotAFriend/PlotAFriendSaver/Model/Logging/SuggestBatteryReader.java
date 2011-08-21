package plotAFriend.PlotAFriendSaver.Model.Logging;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class SuggestBatteryReader {

private int batterylevel = 0;
private static SuggestBatteryReader instance = null;


	private SuggestBatteryReader()
	{
		
		
	}
	
	public static SuggestBatteryReader getInstance()
	{
		if (instance == null)
		{
			instance = new SuggestBatteryReader();
		
		}
		return instance;
	}
	
	
	public int getBatteryLevel(Context c)
	{
		c.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		return batterylevel;
	
	}
	
	 private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver(){
		  @Override
	        public void onReceive(Context context, Intent intent) {
			  
			  if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
			  {
				  batterylevel = intent.getIntExtra("level", 0);
				 // str = "Battery Level: " + batterylevel;
			  }
			  
			 // tv.setText(str);
		  }
	 };
	 
}
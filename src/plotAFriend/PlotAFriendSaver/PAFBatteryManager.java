package plotAFriend.PlotAFriendSaver;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class PAFBatteryManager extends Activity{

	private int batterylevel = 0;
	
	String str = "";
	
	TextView tv ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		tv = new TextView(this);

        this.registerReceiver(this.myBatteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		this.setContentView(tv);
		
	}
	
	 private BroadcastReceiver myBatteryReceiver = new BroadcastReceiver(){
		  @Override
	        public void onReceive(Context context, Intent intent) {
			  
			  if(intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED))
			  {
				  batterylevel = intent.getIntExtra("level", 0);
				  str = "Battery Level: " + batterylevel;
			  }
			  
			  tv.setText(str);
		  }
	 };
	

}

package plotAFriend.PlotAFriendSaver;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class ConnectivityMonitor extends Activity {

	String strConnection = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		ConnectivityManager mgr = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = mgr.getActiveNetworkInfo();

		if (netInfo != null) {

			if (netInfo.getState() == NetworkInfo.State.CONNECTED

			&& netInfo.isAvailable()) {

				
			strConnection =  "Type: "+ netInfo.getTypeName() + " Subtype: " +  netInfo.getSubtypeName();			

			}

		}
		
		TextView tv = new TextView(this);
		tv.setText(strConnection);
		this.setContentView(tv);
	}

}

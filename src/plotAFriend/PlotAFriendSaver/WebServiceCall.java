package plotAFriend.PlotAFriendSaver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

public class WebServiceCall extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		String str = null;
		TextView tv = new TextView(this);
		
		try
		{
			URL myURL = new URL("http://myspotfinder.appspot.com/kevina");
			
			URLConnection conn = myURL.openConnection();
			
			InputStream streamFromWS = conn.getInputStream();
			
			BufferedInputStream bufferStream = new BufferedInputStream(streamFromWS);
			
			ByteArrayBuffer baf = new ByteArrayBuffer(50);
            int current = 0;
            while((current = bufferStream.read()) != -1){
                    baf.append((byte)current);
            }

            /* Convert the Bytes read to a String. */
            str = new String(baf.toByteArray());
			
			
		}
		catch(Exception e)
		{
			str = e.getMessage();
			
			
		}
		tv.setText(str);
        this.setContentView(tv);
	}
	
	

}

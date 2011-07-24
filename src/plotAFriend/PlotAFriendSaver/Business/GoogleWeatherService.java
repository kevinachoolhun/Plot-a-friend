package plotAFriend.PlotAFriendSaver.Business;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

public class GoogleWeatherService {

	public static String CallGoogleWeather(String location)
	{
		String str;
		
		try
		{
		String webserviceUrl = "http://www.google.com/ig/api?weather=";
		
		String urlWithPostalCode = webserviceUrl.concat(location);
		
		URL myURL = new URL(urlWithPostalCode);
		
		URLConnection conn = myURL.openConnection();

		InputStream streamFromWS = conn.getInputStream();

		BufferedInputStream bufferStream = new BufferedInputStream(
				streamFromWS);

		ByteArrayBuffer baf = new ByteArrayBuffer(50);
		int current = 0;
		while ((current = bufferStream.read()) != -1) {
			baf.append((byte) current);
		}

		/* Convert the Bytes read to a String. */
		str = new String(baf.toByteArray());
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
			str = ex.getMessage();
			
		}
		
		return str;
		
	}
}

package plotAFriend.PlotAFriendSaver.Business;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.util.ByteArrayBuffer;

public class GooglePlacesService {

	public static String CallGooglePlaces(double latitude, double longitude) {
		String str = null;
		try {
			URL myURL = new URL(
					"https://maps.googleapis.com/maps/api/place/search/json?location="
							+
							/* lat +","+ lon + */"37.7781157,-122.42592285"
							+ "&radius=100000&types=restaurant&sensor=false&key=AIzaSyA6K2MKCrqnT0ImUCP4Pq3oWGb7Id7UuUA");
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
		catch (Exception ex) {
			
			ex.printStackTrace();
			str = ex.getMessage();
		}
		
		return str;
	}
}

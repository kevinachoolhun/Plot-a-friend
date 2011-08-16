package plotAFriend.PlotAFriendSaver;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.http.util.ByteArrayBuffer;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class Agent extends Activity {
	Geocoder coder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		String str = null;
		TextView tv = new TextView(this);

		try {

			// Acquire a reference to the system Location Manager
			LocationManager locationManager = (LocationManager) this
					.getSystemService(Context.LOCATION_SERVICE);

			// Define a listener that responds to location updates
			LocationListener locationListener = new LocationListener() {
				public void onLocationChanged(Location location) {
					// Called when a new location is found by the network
					// location provider.
					// makeUseOfNewLocation(location);
				}

				public void onStatusChanged(String provider, int status,
						Bundle extras) {
				}

				public void onProviderEnabled(String provider) {
				}

				public void onProviderDisabled(String provider) {
				}
			};

			// Register the listener with the Location Manager to receive
			// location updates
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

			coder = new Geocoder(this);

			// URL myURL = new URL("http://myspotfinder.appspot.com/kevina");
			List<Address> addressList2 = coder.getFromLocationName(
					"Choolun Street, La Caverne, Vacoas, Mauritius", 3);

			if (addressList2 != null && addressList2.size() > 0) {
				Address addr = addressList2.get(0);
				if (addr != null) {
					double lat = (addr.getLatitude()); // * 1000000);
					double lon = (addr.getLongitude());// * 1000000);

					URL myURL = new URL(
							"https://maps.googleapis.com/maps/api/place/search/json?location="
									+ lat
									+ ","
									+ lon
									+ /* "37.7781157,-122.42592285"+ */
									"&radius=100000&types=restaurant&sensor=false&key=AIzaSyA6K2MKCrqnT0ImUCP4Pq3oWGb7Id7UuUA");
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

					// ArrayList<Location> locationResults=
					// JSONLocationParser.GetLocations(str);
				}

			}

		} catch (Exception e) {
			str = e.getMessage();

		}
		tv.setText("web service call successful");
		this.setContentView(tv);
	}

}

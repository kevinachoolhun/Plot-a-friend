package plotAFriend.PlotAFriendSaver.Business;

import java.util.ArrayList;
import java.util.List;

import plotAFriend.PlotAFriendSaver.R;
import plotAFriend.PlotAFriendSaver.Model.LocationResult;
import plotAFriend.PlotAFriendSaver.Model.PlacesStatus;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class MapLocation extends MapActivity {

	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	FriendOverlayItems friendOverlay;
	Geocoder coder;
	int maxResults = 0;

	MapController mapController;
	MyLocationListener locationListener;
	LocationManager locationManager;

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isLocationDisplayed() {
		// TODO Auto-generated method stub
		return super.isLocationDisplayed();
	}

	@Override
	protected void onCreate(Bundle icicle) {
		// TODO Auto-generated method stub
		super.onCreate(icicle);

		setContentView(R.layout.friend_map);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapController = mapView.getController();

		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		friendOverlay = new FriendOverlayItems(drawable);
		coder = new Geocoder(this);

		mapOverlays.clear();

		maxResults = 1;

	}

	public class MyLocationListener implements LocationListener {

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onLocationChanged(Location location) {

			DisplayProposedLocations(location);
		}

	}

	@Override
	protected void onResume() {
		// Define a listener that responds to location updates
		locationListener = new MyLocationListener();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				300000, 0, locationListener);
		super.onResume();
	}

	private void DisplayProposedLocations(Location currentLocation) {
		try {

			locationManager.removeUpdates(locationListener);
			
			if (currentLocation != null) {
				double currentLatitude = currentLocation.getLatitude();
				double currentLongitude = currentLocation.getLongitude();

				LocationResult proposedLocationResult = LocationPlacesFinder
						.GetPossibleLocations(currentLatitude, currentLongitude);

				if (proposedLocationResult.getStatus() != null
						&& proposedLocationResult.getStatus() == PlacesStatus.OK) {

					if (proposedLocationResult.getResults() != null
							&& proposedLocationResult.getResults().size() > 0) {

						ArrayList<plotAFriend.PlotAFriendSaver.Model.Location> proposedLocation = proposedLocationResult
								.getResults();
						for (int i = 0; i < proposedLocation.size(); i++) {

							int latitudeE6 = (int) (proposedLocation.get(i)
									.getLatitude() * 1E6);
							int longitudeE6 = (int) (proposedLocation.get(i)
									.getLongitude() * 1E6);

							GeoPoint point = new GeoPoint(latitudeE6,
									longitudeE6);
							OverlayItem overlayitem = new OverlayItem(point,
									proposedLocation.get(i).getName(),
									proposedLocation.get(i).getVicinity());

							friendOverlay.addOverlay(overlayitem);
						}

						mapOverlays.add(friendOverlay);

						GeoPoint currentPoint = new GeoPoint(
								(int) (currentLatitude * 1E6),
								(int) (currentLongitude * 1E6));
						mapController.animateTo(currentPoint);
						mapController.setZoom(16);
						mapView.invalidate();
					} 
					else {
						Toast.makeText(
								this,
								"No results available right now. Please try later.",
								50).show();
					}

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			Toast.makeText(this, "Exception: " + ex.getMessage(), 50).show();
		}
		
		

	}

}

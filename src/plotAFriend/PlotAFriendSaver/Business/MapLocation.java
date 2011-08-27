package plotAFriend.PlotAFriendSaver.Business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import plotAFriend.PlotAFriendSaver.R;
import plotAFriend.PlotAFriendSaver.Model.PlacesStatus;
import plotAFriend.PlotAFriendSaver.Model.Inference.RequestFactory;
import plotAFriend.PlotAFriendSaver.Model.Inference.RequestMaker;
import plotAFriend.PlotAFriendSaver.Model.Logging.SuggestLogger;
import plotAFriend.PlotAFriendSaver.Model.Services.LocalWeatherForecastService;
import plotAFriend.PlotAFriendSaver.Model.Services.LocationResult;
import plotAFriend.PlotAFriendSaver.Model.Services.PlacesFinderService;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
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

public class MapLocation extends MapActivity implements LocationListener {

	
	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawableAndroid;
	Drawable drawableLocation;
	AndroidOverlayItems androidOverlay;
	LocationOverlayItems locationOverlay;
	Geocoder coder;
	int maxResults = 0;

	MapController mapController;
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

		SuggestLogger.getLogger().l("*********SUGGEST APPLICATION STARTS********", this);
		setContentView(R.layout.friend_map);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapController = mapView.getController();

		mapOverlays = mapView.getOverlays();

		drawableAndroid = this.getResources().getDrawable(
				R.drawable.androidmarker);
		androidOverlay = new AndroidOverlayItems(drawableAndroid);

		drawableLocation = this.getResources().getDrawable(R.drawable.location);
		locationOverlay = new LocationOverlayItems(drawableLocation);

		coder = new Geocoder(this);

		mapOverlays.clear();

		maxResults = 1;

		mapController.setZoom(11);

		// Define a listener that responds to location updates
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		String provider = null;
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			provider = LocationManager.GPS_PROVIDER;
		} else {
			Toast.makeText(this, "GPS not available", 3000).show();
		}

		if (provider != null) {
			locationManager.requestLocationUpdates(provider, 1000, 100, this);
		}

	}

	private void DisplayProposedLocations(Location currentLocation) {
		try {

			if (currentLocation != null) {
				double currentLatitude = currentLocation.getLatitude();
				double currentLongitude = currentLocation.getLongitude();

				RequestFactory request = RequestMaker.getRequest(this, "Location");

				PlacesFinderService service = request.getPlacesFinderService();
				LocationResult proposedLocationResult  = service.getPossibleLocations(this, currentLatitude, currentLongitude);

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

							locationOverlay.addOverlay(overlayitem);

						}

						mapOverlays.add(locationOverlay);

						GeoPoint currentPoint = new GeoPoint(
								(int) (currentLatitude * 1E6),
								(int) (currentLongitude * 1E6));

						OverlayItem overlayitem = new OverlayItem(currentPoint,
								"You are here", "You are here...");

						androidOverlay.addOverlay(overlayitem);

						mapOverlays.add(androidOverlay);

						/* Below will draw weather markers */
						//DrawWeatherMarkup();

						mapController.animateTo(currentPoint);
						mapView.invalidate();
					} else {
						Toast.makeText(
								this,
								"No results available right now. Please try later.",
								50).show();
					}

				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			Toast.makeText(this, "Exception: " + ex.getMessage(), 5000).show();
		}
		
		SuggestLogger.getLogger().l("*********SUGGEST APPLICATION ENDS********", this);

	}

	private void DrawWeatherMarkup() {
		// TODO Auto-generated method stub
		try {

			String[] postcodes = { "port louis", "rose belle", "curepipe",
					"tamarin", "quatre bornes",
					"le morne" };

			for (int i = 0; i < postcodes.length; i++) {
				Drawable drawable = LocalWeatherForecastService.GetWeatherDrawable(
						postcodes[i], this);
				AndroidOverlayItems weatherOverlay = new AndroidOverlayItems(
						drawable);

				List<Address> address = coder.getFromLocationName(postcodes[i],
						maxResults);
				if (address != null && address.size() > 0) {
					double latitude = address.get(0).getLatitude();
					double longitude = address.get(0).getLongitude();

					GeoPoint currentPoint = new GeoPoint(
							(int) (latitude * 1E6), (int) (longitude * 1E6));

					OverlayItem overlayitem = new OverlayItem(currentPoint, "",
							"");

					weatherOverlay.addOverlay(overlayitem);
					this.mapOverlays.add(weatherOverlay);
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	public void onProviderEnabled(String provider) {
	}

	public void onProviderDisabled(String provider) {
	}

	@Override
	public void onLocationChanged(Location location) {
		locationManager.removeUpdates(this);
		//Toast.makeText(this, "Lat: "+ location.getLatitude() + "Long: " + location.getLongitude(), 1000);
		
		for(int i = 0; i< 40; i++)
		{
			
			SuggestLogger.getLogger().l("####################TEST "+i+" STARTS #################### ", this);
			DisplayProposedLocations(location);
			SuggestLogger.getLogger().l("####################TEST "+i+" ENDS #################### ", this);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}

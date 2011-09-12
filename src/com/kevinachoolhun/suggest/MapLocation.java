package com.kevinachoolhun.suggest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.kevinachoolhun.algae.contextreader.Logger;
import com.kevinachoolhun.algae.inference.RequestFactory;
import com.kevinachoolhun.algae.inference.RequestMaker;
import com.kevinachoolhun.suggest.R;
import com.kevinachoolhun.suggest.businesslogic.AndroidOverlayItems;
import com.kevinachoolhun.suggest.businesslogic.LocationOverlayItems;
import com.kevinachoolhun.suggest.businesslogic.services.LocalWeatherForecastService;
import com.kevinachoolhun.suggest.businesslogic.services.LocationResult;
import com.kevinachoolhun.suggest.businesslogic.services.PlacesFinderService;
import com.kevinachoolhun.suggest.entity.PlacesStatus;

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

		//Logger.getLogger().l("*********SUGGEST APPLICATION STARTS********", this);
		setContentView(R.layout.friend_map);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(false);
		mapController = mapView.getController();

		mapOverlays = mapView.getOverlays();

		drawableAndroid = this.getResources().getDrawable(
				R.drawable.androidmarker);
		androidOverlay = new AndroidOverlayItems(drawableAndroid);

		drawableLocation = this.getResources().getDrawable(R.drawable.pin_blue);
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
			provider = LocationManager.NETWORK_PROVIDER;

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

				RequestFactory request = RequestMaker.getRequest(this, "Restaurant");

				PlacesFinderService service = request.getPlacesFinderService();
				LocationResult proposedLocationResult  = service.getPossibleLocations(this, currentLatitude, currentLongitude);

				if (proposedLocationResult.getStatus() != null
						&& proposedLocationResult.getStatus() == PlacesStatus.OK) {

					if (proposedLocationResult.getResults() != null
							&& proposedLocationResult.getResults().size() > 0) {

						ArrayList<com.kevinachoolhun.suggest.entity.Location> proposedLocation = proposedLocationResult
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
						DrawWeatherMarkup();

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
		
		//Logger.getLogger().l("*********SUGGEST APPLICATION ENDS********", this);

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
				
				if (drawable != null)
				{
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
			//Logger.getLogger().l("####################TEST  STARTS #################### ", this);
			DisplayProposedLocations(location);
			//Logger.getLogger().l("####################TEST  ENDS #################### ", this);
			
	}

}

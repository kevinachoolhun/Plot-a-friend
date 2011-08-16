package plotAFriend.PlotAFriendSaver;

import java.io.IOException;
import java.util.List;

import plotAFriend.PlotAFriendSaver.Business.AndroidOverlayItems;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


public class FriendMap extends MapActivity
{
	LinearLayout linearLayout;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	AndroidOverlayItems friendOverlay;
	Geocoder coder;
	int maxResults = 0;
	
	MapController controller;
//	@Override
	/*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}
*/

	@Override
	protected void onCreate(Bundle icicle) {
		
		super.onCreate(icicle);
		setContentView(R.layout.friend_map);
			
		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		controller = mapView.getController();
		
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		friendOverlay = new  AndroidOverlayItems(drawable);
		coder = new Geocoder(this);
		
		mapOverlays.clear();
		
		maxResults = 1;
		List<Address> addressList;
		List<Address> addressList2;
		
		try {
			addressList = coder.getFromLocationName("Coquelicot Ave, Montreal, Coromandel, Mauritius", maxResults);
			
			if (addressList != null && addressList.size()>0 /*&& addressList2 != null && addressList2.size()>0*/)
			{
				for(Address addr :addressList)
				{
					int lat = (int)(addr.getLatitude() * 1000000);
					int lon = (int)(addr.getLongitude() * 1000000);
					
					GeoPoint point = new GeoPoint(lat,lon);
				    OverlayItem overlayitem = new OverlayItem(point, "Kervin's here!", "Kevina's Android App");
				    controller.animateTo(point);
				    friendOverlay.addOverlay(overlayitem);
				}
				
				addressList2 = coder.getFromLocationName("Choolun Lane, La Caverne, Vacoas, Mauritius", maxResults);
				if (addressList2 != null && addressList2.size() > 0)
				{
					for(Address addr :addressList2)
					{
						int lat = (int)(addr.getLatitude() * 1000000);
						int lon = (int)(addr.getLongitude() * 1000000);
						
						GeoPoint point = new GeoPoint(lat,lon);
					    OverlayItem overlayitem2 = new OverlayItem(point, "Kevina's here!", "Kevina's Android App");
					    
					    friendOverlay.addOverlay(overlayitem2);
					}
				}
				
				mapOverlays.add(friendOverlay);
				mapView.invalidate();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(this, "IOException: " + e.getMessage(), 50).show();
		}
		
		
	

	}


	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}

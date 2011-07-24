package plotAFriend.PlotAFriendSaver.Business;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import android.widget.Toast;

import plotAFriend.PlotAFriendSaver.Model.Location;
import plotAFriend.PlotAFriendSaver.Model.LocationResult;
import plotAFriend.PlotAFriendSaver.Model.PlacesStatus;

public class LocationPlacesFinder {

	public static LocationResult GetPossibleLocations(double latitude,
			double longitude) {

	
		LocationResult locResult = null;
		try {

			String jsonString = GooglePlacesService.CallGooglePlaces(
					latitude, longitude);
			locResult = JSONXMLParser.ParseGooglePlacesJSON(jsonString);
		

		} catch (Exception ex) {
			ex.printStackTrace();
			//Toast.makeText(this, "IOException: " + ex.getMessage(), 50).show();
		}

		return locResult;

	}

	

}

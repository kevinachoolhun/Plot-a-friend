package plotAFriend.PlotAFriendSaver.Business;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

import plotAFriend.PlotAFriendSaver.Model.Location;
import plotAFriend.PlotAFriendSaver.Model.LocationResult;

public class JSONLocationParser {

	public static ArrayList<Location> GetLocations(String jsonString) {
		
		ArrayList<Location> locationList = null;
		
		try {
			LocationResult locResult = new LocationResult();
			JSONObject jsonObject = new JSONObject(jsonString);

			locResult.setStatus(jsonObject.getString("status"));
			locResult.setHtml_attributions(jsonObject
					.getString("html_attributions"));

			JSONArray locationResults = new JSONArray(
					jsonObject.getString("results"));
			int i;
			
			locationList = new ArrayList<Location>(); 																
			
			for (i = 0; i < locationResults.length(); i++) {
				Location loc = new Location();
				loc.setId(locationResults.getJSONObject(i).getString("id"));
				loc.setName(locationResults.getJSONObject(i).getString("name"));
				loc.setVicinity(locationResults.getJSONObject(i).getString("vicinity"));
				loc.setReference(locationResults.getJSONObject(i).getString("reference"));

				JSONObject geometryObj = locationResults.getJSONObject(i)
						.getJSONObject("geometry");
				JSONObject locationObj = geometryObj.getJSONObject("location");
				loc.setLatitude(locationObj.getDouble("lat"));
				loc.setLongitude(locationObj.getDouble("lng"));

				locationList.add(loc);
			}

		}
		catch (Exception ex) {

		}

		return locationList;

	}
}

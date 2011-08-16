package plotAFriend.PlotAFriendSaver.Model.Services;

import plotAFriend.PlotAFriendSaver.Business.GooglePlacesService;
import plotAFriend.PlotAFriendSaver.Business.JSONXMLParser;

public class LocalPlacesFinderService implements PlacesFinderService{

	public LocationResult getPossibleLocations(Double latitude, Double longitude) {

		LocationResult locResult = null;
		try {

			String jsonString = GooglePlacesService.CallGooglePlaces(latitude,
					longitude);
			locResult = JSONXMLParser.ParseGooglePlacesJSON(jsonString);

		} catch (Exception ex) {
			ex.printStackTrace();
			// Toast.makeText(this, "IOException: " + ex.getMessage(),
			// 50).show();
		}

		return locResult;

	}

}

package plotAFriend.PlotAFriendSaver.Model.Services;

import android.content.Context;
import plotAFriend.PlotAFriendSaver.Business.GooglePlacesService;
import plotAFriend.PlotAFriendSaver.Business.JSONXMLParser;
import plotAFriend.PlotAFriendSaver.Model.Logging.SuggestLogger;

public class LocalPlacesFinderService implements PlacesFinderService{

	public LocationResult getPossibleLocations(Context c, Double latitude, Double longitude) {

		LocationResult locResult = null;
		try {
			SuggestLogger.getLogger().l("Before: Calling GooglePlaces", c);
			String jsonString = GooglePlacesService.CallGooglePlaces(latitude,
					longitude);
			SuggestLogger.getLogger().l("After: Calling GooglePlaces", c);
			
			SuggestLogger.getLogger().l("Before: Parsing GooglePlaces XML", c);
			
			locResult = JSONXMLParser.ParseGooglePlacesJSON(jsonString);
			
			SuggestLogger.getLogger().l("After: Parsing GooglePlaces XML", c);

		} catch (Exception ex) {
			ex.printStackTrace();
			// Toast.makeText(this, "IOException: " + ex.getMessage(),
			// 50).show();
		}

		return locResult;

	}

}

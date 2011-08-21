package plotAFriend.PlotAFriendSaver.Model.Services;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;

import plotAFriend.PlotAFriendSaver.Business.Utilities.Utilities;
import plotAFriend.PlotAFriendSaver.Model.Logging.SuggestLogger;

public class WSPlacesFinderService implements PlacesFinderService {

	@Override
	public LocationResult getPossibleLocations(Context c, Double latitude, Double longitude) {
		LocationResult result = null;

		SuggestLogger.getLogger().l("Before: Calling App Engine for Locations", c);
		String response = Utilities
				.getStringResponse("http://suggest-webservice.appspot.com/location?lat="
						+ latitude.toString() + "&lng=" + longitude.toString());

		try {

			Serializer serializer = new Persister();
			result = serializer.read(LocationResult.class, response, false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SuggestLogger.getLogger().l("After: Calling App Engine for Locations + serializing", c); 
		return result;
	}

}

package plotAFriend.PlotAFriendSaver.Model.Services;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import plotAFriend.PlotAFriendSaver.Business.Utilities.Utilities;

public class WSPlacesFinderService implements PlacesFinderService {

	@Override
	public LocationResult getPossibleLocations(Double latitude, Double longitude) {
		LocationResult result = null;

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
		return result;
	}

}

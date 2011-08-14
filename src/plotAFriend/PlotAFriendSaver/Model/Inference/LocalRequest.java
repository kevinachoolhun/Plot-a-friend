package plotAFriend.PlotAFriendSaver.Model.Inference;

import plotAFriend.PlotAFriendSaver.Business.LocationPlacesFinder;
import plotAFriend.PlotAFriendSaver.Model.LocationResult;
import plotAFriend.PlotAFriendSaver.Model.WeatherResult;

/**
 * Makes a local Request
 * 
 */
public class LocalRequest implements Request {

	@Override
	public WeatherResult getWeatherResult(String postcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocationResult getPossibleLocations(Double lat, Double lng) {
		return LocationPlacesFinder.GetPossibleLocations(lat, lng);
	}

}

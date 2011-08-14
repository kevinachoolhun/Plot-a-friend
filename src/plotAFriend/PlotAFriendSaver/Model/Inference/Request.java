package plotAFriend.PlotAFriendSaver.Model.Inference;

import plotAFriend.PlotAFriendSaver.Model.LocationResult;
import plotAFriend.PlotAFriendSaver.Model.WeatherResult;

public interface Request {

	WeatherResult getWeatherResult(String postcode);
	
	LocationResult getPossibleLocations(Double lat, Double lng);
	
}

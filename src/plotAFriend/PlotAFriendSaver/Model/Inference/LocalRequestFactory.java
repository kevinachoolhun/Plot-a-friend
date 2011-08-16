package plotAFriend.PlotAFriendSaver.Model.Inference;

import plotAFriend.PlotAFriendSaver.Model.Services.LocalPlacesFinderService;
import plotAFriend.PlotAFriendSaver.Model.Services.LocalWeatherForecastService;
import plotAFriend.PlotAFriendSaver.Model.Services.PlacesFinderService;
import plotAFriend.PlotAFriendSaver.Model.Services.WeatherForecastService;

/**
 * Makes a local Request
 * 
 */
public class LocalRequestFactory implements RequestFactory {

	@Override
	public WeatherForecastService getWeatherService() {
		return new LocalWeatherForecastService();
	}

	@Override
	public PlacesFinderService getPlacesFinderService() {
		return new LocalPlacesFinderService();
	}

}

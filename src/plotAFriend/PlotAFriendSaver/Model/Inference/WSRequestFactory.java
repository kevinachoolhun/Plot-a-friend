package plotAFriend.PlotAFriendSaver.Model.Inference;

import plotAFriend.PlotAFriendSaver.Model.Services.PlacesFinderService;
import plotAFriend.PlotAFriendSaver.Model.Services.WSPlacesFinderService;
import plotAFriend.PlotAFriendSaver.Model.Services.WSWeatherForecastService;
import plotAFriend.PlotAFriendSaver.Model.Services.WeatherForecastService;

public class WSRequestFactory implements RequestFactory {

	@Override
	public WeatherForecastService getWeatherService() {
		return new WSWeatherForecastService();

	}

	@Override
	public PlacesFinderService getPlacesFinderService() {
		return new WSPlacesFinderService();
	}

}

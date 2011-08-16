package plotAFriend.PlotAFriendSaver.Model.Inference;

import plotAFriend.PlotAFriendSaver.Model.Services.PlacesFinderService;
import plotAFriend.PlotAFriendSaver.Model.Services.WeatherForecastService;


public interface RequestFactory {

	WeatherForecastService getWeatherService();
	
	PlacesFinderService getPlacesFinderService();
	
}

package plotAFriend.PlotAFriendSaver.Model.Services;

import android.content.Context;

public interface WeatherForecastService {
	WeatherResult getWeatherResult(String postcode, Context c);

}

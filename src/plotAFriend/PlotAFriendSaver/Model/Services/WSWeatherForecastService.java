package plotAFriend.PlotAFriendSaver.Model.Services;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import android.content.Context;

import plotAFriend.PlotAFriendSaver.Business.Utilities.Utilities;
import plotAFriend.PlotAFriendSaver.Model.Logging.SuggestLogger;

public class WSWeatherForecastService implements WeatherForecastService {

	@Override
	public WeatherResult getWeatherResult(String postcode, Context c) {
		WeatherResult result = null;

		SuggestLogger.getLogger().l("Before: Calling App Engine for Weather - " + postcode, c);

		String response = Utilities
				.getStringResponse("http://suggest-webservice.appspot.com/weather?loc="
						+ postcode);

		try {

			Serializer serializer = new Persister();

			result = serializer.read(WeatherResult.class, response, false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SuggestLogger.getLogger().l("After: Calling App Engine for Weather - " + postcode, c);

		return result;
	}

}

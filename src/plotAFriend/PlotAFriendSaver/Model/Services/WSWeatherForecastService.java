package plotAFriend.PlotAFriendSaver.Model.Services;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import plotAFriend.PlotAFriendSaver.Business.Utilities.Utilities;

public class WSWeatherForecastService implements WeatherForecastService {

	@Override
	public WeatherResult getWeatherResult(String postcode) {
		WeatherResult result = null;

		String response = Utilities
				.getStringResponse("http://suggest-webservice/weather?loc="
						+ postcode);

		try {

			Serializer serializer = new Persister();

			result = serializer.read(WeatherResult.class, response, false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}

package plotAFriend.PlotAFriendSaver.Model.Inference;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import plotAFriend.PlotAFriendSaver.Business.Utilities.Utilities;
import plotAFriend.PlotAFriendSaver.Model.LocationResult;
import plotAFriend.PlotAFriendSaver.Model.WeatherResult;

public class WSRequest implements Request {

	@Override
	public WeatherResult getWeatherResult(String postcode) {
		WeatherResult result = null;

		String response = Utilities
				.getStringResponse("http://suggest-webservice/weather?loc="
						+ postcode);

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(WeatherResult.class);
			Unmarshaller um = context.createUnmarshaller();
			result = (WeatherResult) um.unmarshal(new StreamSource(
					new StringReader(response)));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	@Override
	public LocationResult getPossibleLocations(Double lat, Double lng) {
		LocationResult result = null;

		String response = Utilities
				.getStringResponse("http://suggest-webservice/location?lat="
						+ lat.toString() + "&lng=" + lng.toString());

		JAXBContext context;
		try {
			context = JAXBContext.newInstance(WeatherResult.class);
			Unmarshaller um = context.createUnmarshaller();
			result = (LocationResult) um.unmarshal(new StreamSource(
					new StringReader(response)));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
}

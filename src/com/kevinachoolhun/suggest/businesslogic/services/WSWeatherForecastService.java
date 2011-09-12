package com.kevinachoolhun.suggest.businesslogic.services;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.kevinachoolhun.algae.contextreader.Logger;
import com.kevinachoolhun.suggest.businesslogic.utilities.Utilities;

import android.content.Context;


public class WSWeatherForecastService implements WeatherForecastService {

	@Override
	public WeatherResult getWeatherResult(String postcode, Context c) {
		WeatherResult result = null;

		//Logger.getLogger().l("Before: Calling App Engine for Weather - " + postcode, c);

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
		//Logger.getLogger().l("After: Calling App Engine for Weather - " + postcode, c);

		return result;
	}

}

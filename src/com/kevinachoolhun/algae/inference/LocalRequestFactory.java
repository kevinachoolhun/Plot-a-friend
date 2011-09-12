package com.kevinachoolhun.algae.inference;

import com.kevinachoolhun.suggest.businesslogic.services.LocalPlacesFinderService;
import com.kevinachoolhun.suggest.businesslogic.services.LocalWeatherForecastService;
import com.kevinachoolhun.suggest.businesslogic.services.PlacesFinderService;
import com.kevinachoolhun.suggest.businesslogic.services.WeatherForecastService;

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

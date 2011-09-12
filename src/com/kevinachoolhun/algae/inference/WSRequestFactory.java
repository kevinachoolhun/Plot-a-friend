package com.kevinachoolhun.algae.inference;

import com.kevinachoolhun.suggest.businesslogic.services.PlacesFinderService;
import com.kevinachoolhun.suggest.businesslogic.services.WSPlacesFinderService;
import com.kevinachoolhun.suggest.businesslogic.services.WSWeatherForecastService;
import com.kevinachoolhun.suggest.businesslogic.services.WeatherForecastService;

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

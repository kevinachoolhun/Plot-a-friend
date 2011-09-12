package com.kevinachoolhun.algae.inference;

import com.kevinachoolhun.suggest.businesslogic.services.PlacesFinderService;
import com.kevinachoolhun.suggest.businesslogic.services.WeatherForecastService;


public interface RequestFactory {

	WeatherForecastService getWeatherService();
	
	PlacesFinderService getPlacesFinderService();
	
}

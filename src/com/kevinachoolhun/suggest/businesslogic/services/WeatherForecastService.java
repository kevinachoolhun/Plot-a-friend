package com.kevinachoolhun.suggest.businesslogic.services;

import android.content.Context;

public interface WeatherForecastService {
	WeatherResult getWeatherResult(String postcode, Context c);

}

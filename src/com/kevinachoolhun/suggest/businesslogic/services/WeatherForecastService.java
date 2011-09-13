package com.kevinachoolhun.suggest.businesslogic.services;

import com.kevinachoolhun.suggest.entity.WeatherResult;

import android.content.Context;

public interface WeatherForecastService {
	WeatherResult getWeatherResult(String postcode, Context c);

}

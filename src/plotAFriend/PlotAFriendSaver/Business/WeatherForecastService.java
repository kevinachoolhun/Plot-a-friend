package plotAFriend.PlotAFriendSaver.Business;

import android.content.Context;
import android.graphics.drawable.Drawable;
import plotAFriend.PlotAFriendSaver.R;
import plotAFriend.PlotAFriendSaver.Model.WeatherResult;

public class WeatherForecastService {

	public static Drawable GetWeatherDrawable(String postcode, Context context) {
		Drawable drawable = null;
		WeatherResult result = getWeatherResult(postcode);

		if (result != null) {
			if (result.condition != null) {
				String condition = result.condition;

				if (condition.equals("Chance of Rain")) {
					drawable = context.getResources().getDrawable(
							R.drawable.chance_of_rain);
				} else if (condition.equals("Chance of Snow")) {
					drawable = context.getResources().getDrawable(
							R.drawable.chance_of_snow);
				} else if (condition.equals("Chance of Storm")) {
					drawable = context.getResources().getDrawable(
							R.drawable.chance_of_storm);
				} else if (condition.equals("Cloudy")) {
					drawable = context.getResources().getDrawable(
							R.drawable.cloudy);
				} else if (condition.equals("Flurries")) {
					drawable = context.getResources().getDrawable(
							R.drawable.flurries);
				} else if (condition.equals("Fog")) {
					drawable = context.getResources().getDrawable(
							R.drawable.fog);
				} else if (condition.equals("Icy")) {
					drawable = context.getResources().getDrawable(
							R.drawable.icy);
				} else if (condition.equals("Mist")) {
					drawable = context.getResources().getDrawable(
							R.drawable.mist);
				} else if (condition.equals("Mostly Cloudy")) {
					drawable = context.getResources().getDrawable(
							R.drawable.mostly_cloudy);
				} else if (condition.equals("Mostly Sunny")) {
					drawable = context.getResources().getDrawable(
							R.drawable.mostly_sunny);
				} else if (condition.equals("Rain and Snow")) {
					drawable = context.getResources().getDrawable(
							R.drawable.rain_snow);
				} else if (condition.equals("Rain")) {
					drawable = context.getResources().getDrawable(
							R.drawable.rain);
				} else if (condition.equals("Showers")) {
					drawable = context.getResources().getDrawable(
							R.drawable.showers);
				} else if (condition.equals("Sleet")) {
					drawable = context.getResources().getDrawable(
							R.drawable.sleet);
				} else if (condition.equals("Smoke")) {
					drawable = context.getResources().getDrawable(
							R.drawable.smoke);
				} else if (condition.equals("Snow")) {
					drawable = context.getResources().getDrawable(
							R.drawable.snow);
				} else if (condition.equals("Storm")) {
					drawable = context.getResources().getDrawable(
							R.drawable.storm);
				} else if (condition.equals("Sunny")) {
					drawable = context.getResources().getDrawable(
							R.drawable.sunny);
				} else if (condition.equals("Thunderstorm")) {
					drawable = context.getResources().getDrawable(
							R.drawable.thunderstorm);
				} else {
					drawable = context.getResources().getDrawable(
							R.drawable.mostly_sunny);
				}
			}

		}

		return drawable;

	}
	
	public static WeatherResult getWeatherResult(String postcode)
	{
		WeatherResult result = JSONXMLParser
		.ParseGoogleWeatherXML(GoogleWeatherService
				.CallGoogleWeather(postcode));
		
		return result;
	}
	

}

package plotAFriend.PlotAFriendSaver.Model;

import java.util.Date;

public class WeatherResult {

	public Date forecastDate;
	public String condition;
	public int temperatureInCelcius;
	public int humidity;
	public String windCondition;
	
	public Date getForecastDate() {
		return forecastDate;
	}
	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getTemperatureInCelcius() {
		return temperatureInCelcius;
	}
	public void setTemperatureInCelcius(int temperatureInCelcius) {
		this.temperatureInCelcius = temperatureInCelcius;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public String getWindCondition() {
		return windCondition;
	}
	public void setWindCondition(String windCondition) {
		this.windCondition = windCondition;
	}
	
	
}

package plotAFriend.PlotAFriendSaver.Model.Services;

import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
@Root
public class WeatherResult{
@Element(required = false)
	private String city;
@Element(required = false)
	public Date forecastDate;
@Element(required = false)
	public String condition;
@Element(required = false)
	public int temperatureInCelcius;
@Element(required = false)
	public int humidity;
@Element(required = false)
	public String windCondition;
	
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
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

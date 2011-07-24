package plotAFriend.PlotAFriendSaver.Business;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import plotAFriend.PlotAFriendSaver.Model.Location;
import plotAFriend.PlotAFriendSaver.Model.LocationResult;
import plotAFriend.PlotAFriendSaver.Model.WeatherResult;
import plotAFriend.PlotAFriendSaver.Business.Utilities.Utilities;

public class JSONXMLParser {

	public static LocationResult ParseGooglePlacesJSON(String jsonString) {
		LocationResult locResult = null;

		try {

			if (jsonString != null || jsonString != "") {

				locResult = new LocationResult();
				JSONObject jsonObject = new JSONObject(jsonString);

				locResult.setStatus(Utilities
						.GetPlacesStatusFromString(jsonObject
								.getString("status")));
				locResult.setHtml_attributions(jsonObject
						.getString("html_attributions"));

				JSONArray locationResults = new JSONArray(
						jsonObject.getString("results"));
				int i;

				ArrayList<Location> locationList = new ArrayList<Location>();

				for (i = 0; i < locationResults.length(); i++) {
					Location loc = new Location();
					loc.setId(locationResults.getJSONObject(i).getString("id"));
					loc.setName(locationResults.getJSONObject(i).getString(
							"name"));
					loc.setVicinity(locationResults.getJSONObject(i).getString(
							"vicinity"));
					loc.setReference(locationResults.getJSONObject(i)
							.getString("reference"));

					JSONObject geometryObj = locationResults.getJSONObject(i)
							.getJSONObject("geometry");
					JSONObject locationObj = geometryObj
							.getJSONObject("location");
					loc.setLatitude(locationObj.getDouble("lat"));
					loc.setLongitude(locationObj.getDouble("lng"));

					locationList.add(loc);
				}

				locResult.setResults(locationList);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}

		return locResult;

	}

	public static void ParseGoogleWeatherXML(String XML) {

		if (XML != null || XML != "") {
			
			
			

			try {
				//get the factory
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

				//Using factory get an instance of document builder
				DocumentBuilder db = dbf.newDocumentBuilder();

				//parse using builder to get DOM representation of the XML file
				Document dom = db.parse("XML");
					
				//get the root element
				Element docEle = dom.getDocumentElement();

				WeatherResult weather = new WeatherResult();
				//get a nodelist of elements
				
				
				
				NodeList nl = docEle.getElementsByTagName("weather");
				if(nl != null && nl.getLength() == 1) {
					
				
					

					
				}

			}catch(ParserConfigurationException pce) {
				pce.printStackTrace();
			}catch(SAXException se) {
				se.printStackTrace();
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}
			
		}
	}

}

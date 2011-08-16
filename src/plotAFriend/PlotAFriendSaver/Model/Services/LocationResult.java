package plotAFriend.PlotAFriendSaver.Model.Services;

import java.util.ArrayList;

import plotAFriend.PlotAFriendSaver.Model.Location;
import plotAFriend.PlotAFriendSaver.Model.PlacesStatus;

public class LocationResult {
	
	String html_attributions;
	ArrayList<Location> results;
	PlacesStatus status;
	
	public String getHtml_attributions() {
		return html_attributions;
	}
	public void setHtml_attributions(String html_attributions) {
		this.html_attributions = html_attributions;
	}
	public ArrayList<Location> getResults() {
		return results;
	}
	public void setResults(ArrayList<Location> results) {
		this.results = results;
	}
	public PlacesStatus getStatus() {
		return status;
	}
	public void setStatus(PlacesStatus placesStatus) {
		this.status = placesStatus;
	}


}

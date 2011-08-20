package plotAFriend.PlotAFriendSaver.Model.Services;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import plotAFriend.PlotAFriendSaver.Model.Location;
import plotAFriend.PlotAFriendSaver.Model.PlacesStatus;

@Root(name="locationResult")
public class LocationResult {
	
	@Element(required=false)
	String html_attributions;
	
	@ElementList(inline=true, required=false)
	ArrayList<Location> results;
	
	@Element(required=false)
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

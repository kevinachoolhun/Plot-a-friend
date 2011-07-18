package plotAFriend.PlotAFriendSaver.Model;

import java.util.ArrayList;
import java.util.List;

public class LocationResult {
	
	String html_attributions;
	ArrayList<Location> results;
	String status;
	
	public String getHtml_attributions() {
		return html_attributions;
	}
	public void setHtml_attributions(String html_attributions) {
		this.html_attributions = html_attributions;
	}
	public List<Location> getResults() {
		return results;
	}
	public void setResults(ArrayList<Location> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}

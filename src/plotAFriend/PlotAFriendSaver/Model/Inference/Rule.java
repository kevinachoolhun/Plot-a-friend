package plotAFriend.PlotAFriendSaver.Model.Inference;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
@Root(name="rule")
public class Rule {
	@Element(required=false)
	private String service;

	@Element(required=false)
	private String connection;

	@Element(required=false)
	private String battery;

	public void setService(String service) {
		this.service = service;
	}

	public String getService() {
		return service;
	}

	public void setConnection(String connection) {
		this.connection = connection;
	}

	public Double getConnection() {
		return Double.valueOf(connection);
	}

	public void setBattery(String battery) {
		this.battery = battery;
	}

	public Double getBattery() {
		return Double.valueOf(battery);
	}

}

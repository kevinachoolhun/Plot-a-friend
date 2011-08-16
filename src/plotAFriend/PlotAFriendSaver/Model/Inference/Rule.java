package plotAFriend.PlotAFriendSaver.Model.Inference;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Rule {
	@Element
	private String name;

	@Element
	private String connection;

	@Element
	private String battery;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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

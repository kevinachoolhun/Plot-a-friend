package plotAFriend.PlotAFriendSaver.Model;


import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
@Root(name="results")
public class Location {
	@Element(required=false)
	String id;
	@Element(required=false)
	String name;
	@Element(required=false)
	String vicinity;
	@Element(required=false)
	double latitude;
	@Element(required=false)
	double longitude;
	@Element(required=false)
	String reference;
	@Element(required=false)
	List<Type> types;
	
	public String getVicinity() {
		return vicinity;
	}
	
	public void setVicinity(String vicinity) {
		this.vicinity = vicinity;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getReference() {
		return reference;
	}
	
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	public List<Type> getTypes() {
		return types;
	}
	
	public void setTypes(List<Type> types) {
		this.types = types;
	}

	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}

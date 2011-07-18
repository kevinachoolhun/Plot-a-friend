package plotAFriend.PlotAFriendSaver.Model;


import java.util.List;

public class Location {
	
	String id;
	String name;
	String vicinity;
	double latitude;
	double longitude;
	String reference;
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

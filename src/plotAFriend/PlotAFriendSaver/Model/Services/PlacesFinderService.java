package plotAFriend.PlotAFriendSaver.Model.Services;

public interface PlacesFinderService {
	public LocationResult getPossibleLocations(Double latitude, Double longitude);
}
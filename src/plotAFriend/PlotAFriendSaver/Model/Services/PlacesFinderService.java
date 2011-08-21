package plotAFriend.PlotAFriendSaver.Model.Services;

import android.content.Context;

public interface PlacesFinderService {
	public LocationResult getPossibleLocations(Context c, Double latitude, Double longitude);
}
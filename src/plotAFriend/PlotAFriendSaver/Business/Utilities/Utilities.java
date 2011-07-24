package plotAFriend.PlotAFriendSaver.Business.Utilities;

import plotAFriend.PlotAFriendSaver.Model.PlacesStatus;

public class Utilities {

	
	public static PlacesStatus GetPlacesStatusFromString(String status) {
		
		PlacesStatus statusEnum = PlacesStatus.NONE;

		if (status == "OK") {
			statusEnum = PlacesStatus.OK;
		} else if (status == "ZERO_RESULTS") {
			statusEnum = PlacesStatus.ZERO_RESULTS;
		} else if (status == "OVER_QUERY_LIMIT") {
			statusEnum = PlacesStatus.OVER_QUERY_LIMIT;
		} else if (status == "REQUEST_DENIED") {
			statusEnum = PlacesStatus.REQUEST_DENIED;
		} else if (status == "INVALID_REQUEST") {
			statusEnum = PlacesStatus.INVALID_REQUEST;
		}

		return statusEnum;
	}
}

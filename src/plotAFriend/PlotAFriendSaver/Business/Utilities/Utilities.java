package plotAFriend.PlotAFriendSaver.Business.Utilities;

import plotAFriend.PlotAFriendSaver.Model.PlacesStatus;

public class Utilities {

	
	public static PlacesStatus GetPlacesStatusFromString(String status) {
		
		PlacesStatus statusEnum = PlacesStatus.NONE;

		if (status.equals("OK")) {
			statusEnum = PlacesStatus.OK;
		} else if (status.equals("ZERO_RESULTS")) {
			statusEnum = PlacesStatus.ZERO_RESULTS;
		} else if (status.equals("OVER_QUERY_LIMIT")) {
			statusEnum = PlacesStatus.OVER_QUERY_LIMIT;
		} else if (status.equals( "REQUEST_DENIED")) {
			statusEnum = PlacesStatus.REQUEST_DENIED;
		} else if (status.equals( "INVALID_REQUEST")) {
			statusEnum = PlacesStatus.INVALID_REQUEST;
		}

		return statusEnum;
	}
}

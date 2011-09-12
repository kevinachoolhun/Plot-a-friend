package com.kevinachoolhun.suggest.businesslogic.services;

import android.content.Context;

public interface PlacesFinderService {
	public LocationResult getPossibleLocations(Context c, Double latitude, Double longitude);
}
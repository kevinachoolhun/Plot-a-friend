package com.kevinachoolhun.suggest.businesslogic.services;

import com.kevinachoolhun.algae.contextreader.Logger;
import com.kevinachoolhun.suggest.businesslogic.JSONXMLParser;
import com.kevinachoolhun.suggest.datalayer.GooglePlacesService;
import com.kevinachoolhun.suggest.entity.LocationResult;

import android.content.Context;

public class LocalPlacesFinderService implements PlacesFinderService{

	public LocationResult getPossibleLocations(Context c, Double latitude, Double longitude) {

		LocationResult locResult = null;
		try {
			//Logger.getLogger().l("Before: Calling GooglePlaces", c);
			String jsonString = GooglePlacesService.CallGooglePlaces(latitude,
					longitude);
			//Logger.getLogger().l("After: Calling GooglePlaces", c);
			
			//Logger.getLogger().l("Before: Parsing GooglePlaces XML", c);
			
			locResult = JSONXMLParser.ParseGooglePlacesJSON(jsonString);
			
			//Logger.getLogger().l("After: Parsing GooglePlaces XML", c);

		} catch (Exception ex) {
			ex.printStackTrace();
			// Toast.makeText(this, "IOException: " + ex.getMessage(),
			// 50).show();
		}

		return locResult;

	}

}

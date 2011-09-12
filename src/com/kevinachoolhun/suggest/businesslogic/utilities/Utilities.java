package com.kevinachoolhun.suggest.businesslogic.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;

import com.kevinachoolhun.suggest.entity.PlacesStatus;



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
	
	public static String convertStreamToString(InputStream is) throws IOException {
		/*
		 * To convert the InputStream to String we use the Reader.read(char[]
		 * buffer) method. We iterate until the Reader return -1 which means
		 * there's no more data to read. We use the StringWriter class to
		 * produce the string.
		 */
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}
	}
	
	
	/**
	 * Gets the response when for plain/text response
	 * 
	 * @param address
	 *            url of the site
	 * @return string
	 */
	public static String getStringResponse(String address) {

		String response = new String();
		try {
			URL url = new URL(address.replace(" ", "%20"));;
			Object stream = url.getContent();
			InputStream bais = (InputStream) stream;
			response = Utilities.convertStreamToString(bais);


		} catch (Exception ex) {
			response = ex.getMessage();
		}

		return response;
	}
}

package com.kevinachoolhun.suggest.datalayer;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class GoogleWeatherService {

	public static InputStream CallGoogleWeather(String location)
	{
		String str;
		InputStream streamFromWS = null;
		try
		{
		String webserviceUrl = "http://www.google.com/ig/api?weather=";
		
		String urlWithPostalCode = webserviceUrl.concat(location);
		
		//URL myURL = new URL(urlWithPostalCode);
		 URL myURL = new URL(urlWithPostalCode.replace(" ", "%20"));
		
		 HttpGet httpRequest = null;

         httpRequest = new HttpGet(myURL.toURI());

         HttpClient httpclient = new DefaultHttpClient();
         HttpResponse response = (HttpResponse) httpclient.execute(httpRequest);

         HttpEntity entity = response.getEntity();
         BufferedHttpEntity bufHttpEntity = new BufferedHttpEntity(entity);
         streamFromWS = bufHttpEntity.getContent();
        
		
		} 
		catch(Exception ex)
		{
			ex.printStackTrace();
			str = ex.getMessage();
			
		}
		
		return streamFromWS;
		
	}
}

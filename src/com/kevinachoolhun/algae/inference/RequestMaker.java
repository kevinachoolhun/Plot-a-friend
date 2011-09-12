package com.kevinachoolhun.algae.inference;

import android.content.Context;


public class RequestMaker {

	public static RequestFactory getRequest(Context c, String service) {
		Agent agent = new Agent(c, service);
		
	
		if (agent.useLocal()) {
			return new LocalRequestFactory();
		} else {
			return new WSRequestFactory();
		}
	}

}

package com.kevinachoolhun.algae.inference;

import org.simpleframework.xml.Element;

public class Stat {
	
	@Element(required=false)
	private Usage wifi;
	
	@Element(required=false)
	private Usage mobile;

	public void setWifi(Usage wifi) {
		this.wifi = wifi;
	}

	public Usage getWifi() {
		return wifi;
	}

	public void setMobile(Usage mobile) {
		this.mobile = mobile;
	}

	public Usage getMobile() {
		return mobile;
	}
	
}

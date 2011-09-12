package com.kevinachoolhun.algae.inference;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
@Root(name="rule")
public class Rule {
	@Element(required=false)
	private String service;

	@Element(required=false)
	private BatteryUsage batteryUsage;
	
	public void setService(String service) {
		this.service = service;
	}

	public String getService() {
		return service;
	}

	public void setBatteryUsage(BatteryUsage batteryUsage) {
		this.batteryUsage = batteryUsage;
	}

	public BatteryUsage getBatteryUsage() {
		return batteryUsage;
	}

}

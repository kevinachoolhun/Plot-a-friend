package com.kevinachoolhun.algae.inference;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.kevinachoolhun.algae.contextreader.BatteryLevelMonitor;
import com.kevinachoolhun.algae.contextreader.ConnectivityMonitor;
import com.kevinachoolhun.algae.contextreader.ConnectivityType;


import android.content.Context;

public class Agent {

	private Boolean useLocal;
	
	private Rules getAllRules(Context c) {
		Rules rules = null;

		try {
			Serializer serializer = new Persister();
			rules = serializer.read(Rules.class, c.getAssets().open("rules.xml"), false);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rules;
	}
	
	private Rule getRule(Context c, String serviceName) {
		Rules rules = this.getAllRules(c);
		Rule rule = rules.getServiceRule(serviceName);
		return rule;
	}
	
	
	
	public Agent(Context c, String serviceName) {
		
		// Get rules and configurations
		Rules rules = this.getAllRules(c);
		
		// Get rule for this service
		Rule serviceRule = rules.getServiceRule(serviceName);
	
		if (rules.getSaveBattery()) {
			// Save Battery
			
			// Wifi
			if (ConnectivityMonitor.getInstance().getConnectivity(c) == ConnectivityType.WIFI) {
				BatteryUsage usage = serviceRule.getBatteryUsage();
				
				if (usage.getLocal().getWifi().getEnergy() < usage.getCloud().getWifi().getEnergy()) {
					this.useLocal = true;
				} else {
					this.useLocal = false;
				}
			}
			
			// Mobile
			if (ConnectivityMonitor.getInstance().getConnectivity(c) == ConnectivityType.MOBILE) {
				BatteryUsage usage = serviceRule.getBatteryUsage();
				
				if (usage.getLocal().getMobile().getEnergy() < usage.getCloud().getMobile().getEnergy()) {
					this.useLocal = true;
				} else { 
					this.useLocal = false;
				}
			}
			
		} else {
			// Fast Response time
			
			// Wifi
			if (ConnectivityMonitor.getInstance().getConnectivity(c) == ConnectivityType.WIFI) {
				BatteryUsage usage = serviceRule.getBatteryUsage();
				
				if (usage.getLocal().getWifi().getResponse() < usage.getCloud().getWifi().getResponse()) {
					this.useLocal = true;
				} else {
					this.useLocal = false;
				}
			}
			
			// Mobile
			if (ConnectivityMonitor.getInstance().getConnectivity(c) == ConnectivityType.MOBILE) {
				BatteryUsage usage = serviceRule.getBatteryUsage();
				
				if (usage.getLocal().getMobile().getResponse() < usage.getCloud().getMobile().getResponse()) {
					this.useLocal = true;
				} else { 
					this.useLocal = false;
				}
			}
			
			
		}

	}

	public Boolean useLocal() {
		return useLocal;
	}
}

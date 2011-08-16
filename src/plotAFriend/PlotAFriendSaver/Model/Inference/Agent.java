package plotAFriend.PlotAFriendSaver.Model.Inference;

import java.io.File;
import java.util.ArrayList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;


import android.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

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
	
	private Double getBattery(Context c) {
		return Double.valueOf("20");
	}
	
	private Double getConnectivity(Context c) {
		return Double.valueOf("10");
	}
	
	public Agent(Context c, String serviceName) {
		
		// get xml for this service
		Rule rule = this.getRule(c, serviceName);
		
		// depending on the context of the device

		// compare context with rules for the passed service
		if (rule.getBattery() > this.getBattery(c)) {
			useLocal &= true;
		}
		
		if (rule.getConnection() > this.getConnectivity(c)) {
			useLocal &= true;
		}
	}

	public Boolean useLocal() {
		return useLocal;
	}
}

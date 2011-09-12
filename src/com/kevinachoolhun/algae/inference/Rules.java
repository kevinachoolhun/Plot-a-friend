package com.kevinachoolhun.algae.inference;

import java.util.ArrayList;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="rules")
public class Rules {

	@ElementList(inline=true)
	private ArrayList<Rule> rule;

	@Element(required=false)
	private Boolean saveBattery;
	
	@Element(required=false)
	private Boolean quickResponse;

	public void setSaveBattery(Boolean saveBattery) {
		this.saveBattery = saveBattery;
	}

	public Boolean getSaveBattery() {
		return saveBattery;
	}

	public void setQuickResponse(Boolean quickResponse) {
		this.quickResponse = quickResponse;
	}

	public Boolean getQuickResponse() {
		return quickResponse;
	}
	
	
	public void setRules(ArrayList<Rule> rule) {
		this.rule = rule;
	}

	public ArrayList<Rule> getRules() {
		return rule;
	}

	public Rule getServiceRule(String serviceName) {
		
		for (Rule rule : this.rule) {
			if (rule.getService().equals(serviceName)) {
				return rule;
			}
		}
		
		return null;
	}


}

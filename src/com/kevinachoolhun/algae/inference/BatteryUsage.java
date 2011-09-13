package com.kevinachoolhun.algae.inference;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="batteryUsage")
public class BatteryUsage {

	@Element(required=false)
	private Stat local;
	
	@Element(required=false)
	private Stat cloud;

	public void setLocal(Stat local) {
		this.local = local;
	}

	public Stat getLocal() {
		return local;
	}

	public void setCloud(Stat cloud) {
		this.cloud = cloud;
	}

	public Stat getCloud() {
		return cloud;
	}
}

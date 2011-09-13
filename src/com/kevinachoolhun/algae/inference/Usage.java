package com.kevinachoolhun.algae.inference;

import org.simpleframework.xml.Element;

public class Usage {

	@Element(required=false)
	private Double energy;
	
	@Element(required=false)
	private Double response;

	public void setEnergy(Double energy) {
		this.energy = energy;
	}

	public Double getEnergy() {
		return energy;
	}

	public void setResponse(Double response) {
		this.response = response;
	}

	public Double getResponse() {
		return response;
	}
	
}

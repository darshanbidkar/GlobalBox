package com.globalbox.model.onedrive;

import java.util.HashMap;
import java.util.Map;

public class SharedWith {

	private String access;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The access
	 */
	public String getAccess() {
		return access;
	}

	/**
	 * 
	 * @param access
	 *            The access
	 */
	public void setAccess(String access) {
		this.access = access;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
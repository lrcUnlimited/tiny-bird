package com.tiny.beans;

/**
 * 属性值，通过setter注入
 * 
 * @author li
 * 
 */
public class PropertyValue {
	private final String propertyName;
	private final Object propertyValue;

	public PropertyValue(String propertyName, Object propertyValue) {
		super();
		this.propertyName = propertyName;
		this.propertyValue = propertyValue;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public Object getPropertyValue() {
		return propertyValue;
	}

}

package com.tiny.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {
	private final List<PropertyValue> list = new ArrayList<PropertyValue>();

	public void add(PropertyValue val) {
		list.add(val);

	}

	public List<PropertyValue> getList() {
		return list;
	}

}

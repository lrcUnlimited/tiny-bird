package com.tiny.beans;

import java.util.HashMap;
import java.util.Map;

import com.tiny.beans.io.ResourceLoader;

/**
 * BeanDefination容器
 * 
 * @author li
 * 
 */
public abstract class AbstractBeanDefinationReader implements
		BeanDefinationReader {

	private Map<String, BeanDefination> registry;

	private ResourceLoader resourceLoader;

	protected AbstractBeanDefinationReader(ResourceLoader resourceLoader) {
		this.registry = new HashMap<String, BeanDefination>();
		this.resourceLoader = resourceLoader;
	}

	public Map<String, BeanDefination> getRegistry() {
		return registry;
	}

	public ResourceLoader getResourceLoader() {
		return resourceLoader;
	}

}

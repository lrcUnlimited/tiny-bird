package com.tiny.beans;

public class BeanDefination {
	private Object bean;
	private String beanClassName;
	private PropertyValues propertyValues = new PropertyValues();
	private Class beanClass;

	public BeanDefination() {

	}

	public String getBeanClassName() {
		return beanClassName;
	}

	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
		try {
			Class beanClass = Class.forName(beanClassName);
			setBeanClass(beanClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public PropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(PropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

}

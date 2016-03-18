package com.tiny.beans.xml;

import java.util.Map;

import com.tiny.beans.BeanDefination;
import com.tiny.beans.io.ResourceLoader;

public class XmlBeanDefinationTest {
	public static void main(String[] args) {
		XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader(
				new ResourceLoader());
		try {
			xmlBeanDefinationReader.loadBeanDefination("tinybird.xml");
			for (Map.Entry<String, BeanDefination> entry : xmlBeanDefinationReader
					.getRegistry().entrySet()) {
				System.out.println(entry.getKey());
				System.out.println(entry.getValue().getPropertyValues());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

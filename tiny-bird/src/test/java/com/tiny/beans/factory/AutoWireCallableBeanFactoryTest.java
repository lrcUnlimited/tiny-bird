package com.tiny.beans.factory;

import java.util.Map;
import java.util.Map.Entry;

import com.tiny.HelloWorldService;
import com.tiny.HelloWorldServiceImpl;
import com.tiny.OutputService;
import com.tiny.beans.BeanDefination;
import com.tiny.beans.io.ResourceLoader;
import com.tiny.beans.xml.XmlBeanDefinationReader;

public class AutoWireCallableBeanFactoryTest {
	public static void main(String[] args) {
		XmlBeanDefinationReader xmlBeanDefinationReader = new XmlBeanDefinationReader(
				new ResourceLoader());
		AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
		try {
			xmlBeanDefinationReader.loadBeanDefination("tinybird.xml");
			for (Entry<String, BeanDefination> entry : xmlBeanDefinationReader
					.getRegistry().entrySet()) {
				beanFactory.registerBeanDefination(entry.getKey(),
						entry.getValue());
			}
			HelloWorldServiceImpl helloWorldService = (HelloWorldServiceImpl) beanFactory
					.getBean("helloWorldService");
			HelloWorldServiceImpl helloWorldService1 = (HelloWorldServiceImpl) beanFactory
					.getBean("helloWorldService");
			if(helloWorldService==helloWorldService1){
				System.out.println("true");
			}

			helloWorldService.helloWorld();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

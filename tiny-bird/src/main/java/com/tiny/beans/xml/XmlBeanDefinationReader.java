package com.tiny.beans.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tiny.BeanReference;
import com.tiny.beans.AbstractBeanDefinationReader;
import com.tiny.beans.BeanDefination;
import com.tiny.beans.PropertyValue;
import com.tiny.beans.io.ResourceLoader;

public class XmlBeanDefinationReader extends AbstractBeanDefinationReader {
	private ResourceLoader resourceLoader;

	public XmlBeanDefinationReader(ResourceLoader resourceLoader) {
		super(resourceLoader);
		this.resourceLoader = resourceLoader;
	}

	public void loadBeanDefination(String location) throws Exception {
		InputStream inputStream = resourceLoader.getResource(location)
				.getInputStream();
		doLoadBeanDefination(inputStream);

	}

	/**
	 * 解析xml文件
	 * 
	 * @param inputStream
	 * @throws IOException
	 * @throws SAXException
	 */

	public void doLoadBeanDefination(InputStream inputStream) throws Exception {
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		Document document = null;

		// DOM parser instance
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		// parse an XML file into a DOM tree
		document = builder.parse(inputStream);
		registerBeanDefination(document);

	}

	/**
	 * 解析元素
	 * 
	 * @param document
	 * @throws Exception
	 */
	protected void registerBeanDefination(Document document) throws Exception {
		Element rootElement = document.getDocumentElement();
		parseBeanDefinnation(rootElement);
	}

	/**
	 * 获取节点
	 * 
	 * @param root
	 * @throws Exception
	 */
	protected void parseBeanDefinnation(Element root) throws Exception {
		NodeList nodeList = root.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				Element ele = (Element) node;
				processBeanDefination(ele);
			}
		}

	}

	/**
	 * 解析节点
	 * 
	 * @param ele
	 * @throws Exception
	 */

	protected void processBeanDefination(Element ele) throws Exception {
		String name = ele.getAttribute("id");
		String beanClassName = ele.getAttribute("class");
		BeanDefination beanDefination = new BeanDefination();
		beanDefination.setBeanClassName(beanClassName);
		processPropertyValues(ele, beanDefination);
		getRegistry().put(name, beanDefination);// 将解析好的BeanDefination放入到map中
	}

	/**
	 * 解析节点的property属性
	 * 
	 * @param ele
	 * @param beanDefination
	 */

	protected void processPropertyValues(Element ele,
			BeanDefination beanDefination) {
		NodeList nodeList = ele.getElementsByTagName("property");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Element el = (Element) nodeList.item(i);
			String name = el.getAttribute("name");
			if (el.hasAttribute("value")) {
				String value = el.getAttribute("value");
				PropertyValue propertyValue = new PropertyValue(name, value);
				beanDefination.getPropertyValues().add(propertyValue);
			}
			if (el.hasAttribute("ref")) {
				String ref = el.getAttribute("ref");
				BeanReference beanReference = new BeanReference();
				beanReference.setName(ref);
				PropertyValue propertyValue = new PropertyValue(name,
						beanReference);
				beanDefination.getPropertyValues().add(propertyValue);
			}

		}

	}

}

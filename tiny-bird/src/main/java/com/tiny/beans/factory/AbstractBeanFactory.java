package com.tiny.beans.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.tiny.beans.BeanDefination;
import com.tiny.beans.BeanPostProcessor;

public abstract class AbstractBeanFactory implements BeanFactory {
	private ConcurrentHashMap<String, BeanDefination> map = new ConcurrentHashMap<String, BeanDefination>();// BeanDefination容器

	private List<String> beanDefinationNames = new ArrayList<String>();// 保存所有BeanDefination的名称
	private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();// bean声明周期事件

	public Object getBean(String beanName) throws Exception {
		BeanDefination beanDefination = map.get(beanName);
		if (beanDefination == null) {
			throw new IllegalArgumentException(beanName + " is not exist");
		}
		Object bean = beanDefination.getBean();
		if (bean == null) {
			bean = doCreateBean(beanDefination);// 创建bean的实例，并根据配置文件的内容进行依赖注入
			bean = initializeBean(bean, beanName);// 添加初始化方法，并添加初始化前事件以及初始化后事件
			beanDefination.setBean(bean);
		}

		return bean;
	}

	/**
	 * 
	 * @param bean
	 * @param beanName
	 * @return
	 * @throws Exception
	 */

	protected Object initializeBean(Object bean, String beanName)
			throws Exception {
		// 初始化前的事件
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessBeforeInitialization(bean,
					beanName);
		}
		// TODO call initialize method
		for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
			bean = beanPostProcessor.postProcessAfterInitialization(bean,
					beanName);
		}
		return bean;

	}

	/**
	 * 获取bean的实例
	 * 
	 * @param beanDefination
	 * @return
	 * @throws Exception
	 */

	public Object doCreateBeanInstance(BeanDefination beanDefination)
			throws Exception {
		return beanDefination.getBeanClass().newInstance();

	}

	/**
	 * 创建bean的实例，并根据配置文件的内容进行依赖注入
	 * 
	 * @param beanDefination
	 * @return
	 * @throws Exception
	 */

	public Object doCreateBean(BeanDefination beanDefination) throws Exception {
		Object bean = doCreateBeanInstance(beanDefination);
		beanDefination.setBean(bean);
		applyPropertyValues(bean, beanDefination);
		return bean;

	}

	/**
	 * 根据配置文件中的内容通过setter进行依赖注入
	 * 
	 * @param bean
	 * @param beanDefination
	 * @throws Exception 
	 */

	protected void applyPropertyValues(Object bean,
			BeanDefination beanDefination) throws Exception {

	}

	protected void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
		beanPostProcessors.add(beanPostProcessor);
	}

	/**
	 * 向map中注册BeanDefination
	 * 
	 * @param name
	 * @param beanDefination
	 */

	protected void registerBeanDefination(String name,
			BeanDefination beanDefination) {
		beanDefinationNames.add(name);
		map.put(name, beanDefination);

	}

}

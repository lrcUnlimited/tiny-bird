package com.tiny.beans.factory;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.util.List;

import com.tiny.BeanReference;
import com.tiny.beans.BeanDefination;
import com.tiny.beans.PropertyValue;
import com.tiny.beans.PropertyValues;

public class AutowireCapableBeanFactory extends AbstractBeanFactory {
	protected void applyPropertyValues(Object bean,
			BeanDefination beanDefination) throws Exception {
		PropertyValues propertyValues = beanDefination.getPropertyValues();
		List<PropertyValue> list = propertyValues.getList();
		for (PropertyValue propertyValue : list) {
			Object value = propertyValue.getPropertyValue();
			String name = propertyValue.getPropertyName();
			if (value instanceof BeanReference) {
				BeanReference beanReference = (BeanReference) value;
				String beanName = beanReference.getName();
				value = getBean(beanName);
			}
			try {
				@SuppressWarnings("unchecked")
				Method method = beanDefination.getBeanClass()
						.getDeclaredMethod(getMethodName(name),
								value.getClass());
				method.setAccessible(true);
				method.invoke(bean, value);
			} catch (NoSuchMethodException e) {
				// why
				Field declaredField = bean.getClass().getDeclaredField(
						propertyValue.getPropertyName());
				declaredField.setAccessible(true);
				declaredField.set(bean, value);
			}

		}

	}

	private String getMethodName(String name) {
		StringBuffer methodName = new StringBuffer("set");
		methodName.append(name.substring(0, 1).toUpperCase());
		methodName.append(name.substring(1));
		return methodName.toString();

	}

}

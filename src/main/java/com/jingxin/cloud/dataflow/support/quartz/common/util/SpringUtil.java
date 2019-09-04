package com.jingxin.cloud.dataflow.support.quartz.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring 工具类,用于取出在spring容器中的对象
 * @author cyh
 *
 */

@Component
public class SpringUtil implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 在Spring容器取对象
	 * @param beanId
	 * @return
	 * @throws BeansException
	 */
	public static Object getBean(String beanId)throws BeansException{
		return applicationContext.getBean(beanId);
	}
	
	public static<T> T getBean(Class<T> cls)throws BeansException{
		return applicationContext.getBean(cls);
	}
}

package com.jingxin.cloud.dataflow.support.quartz.common;

import org.quartz.impl.JobDetailImpl;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

import com.jingxin.cloud.dataflow.support.quartz.common.ClusterQuartzSpringJobBean.ClusterStatefulQuartzSpringJobBean;

public class ClusterQuartzSpringJobFactoryBean extends JobDetailFactoryBean{

	private boolean concurrent = false;
	
	public void afterPropertiesSet() {
		this.setJobClass(ClusterStatefulQuartzSpringJobBean.class);
		super.afterPropertiesSet();
		JobDetailImpl jdi = (JobDetailImpl) this.getObject();
		if (concurrent) 
			jdi.setJobClass(ClusterQuartzSpringJobBean.class);
	}
	
	public void setConcurrent(boolean concurrent) {
		this.concurrent = concurrent;
	}
}

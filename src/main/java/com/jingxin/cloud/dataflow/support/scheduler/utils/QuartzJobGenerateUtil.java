package com.jingxin.cloud.dataflow.support.scheduler.utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import com.jingxin.cloud.dataflow.support.quartz.common.ClusterQuartzSpringJobFactoryBean;

public class QuartzJobGenerateUtil {

	public static QuartzJobInfo quartzCornJobFactoryBean(String name, String target, String method, String cron,
			Map<String, Object> params) throws ParseException {
		ClusterQuartzSpringJobFactoryBean jobFactory = new ClusterQuartzSpringJobFactoryBean();
		jobFactory.setDurability(true);
		jobFactory.setRequestsRecovery(true);
		jobFactory.setConcurrent(false);
		jobFactory.setName(name);
		Map<String, Object> jobDataMap = new HashMap<String, Object>();
		jobDataMap.put("targetObject", target);
		jobDataMap.put("targetMethod", method);
		if (params != null) {
			for (Entry<String, Object> param : params.entrySet()) {
				jobDataMap.put(param.getKey(), param.getValue());
			}
		}
		jobFactory.setJobDataAsMap(jobDataMap);
		jobFactory.afterPropertiesSet();
		CronTriggerFactoryBean cronTriggerFactory = new CronTriggerFactoryBean();
		cronTriggerFactory.setName(name.concat("_trigger"));
		cronTriggerFactory.setCronExpression(cron);
		cronTriggerFactory.afterPropertiesSet();
		
		return new QuartzJobInfo(jobFactory.getObject(), cronTriggerFactory.getObject());
	}
	
	public static class QuartzJobInfo {
		
		private JobDetail jobDetail;
		private CronTrigger cronTrigger;
		
		public QuartzJobInfo(JobDetail jobDetail, CronTrigger cronTrigger) {
			super();
			this.jobDetail = jobDetail;
			this.cronTrigger = cronTrigger;
		}
		public JobDetail getJobDetail() {
			return jobDetail;
		}
		public CronTrigger getCronTrigger() {
			return cronTrigger;
		}
	}
}

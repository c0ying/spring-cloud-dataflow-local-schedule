package com.jingxin.cloud.dataflow.support.quartz.configuration;

import java.text.ParseException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.jingxin.cloud.dataflow.support.quartz.common.util.SpringUtil;
import com.jingxin.cloud.dataflow.support.scheduler.QuartzExecutionJob;

@Configuration
public class QuartzConfiguration {

	private static final Logger LOG = LoggerFactory.getLogger(QuartzConfiguration.class);
	
	@Bean(destroyMethod = "destroy")
	@Lazy(false)
	public SchedulerFactoryBean quartzScheduler(DataSource dataSource, 
					ThreadPoolTaskExecutor taskExecutor, PlatformTransactionManager transactionManager) 
																throws SchedulerException, ParseException{
		SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
		factoryBean.setSchedulerName("data-flow-quartz");
		factoryBean.setDataSource(dataSource);
		factoryBean.setTaskExecutor(taskExecutor);
		factoryBean.setAutoStartup(true);
		factoryBean.setStartupDelay(60);
		factoryBean.setWaitForJobsToCompleteOnShutdown(true);
		factoryBean.setTransactionManager(transactionManager);
		factoryBean.setQuartzProperties(quartzProperties());
		return factoryBean;
	}
	
	public Properties quartzProperties() {
		Properties properties = new Properties();
		properties.put("org.quartz.scheduler.instanceName", "system-quartz");
		properties.put("org.quartz.scheduler.instanceId", "AUTO");
		properties.put("org.quartz.jobStore.misfireThreshold", "60000");
		properties.put("org.quartz.jobStore.useProperties", "true");
		properties.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		properties.put("org.quartz.jobStore.isClustered", "true");
		properties.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
		return properties;
	}
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor(){
		ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
		threadPool.setThreadNamePrefix("my-ThreadPool-");
		threadPool.setCorePoolSize(20);
		threadPool.setMaxPoolSize(40);
		threadPool.setKeepAliveSeconds(300);
		threadPool.setQueueCapacity(5);
		return threadPool;
	}
	
	@Bean
	public ExecutorServiceAdapter executorServiceAdapter(){
		return new ExecutorServiceAdapter(taskExecutor());
	}
	
	@Bean
	public SpringUtil springUtil() {
		return new SpringUtil();
	}
	
	@Bean
	public QuartzExecutionJob quartzExecutionJob() {
		return new QuartzExecutionJob();
	}
	
}

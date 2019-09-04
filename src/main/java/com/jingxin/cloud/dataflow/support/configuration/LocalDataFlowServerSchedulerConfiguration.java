package com.jingxin.cloud.dataflow.support.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.scheduler.spi.core.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jingxin.cloud.dataflow.support.quartz.configuration.QuartzConfiguration;
import com.jingxin.cloud.dataflow.support.scheduler.LocalScheduler;
import com.jingxin.cloud.dataflow.support.scheduler.repository.ScheduleInfoRepository;

@Configuration
@Import(QuartzConfiguration.class)
public class LocalDataFlowServerSchedulerConfiguration {

	@Value("${spring.cloud.dataflow.server.uri:}")
	private String dataflowServerUri;
	
	@Bean
	public Scheduler localScheduler() {
		return new LocalScheduler();
	}
	
	@Bean
	public ScheduleInfoRepository scheduleInfoRepository() {
		return new ScheduleInfoRepository();
	}
}

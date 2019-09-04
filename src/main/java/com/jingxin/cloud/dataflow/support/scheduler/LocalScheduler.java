package com.jingxin.cloud.dataflow.support.scheduler;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.scheduler.spi.core.ScheduleInfo;
import org.springframework.cloud.scheduler.spi.core.ScheduleRequest;
import org.springframework.cloud.scheduler.spi.core.Scheduler;
import org.springframework.cloud.scheduler.spi.core.SchedulerPropertyKeys;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.jingxin.cloud.dataflow.support.scheduler.repository.ScheduleInfoRepository;
import com.jingxin.cloud.dataflow.support.scheduler.utils.DeploymentRequestUtil;
import com.jingxin.cloud.dataflow.support.scheduler.utils.QuartzJobGenerateUtil;
import com.jingxin.cloud.dataflow.support.scheduler.utils.QuartzJobGenerateUtil.QuartzJobInfo;

public class LocalScheduler implements Scheduler{

	private String target = "quartzExecutionJob";
	private String method = "execute";
	
	@Override
	public void schedule(ScheduleRequest scheduleRequest) {
		String scheduleName = scheduleRequest.getScheduleName();
		String workJson = DeploymentRequestUtil.toJson(scheduleRequest);
		String cron = scheduleRequest.getSchedulerProperties().get(SchedulerPropertyKeys.CRON_EXPRESSION);
		if (StringUtils.isBlank(cron)) {
			throw new IllegalArgumentException("cron can not be null");
		}
		Map<String, Object> params = new HashMap<>();
		params.put("properties", workJson);
		try {
			QuartzJobInfo quartzJobInfo = 
					QuartzJobGenerateUtil.quartzCornJobFactoryBean(scheduleName, target, method, cron, params);
			schedulerFactoryBean.getObject().scheduleJob(quartzJobInfo.getJobDetail(), quartzJobInfo.getCronTrigger());
			scheduleInfoRepository.save(scheduleName, scheduleRequest.getDefinition().getName(), cron, workJson);
		} catch (ParseException | SchedulerException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public void unschedule(String scheduleName) {
		try {
			schedulerFactoryBean.getObject().deleteJob(new JobKey(scheduleName));
			scheduleInfoRepository.delete(scheduleName);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ScheduleInfo> list(String taskDefinitionName) {
		return scheduleInfoRepository.findOne(taskDefinitionName);
	}

	@Override
	public List<ScheduleInfo> list() {
		return scheduleInfoRepository.findList();
	}

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private ScheduleInfoRepository scheduleInfoRepository;
}

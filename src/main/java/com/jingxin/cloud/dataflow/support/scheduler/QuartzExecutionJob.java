package com.jingxin.cloud.dataflow.support.scheduler;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.dataflow.server.service.TaskService;
import org.springframework.stereotype.Component;

import com.jingxin.cloud.dataflow.support.scheduler.utils.DeploymentRequestUtil.DeploymentRequestJsonBean;
import com.jingxin.cloud.dataflow.support.scheduler.utils.JsonUtil;

@Component
public class QuartzExecutionJob {
	
	public void execute(JobExecutionContext jobExecutionContext) {
		String deploymentJson = (String) jobExecutionContext.getMergedJobDataMap().get("properties");
		DeploymentRequestJsonBean jsonBean = JsonUtil.parseJson(deploymentJson, DeploymentRequestJsonBean.class);
		String taskName = jsonBean.getDefinition().getName();
		taskService.executeTask(taskName, jsonBean.getDeploymentProperties(), jsonBean.getCommandlineArguments());
	}
	
	@Autowired
	private TaskService taskService;
}

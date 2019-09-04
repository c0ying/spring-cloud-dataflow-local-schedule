package com.jingxin.cloud.dataflow.support.quartz.common;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.jingxin.cloud.dataflow.support.quartz.common.util.SpringUtil;

public class ClusterQuartzSpringJobBean extends QuartzJobBean {

	private final static Logger logger = LoggerFactory.getLogger(ClusterQuartzSpringJobBean.class);
	
    private String targetObject;
    private String targetMethod;
    
  
    @Override  
    protected void executeInternal(JobExecutionContext context)  
            throws JobExecutionException {  
        try {  
            logger.debug("execute [" + targetObject + "] at once>>>>>>");
            Object otargetObject = SpringUtil.getBean(targetObject);
            
            if(otargetObject != null){
            	ArgumentConvertingMethodInvoker methodInvoker = new ArgumentConvertingMethodInvoker();
            	methodInvoker.setTargetObject(otargetObject);
            	methodInvoker.setTargetMethod(targetMethod);
            	methodInvoker.setArguments(context);
            	methodInvoker.prepare();
            	methodInvoker.invoke();
            }else{
            	logger.error("{}  can not be found in Spring Context", targetObject);
            	throw new JobExecutionException(targetObject+" can not be found in Spring Context");
            }
        } catch (Exception e) {  
            throw new JobExecutionException(e);  
        }  
    }  
  
    public void setTargetObject(String targetObject) {  
        this.targetObject = targetObject;  
    }  
  
    public void setTargetMethod(String targetMethod) {  
        this.targetMethod = targetMethod;  
    }
    
    
    @PersistJobDataAfterExecution
	@DisallowConcurrentExecution
    public static class ClusterStatefulQuartzSpringJobBean extends ClusterQuartzSpringJobBean{
    	
    }
	
}
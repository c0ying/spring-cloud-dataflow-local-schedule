package com.jingxin.cloud.dataflow.support.scheduler.utils;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.deployer.spi.core.AppDefinition;
import org.springframework.cloud.deployer.spi.core.AppDeploymentRequest;

public class DeploymentRequestUtil {

	public static String toJson(AppDeploymentRequest request) {
		return JsonUtil.toJson(
			new DeploymentRequestJsonBean(
					request.getDefinition(), "", 
					request.getDeploymentProperties(), request.getCommandlineArguments()));
	}
	
	public static class DeploymentRequestJsonBean{
		
		private AppJsonDefinition definition;

		private String resource;

		private Map<String, String> deploymentProperties;

		private List<String> commandlineArguments;
		
		public DeploymentRequestJsonBean() {
		}
		
		public DeploymentRequestJsonBean(AppDefinition definition, String resource,
				Map<String, String> deploymentProperties, List<String> commandlineArguments) {
			super();
			this.definition = new AppJsonDefinition(definition);
			this.resource = resource;
			this.deploymentProperties = deploymentProperties;
			this.commandlineArguments = commandlineArguments;
		}
		
		public AppJsonDefinition getDefinition() {
			return definition;
		}

		public String getResource() {
			return resource;
		}

		public Map<String, String> getDeploymentProperties() {
			return deploymentProperties;
		}

		public List<String> getCommandlineArguments() {
			return commandlineArguments;
		}

		public void setDefinition(AppJsonDefinition definition) {
			this.definition = definition;
		}

		public void setResource(String resource) {
			this.resource = resource;
		}

		public void setDeploymentProperties(Map<String, String> deploymentProperties) {
			this.deploymentProperties = deploymentProperties;
		}

		public void setCommandlineArguments(List<String> commandlineArguments) {
			this.commandlineArguments = commandlineArguments;
		}
		
		public static class AppJsonDefinition{
			/**
			 * Name of the app.
			 */
			private String name;

			/**
			 * Properties for this app.
			 */
			private Map<String, String> properties;

			public AppJsonDefinition() {
			}
			
			public AppJsonDefinition(AppDefinition appDefinition){
				this.name = appDefinition.getName();
				this.properties = appDefinition.getProperties();
			}
			
			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public Map<String, String> getProperties() {
				return properties;
			}

			public void setProperties(Map<String, String> properties) {
				this.properties = properties;
			}
		}
	}
}

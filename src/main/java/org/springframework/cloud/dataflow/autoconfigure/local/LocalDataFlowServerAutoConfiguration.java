/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.dataflow.autoconfigure.local;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.cloud.dataflow.server.config.DataFlowControllerAutoConfiguration;
import org.springframework.cloud.deployer.resource.docker.DockerResourceLoader;
import org.springframework.cloud.deployer.resource.maven.MavenProperties;
import org.springframework.cloud.deployer.resource.maven.MavenResourceLoader;
import org.springframework.cloud.deployer.resource.support.DelegatingResourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import com.jingxin.cloud.dataflow.support.configuration.LocalDataFlowServerSchedulerConfiguration;

/**
 * Auto-configuration for local dataflow server.
 *
 * @author Janne Valkealahti
 */
@Configuration
@Import(LocalDataFlowServerSchedulerConfiguration.class)
@AutoConfigureBefore(DataFlowControllerAutoConfiguration.class)
public class LocalDataFlowServerAutoConfiguration {

	@Bean
	public DelegatingResourceLoader delegatingResourceLoader(MavenProperties mavenProperties) {
		DockerResourceLoader dockerLoader = new DockerResourceLoader();
		MavenResourceLoader mavenResourceLoader = new MavenResourceLoader(mavenProperties);
		Map<String, ResourceLoader> loaders = new HashMap<>();
		loaders.put("docker", dockerLoader);
		loaders.put("maven", mavenResourceLoader);
		return new DelegatingResourceLoader(loaders);
	}

//	@Bean
//	public Scheduler localScheduler() {
//		return new Scheduler() {
//			@Override
//			public void schedule(ScheduleRequest scheduleRequest) {
//				throw new UnsupportedOperationException("Interface is not implemented for schedule method.");
//			}
//
//			@Override
//			public void unschedule(String scheduleName) {
//				throw new UnsupportedOperationException("Interface is not implemented for unschedule method.");
//			}
//
//			@Override
//			public  List<ScheduleInfo> list(String taskDefinitionName) {
//				throw new UnsupportedOperationException("Interface is not implemented for list method.");
//			}
//
//			@Override
//			public  List<ScheduleInfo> list() {
//				throw new UnsupportedOperationException("Interface is not implemented for list method.");
//			}
//		};
//	}
}

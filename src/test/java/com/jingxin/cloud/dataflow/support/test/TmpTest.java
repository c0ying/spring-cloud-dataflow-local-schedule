package com.jingxin.cloud.dataflow.support.test;

import com.jingxin.cloud.dataflow.support.scheduler.utils.DeploymentRequestUtil.DeploymentRequestJsonBean;
import com.jingxin.cloud.dataflow.support.scheduler.utils.JsonUtil;

public class TmpTest {

	public static void main(String[] args) {
		String json = "{\"definition\":{\"name\":\"demoTask\",\"properties\":{\"spring.datasource.username\":\"root\",\"spring.cloud.task.name\":\"demoTask\",\"spring.datasource.url\":\"jdbc:mysql://localhost:3306/dataflow_test\",\"spring.datasource.driverClassName\":\"org.mariadb.jdbc.Driver\",\"spring.datasource.password\":\"mysql123\"}},\"resource\":\"\",\"deploymentProperties\":{},\"commandlineArguments\":[]}";
		DeploymentRequestJsonBean bean = JsonUtil.parseJson(json, DeploymentRequestJsonBean.class);
		System.out.println(bean.getDefinition().getName());
	}
}

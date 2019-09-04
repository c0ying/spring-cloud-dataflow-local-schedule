package com.jingxin.cloud.dataflow.support.scheduler.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.scheduler.spi.core.ScheduleInfo;
import org.springframework.cloud.scheduler.spi.core.SchedulerPropertyKeys;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleInfoRepository {
	
	private String find_list_sql = "SELECT * FROM T_SCHEDULE_INFO";
	private String find_one_sql = "SELECT * FROM T_SCHEDULE_INFO WHERE F_TASK_NAME = ?";
	private String save_sql = "INSERT INTO T_SCHEDULE_INFO (F_SCHEDULE_NAME, F_TASK_NAME, F_TASK_CRON, F_SCHEDULE_PROPERTIES) VALUES (?,?,?,?)";
	private String delete_sql = "DELETE FROM T_SCHEDULE_INFO WHERE F_SCHEDULE_NAME = ?";

	public List<ScheduleInfo> findList() {
		return jdbcTemplate.query(find_list_sql, new RowMapper<ScheduleInfo>() {

			@Override
			public ScheduleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				String scheduleName = rs.getString("f_schedule_name");
				String taskName = rs.getString("f_task_name");
				String cron = rs.getString("f_task_cron");
				ScheduleInfo scheduleInfo = new ScheduleInfo();
				scheduleInfo.setScheduleName(scheduleName);
				scheduleInfo.setTaskDefinitionName(taskName);
				Map<String, String> properties = new HashMap<>();
				properties.put(SchedulerPropertyKeys.CRON_EXPRESSION, cron);
				scheduleInfo.setScheduleProperties(properties);
				return scheduleInfo;
			}
			
		});
	}
	
	public List<ScheduleInfo> findOne(String taskDefinitionName) {
		return jdbcTemplate.query(find_one_sql, new RowMapper<ScheduleInfo>() {
			@Override
			public ScheduleInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
				String scheduleName = rs.getString("f_schedule_name");
				String taskName = rs.getString("f_task_name");
				String cron = rs.getString("f_task_cron");
				ScheduleInfo scheduleInfo = new ScheduleInfo();
				scheduleInfo.setScheduleName(scheduleName);
				scheduleInfo.setTaskDefinitionName(taskName);
				Map<String, String> properties = new HashMap<>();
				properties.put(SchedulerPropertyKeys.CRON_EXPRESSION, cron);
				scheduleInfo.setScheduleProperties(properties);
				return scheduleInfo;
			}
		}, taskDefinitionName);
	}
	
	public void save(String scheduleName, String taskName, String cron, String properties) {
		jdbcTemplate.update(save_sql, scheduleName, taskName, cron, properties);
	}
	
	public void delete(String scheduleName) {
		jdbcTemplate.update(delete_sql, scheduleName);
	}
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
}

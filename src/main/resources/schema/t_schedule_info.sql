/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : dataflow_test

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2019-03-11 16:14:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_schedule_info
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_info`;
CREATE TABLE `t_schedule_info` (
  `f_schedule_name` varchar(255) NOT NULL,
  `f_task_name` varchar(255) NOT NULL,
  `f_task_cron` varchar(255) NOT NULL,
  `f_schedule_properties` text NOT NULL,
  `f_create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `f_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`f_schedule_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
